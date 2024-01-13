/*
 * Copyright (c) 2023, Project-K
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package jp.mydns.projectk.formula.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toCollection;
import jp.mydns.projectk.formula.Element;
import jp.mydns.projectk.formula.Formula;
import jp.mydns.projectk.formula.FormulaParseException;
import jp.mydns.projectk.formula.Function;
import jp.mydns.projectk.formula.Token;
import jp.mydns.projectk.formula.impl.FixedValueElement;
import jp.mydns.projectk.formula.impl.FormulaImpl;
import jp.mydns.projectk.formula.impl.FunctionElement;
import jp.mydns.projectk.formula.impl.InputElement;
import jp.mydns.projectk.formula.impl.function.*;

/**
 * Formula parser. Parser construct a {@link Formula} instance by parsing text as a formula.
 * <table border="1"><caption>Formula syntax</caption>
 * <thead><tr><th>Element</th><th>Syntax</th><th>Description</th></tr></thead>
 * <tbody>
 * <tr><td>Literal value element</td><td>`literal-value`</td><td>The literal value is enclosed by "{@code `}".<br>If you
 * want to escape it, prefix it with "{@code \}".</td></tr>
 * <tr><td>Input value element</td><td>[input-value-name]</td><td>The input value encloses its name in "{@code [}" and
 * "{@code ]}".</td></tr>
 * <tr><td>Elements joiner</td><td>&amp;</td><td>Joiner concatenates the values before and after the element.</td></tr>
 * <tr><td>Function element</td><td>function-name(argument, argument)</td><td>Function consists of a function name
 * followed by arguments enclosed in "{@code (}" and "{@code )}".<br>The arguments are separated by "{@code ,}" and the
 * number varies depending on the function.
 * <p>
 * The argument is any kind of the element that "Input" or "Literal" or "Function".</td></tr>
 * </tbody></table>
 * <p>
 * <b>Example</b>
 * <pre>
 * -- premise -----------------------------------------------
 * <i>Formula</i>      ... `Hello` &amp; `!!` &amp; [name] &amp; `:` &amp; LPAD( TRIM( [id] ), `4`, `P` )
 * <i>Input value</i>  ... {"id": "   01", "name": "taro"}
 * <i>Function #1</i>  ... TRIM( arg-value )
 * <i>Function #2</i>  ... LPAD( arg-value, arg-length, arg-padding-char )
 * ----------------------------------------------------------
 *
 * -- Process of calculating --------------------------------
 * `Hello` &amp; `!!` &amp; [name] &amp; `:` &amp; LPAD( TRIM( [id] ), `4`, `P` )
 * ↓
 * `Hello` &amp; `!!` &amp; [name] &amp; `:` &amp; LPAD( `01`, `4`, `P` )
 * ↓
 * `Hello` &amp; `!!` &amp; `taro` &amp; `:` &amp; `PP01`
 * ↓
 * `Hello!!taro:PP01`
 * ----------------------------------------------------------
 * </pre>
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class Parser {

    private final Lexer lexer = new Lexer();
    private final Verifier verifier = new Verifier();
    private final Map<String, Supplier<? extends Function>> functions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private final Set<Supplier<Function>> internals = Set.of(Not::new, And::new, Case::new, Cmp::new, Eq::new, If::new,
            IfNull::new, IsNull::new, NoNull::new, Not::new, Or::new);

    /**
     * Constructor.
     *
     * @param externals external function suppliers
     * @throws NullPointerException if any argument is {@code null}
     * @since 1.0.0
     */
    public Parser(Map<String, Supplier<? extends Function>> externals) {
        Objects.requireNonNull(externals);
        internals.stream().forEach(s -> functions.put(s.get().getClass().getSimpleName(), s));
        functions.putAll(externals);
    }

    /**
     * Parse the formula text.
     *
     * @param formula formula text
     * @return the {@code Formula}
     * @throws NullPointerException if {@code formula} is {@code null}
     * @throws FormulaParseException if detected syntax error within {@code formula}
     * @since 1.0.0
     */
    public Formula parse(String formula) {
        List<Token> tokens = verifier.requireValid(lexer.toTokens(formula));
        return buildFormula(cleanse(tokens));
    }

    /**
     * Remove unnecessary token for build a formula.
     *
     * @param tokens original tokens
     * @return cleansed tokens
     */
    private Queue<Token> cleanse(List<Token> tokens) {

        Set<Token.Kind> unnecessaries = Set.of(
                Token.Kind.INPUT_ENCLOSURE_L, Token.Kind.INPUT_ENCLOSURE_R, Token.Kind.JOINER,
                Token.Kind.LITERAL_ENCLOSURE_L, Token.Kind.LITERAL_ENCLOSURE_R, Token.Kind.ARGS_ENCLOSURE_L);

        Predicate<Token> isNecessary = t -> !unnecessaries.contains(t.getKind());

        return tokens.stream().sequential().filter(isNecessary).collect(toCollection(LinkedList::new));

    }

    private Formula buildFormula(Queue<Token> q) {

        List<Element> elements = new ArrayList<>();

        while (!q.isEmpty()) {

            Element e = switch (q.element().getKind()) {

                case INPUT_NAME ->
                    new InputElement(q.remove().getValue());

                case LITERAL_VALUE ->
                    new FixedValueElement(q.remove().getValue());

                case FUNCTION_NAME ->
                    buildFunction(q);

                default ->
                    null;

            };

            if (e == null) {
                break;
            }

            elements.add(e);

        }

        return new FormulaImpl(elements);

    }

    private Element buildFunction(Queue<Token> q) {

        String name = q.remove().getValue();

        if (!functions.containsKey(name)) {
            throw new FormulaParseException("Missing function. [%s]".formatted(name));
        }

        List<Formula> args = new ArrayList<>();

        while (q.element().getKind() != Token.Kind.ARGS_ENCLOSURE_R) {

            if (q.element().getKind() == Token.Kind.ARGS_SEPARATOR) {
                q.remove();
            } else {
                args.add(buildFormula(q));
            }

        }

        q.remove();

        return new FunctionElement(functions.get(name).get(), args);

    }
}

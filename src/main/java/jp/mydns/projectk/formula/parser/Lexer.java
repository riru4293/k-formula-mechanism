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
import static java.util.Collections.unmodifiableList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toCollection;
import jp.mydns.projectk.formula.Token;
import jp.mydns.projectk.formula.impl.FixedToken;
import jp.mydns.projectk.formula.impl.VariableToken;

/**
 * Lexical analyzer for formula text.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class Lexer {

    private static final char LITERAL_ENCLOSURE_CHAR = '`';
    private static final char INPUT_ENCLOSURE_L_CHAR = '[';
    private static final char INPUT_ENCLOSURE_R_CHAR = ']';
    private static final char ARGS_ENCLOSURE_L_CHAR = '(';
    private static final char ARGS_ENCLOSURE_R_CHAR = ')';
    private static final char ARGS_SEPARATOR_CHAR = ',';
    private static final char JOINER_CHAR = '&';
    private static final char ESCAPE_CHAR = '\\';

    private static final EnclosedTokensConstruct LITERAL_TOKENS_CONSTRUCT = new EnclosedTokensConstruct(
            Token.Kind.LITERAL_ENCLOSURE_L, Token.Kind.LITERAL_VALUE, Token.Kind.LITERAL_ENCLOSURE_R);

    private static final EnclosedTokensConstruct INPUT_TOKENS_CONSTRUCT = new EnclosedTokensConstruct(
            Token.Kind.INPUT_ENCLOSURE_L, Token.Kind.INPUT_NAME, Token.Kind.INPUT_ENCLOSURE_R);

    /**
     * Lexical analyze the formula text and divide into tokens.
     *
     * @param formula formula text
     * @return tokens
     * @throws NullPointerException if {@code formula} is {@code null}
     * @since 1.0.0
     */
    public List<Token> toTokens(String formula) {

        Objects.requireNonNull(formula);

        List<Token> tokens = new ArrayList<>();

        Queue<Character> q = formula.chars().mapToObj(i -> (char) i).collect(toCollection(LinkedList::new));

        while (!q.isEmpty()) {

            char c = q.element();

            // Ignore blank char.
            if (isIgnoreChar(c)) {

                q.remove();
                continue;
            }

            switch (c) {

                case LITERAL_ENCLOSURE_CHAR ->

                    tokens.addAll(extractEnclosedTokens(q, LITERAL_ENCLOSURE_CHAR, LITERAL_TOKENS_CONSTRUCT));

                case INPUT_ENCLOSURE_L_CHAR ->

                    tokens.addAll(extractEnclosedTokens(q, INPUT_ENCLOSURE_R_CHAR, INPUT_TOKENS_CONSTRUCT));

                case JOINER_CHAR -> {

                    q.remove();
                    tokens.add(new FixedToken(Token.Kind.JOINER));
                }

                case ARGS_ENCLOSURE_L_CHAR -> {

                    q.remove();
                    tokens.add(new FixedToken(Token.Kind.ARGS_ENCLOSURE_L));
                }

                case ARGS_ENCLOSURE_R_CHAR -> {

                    q.remove();
                    tokens.add(new FixedToken(Token.Kind.ARGS_ENCLOSURE_R));
                }

                case ARGS_SEPARATOR_CHAR -> {

                    q.remove();
                    tokens.add(new FixedToken(Token.Kind.ARGS_SEPARATOR));
                }

                default -> // Begin parse the function

                    tokens.add(extractFunctionName(q));
            }
        }

        return unmodifiableList(tokens);
    }

    private boolean isIgnoreChar(char c) {

        return String.valueOf(c).isBlank();
    }

    private List<Token> extractEnclosedTokens(Queue<Character> q, char terminal, EnclosedTokensConstruct c) {

        List<Token> tokens = new ArrayList<>();

        q.remove();
        tokens.add(new FixedToken(c.leftKind));

        tokens.add(new VariableToken(c.valueKind, extractUpToTerminal(q, terminal)));

        if (!q.isEmpty()) {

            q.remove();
            tokens.add(new FixedToken(c.rightKind));

        }

        return tokens;
    }

    private String extractUpToTerminal(Queue<Character> q, char terminal) {

        final StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {

            if (terminal == q.element()) {

                return sb.toString();
            }

            if (ESCAPE_CHAR != q.element()) {

                sb.append(q.remove());

            } else {

                q.remove(); // Ignore escape char
                Optional.of(q).filter(not(Queue::isEmpty)).map(Queue::remove).ifPresent(sb::append);
            }
        }

        return sb.toString();
    }

    private Token extractFunctionName(Queue<Character> q) {

        String name = extractUpToTerminal(q, ARGS_ENCLOSURE_L_CHAR);

        return new VariableToken(Token.Kind.FUNCTION_NAME, name);
    }

    private record EnclosedTokensConstruct(Token.Kind leftKind, Token.Kind valueKind, Token.Kind rightKind) {

    }
}

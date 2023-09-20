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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import jp.mydns.projectk.formula.FormulaParseException;
import jp.mydns.projectk.formula.Token;

/**
 * Formula syntax verifier. Use when after lexical analysis and before parse.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class Verifier {

    /**
     * Verify the syntax as formula.
     *
     * @param tokens tokens that represents a formula
     * @return valid tokens. It is argument value itself.
     * @throws NullPointerException if {@code tokens} is {@code null} or if {@code tokens} contains {@code null}
     * @throws FormulaParseException if detected syntax error within formula
     * @since 1.0.0
     */
    public List<Token> requireValid(List<Token> tokens) {

        confirmFormulaSyntax(new LinkedList<>(List.copyOf(tokens)), ConfirmMode.STANDARD);

        return tokens;
    }

    private void confirmFormulaSyntax(Queue<Token> q, ConfirmMode mode) {

        boolean requireSeparatorNext = false;
        boolean requireValueNext = false;

        while (!q.isEmpty()) {

            Token t = q.element();

            if (mode == ConfirmMode.FUNCTION_ARGUMENTS && !requireValueNext
                    && typeIs(Token.Kind.ARGS_ENCLOSURE_R).test(t)) {

                // Finish confirming the FUNCTION_ARGUMENTS mode.
                return;
            }

            if (requireSeparatorNext) {

                if (!mode.isValidSeparator(q.remove())) {

                    throw new FormulaParseException("Delimiter required but not found.");
                }

            } else {

                switch (t.getKind()) {

                    case LITERAL_ENCLOSURE_L ->
                        confirmEnclosedSyntax(q, EnclosedStructure.LITERAL);

                    case INPUT_ENCLOSURE_L ->
                        confirmEnclosedSyntax(q, EnclosedStructure.INPUT);

                    case FUNCTION_NAME ->
                        confirmFunctionSyntax(q);

                    default ->
                        throw new FormulaParseException("Expected the start token of the element but not found.");
                }
            }

            requireSeparatorNext = !requireSeparatorNext;
            requireValueNext = !requireSeparatorNext;
        }

        if (requireValueNext) {

            throw new FormulaParseException("Requires an element following delimiter but not found.");
        }
    }

    private void confirmFunctionSyntax(Queue<Token> tokens) {

        tokens.remove();

        if (typeIs(Token.Kind.ARGS_ENCLOSURE_L).test(tokens.poll())) {

            // Begin confirming the FUNCTION_ARGUMENTS mode.
            confirmFormulaSyntax(tokens, ConfirmMode.FUNCTION_ARGUMENTS);

        } else {

            throw new FormulaParseException("Function-arguments enclosure not started.");
        }

        if (!typeIs(Token.Kind.ARGS_ENCLOSURE_R).test(tokens.poll())) {

            throw new FormulaParseException("Function-arguments enclosure not closed.");
        }
    }

    private void confirmEnclosedSyntax(Queue<Token> tokens, EnclosedStructure structure) {

        tokens.remove();

        if (!typeIs(structure.valueType).test(tokens.poll()) || !typeIs(structure.endType).test(tokens.poll())) {

            throw new FormulaParseException("Enclosure not closed.");
        }
    }

    private Predicate<Token> typeIs(Token.Kind type) {

        return t -> Optional.ofNullable(t).map(Token::getKind).map(type::equals).orElse(false);
    }

    private enum ConfirmMode {

        STANDARD(Token.Kind.JOINER),
        FUNCTION_ARGUMENTS(Token.Kind.JOINER, Token.Kind.ARGS_SEPARATOR);

        private final Set<Token.Kind> validSeparators;

        private ConfirmMode(Token.Kind... separators) {

            this.validSeparators = Set.of(separators);
        }

        public boolean isValidSeparator(Token token) {

            return validSeparators.contains(token.getKind());
        }
    }

    private enum EnclosedStructure {

        LITERAL(Token.Kind.LITERAL_VALUE, Token.Kind.LITERAL_ENCLOSURE_R),
        INPUT(Token.Kind.INPUT_NAME, Token.Kind.INPUT_ENCLOSURE_R);

        private final Token.Kind valueType;
        private final Token.Kind endType;

        private EnclosedStructure(Token.Kind valueType, Token.Kind endType) {
            this.valueType = valueType;
            this.endType = endType;
        }
    }
}

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
package jp.mydns.projectk.formula;

/**
 * Minimum string with meaning in mathematical expression.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Token {

    /**
     * Get this token kind.
     *
     * @return token type
     * @since 1.0.0
     */
    Kind getKind();

    /**
     * Get the value of this token.
     *
     * @return token value
     * @throws UnsupportedOperationException for token kind that have no value
     * @since 1.0.0-M6
     */
    String getValue();

    /**
     * Kinds of token.
     *
     * @author riru
     * @version 1.0.0
     * @since 1.0.0
     */
    public enum Kind {

        /**
         * Left of enclosure at function arguments.
         *
         * @since 1.0.0
         */
        ARGS_ENCLOSURE_L,
        /**
         * Right of enclosure at function arguments.
         *
         * @since 1.0.0
         */
        ARGS_ENCLOSURE_R,
        /**
         * Separator at function arguments.
         *
         * @since 1.0.0
         */
        ARGS_SEPARATOR,
        /**
         * Left of enclosure at input value name.
         *
         * @since 1.0.0
         */
        INPUT_ENCLOSURE_L,
        /**
         * Right of enclosure at input value name.
         *
         * @since 1.0.0
         */
        INPUT_ENCLOSURE_R,
        /**
         * Input value name.
         *
         * @since 1.0.0
         */
        INPUT_NAME,
        /**
         * The join operator at formula elements.
         *
         * @since 1.0.0
         */
        JOINER,
        /**
         * Left of enclosure at literal value.
         *
         * @since 1.0.0
         */
        LITERAL_ENCLOSURE_L,
        /**
         * Right of enclosure at literal value.
         *
         * @since 1.0.0
         */
        LITERAL_ENCLOSURE_R,
        /**
         * Literal value.
         *
         * @since 1.0.0
         */
        LITERAL_VALUE,
        /**
         * Function name.
         *
         * @since 1.0.0
         */
        FUNCTION_NAME
    }
}

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

import java.util.Map;

/**
 * Represents a parsed formula. Can get the result of formula calculation using input.
 * <p>
 * <b>About Java null values.</b>
 * <br>
 * The existence of null values ​​is allowed with input value and output value. If used in the argument of a formula
 * function, the result depends on the specification of the formula function. If joining a null value with any value
 * using the joiner(&amp;) results in null.
 * <p>
 * <b>About Exception</b><br>
 * All of the {@code Exception} other than the {@link FormulaExecutionException} must not be thrown to outside the
 * formula.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Formula {

    /**
     * Calculate this and get result.
     *
     * @param inputs input values
     * @return result of calculate. It may be {@code null}.
     * @throws NullPointerException if {@code inputs} is {@code null}
     * @throws FormulaExecutionException if an error occurred while calculating formula
     * @since 1.0.0
     */
    String calculate(Map<String, String> inputs);
}

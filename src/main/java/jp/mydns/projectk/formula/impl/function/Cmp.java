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
package jp.mydns.projectk.formula.impl.function;

import jp.mydns.projectk.formula.FormulaRuntimeException;
import jp.mydns.projectk.formula.Function;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;

/**
 * A formula function that compares two texts in a specified way.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class Cmp extends AbstractFunction {

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>Value</td><td>Text</td><td>The value being compared.</td></tr>
     * <tr><td>2</td><td>Another</td><td>Text</td><td>The value to compare with {@code "Value"}</td></tr>
     * <tr><td>3</td><td>ComparisonWay</td><td>Enum</td>
     * <td><ul>
     * <li><b>Enum</b>&#009;<b>Description</b></li>
     * <li>&lt;&#009;"Value" is less than "Another"</li>
     * <li>&gt;&#009;"Value" is greater than "Another"</li>
     * <li>=&#009;"Value" equal to "Another"</li>
     * <li>&lt;=&#009;"Value" is less than "Another" or "Value" equal to "Another"</li>
     * <li>&gt;=&#009;"Value" is greater than "Another" or "Value" equal to "Another"</li>
     * <li>&lt;&gt;&#009;"Value" is not equal to "Another"</li>
     * </ul>
     * </td></tr>
     * </tbody></table>
     * @return {@code true} if the comparison result is true, otherwise {@code false}. Either is {@code null}, then
     * {@code "false"}.
     * @since 1.0.0
     */
    @Override
    public String calculate(Function.Argument... args) {

        String value = args[0].resolve();
        String another = args[1].resolve();
        String compWay = args[2].resolve();

        if (value == null || another == null) {

            return "false";
        }

        if (compWay == null) {

            throw new FormulaRuntimeException("[Compare] Null comparison way.");
        }

        int result = value.compareTo(another);

        return Boolean.toString(switch (compWay) {

            case "<" ->
                result < 0;

            case ">" ->
                result > 0;

            case "=" ->
                result == 0;

            case "<=" ->
                result <= 0;

            case ">=" ->
                result >= 0;

            case "<>" ->
                result != 0;

            default ->
                throw new FormulaRuntimeException("[Compare] Unexpected comparison way. [%s]".formatted(compWay));
        });
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {

        return new ArgumentSchemeImpl(
                new ArgdefImpl("Value", "The value being compared."),
                new ArgdefImpl("Another", "The value to compare with \"Value\""),
                new ArgdefImpl("ComparisonWay",
                        """
                        "<" or ">" or "=" or "<=" or ">=" or "<>".""")
        );
    }
}

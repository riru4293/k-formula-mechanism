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

import java.util.Objects;
import jp.mydns.projectk.formula.Function;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;
import jp.mydns.projectk.formula.impl.NestRepeatArgdefImpl;

/**
 * A formula function that returns the value corresponding to matched cases. If multiple cases match, the first case is
 * used; if none match, the default value is returned.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class Case extends AbstractFunction {

    private static final int REPEAT_MIN = 0;
    private static final int REPEAT_MAX = 20;

    /**
     * Run this function. You can use {@code null} for all arguments, but {@code null} will not match anything.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>repeat</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>-</td><td>ConfirmationValue</td><td>Text</td><td>Value to confirm match with cases.</td></tr>
     * <tr><td rowspan="2">2</td><td rowspan="2">{@value #REPEAT_MIN} ..
     * {@value #REPEAT_MAX}</td><td>CompareValue</td><td>Text</td><td>Value to compare.</td></tr>
     * <tr><td>ReturnValue</td><td>Text</td><td>Value to returned if {@code CompareValue} is match to
     * {@code ConfirmationValue}.</td></tr>
     * <tr><td>3</td><td>-</td><td>DefaultValue</td><td>Text</td><td>Value to return if none match.</td></tr>
     * </tbody></table>
     * @return The {@code ReturnValue} corresponding to {@code CompareValue} if {@code CompareValue} matches
     * {@code confirmValue}. {@code DefaultValue} if not all match.
     * @since 1.0.0
     */
    @Override
    public String calculate(Function.Argument... args) {

        String confirmation = args[0].resolve();

        for (int i = 1; i < args.length - 1; i = i + 2) {
            if (Objects.equals(confirmation, args[i].resolve())) {
                return args[i + 1].resolve();
            }
        }

        return args[args.length - 1].resolve();

    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {
        return new ArgumentSchemeImpl(new ArgdefImpl("ConfirmationValue", "Value to confirm match with cases."),
                new NestRepeatArgdefImpl("Case", REPEAT_MIN, REPEAT_MAX, "Combination of value to compare and value to return if it match.",
                        new ArgdefImpl("CompareValue", "Value to compare."),
                        new ArgdefImpl("ReturnValue", "Value to returned if \"CompareValue\" is match to \"ConfirmationValue\".")),
                new ArgdefImpl("DefaultValue", "Value to return if none match."));
    }
}

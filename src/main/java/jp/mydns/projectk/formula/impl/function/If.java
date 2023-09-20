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

import jp.mydns.projectk.formula.Function;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;

/**
 * A formula function that returns {@code "true"} if the boolean value is {@code true} , otherwise {@code "false"} .
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class If extends AbstractFunction {

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>BoolValue</td><td>Boolean</td><td>The string "true" is interpreted as {@code true}<br>, and the
     * others are interpreted as false.<br>Case is insensitive.</td></tr>
     * <tr><td>2</td><td>TrueCaseValue</td><td>Text</td><td>Value returned if {@code BoolValue} is not
     * {@code null}.</td></tr>
     * <tr><td>3</td><td>FalseCaseValue</td><td>Text</td><td>Value returned if {@code BoolValue} is
     * {@code null}.</td></tr>
     * </tbody></table>
     * @return {@code TrueCaseValue} if {@code BoolValue} is {@code true}, otherwise {@code FalseCaseValue}.
     * @since 1.0.0
     */
    @Override
    public String calculate(Function.Argument... args) {

        return Boolean.parseBoolean(args[0].resolve()) ? args[1].resolve() : args[2].resolve();
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {

        return new ArgumentSchemeImpl(
                new ArgdefImpl("BoolValue",
                        """
                        The string "true" is interpreted as true, \
                        and the others are interpreted as false. Case is insensitive."""),
                new ArgdefImpl("TrueCaseValue", "Value returned if {@code BoolValue} is not null."),
                new ArgdefImpl("FalseCaseValue", "Value returned if {@code BoolValue} is null.")
        );
    }
}

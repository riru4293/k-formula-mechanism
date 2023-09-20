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

import java.util.stream.Stream;
import jp.mydns.projectk.formula.Function;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;
import jp.mydns.projectk.formula.impl.RepeatArgdefImpl;

/**
 * A formula function that determines whether one or more boolean values are {@code true}. Short-sighted judgment is
 * used to make decisions. In other words, the determination ends when {@code true} is detected. The processing order is
 * the same as the argument order.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class Or extends AbstractFunction {

    private static final int REPEAT_MIN = 0;
    private static final int REPEAT_MAX = 20;

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>repeat</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>{@value #REPEAT_MIN} .. {@value #REPEAT_MAX}</td><td>BoolValue</td><td>Boolean</td><td>The
     * string "true" is interpreted as {@code true}<br>, and the others are interpreted as false.<br>Case is
     * insensitive.</td></tr>
     * </tbody></table>
     * @return {@code "true"} if contain {@code true} in {@code BoolValue}, otherwise {@code "false"}. If there are 0
     * arguments, {@code "false"}.
     * @since 1.0.0
     */
    @Override
    public String calculate(Function.Argument... args) {

        return Boolean.toString(Stream.of(args).sequential().map(Function.Argument::resolve)
                .map(Boolean::valueOf).anyMatch(Boolean.TRUE::equals));
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {

        return new ArgumentSchemeImpl(
                new RepeatArgdefImpl("BoolValue", REPEAT_MIN, REPEAT_MAX,
                        """
                        The string "true" is interpreted as true, \
                        and the others are interpreted as false. Case is insensitive."""));
    }
}

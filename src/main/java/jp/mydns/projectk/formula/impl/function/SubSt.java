/*
 * Copyright (c) 2024, Project-K
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

import static java.lang.Character.isHighSurrogate;
import static java.lang.Character.isSurrogatePair;
import java.util.LinkedList;
import java.util.Queue;
import static java.util.stream.Collectors.toCollection;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;
import jp.mydns.projectk.formula.impl.RepeatArgdefImpl;

/**
 * Formula function that returns a string that is a substring of a specified string. The substring begins with the
 * character at the specified index and continues for the specified number of characters or until the end of the string.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class SubSt extends AbstractFunction {

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>repeat</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>-</td><td>Value</td><td>Text</td><td>A string.</td></tr>
     * <tr><td>2</td><td>-</td><td>BeginIndex</td><td>Int</td><td>Begin index. It begin with 0.</td></tr>
     * <tr><td>3</td><td>0 .. 1</td><td>Length</td><td>Int</td><td>Substring length.</td></tr>
     * </tbody></table>
     * @return a substring of a specified string. {@code null} if specified string is {@code null}.
     * @since 1.0.0
     */
    @Override
    public String calculate(Argument... args) {

        boolean hasLength = args.length > 2;
        String value = args[0].resolve();
        int beginIdx = Argument.Utils.requireInt(args[1]);
        int maxLength = hasLength ? Argument.Utils.requireInt(args[2]) : Integer.MAX_VALUE;

        if (value == null) {
            return null;
        }

        final Queue<Character> q = value.chars().mapToObj(i -> (char) i).collect(toCollection(LinkedList::new));
        final StringBuffer sb = new StringBuffer();
        int len = 0;
        int idx = 0;

        while (!q.isEmpty() && maxLength > len) {

            char c = q.remove();

            boolean isSurrogate = isHighSurrogate(c) && !q.isEmpty() && isSurrogatePair(c, q.element());

            if (beginIdx <= idx) {

                sb.append(c);

                if (isSurrogate) {
                    sb.append(q.remove());
                }

                len++;

            } else if (isSurrogate) {
                q.remove();
            }

            idx++;

        }

        return sb.toString();

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
                new ArgdefImpl("BeginIndex", "Begin index. It begin with 0."),
                new RepeatArgdefImpl("Length", 0, 1, "Substring length."));
    }
}

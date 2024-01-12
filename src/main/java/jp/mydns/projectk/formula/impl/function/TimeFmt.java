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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jp.mydns.projectk.formula.FormulaExecutionException;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;

/**
 * Formula function to change the format of a datetime.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeFmt extends AbstractFunction {

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>DateTime</td><td>Text</td><td>Source datetime.</td></tr>
     * <tr><td>2</td><td>SourceFormat</td><td>DateTimeFormat</td><td>DateTimeFormat of source datetime.</td></tr>
     * <tr><td>3</td><td>ResultFormat</td><td>DateTimeFormat</td><td>DateTimeFormat of result datetime.</td></tr>
     * </tbody></table>
     *
     * @return datetime the format was changed
     * @throws FormulaExecutionException if any argument is invalid
     * @since 1.0.0
     * @see DateTimeFormatter
     */
    @Override
    public String calculate(Argument... args) {

        final LocalDateTime srcDateTime = Argument.Utils.requireLocalDateTime(args[0], args[1]);
        final DateTimeFormatter resultFormatter = Argument.Utils.requireDateTimeFormat(args[2]);

        return srcDateTime.format(resultFormatter);

    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {
        return new ArgumentSchemeImpl(
                new ArgdefImpl("DateTime", "Source datetime."),
                new ArgdefImpl("SourceFormat", "DateTimeFormat of source datetime."),
                new ArgdefImpl("ResultFormat", "DateTimeFormat of result datetime.")
        );
    }
}

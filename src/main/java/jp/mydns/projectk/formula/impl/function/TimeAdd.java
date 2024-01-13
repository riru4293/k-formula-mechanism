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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import jp.mydns.projectk.formula.FormulaExecutionException;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;

/**
 * A formula function that adds a duration to a datetime.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeAdd extends AbstractFunction {

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>DateTime</td><td>LocalDateTime</td><td>Source datetime. It format is
     * {@code uuuu-MM-ddTHH:mm:ss}</td></tr>
     * <tr><td>2</td><td>Duration</td><td>ISO8601Durations</td><td>Additional duration to the source datetime. It format
     * is [-]P[nY][nM][nD][T[nH][nM][nS]].</td></tr>
     * </tbody></table>
     *
     * @return datetime the durations was added
     * @throws FormulaExecutionException if any argument is invalid
     * @since 1.0.0
     */
    @Override
    public String calculate(Argument... args) {

        final LocalDateTime srcDateTime = Argument.Utils.requireLocalDateTime(args[0]);
        final PeriodDuration durations = requirePeriodDuration(args[1]);

        return srcDateTime.plus(durations.p).plus(durations.d).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    }

    private PeriodDuration requirePeriodDuration(Argument value) {

        String pattern = value.resolve().toUpperCase();
        boolean isNegative = pattern.startsWith("-");
        String sign = isNegative ? "-" : "";

        if (isNegative) {
            pattern = pattern.substring(1);
        }

        try {

            if (pattern.startsWith("PT")) {
                return new PeriodDuration(Period.ZERO, Duration.parse(sign + pattern));
            }

            int posT = pattern.indexOf('T');

            if (posT < 0) {
                return new PeriodDuration(Period.parse(sign + pattern), Duration.ZERO);
            }

            return new PeriodDuration(Period.parse(sign + pattern.substring(0, posT)),
                    Duration.parse(sign + "P" + pattern.substring(posT)));

        } catch (RuntimeException ex) {
            throw new FormulaExecutionException("Must be valid durations. But [%s].".formatted(value.resolve()));
        }
    }

    private record PeriodDuration(Period p, Duration d) {

    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {
        return new ArgumentSchemeImpl(
                new ArgdefImpl("DateTime", "Source datetime. It format is \"uuuu-MM-ddTHH:mm:ss\"."),
                new ArgdefImpl("Duration", "Additional duration to the source datetime."
                        + " It format is \"[-]P[nY][nM][nD][T[nH][nM][nS]]\".")
        );
    }
}

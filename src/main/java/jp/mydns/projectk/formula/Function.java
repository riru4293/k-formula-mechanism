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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

/**
 * Formula function interface.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Function {

    /**
     * Get the {@code ArgumentScheme}.
     *
     * @return the {@code ArgumentScheme}
     * @since 1.0.0
     */
    ArgumentScheme getArgumentScheme();

    /**
     * Execute this function.
     *
     * @param args function arguments
     * @return result of execute function. It may be {@code null}.
     * @throws NullPointerException if {@code args} is {@code null} or an element of {@code args} is {@code null}
     * @throws FormulaExecutionException if occurs an any error
     * @since 1.0.0
     */
    String execute(Argument... args);

    /**
     * Wrapper for an argument value of formula function.
     *
     * @author riru
     * @version 1.0.0
     * @since 1.0.0
     */
    interface Argument {

        /**
         * Resolve a value of argument.
         *
         * @return argument value. It may be {@code null}.
         * @since 1.0.0
         */
        String resolve();

        /**
         * Creates a new {@code Argument} that has fixed value.
         *
         * @param value value of argument. It can be set {@code null}
         * @return a new {@code Argument} that has fixed value
         * @since 1.0.0
         */
        static Argument of(String value) {
            return () -> value;
        }

        /**
         * Utilities for {@code Argument}.
         *
         * @author riru
         * @version 1.0.0
         * @since 1.0.0
         */
        class Utils {

            private Utils() {
            }

            /**
             * Checks that the specified {@code Argument} is valid {@code LocalDateTime}.
             *
             * @param value {@code DateTimeFormatter} as {@code Argument}
             * @return the {@code LocalDateTime} that made from {@code value}
             * @throws FormulaExecutionException if {@code value} is invalid as {@code LocalDateTime}
             * @since 1.0.0
             */
            public static LocalDateTime requireLocalDateTime(Argument value) {
                return requireLocalDateTime(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }

            /**
             * Checks that the specified {@code Argument} is valid {@code LocalDateTime}.
             *
             * @param value {@code LocalDateTime} as {@code Argument}
             * @param pattern the {@code Argument}
             * @return the {@code LocalDateTime} that made from {@code value}
             * @throws FormulaExecutionException if {@code value} is invalid as {@code LocalDateTime}, or if
             * {@code pattern} is invalid as {@code DateTimeFormatter}.
             * @since 1.0.0
             */
            public static LocalDateTime requireLocalDateTime(Argument value, Argument pattern) {
                return requireLocalDateTime(value, requireDateTimeFormat(pattern));
            }

            private static LocalDateTime requireLocalDateTime(Argument value, DateTimeFormatter formatter) {
                try {
                    return LocalDateTime.parse(value.resolve(), formatter);
                } catch (RuntimeException ex) {
                    throw new FormulaExecutionException("Must be [%s] format.".formatted(formatter));
                }
            }

            /**
             * Checks that the specified {@code Argument} is valid {@code ZoneId}.
             *
             * @param value {@code ZoneId} as {@code Argument}
             * @return the {@code ZoneId} that made from {@code value}
             * @throws FormulaExecutionException if {@code value} is invalid as {@code ZoneId}
             * @since 1.0.0
             */
            public static ZoneId requireZoneId(Argument value) {
                try {
                    return ZoneId.of(value.resolve());
                } catch (RuntimeException ex) {
                    throw new FormulaExecutionException("Must be valid zone id. But [%s].".formatted(value.resolve()));
                }
            }

            /**
             * Checks that the specified {@code Argument} is valid {@code DateTimeFormatter}.
             *
             * @param value {@code DateTimeFormatter} as {@code Argument}
             * @return the {@code DateTimeFormatter} that made from {@code value}
             * @throws FormulaExecutionException if {@code value} is invalid as {@code DateTimeFormatter}
             * @since 1.0.0
             */
            public static DateTimeFormatter requireDateTimeFormat(Argument value) {
                try {
                    return DateTimeFormatter.ofPattern(value.resolve()).withResolverStyle(ResolverStyle.STRICT);
                } catch (RuntimeException ex) {
                    throw new FormulaExecutionException(
                            "Must be valid datetime format. But [%s].".formatted(value.resolve()));
                }
            }
        }
    }

    /**
     * Definition of the acceptable arguments at the formula function.
     *
     * @author riru
     * @version 1.0.0
     * @since 1.0.0
     */
    interface ArgumentScheme {

        /**
         * Get argument definitions.
         *
         * @return argument definitions
         * @since 1.0.0
         */
        List<Argdef> getArgdefs();

        /**
         * Checks that number of argument is valid.
         *
         * @param args the {@code Argument} array
         * @return the {@code args}. It is argument value itself.
         * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
         * @throws FormulaExecutionException if invalid number of argument
         * @since 1.0.0
         */
        Argument[] requireValid(Argument... args);

        /**
         * Validate meet the arguments count.
         * <p>
         * That is, it satisfies {@link #isValidMaximum(Argument...)} , {@link #isValidMinimum(Argument...)} and
         * {@link #isValidRepeatUnit(Argument...)}.
         *
         * @param args the {@code Argument} array
         * @return {@code true} if arguments count is valid
         * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
         * @since 1.0.0
         */
        boolean isValid(Argument... args);

        /**
         * Validate meet the maximum arguments count.
         *
         * @param args the {@code Argument} array
         * @return {@code true} if arguments count is valid
         * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
         * @since 1.0.0
         */
        boolean isValidMaximum(Argument... args);

        /**
         * Validate meet the minimum arguments count.
         *
         * @param args the {@code Argument} array
         * @return {@code true} if arguments count is valid
         * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
         * @since 1.0.0
         */
        boolean isValidMinimum(Argument... args);

        /**
         * Validate meet the arguments repeat unit count.
         *
         * @param args the {@code Argument} array
         * @return {@code true} if arguments count is valid
         * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
         * @since 1.0.0
         */
        boolean isValidRepeatUnit(Argument... args);
    }
}

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
package jp.mydns.projectk.formula.impl;

import java.util.List;
import static java.util.function.Predicate.not;
import java.util.stream.Stream;
import jp.mydns.projectk.formula.Argdef;
import jp.mydns.projectk.formula.FormulaExecutionException;
import jp.mydns.projectk.formula.Function;
import jp.mydns.projectk.formula.NestArgdef;
import jp.mydns.projectk.formula.RepeatArgdef;

/**
 * Implements of {@code Function.ArgumentScheme}.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArgumentSchemeImpl implements Function.ArgumentScheme {

    private final List<Argdef> argdefs;
    private final int minAllowable;
    private final int maxAllowable;
    private final int increment;

    /**
     * Constructor.
     *
     * @param argdefs formula function argument definitions
     * @throws NullPointerException if {@code argdefs} is {@code null} or if {@code argdefs} contains {@code null}
     * @throws FormulaExecutionException if {@code args} contains two or more repeatable argument definition
     * @since 1.0.0
     */
    public ArgumentSchemeImpl(Argdef... argdefs) {

        List<Argdef> repeatableArgs = Stream.of(requireNonNull(argdefs)).filter(this::isRepeatable).toList();

        boolean hasRepeatable = switch (repeatableArgs.size()) {

            case 0 ->
                false;

            case 1 ->
                true;

            default ->
                throw new FormulaExecutionException("Arguments of formula function cannot be resolved."
                        + " Two or more repeatable argument definitions were detected.");

        };

        final int staticArgsCount = Stream.of(argdefs).filter(not(this::isRepeatable))
                .mapToInt(this::getNumOfContained).sum();

        final int min;
        final int max;
        final int inc;

        if (hasRepeatable) {

            final var reArg = repeatableArgs.get(0);
            final var reCtx = RepeatArgdef.class.cast(reArg).getRepeatContext();
            final int reInc = getNumOfContained(reArg);

            min = staticArgsCount + reCtx.getMinIteration() * reInc;
            max = staticArgsCount + reCtx.getMaxIteration() * reInc;
            inc = reInc;

        } else {

            min = staticArgsCount;
            max = staticArgsCount;
            inc = 0;

        }

        this.argdefs = Stream.of(argdefs).toList();
        this.minAllowable = min;
        this.maxAllowable = max;
        this.increment = inc;

    }

    private boolean isRepeatable(Argdef argdef) {
        return RepeatArgdef.class.isAssignableFrom(argdef.getClass());
    }

    private int getNumOfContained(Argdef argdef) {
        return NestArgdef.class.isAssignableFrom(argdef.getClass())
                ? NestArgdef.class.cast(argdef).getChildren().size() : 1;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public List<Argdef> getArgdefs() {
        return argdefs;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
     * @throws FormulaExecutionException if invalid
     * @since 1.0.0
     */
    @Override
    public Function.Argument[] requireValid(Function.Argument... args) {

        if (isValid(requireNonNull(args))) {
            return args;
        }

        throw new FormulaExecutionException("Incorrect number of arguments for formula function.");

    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
     * @since 1.0.0
     */
    @Override
    public boolean isValid(Function.Argument... args) {
        Function.Argument[] nonNullArgs = requireNonNull(args);
        return isValidMaximum(nonNullArgs) && isValidMinimum(nonNullArgs) && isValidRepeatUnit(nonNullArgs);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
     * @since 1.0.0
     */
    @Override
    public boolean isValidMinimum(Function.Argument... args) {
        return requireNonNull(args).length >= minAllowable;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
     * @since 1.0.0
     */
    @Override
    public boolean isValidMaximum(Function.Argument... args) {
        return requireNonNull(args).length <= maxAllowable;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code args} is {@code null} or if {@code args} contains {@code null}
     * @since 1.0.0
     */
    @Override
    public boolean isValidRepeatUnit(Function.Argument... args) {
        return increment == 0 || (requireNonNull(args).length - minAllowable) % increment == 0;
    }

    /**
     * Returns a string representation of this.
     *
     * @return string representation
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return "ArgumentScheme{" + "argdefs=" + argdefs + ", minAllowable=" + minAllowable
                + ", maxAllowable=" + maxAllowable + ", increment=" + increment + '}';
    }

    private <T> T[] requireNonNull(T[] array) {
        // Note: throw NullPointerException if array is null or contains null within array.
        List.of(array);
        return array;
    }
}

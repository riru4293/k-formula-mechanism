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
import java.util.Map;
import java.util.Objects;
import jp.mydns.projectk.formula.Element;
import jp.mydns.projectk.formula.Formula;
import jp.mydns.projectk.formula.FormulaRuntimeException;
import jp.mydns.projectk.formula.Function;

/**
 * Element of a formula function.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class FunctionElement implements Element {

    private final Function function;
    private final List<Formula> args;

    /**
     * Constructor.
     *
     * @param function the {@code Function}
     * @param args function arguments
     * @throws NullPointerException if any argument is {@code null} or if {@code args} contains {@code null}
     * @since 1.0.0
     */
    public FunctionElement(Function function, List<Formula> args) {

        this.function = Objects.requireNonNull(function);
        this.args = List.copyOf(args);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code inputs} is {@code null}
     * @throws FormulaRuntimeException if an error occurred while calculating formula
     * @since 1.0.0
     */
    @Override
    public String calculate(Map<String, String> inputs) {

        Objects.requireNonNull(inputs);

        try {

            return function.execute(args.stream().map(a -> new ArgumentImpl(a, inputs)).toArray(Function.Argument[]::new));

        } catch (FormulaRuntimeException ex) {

            throw ex;

        } catch (RuntimeException ex) {

            throw new FormulaRuntimeException("Occurs unexpected exception while calculating formula.");
        }
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation
     * @since 1.0.0
     */
    @Override
    public String toString() {

        return "FunctionElement{" + "function=" + function + ", args=" + args + '}';
    }

    private class ArgumentImpl implements Function.Argument {

        private final Formula formula;
        private final Map<String, String> inputs;

        ArgumentImpl(Formula formula, Map<String, String> inputs) {

            this.formula = formula;
            this.inputs = inputs;
        }

        public String resolve() {
            return formula.calculate(inputs);
        }

        @Override
        public String toString() {

            return "Function.Argument{" + "formula=" + formula + ", inputs=" + inputs + '}';
        }
    }
}

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

import jp.mydns.projectk.formula.FormulaExecutionException;
import jp.mydns.projectk.formula.Function;

/**
 * Abstract implements of the {@code Function}
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFunction implements Function {

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code args} is {@code null} or an element of {@code args} is {@code null}
     * @throws FormulaExecutionException if occurs an any error
     * @since 1.0.0
     */
    @Override
    public String execute(Argument... args) {
        return calculate(getArgumentScheme().requireValid(args));
    }

    /**
     * Calculate result of this formula function.
     *
     * @param args valid number arguments
     * @return result of function. It may be {@code null}.
     * @throws FormulaExecutionException if occurs an any error
     * @since 1.0.0
     */
    protected abstract String calculate(Argument... args);
}

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

import jp.mydns.projectk.formula.Argdef;
import jp.mydns.projectk.formula.FormulaRuntimeException;
import jp.mydns.projectk.formula.RepeatArgdef;

/**
 * Implements of {@code NestArgdef} and {@code RepeatArgdef}.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class NestRepeatArgdefImpl extends NestArgdefImpl implements RepeatArgdef {

    protected final RepeatArgdef.RepeatContext repeatContext;

    /**
     * Constructor.
     *
     * @param name argument name
     * @param minIteration minimum iteration count
     * @param maxIteration maximum iteration count
     * @param about argument description
     * @param children nested children
     * @throws NullPointerException if any argument is {@code null} or if {@code children} contains {@code null}
     * @throws FormulaRuntimeException if {@code minIteration} grater than {@code maxIteration} or if
     * {@code minIteration} is negative
     * @since 1.0.0
     */
    public NestRepeatArgdefImpl(String name, int minIteration, int maxIteration, String about, Argdef... children) {

        super(name, about, children);

        try {

            this.repeatContext = new RepeatContextImpl(minIteration, maxIteration);

        } catch (IllegalArgumentException ex) {

            throw new FormulaRuntimeException(ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public RepeatArgdef.RepeatContext getRepeatContext() {

        return repeatContext;
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation of this
     * @since 1.0.0
     */
    @Override
    public String toString() {

        return "NestRepeatArgdef{" + "name=" + name + ", about=" + about
                + ", repeatContext=" + repeatContext + ", children=" + children + '}';
    }
}

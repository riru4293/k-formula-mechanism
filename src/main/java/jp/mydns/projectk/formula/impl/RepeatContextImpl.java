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

import jp.mydns.projectk.formula.RepeatArgdef;

/**
 * The repetition information for {@link RepeatArgdef}. Defines the minimum and maximum number of iterations.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class RepeatContextImpl implements RepeatArgdef.RepeatContext {

    private final int minIteration;
    private final int maxIteration;

    /**
     * Constructor.
     *
     * @param minIteration minimum iteration count
     * @param maxIteration maximum iteration count
     * @throws IllegalArgumentException if {@code minIteration} grater than {@code maxIteration} or if
     * {@code minIteration} is negative
     * @since 1.0.0
     */
    public RepeatContextImpl(int minIteration, int maxIteration) {

        if (minIteration > maxIteration) {
            throw new IllegalArgumentException("Minimum argument iteration is greater than it max iteration.");
        }

        if (minIteration < 0) {
            throw new IllegalArgumentException("Minimum argument iteration can not negative.");
        }

        this.minIteration = minIteration;
        this.maxIteration = maxIteration;

    }

    /**
     * Get minimum iteration count.
     *
     * @return minimum iteration count
     * @since 1.0.0
     */
    @Override
    public int getMinIteration() {
        return minIteration;
    }

    /**
     * Get maximum iteration count.
     *
     * @return maximum iteration count
     * @since 1.0.0
     */
    @Override
    public int getMaxIteration() {
        return maxIteration;
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation of this
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return "RepeatContext{" + "minIteration=" + minIteration + ", maxIteration=" + maxIteration + '}';
    }
}

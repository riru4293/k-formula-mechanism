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

import java.util.Map;
import jp.mydns.projectk.formula.Element;

/**
 * Element of fixed value.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class FixedValueElement implements Element {

    private final String value;

    /**
     * Constructor.
     *
     * @param value this element value. It can be set {@code null}.
     * @since 1.0.0
     */
    public FixedValueElement(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}.
     *
     * @param inputs no used
     * @return this element value. It may be {@code null}.
     * @since 1.0.0
     */
    @Override
    public String calculate(Map<String, String> inputs) {
        return value;
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return "FixedValueElement{" + "value=" + value + '}';
    }
}

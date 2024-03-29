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
import java.util.Objects;
import jp.mydns.projectk.formula.Element;

/**
 * Element of an input value.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class InputElement implements Element {

    private final String name;

    /**
     * Constructor.
     *
     * @param name name of input value
     * @throws NullPointerException if {@code name} is {@code null}
     * @since 1.0.0
     */
    public InputElement(String name) {
        this.name = Objects.requireNonNull(name);
    }

    /**
     * Get the value obtained from {@code inputs} with the name of the input value this element has.
     *
     * @param inputs input values
     * @return the value obtained from {@code inputs} with the name of the input value this element has. It may be
     * {@code null}.
     * @throws NullPointerException if {@code inputs} is {@code null}
     * @since 1.0.0
     */
    @Override
    public String calculate(Map<String, String> inputs) {
        return Objects.requireNonNull(inputs).get(name);
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "InputElement{" + "name=" + name + '}';
    }
}

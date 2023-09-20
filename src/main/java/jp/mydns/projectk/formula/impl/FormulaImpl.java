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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jp.mydns.projectk.formula.Element;
import jp.mydns.projectk.formula.Formula;
import jp.mydns.projectk.formula.FormulaRuntimeException;

/**
 * Implements of the {@code Formula}.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class FormulaImpl implements Formula {

    private final List<Element> elements;

    /**
     * Constructor.
     *
     * @param elements elements of formula
     * @throws NullPointerException if {@code elements} is {@code null}, or it contain any {@code null} elements.
     * @since 1.0.0
     */
    public FormulaImpl(List<Element> elements) {

        this.elements = List.copyOf(elements);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code inputs} is {@code null}
     * @throws FormulaRuntimeException if occurs error inside formula function
     * @since 1.0.0
     */
    @Override
    public String calculate(Map<String, String> inputs) {

        Objects.requireNonNull(inputs);

        List<String> values = new ArrayList<>();

        elements.stream().sequential().map(e -> e.calculate(inputs)).forEachOrdered(values::add);

        return values.stream().noneMatch(Objects::isNull) ? String.join("", values) : null;
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation
     * @since 1.0.0
     */
    @Override
    public String toString() {

        return "Formula{" + "elements=" + elements + '}';
    }
}

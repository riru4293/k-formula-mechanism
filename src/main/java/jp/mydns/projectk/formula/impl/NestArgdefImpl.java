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
import jp.mydns.projectk.formula.Argdef;
import jp.mydns.projectk.formula.NestArgdef;

/**
 * Implements of {@code NestArgdef}.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class NestArgdefImpl extends ArgdefImpl implements NestArgdef {

    protected final List<Argdef> children;

    /**
     * Constructor.
     *
     * @param name argument name
     * @param about argument description
     * @param children nested argument definition
     * @throws NullPointerException if any argument is {@code null} or if {@code children} contains {@code null}
     * @since 1.0.0
     */
    public NestArgdefImpl(String name, String about, Argdef... children) {

        super(name, about);

        this.children = List.of(children);
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public List<Argdef> getChildren() {

        return children;
    }

    /**
     * Returns a string representation of this.
     *
     * @return a string representation of this
     * @since 1.0.0
     */
    @Override
    public String toString() {

        return "NestArgdef{" + "name=" + name + ", about=" + about + ", children=" + children + '}';
    }
}

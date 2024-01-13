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

import java.util.Objects;
import jp.mydns.projectk.formula.Argdef;

/**
 * Implements of {@code Argdef}.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArgdefImpl implements Argdef {

    protected final String name;
    protected final String about;

    /**
     * Constructor.
     *
     * @param name argument name
     * @param about argument description
     * @throws NullPointerException if any argument is {@code null}.
     */
    public ArgdefImpl(String name, String about) {
        this.name = Objects.requireNonNull(name);
        this.about = Objects.requireNonNull(about);
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public String getAbout() {
        return about;
    }

    /**
     * Returns a string representation of this.
     *
     * @return string representation of this
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return "Argdef{" + "name=" + name + ", about=" + about + '}';
    }
}

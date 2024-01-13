/*
 * Copyright (c) 2024, Project-K
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
package jp.mydns.projectk.formula.impl.function;

import jp.mydns.projectk.formula.Function;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * Test of class TimeFmt.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
class TimeAddTest {

    /**
     * Test of calculate method.
     *
     * @since 1.0.0
     */
    @Test
    void testCalculate() {

        Function.Argument a1 = Function.Argument.of("2999-12-31T23:59:59");
        Function.Argument a2 = Function.Argument.of("-P1000Y2M16DT11H35M11S");

        TimeAdd instance = new TimeAdd();

        String result = instance.calculate(a1, a2);

        assertThat(result).isEqualTo("1999-10-15T12:24:48");

    }

    /**
     * Test of calculate method. If only add year.
     *
     * @since 1.0.0
     */
    @Test
    void testCalculate_AddYearOnly() {

        Function.Argument a1 = Function.Argument.of("2999-12-31T23:59:59");
        Function.Argument a2 = Function.Argument.of("P1Y");

        TimeAdd instance = new TimeAdd();

        String result = instance.calculate(a1, a2);

        assertThat(result).isEqualTo("3000-12-31T23:59:59");

    }

    /**
     * Test of calculate method. If only add second.
     *
     * @since 1.0.0
     */
    @Test
    void testCalculate_AddSecondOnly() {

        Function.Argument a1 = Function.Argument.of("2999-12-31T23:59:59");
        Function.Argument a2 = Function.Argument.of("PT1S");

        TimeAdd instance = new TimeAdd();

        String result = instance.calculate(a1, a2);

        assertThat(result).isEqualTo("3000-01-01T00:00:00");

    }

}

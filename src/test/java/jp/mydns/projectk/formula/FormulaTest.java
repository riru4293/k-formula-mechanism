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
package jp.mydns.projectk.formula;

import java.util.Map;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;
import jp.mydns.projectk.formula.parser.Parser;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author riru
 */
class FormulaTest {

    /**
     * Test parse formula text to build and calculate formula.
     *
     * @since 1.0.0
     */
    @Test
    void testParseAndCalculate() {

        String formulaText = "`Hello ` & [name] & ` world!!`";

        Map<String, String> inputs = Map.of("name", "formula-mechanism");

        Parser parser = new Parser(Map.of());

        Formula parsedFormula = parser.parse(formulaText);

        String result = parsedFormula.calculate(inputs);

        assertThat(result).isEqualTo("Hello formula-mechanism world!!");
    }

    /**
     * Parse formula text containing functions to test constructing and calculating formula.
     *
     * @since 1.0.0
     */
    @Test
    void testParseAndCalculateWithFunction() {

        String formulaText = "[prefix] & x3( x3( sayHello() ) ) & [suffix]";

        Map<String, String> inputs = Map.of("prefix", "[", "suffix", "]");

        Parser parser = new Parser(Map.of("SayHello", SayHello::new, "x3", X3::new));

        Formula parsedFormula = parser.parse(formulaText);

        String result = parsedFormula.calculate(inputs);

        assertThat(result).isEqualTo("[HelloHelloHelloHelloHelloHelloHelloHelloHello]");
    }

    private class SayHello extends AbstractFunction {

        @Override
        protected String calculate(Argument... args) {

            return "Hello";
        }

        @Override
        public ArgumentScheme getArgumentScheme() {

            return new ArgumentSchemeImpl();
        }
    }

    private class X3 extends AbstractFunction {

        @Override
        protected String calculate(Argument... args) {

            return args[0].resolve() + args[0].resolve() + args[0].resolve();
        }

        @Override
        public ArgumentScheme getArgumentScheme() {

            return new ArgumentSchemeImpl(new ArgdefImpl("x3 word", "Words to triple."));
        }
    }
}

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

import jp.mydns.projectk.formula.Function.Argument;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * Test of class SubSt.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
class SubStTest {

    /**
     * Test of calculate method.
     *
     * @since 1.0.0
     */
    @Test
    void testCalculate() {

        Argument a1 = Argument.of("Hello world!!");
        Argument a2 = Argument.of("3");

        SubSt instance = new SubSt();

        String result = instance.calculate(a1, a2);

        assertThat(result).isEqualTo("lo world!!");

    }

    /**
     * Test of calculate method. With length.
     *
     * @since 1.0.0
     */
    @Test
    void testCalculate_WithLength() {

        Argument a1 = Argument.of("Hello world!!");
        Argument a2 = Argument.of("3");
        Argument a3 = Argument.of("5");

        SubSt instance = new SubSt();

        String result = instance.calculate(a1, a2, a3);

        assertThat(result).isEqualTo("lo wo");

    }

    /**
     * Test of calculate method. Contains surrogate pair.
     *
     * @since 1.0.0
     */
    @Test
    void testCalculate_ContainsSurrogatePair() {

        Argument a1 = Argument.of("𠀋 𡈽 𡌛 𡑮 𡢽 𠮟 𡚴 𡸴 𣇄 𣗄 𣜿 𣝣 𣳾 𤟱 𥒎 𥔎 𥝱 𥧄 𥶡 𦫿 𦹀 𧃴 𧚄 𨉷 𨏍 𪆐 𠂉 𠂢 𠂤 𠆢 𠈓 𠌫 𠎁 𠍱 𠏹 𠑊 𠔉 𠗖 𠘨 𠝏 𠠇 𠠺 𠢹 𠥼 𠦝 𠫓 𠬝 𠵅 𠷡 𠺕 𠹭 𠹤 𠽟 𡈁 𡉕 𡉻 𡉴 𡋤 𡋗 𡋽 𡌶 𡍄 𡏄 𡑭 𡗗 𦰩 𡙇 𡜆 𡝂 𡧃 𡱖 𡴭 𡵅 𡵸 𡵢 𡶡 𡶜 𡶒 𡶷 𡷠 𡸳 𡼞 𡽶 𡿺 𢅻 𢌞 𢎭 𢛳 𢡛 𢢫 𢦏 𢪸 𢭏 𢭐 𢭆 𢰝 𢮦 𢰤 𢷡 𣇃 𣇵 𣆶 𣍲 𣏓 𣏒 𣏐 𣏤 𣏕 𣏚 𣏟 𣑊 𣑑 𣑋 𣑥 𣓤 𣕚 𣖔 𣘹 𣙇 𣘸 𣘺 𣜜 𣜌 𣝤 𣟿 𣟧 𣠤 𣠽 𣪘 𣱿 𣴀 𣵀 𣷺 𣷹 𣷓 𣽾 𤂖 𤄃 𤇆 𤇾 𤎼 𤘩 𤚥 𤢖 𤩍 𤭖 𤭯 𤰖 𤴔 𤸎 𤸷 𤹪 𤺋 𥁊 𥁕 𥄢 𥆩 𥇥 𥇍 𥈞 𥉌 𥐮 𥓙 𥖧 𥞩 𥞴 𥧔 𥫤 𥫣 𥫱 𥮲 𥱋 𥱤 𥸮 𥹖 𥹥 𥹢 𥻘 𥻂 𥻨 𥼣 𥽜 𥿠 𥿔 𦀌 𥿻 𦀗 𦁠 𦃭 𦉰 𦊆 𦍌 𣴎 𦐂 𦙾 𦚰 𦜝 𦣝 𦣪 𦥑 𦥯 𦧝 𦨞 𦩘 𦪌 𦪷 𦱳 𦳝 𦹥 𦾔 𦿸 𦿶 𦿷 𧄍 𧄹 𧏛 𧏚 𧏾 𧐐 𧑉 𧘕 𧘔 𧘱 𧚓 𧜎 𧜣 𧝒 𧦅 𧪄 𧮳 𧮾 𧯇 𧲸 𧶠 𧸐 𧾷 𨂊 𨂻 𨊂 𨋳 𨐌 𨑕 𨕫 𨗈 𨗉 𨛗 𨛺 𨥉 𨥆 𨥫 𨦇 𨦈 𨦺 𨦻 𨨞 𨨩 𨩱 𨩃 𨪙 𨫍 𨫤 𨫝 𨯁 𨯯 𨴐 𨵱 𨷻 𨸟 𨸶 𨺉 𨻫 𨼲 𨿸 𩊠 𩊱 𩒐 𩗏 𩙿 𩛰 𩜙 𩝐 𩣆 𩩲 𩷛 𩸽 𩸕 𩺊 𩹉 𩻄 𩻩 𩻛 𩿎 𪀯 𪀚 𪃹 𪂂 𢈘 𪎌 𪐷 𪗱 𪘂 𪘚 𪚲");
        Argument a2 = Argument.of("1");
        Argument a3 = Argument.of("8");

        SubSt instance = new SubSt();

        String result = instance.calculate(a1, a2, a3);

        assertThat(result).isEqualTo(" 𡈽 𡌛 𡑮 𡢽");

    }

}

package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringCalculatorParserTest {

    String inputString;

    @BeforeEach
    void setUp() {
        inputString = "2 + 3 * 4 / 2";
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2 + 3 * 4 / 2",
            "1 + 1",
            "1 - 1 + 3",
            "2 * 1",
            "4 / 2",
            "2 / 2 * 3 + 2 - 1",
            "16 / 2 / 2 / 2"})
    void 정상_식_판별(String input) {
        StringCalculatorParser parser = new StringCalculatorParser(input);
        assertThat(parser.isExpressionValid()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " ",
            "    ",
            "222",
            "1 + - +",
            "1 + 2 *",
            "2 3 4",
            "+ - * /",
            "2 + + * 4 / 2",
            "1+1",
            "1- 1 + 3",
            "2 * 1*",
            "+ 4 -",
            "*5 + 2",
            "16 + 2 $ 2 - 2"})
    void 비정상_식_판별(String input) {
        StringCalculatorParser parser = new StringCalculatorParser(input);
        assertThat(parser.isExpressionValid()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "+", "3", "*", "4", "/", "2"})
    void values_확인(String input) {
        StringCalculatorParser parser = new StringCalculatorParser(inputString);
        assertThat(parser.getValues()).contains(input);
    }
}
package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StringCalculatorTest {
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
    void 정상_식_판별_테스트(String input) {
        StringCalculator calculator = new StringCalculator(input);
        assertThat(calculator).isNotNull();
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
    void 비정상_식_판별_테스트(String input) {

        assertThatThrownBy(() -> {
            StringCalculator calculator = new StringCalculator(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("식이 잘못됐습니다.");
    }

    @DisplayName("문자열 파싱")
    @ParameterizedTest
    @ValueSource(strings = {"2", "+", "3", "*", "4", "/", "2"})
    void parsingInput(String input) {
        String[] values = inputString.split(" ");
        assertThat(values).contains(input);
    }
}

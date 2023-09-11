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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    String inputString;

    @BeforeEach
    void setUp() {
        inputString = "2 + 3 * 4 / 2";
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
        assertThatThrownBy(() -> {
            StringCalculator calculator = new StringCalculator(input);
        }).isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("식이 잘못됐습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1 + 1:2",
            "1 + 3:4",
            "1 - 1:0",
            "3 - 4:-1",
            "3 * 3 * 3 / 9 + 3 * 4 - 5:19",
            "645234 - 6345 * 112 / 2 + 9:35777793"
    }, delimiter = ':')
    void 계산_검증(String input, Double expected) {
        StringCalculator calculator = new StringCalculator(input);
        assertEquals(expected, calculator.getResult());
    }
}

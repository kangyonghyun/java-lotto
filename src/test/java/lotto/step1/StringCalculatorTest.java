package lotto.step1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Test
    @DisplayName("문자열 계산기 객체 생성")
    void createStringCalculator() {
        String input = "3,4:5";
        StringCalculator calculator = new StringCalculator(input);
        assertThat(calculator).isEqualTo(new StringCalculator(input));
    }

    @Test
    @DisplayName("구분자(,:)를 기준으로 분리")
    void splitDelimiter() {
        StringCalculator calculator = new StringCalculator("3,4:5");
        assertThat(calculator.getValues()).containsExactly(3, 4, 5);
    }

    @Test
    @DisplayName("분리된 숫자 합")
    void add() {
        StringCalculator calculator = new StringCalculator("3,4:5");
        calculator.add();
        assertThat(calculator.calculateResult()).isEqualTo(12);
    }

    @Test
    @DisplayName("문자열 값이 빈문자열 or null -> 0 반환")
    void nullOrEmpty() {
        StringCalculator empty = new StringCalculator("");
        StringCalculator nullValue = new StringCalculator(null);
        assertThat(empty.calculateResult()).isEqualTo(0);
        assertThat(nullValue.calculateResult()).isEqualTo(0);
    }

    @Test
    @DisplayName("커스텀 구분자 도입")
    void customDelimiter() {
        StringCalculator calculator = new StringCalculator("//;\n3;4;5");
        calculator.add();
        int result = calculator.calculateResult();
        assertThat(result).isEqualTo(12);
    }

}

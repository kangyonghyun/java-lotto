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

}

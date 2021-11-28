package lotto.lottogame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static lotto.lottogame.domain.LottoGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @Test
    void create() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(createManualLotto(lottoNumbers)).isEqualTo(createManualLotto(lottoNumbers));
    }

    @ParameterizedTest
    @MethodSource("provideWiningNumbers")
    @DisplayName("당첨번호와 비교했을 때, 몇개 맞냐?")
    void countOfMatch(Lotto winningNumbers, int result) {
        Lotto lotto = createManualLotto(Arrays.asList(1, 2, 3, 4, 11, 12));
        assertThat(lotto.countOfMatch(winningNumbers)).isEqualTo(result);
    }

    private static Stream<Arguments> provideWiningNumbers() {
        return Stream.of(
                Arguments.of(createManualLotto(Arrays.asList(1, 2, 3, 35, 37, 26)), 3),
                Arguments.of(createManualLotto(Arrays.asList(1, 2, 3, 4, 37, 26)), 4),
                Arguments.of(createManualLotto(Arrays.asList(1, 2, 3, 4, 11, 26)), 5),
                Arguments.of(createManualLotto(Arrays.asList(1, 2, 3, 4, 11, 12)), 6)
        );
    }

}
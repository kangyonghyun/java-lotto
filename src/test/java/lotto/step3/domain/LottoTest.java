package lotto.step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @ParameterizedTest
    @MethodSource("provideWiningNumbers")
    @DisplayName("당첨번호와 비교했을 때, 몇개 맞냐?")
    void countOfMatch(Lotto winningNumbers, int result) {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 11, 12);
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.countOfMatch(winningNumbers)).isEqualTo(result);
    }

    private static Stream<Arguments> provideWiningNumbers() {
        return Stream.of(
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 35, 37, 26)), 3),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 37, 26)), 4),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 26)), 5),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12)), 6)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWiningNumbers1")
    @DisplayName("당첨번호와 비교했을 때, 2등을 제외한 예상된 매칭수랑 맞는지 확인, 2등은 false 반환")
    void isMatchExpected(Lotto winningNumbers, int count, int bonusBall, boolean result) {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 11, 12);
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.isCountOfMatch(winningNumbers, count, new LottoNumber(bonusBall))).isEqualTo(result);
    }

    private static Stream<Arguments> provideWiningNumbers1() {
        return Stream.of(
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 35, 37, 26)), 3, 15, true),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 37, 26)), 4, 15, true),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 26)), 5, 15, true),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 32)), 5, 12, false),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12)), 6, 20, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWiningNumbers2")
    @DisplayName("당첨번호와 비교했을 때, 5개 매칭 그리고 보너스 볼과 맞는지 확인(2등이냐?)")
    void isSecondPrizeWinner(Lotto winningNumbers, int bonusBall, boolean expected) {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 11, 12);
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.isSecondPrizeWinner(winningNumbers, new LottoNumber(bonusBall))).isEqualTo(expected);
    }

    private static Stream<Arguments> provideWiningNumbers2() {
        return Stream.of(
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 35, 37, 26)), 11, false),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 37, 26)), 11, false),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 26)), 11, true),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12)), 11, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWiningNumbers3")
    @DisplayName("당첨번호와 비교했을 때, 매칭된 우승상금이랑 맞는가?")
    void prizeMoney(Lotto winningNumbers, int bonusBall, int money) {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 11, 12);
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.calculatePrizeMoney(winningNumbers, new LottoNumber(bonusBall))).isEqualTo(money);
    }

    private static Stream<Arguments> provideWiningNumbers3() {
        return Stream.of(
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 35, 37, 26)), 11, Rank.FIFTH.getPrizeMoney()),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 37, 26)), 11, Rank.FOURTH.getPrizeMoney()),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 26)), 5, Rank.THIRD.getPrizeMoney()),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 26)), 11, Rank.SECOND.getPrizeMoney()),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12)), 11,Rank.FIRST.getPrizeMoney())
        );
    }

}
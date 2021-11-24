package lotto.step3.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {

    public static final int LOTTO_BONUS_COUNT = 5;

    private final List<LottoNumber> numbers;

    public Lotto() {
        this.numbers = LottoGenerator.createLotto();
    }

    public Lotto(List<Integer> numbers) {
        this.numbers = LottoGenerator.createWinningNumbers(numbers);
    }

    // 당첨번호와 몇개 맞냐?
    public int countOfMatch(Lotto winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }

    // 2등을 제외한 매칭이 맞냐?
    public boolean isCountOfMatch(Lotto winningNumbers, int count, LottoNumber bonusBall) {
        if (isSecondPrizeWinner(winningNumbers, bonusBall)) {
            return false;
        }
        return countOfMatch(winningNumbers) == count;
    }

    // 2등인지 확인
    public boolean isSecondPrizeWinner(Lotto winningNumbers, LottoNumber bonusBall) {
        return countOfMatch(winningNumbers) == LOTTO_BONUS_COUNT && hasBonusBall(bonusBall);
    }

    public int calculatePrizeMoney(Lotto winningNumbers, LottoNumber bonusBall) {
        return Rank.valueOf(countOfMatch(winningNumbers),
                isSecondPrizeWinner(winningNumbers, bonusBall)).getPrizeMoney();
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    private boolean hasBonusBall(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }

}

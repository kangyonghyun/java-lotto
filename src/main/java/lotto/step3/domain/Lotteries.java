package lotto.step3.domain;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Lotteries {

    private static final String RATE_PATTERN = "0.##";

    private final List<Lotto> lotteries;

    public Lotteries(int orderCount) {
        this(range(0, orderCount)
                .mapToObj(i -> new Lotto())
                .collect(toList()));
    }

    public Lotteries(List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public Lotteries(List<Lotto> lotteries, int orderCount) {
        this.lotteries = lotteries;
        this.lotteries.addAll(new Lotteries(orderCount).getLotteries());
    }

    // 당첨 통계 Map에 저장
    public Map<Rank, Integer> createLottoStatistics(Lotto winningNumbers, LottoNumber bonusBall) {
        Map<Rank, Integer> lottoStatistics = new HashMap<>();
        for (Lotto lotto : lotteries) {
            Rank rank = Rank.valueOf(lotto.countOfMatch(winningNumbers), lotto.isSecondPrizeWinner(winningNumbers, bonusBall));
            lottoStatistics.put(rank, totalCountOfMatch(rank, winningNumbers, bonusBall));
        }
        return lottoStatistics;
    }

    public double calculateRateOfProfit(Lotto winningNumbers, LottoNumber bonusBall, int orderPrice) {
        double totalPrizeMoney = totalPrizeMoney(winningNumbers, bonusBall);
        DecimalFormat format = new DecimalFormat(RATE_PATTERN);
        format.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(format.format(totalPrizeMoney / orderPrice));
    }

    public List<Lotto> getLotteries() {
        return lotteries;
    }

    // 2등을 제외한 인원 + 2등 인원
    private int totalCountOfMatch(Rank rank, Lotto winningNumbers, LottoNumber bonusBall) {
        if (rank == Rank.SECOND) {
            return totalSecondPrizeWinners(winningNumbers, bonusBall);
        }
        return excludedSecondWinners(winningNumbers, rank.getCountOfMatch(), bonusBall);
    }

    // 2등인원
    private int totalSecondPrizeWinners(Lotto winningNumbers, LottoNumber bonusBall) {
        return (int) lotteries.stream()
                .filter(lotto -> lotto.isSecondPrizeWinner(winningNumbers, bonusBall))
                .count();
    }

    // 2등을 제외한 토탈매칭수
    private int excludedSecondWinners(Lotto winningNumbers, int count, LottoNumber bonusBall) {
        return (int) lotteries.stream()
                .filter(lotto -> lotto.isCountOfMatch(winningNumbers, count, bonusBall))
                .count();
    }

    private int totalPrizeMoney(Lotto winningNumbers, LottoNumber bonusBall) {
        return lotteries.stream()
                .mapToInt(lotto -> lotto.calculatePrizeMoney(winningNumbers, bonusBall))
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotteries lotteries1 = (Lotteries) o;
        return Objects.equals(lotteries, lotteries1.lotteries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotteries);
    }

    @Override
    public String toString() {
        return "Lotteries{" +
                "lotteries=" + lotteries +
                '}';
    }

}

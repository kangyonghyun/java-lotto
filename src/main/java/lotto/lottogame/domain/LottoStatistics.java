package lotto.lottogame.domain;

import java.util.Map;

public class LottoStatistics {

    private final Map<Rank, Integer> lottoStatistics;
    private final OrderPrice orderPrice;

    public LottoStatistics(Map<Rank, Integer> lottoStatistics, OrderPrice orderPrice) {
        this.lottoStatistics = lottoStatistics;
        this.orderPrice = orderPrice;
    }

    public double calculateRateOfProfit() {
        int sum = lottoStatistics.keySet().stream()
                .mapToInt(rank -> rank.getPrizeMoney() * lottoStatistics.get(rank))
                .sum();
        return  sum / (double) orderPrice.getOrderPrice();
    }

    public Map<Rank, Integer> getLottoStatistics() {
        return lottoStatistics;
    }

}

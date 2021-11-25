package lotto.step3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;

    private final OrderPrice orderPrice;

    public LottoStore(int orderPrice) {
        this.orderPrice = new OrderPrice(orderPrice);
    }

    public OrderCount calculateAllCount() {
        return new OrderCount(orderPrice.getOrderPrice() / LOTTO_PRICE);
    }

    public Lotteries sellAllLotteries(int orderManualCount, List<List<Integer>> list) {
        List<Lotto> lotteries = new ArrayList<>();
        for (int i = 0; i < orderManualCount; i++) {
            lotteries.add(new Lotto(list.get(i)));
        }
        return new Lotteries(lotteries, calculateAutoCount(orderManualCount));
    }

    public OrderPrice getOrderPrice() {
        return orderPrice;
    }

    private int calculateAutoCount(int orderManualCount) {
        OrderCount manualCount = new OrderCount(orderManualCount);
        OrderCount autoCount = calculateAllCount().minusCount(manualCount);
        return autoCount.getOrderCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoStore that = (LottoStore) o;
        return Objects.equals(orderPrice, that.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderPrice);
    }

    @Override
    public String toString() {
        return "LottoStore{" +
                "orderPrice=" + orderPrice +
                '}';
    }

}

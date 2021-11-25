package lotto.step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStoreTest {

    @Test
    @DisplayName("로또 상점 생성하여 주문가격 입력")
    void getOrderCount() {
        LottoStore store = new LottoStore(14000);
        assertThat(store.getOrderPrice()).isEqualTo(new OrderPrice(14000));
    }

    @Test
    @DisplayName("총개수 = 14000 / 1000")
    void calculateAutoCount() {
        LottoStore store = new LottoStore(14000);
        assertThat(store.calculateAllCount()).isEqualTo(new OrderCount(14));
    }

    @Test
    @DisplayName("총 로또개수 판매 = 수동개수 + 자동개수")
    void sellAllLotteries() {
        LottoStore store = new LottoStore(14000);
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        list.add(Arrays.asList(7, 8, 9, 10, 11, 12));
        list.add(Arrays.asList(13, 14, 15, 16, 17, 18));
        Lotteries allLotteries = store.sellAllLotteries(3, list);
        assertThat(allLotteries.getLotteries()).size().isEqualTo(14);
        assertThat(allLotteries.getLotteries()).contains(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

}
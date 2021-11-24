package lotto.step3.view;

import lotto.step3.domain.Lotteries;
import lotto.step3.domain.Lotto;
import lotto.step3.domain.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public final class ResultView {

    private ResultView() {
        throw new AssertionError();
    }

    public static void printOrderLottoNumber(Lotteries lotteries) {
        lotteries.getLotteries().stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printOrderLottoCount(Lotteries lotteries) {
        System.out.println(lotteries.getLotteries().size() + "개를 구매했습니다.");
    }

    public static void printLottoStatics(Map<Rank, Integer> lottoStatistics, double profit) {
        StringBuilder builder = new StringBuilder();
        builder.append("당첨 통계");
        builder.append("\n");
        builder.append("----------");
        builder.append("\n");
        Rank[] ranks = Rank.values();
        Arrays.sort(ranks, Collections.reverseOrder());
        for (int i = 1; i < ranks.length; i++) {
            builder.append(ranks[i].getCountOfMatch());
            builder.append("개 일치");
            isSecondPrize(builder, ranks[i]);
            builder.append("(");
            builder.append(ranks[i].getPrizeMoney());
            builder.append("원) - ");
            int value = lottoStatistics.get(ranks[i]) == null ? 0 : lottoStatistics.get(ranks[i]);
            builder.append(value);
            builder.append("개");
            builder.append("\n");
        }
        System.out.print(builder);
        System.out.println("총 수익률은 " + profit + "입니다.");
    }

    private static void isSecondPrize(StringBuilder builder, Rank rank) {
        if (rank == Rank.SECOND) {
            builder.append(", 보너스볼 일치 ");
        }
    }

}

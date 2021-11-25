package lotto.step3.controller;

import lotto.step3.domain.*;

import java.util.List;
import java.util.Map;

import static lotto.step3.view.InputView.*;
import static lotto.step3.view.ResultView.*;

public class LottoController {

    public static void main(String[] args) {

        // 구입금액 입력받고
        int orderPrice = printInputOrderPrice();
        // 수동으로 구매할 수량 입력받고
        int orderManualCount = printInputOrderCount();

        LottoStore store = new LottoStore(orderPrice);

        Lotteries allLotteries = store.sellAllLotteries(orderManualCount, printInputLottoNumbers(orderManualCount));

        printOrderLottoCount(orderManualCount, store);
        printOrderLottoNumber(allLotteries);

        WinningLotto winningLotto = new WinningLotto(printInputWinningNumbers(), PrintInputBonusBall());

        Map<Rank, Integer> lottoStatistics = winningLotto.createLottoStatistics(allLotteries);
        double rateOfReturn = winningLotto.calculateRateOfProfit(allLotteries, orderPrice);
        printLottoStatics(lottoStatistics, rateOfReturn);

    }

}

package lotto.lottogame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class LottoGenerator {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private static final List<LottoNumber> allLottoNumbers = new ArrayList<>();

    static{
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            allLottoNumbers.add(LottoNumber.of(i));
        }
    }

    public static Lotto createAutoLotto() {
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> randomLottoNumbers = allLottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .collect(toList());
        return new Lotto(Collections.unmodifiableList(randomLottoNumbers));
    }

    public static Lotto createManualLotto(List<Integer> numbers) {
        checkNumbers(numbers);
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::of)
                .collect(toList());
        return new Lotto(Collections.unmodifiableList(lottoNumbers));
    }

    private static void checkNumbers(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("6자리 입력해주세요");
        }
        long count = numbers.stream()
                .distinct()
                .count();
        if (count != LOTTO_SIZE) {
            throw new IllegalArgumentException("중복 번호는 안됩니다");
        }
    }

}

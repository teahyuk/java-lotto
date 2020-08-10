package vo;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private final int number;

    private static final Map<Integer, LottoNumber> numbers = IntStream.range(1, 46)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), LottoNumber::new));

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        if(!numbers.containsKey(number)){
            throw new IllegalArgumentException("number must be 1~45");
        }
        return numbers.get(number);
    }

    public int getNumber() {
        return number;
    }
}

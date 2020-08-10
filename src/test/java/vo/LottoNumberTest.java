package vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    public void voCacheTest() {
        for (int i = 1; i < 46; i++) {
            assertThat(LottoNumber.of(i))
                    .isEqualTo(LottoNumber.of(i));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    public void constructorTest(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getterTest() {
        for (int i = 1; i < 46; i++) {
            assertThat(LottoNumber.of(i).getNumber())
                    .isEqualTo(i);
        }
    }

}
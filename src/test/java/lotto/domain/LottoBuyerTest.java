package lotto.domain;

import lotto.dto.LottoNumberDto;
import lotto.dto.PurchasedLottoNumbersDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoBuyerTest {

	@Test
	@DisplayName("로또 번호 구매 갯수 테스트")
	void buyLottoNumbersCountTest() {
		LottoBuyer buyer = new LottoBuyer();

		assertThat(getPurchasedLottoNumbersDto(buyer, new LottoMoney(14000)).getAutoCount())
				.isEqualTo(getPurchasedLottoNumbersDto(buyer, new LottoMoney(14999)).getAutoCount())
				.isEqualTo(14);
	}

	private PurchasedLottoNumbersDto getPurchasedLottoNumbersDto(LottoBuyer buyer, LottoMoney lottoMoney) {
		return buyer.buyLottoNumbers(lottoMoney, Collections.emptyList());
	}

	@Test
	@DisplayName("로또 번호 전부 다 같은 번호 나오지는 않는지 확인 테스트")
	void LottoDistinctionTest() {
		LottoBuyer buyer = new LottoBuyer();
		List<LottoNumberDto> lottoNumbers = getPurchasedLottoNumbersDto(buyer, new LottoMoney(2000)).getLottoNumbers();

		assertThat(lottoNumbers.get(0))
				.isNotEqualTo(lottoNumbers.get(1));
	}

	@Test
	@DisplayName("로또 수동 포함한 테스트")
	void LottoPassiveTest() {
		List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6);
		LottoBuyer buyer = new LottoBuyer();
		PurchasedLottoNumbersDto lottoNumbers = buyer.buyLottoNumbers(new LottoMoney(4000),
				Arrays.asList(new LottoNumberDto(numberList),
						new LottoNumberDto(numberList),
						new LottoNumberDto(numberList)));

		assertThat(lottoNumbers.getAutoCount())
				.isEqualTo(1);

		assertThat(lottoNumbers.getPassiveCount())
				.isEqualTo(3);

		assertThat(lottoNumbers.getLottoNumbers())
				.anyMatch(number->number.getNumbers().equals(numberList))
				.hasSize(4);
	}

}
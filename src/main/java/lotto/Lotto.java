package lotto;

import lotto.domain.LottoBuyer;
import lotto.domain.LottoChecker;
import lotto.domain.LottoWinningNumber;
import lotto.domain.dto.LottoNumbers;
import lotto.ui.InputView;
import lotto.ui.OutputView;

import java.util.List;

public class Lotto {

	public void start() {
		InputView inputView = InputView.getInstance();
		OutputView outputView = OutputView.getInstance();

		LottoBuyer lottoBuyer = new LottoBuyer();
		long spentMoney = inputView.getSpentMoney();
		List<LottoNumbers> numbers = lottoBuyer.buyLottoNumbers(spentMoney);
		outputView.printLottoNumbers(numbers);

		LottoWinningNumber winNumber = inputView.getWinningLastWeekNumber();
		LottoChecker lottoChecker = new LottoChecker(winNumber, spentMoney);

		outputView.printStatistics(lottoChecker.getWinningResult(numbers));
	}

	public static void main(String[] args) {
		new Lotto().start();
	}
}

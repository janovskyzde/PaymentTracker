package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Locale;

public class Exchange {
	static HashMap<String, Double> exchangeRates = new HashMap<String, Double>();
	static {
		exchangeRates.put("HKD", 0.12874);
		exchangeRates.put("RMB", 0.1573);
	}
	public static final String DEST_CURR = "USD";

	public static BigDecimal recalculate(Payment payment) {
		Double rate = exchangeRates.get(payment.getCurrency());
		if (rate == null) {
			return null;
		}
		return payment.getAmount().multiply(new BigDecimal(rate)).setScale(2,
				RoundingMode.HALF_UP);
	};

	public static String format(Payment acc) {
		String result = String.format(Locale.US, "%s %.0f", acc.getCurrency(), acc.getAmount().doubleValue());
		BigDecimal usdAmount = recalculate(acc);
		if (usdAmount != null && !acc.getCurrency().equals(DEST_CURR)) {
			result = result + String.format(Locale.US, " (%s %.2f)", DEST_CURR, usdAmount.doubleValue());
		}
		return result;
	}
}

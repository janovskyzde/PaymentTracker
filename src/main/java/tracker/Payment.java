package tracker;

import java.math.BigDecimal;

public class Payment {
	private String currency;
	private BigDecimal amount = new BigDecimal(0);

	public String getCurrency() {
		return currency;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Payment(String line) {
		currency = line.substring(0, 3).toUpperCase();
		try {
			String amPart = line.substring(4);
			amount = BigDecimal.valueOf(Double.parseDouble(amPart));
		} catch (NumberFormatException e) {
			amount = new BigDecimal(0);
		}
	}

	public Payment add(Payment x) {
		if (x != null) {
			amount = amount.add(x.amount);
		}
		return this;
	}
}

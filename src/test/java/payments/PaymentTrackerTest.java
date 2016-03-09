package payments;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tracker.PaymentTracker;

public class PaymentTrackerTest {

	@Test
	public void threeValidCurrencies() {

		// tested class
		PaymentTracker tester = new PaymentTracker();

		tester.track(getClass().getClassLoader().getResourceAsStream(
				"test-input.txt"));

		// assert statements
		assertEquals("3 valid currencies", 3, tester.getPayments().size());
		assertEquals("sum of payments in USD: 900", 900, tester.getPayments().get("USD").getAmount().longValue());
	}
}

package tracker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaymentTracker {

	private HashMap<String, Payment> payments = new HashMap<String, Payment>();

	public HashMap<String, Payment> getPayments() {
		return payments;
	}

	private void list() {
		for (String curr : payments.keySet()) {
			Payment acc = payments.get(curr);
			if (acc.getAmount().doubleValue() != 0) {
				System.out.println(Exchange.format(acc));
			}
		}
	}

	public void track(InputStream fis) {
		// InputStream fis = null;
		try {

			// fis = new FileInputStream(filename);
			if (fis == null || (fis.available() == 0)) {
				fis = System.in;
				System.out.println("Enter lines - currency amount");
				System.out.println("Example: USD 1000");
				System.out.println("Enter 'quit' to quit.");
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String str;
			do {
				str = br.readLine();
				if (str != null && str.length() > 3) {
					Payment acc = new Payment(str);
					Payment old = payments.get(acc.getCurrency());
					acc.add(old);
					payments.put(acc.getCurrency(), acc);

				}
			} while (str != null && !str.equals("quit"));
		} catch (IOException e) {

		}

	}

	public static void main(String[] args) {
		final PaymentTracker tracker = new PaymentTracker();
		ScheduledExecutorService scheduler = Executors
				.newSingleThreadScheduledExecutor();
		Runnable periodicTask = new Runnable() {
			public void run() {
				tracker.list();
			}
		};
		scheduler.scheduleAtFixedRate(periodicTask, 0, 10, TimeUnit.SECONDS);

		InputStream is = null;
		if (args.length > 0) {
			try {
				is = new FileInputStream(args[0]);
			} catch (FileNotFoundException e) {
			}
		}
		tracker.track(is);

		tracker.list();
		System.out.println("FINISHED.");
		scheduler.shutdown();
	}

}

package thread;

import java.util.HashMap;
import java.util.Map;

public class DecreaseLockRange {

	private Map<String, String> map = new HashMap<String, String>();

	// 减少前：
	public synchronized void test() throws InterruptedException {

		Thread.sleep(123);

		String value = map.get("");

		Thread.sleep(333);

	}

	// 减少后：
	public void testOpt() throws InterruptedException {

		Thread.sleep(123);

		synchronized (map) {

			String value = map.get("");

		}

		Thread.sleep(333);

	}

}

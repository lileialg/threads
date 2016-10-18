package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptedTest2 {

	public void test() throws InterruptedException {
		int sum = 0;

		while (true) {

			for (int i = 0; i < Integer.MAX_VALUE; i++)
				sum += 1;

			Thread.sleep(1);

			System.out.println(System.currentTimeMillis());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final InterruptedTest2 it = new InterruptedTest2();
		
		ExecutorService exec = Executors.newFixedThreadPool(1);
		
		Future<?> future = exec.submit(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					it.test();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
			}
		});
		
		exec.shutdown();
		
		System.out.println("start await--->"+System.currentTimeMillis());
		
		try {
			exec.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("end await--->"+System.currentTimeMillis());
		
		
		future.cancel(true);
		
		
	}

}

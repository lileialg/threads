package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Serial2ParallelApp1 {

	public static volatile boolean flag = true;

	public static class MyObj {

		private AtomicLong al = new AtomicLong();

		public long get() {
			return al.get();
		}

		public synchronized void increase() {
			al.incrementAndGet();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		final MyObj mo = new MyObj();

		ExecutorService exec = Executors.newFixedThreadPool(1);
		
		exec.execute(new Runnable() {

			public void run() {
				
				while(flag){
					mo.increase();
				}
				
			}
		});
		
		exec.shutdown();

		Thread.sleep(4000);

		flag = false;

		System.out.println(mo.get()); //Êä³ö150471644
		
		/**********************************************************/
		
		final MyObj mo2 = new MyObj();
		
		flag = true;
		
		exec = Executors.newFixedThreadPool(4);
		
		for(int i=0;i<4;i++){
		
		exec.execute(new Runnable() {

			public void run() {
				
				while(flag){
					mo2.increase();
				}
				
			}
		});
		
		}
		
		exec.shutdown();

		Thread.sleep(4000);

		flag = false;

		System.out.println(mo2.get()); //Êä³ö150471644
		
		

	}

}

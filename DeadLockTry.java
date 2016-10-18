package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final Lock lock1 = new ReentrantLock();
		
		final Lock lock2 = new ReentrantLock();
		
		ExecutorService exec = Executors.newFixedThreadPool(2);
		
		exec.submit(new Runnable(){
			public void run(){
				
				if (lock1.tryLock()){
					System.out.println(Thread.currentThread().getName()+" lock1 locked");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" try to lock2 to lock");
					if (lock2.tryLock()){
						System.out.println(Thread.currentThread().getName()+" lock2 lock");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						lock1.unlock();
						lock2.unlock();
					}else{
						System.out.println(Thread.currentThread().getName()+" lock2 not locked");
						lock1.unlock();
					}
				}
				
			}
		});
		
		
		exec.submit(new Runnable(){
			public void run(){
				
				if (lock2.tryLock()){
					System.out.println(Thread.currentThread().getName()+" lock2 locked");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" try to lock1 to lock");
					if (lock2.tryLock()){
						System.out.println(Thread.currentThread().getName()+" lock1 lock");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						lock1.unlock();
						lock2.unlock();
					}else{
						System.out.println(Thread.currentThread().getName()+" lock1 not locked");
						lock1.unlock();
					}
				}
				
			}
		});
		
		exec.shutdown();
	}

}

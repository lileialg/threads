package ci;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadInterruptedOK {
	private static volatile boolean flag = false;
	
	public static class SleepApp implements Runnable{
		
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(9999999);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
			}
			
			while(true){
				System.out.println("------------");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static class WaitApp implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			synchronized(this){
				try {
					this.wait();
					
					
					System.out.println("&&&&&&&&&&&&&&&");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
				}
			}
		}
	}
	
	public static class JoinApp implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Thread t = new Thread(new JoinSubApp(),"SUBJOIN");
			
			t.start();
			
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
			}
			
			System.out.println("***********************");
			
			while(true){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
				}
			}
			
		}
	}
	
	public static class JoinSubApp implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean ok = true;
			while(ok){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
				}
			}
			
			System.out.println("=====start sleep");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
			}
			System.out.println("=====woke up");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService exec = Executors.newFixedThreadPool(3);
		
		SleepApp sleep = new SleepApp();
		
		WaitApp wait = new WaitApp();
		
		JoinApp join = new JoinApp();
		
		Future<?> f1 = exec.submit(sleep);
		Future<?> f2 = exec.submit(wait);
		Future<?> f3 = exec.submit(join);
		exec.shutdown();
		
		
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()){
			
			String line = scanner.nextLine();
			
			if ("exit".equals(line)){
				f1.cancel(true);
				f2.cancel(true);
				f3.cancel(true);
				flag = true;
				break;
			}
		}
		
		scanner.close();
		
		
		
	}

}

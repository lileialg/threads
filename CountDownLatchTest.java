package com.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountDownLatchTest {

	/**
	 * 个人理解：CountDownLatch的作用是对join的扩展
	 * join是优先执行等待主线程的子线程
	 * countDownLatch是等待非子线程
	 */
	
	public static class Cdt implements Runnable{
		
		private CountDownLatch latch;
		
		private int id;
		
		public Cdt(CountDownLatch latch,int id){
			this.latch = latch;
			this.id = id;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			
			System.out.println(this.id+" starting...");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(this.id+" complete...");
			
			latch.countDown();
			
		}
	}
	
	public static class Sleeper implements Callable<String>{
		
		private CountDownLatch latch;
		
		public Sleeper(CountDownLatch latch){
			this.latch = latch;
		}

		public String call() throws Exception {

			System.out.println("sleeper starting...");
			
			Thread.sleep(1000);
			
			System.out.println("sleeper sleep over,starting await...");
			
			latch.await();
			
			System.out.println("sleeper over...");
			
			return "OK";
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		CountDownLatch latch = new CountDownLatch(5);
		
		
		
		for(int i=1;i<=5;i++){
			service.submit(new Cdt(latch,i));
		}
		
		Future<String> future = service.submit(new Sleeper(latch));
		
		service.shutdown();
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

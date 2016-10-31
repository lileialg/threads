package com.chapter8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserDefineThreadPoolApp {

	public static class MyThreadPool extends ThreadPoolExecutor {

		public MyThreadPool(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			System.out.println(Thread.currentThread().getName()+"-----开始执行任务:"+t.getName());
			super.beforeExecute(t, r);
		}
		
		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			// TODO Auto-generated method stub
			System.out.println("执行完成"+Thread.currentThread().getName());
			super.afterExecute(r, t);
		}
		
		
		@Override
		protected void terminated() {
			// TODO Auto-generated method stub
			//只会执行一次，如果
			System.out.println(Thread.currentThread().getName()+"******TERMINATED********");
			super.terminated();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService exec = new MyThreadPool(5, 5, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));
		
		for(int i=0;i<10;i++){
			exec.execute(new Runnable(){
				
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
					}
				}
				
			});
		}
		
//		exec.shutdown();
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exec.shutdownNow();
	}

}

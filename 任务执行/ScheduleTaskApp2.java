package com.threads;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTaskApp2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(3);
		
		System.out.println(Thread.currentThread().getName()+"-"+new Date());
		
		exec.schedule(new Callable<Object>(){
			
			public Object call() {
				System.out.println("start "+Thread.currentThread().getName()+"-"+new Date());
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("end "+Thread.currentThread().getName()+"-"+new Date());

				return null;
			}
			
		}, 5, TimeUnit.SECONDS);
		
		
		exec.shutdown();
	}

}

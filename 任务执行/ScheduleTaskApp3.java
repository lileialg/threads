package com.threads;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTaskApp3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(29);
		
		System.out.println(Thread.currentThread().getName()+"-"+new Date());
		
		exec.scheduleAtFixedRate(new Runnable(){
//		exec.scheduleWithFixedDelay(new Runnable(){
			public void run() {
				System.out.println("start "+Thread.currentThread().getName()+"-"+new Date());
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("end "+Thread.currentThread().getName()+"-"+new Date());

			}
			
		}, 2,5, TimeUnit.SECONDS);
		
		
//		exec.shutdown();
	}

}

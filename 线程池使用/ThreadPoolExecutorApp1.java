package com.chapter8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorApp1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(1);
		
		ExecutorService exec = new ThreadPoolExecutor(1,2,60l,TimeUnit.SECONDS,workQueue,
				 Executors.defaultThreadFactory(),new AbortPolicy());
		
		//如果提交线程数>(maximumPoolSize + workQueue.size())则会触发 RejectedExecutionHandler 
		for(int i=0;i<4;i++){
			exec.execute(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
				
					try {
						Thread.sleep(500000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		
		try {
			 exec.awaitTermination(2, TimeUnit.SECONDS);
			
			System.out.println(workQueue.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

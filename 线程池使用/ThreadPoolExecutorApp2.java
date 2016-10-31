package com.chapter8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorApp2 {
	
	/**
	 * 
	ThreadPoolExecutor.AbortPolicy()抛出java.util.concurrent.RejectedExecutionException异常 终止策略是默认的饱和策略；
	ThreadPoolExecutor.CallerRunsPolicy()当抛出RejectedExecutionException异常时，会调rejectedExecution方法 调用者运行策略实现了一种调节机制，该策略既不会抛弃任务也不会爆出异常，而是将任务退回给调用者，从而降低新任务的流量
	ThreadPoolExecutor.DiscardOldestPolicy()抛弃旧的任务；当新提交的任务无法保存到队列中等待执行时将抛弃最旧的任务，然后尝试提交新任务。如果等待队列是一个优先级队列，抛弃最旧的策略将导致抛弃优先级最高的任务，因此AbortPolicy最好不要和优先级队列一起使用。
	ThreadPoolExecutor.DiscardPolicy()抛弃当前的任务
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(1);
		
		RejectedExecutionHandler handler1 = new AbortPolicy();
		
		RejectedExecutionHandler handler2 = new CallerRunsPolicy();
		
		RejectedExecutionHandler handler3 = new DiscardOldestPolicy();
		
		RejectedExecutionHandler handler4 = new DiscardPolicy();
		
		ExecutorService exec = new ThreadPoolExecutor(1,2,60l,TimeUnit.SECONDS,workQueue,
				 Executors.defaultThreadFactory(),handler3);
		
		//如果提交线程数>(maximumPoolSize + workQueue.size())则会触发 RejectedExecutionHandler 
		for(int i=0;i<5;i++){
			
			//如果为CallerRunsPolicy，任务超过指定数，则会在此等待
			exec.execute(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
				
					try {
						Thread.sleep(10000);
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
			
			exec.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

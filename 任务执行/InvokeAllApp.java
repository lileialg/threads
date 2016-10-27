package com.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InvokeAllApp {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		List<Callable<String>> tasks = new ArrayList<Callable<String>>();
		
		for(int i=0;i<4;i++){
			tasks.add(new Callable<String>(){

				public String call() throws Exception {
					// TODO Auto-generated method stub
					boolean flag = true;
					
					while(flag){
						//只有是sleep才能终止
						Thread.sleep(9999999);
						
					}
					
					return null;
				}
				
				
			});
		}
		
		ExecutorService exec = Executors.newFixedThreadPool(4);
		
		List<Future<String>> result = exec.invokeAll(tasks,5,TimeUnit.SECONDS);
		
		
		exec.shutdown();
		System.out.println(result.size());
		
	}

}

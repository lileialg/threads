package com.threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService exec = Executors.newFixedThreadPool(10);
		
		CompletionService<Integer> compService = new ExecutorCompletionService<Integer>(exec);
		
		for(int i=0;i<10;i++){
			
			compService.submit(new Callable<Integer>(){
				
				public Integer call() throws Exception {

					Random rand = new Random();
					
					int s = rand.nextInt(100000);
					
					Thread.sleep(s);
					
					return s;
				}
			});
			
		}
		
		exec.shutdown();
		
		for(int i=0;i<10;i++){
			
			try {
				Future<Integer> future = compService.take();
				
				try {
					System.out.println(future.get());
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}

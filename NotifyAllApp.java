package com.threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotifyAllApp {

	public static void main(String[] args) {
		
		final Object obj = new Object();
		
		Thread t1 = new Thread(){
			
			public void run(){
				
				synchronized(obj){
					Scanner scanner = new Scanner(System.in);
					
					while(scanner.hasNextLine()){
						String line = scanner.nextLine();
						System.out.println(line);
						if ("stop".equals(line)){
							obj.notifyAll();
							break;
						}
					}
				}
				
			}
		};
		
		t1.start();
		
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		for(int i=1;i<6;i++){
			service.execute(new Runnable(){
				
				public void run(){
					synchronized(obj){
						System.out.println("ok.."+Thread.currentThread().getName());
					}
					
					
				}
			});
		}
		
		service.shutdown();
		
		
		
	}
}

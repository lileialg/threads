package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {

	public void producer(){
		
		synchronized (this) {
			System.out.println("produce starting ...");
			try {
				Thread.sleep(20000);

				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("produce resume");
			
			try {
				Thread.sleep(20000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void consumer(){
	
		synchronized(this){
			System.out.println("consume starting...");
			
			try {
				Thread.sleep(50000);
				
				
				
				notify();
				
				System.out.println("consume over");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		final ProducerConsumer pc = new ProducerConsumer();
		
		service.execute(new Runnable(){
			
			public void run(){
				pc.producer();
			}
			
		});
		
		service.execute(new Runnable(){
			
			public void run(){
				pc.consumer();
			}
			
		});
		
		service.shutdown();
		
	}
	
}

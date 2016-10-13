package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyApp {
	
	/**
	 * 1）wait()、notify()和notifyAll()方法是本地方法，并且为final方法，无法被重写。

　　2）调用某个对象的wait()方法能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）

　　3）调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程；

　　4）调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程；
		
		为何这三个不是Thread类声明中的方法，
		而是Object类中声明的方法（当然由于Thread类继承了Object类，
		所以Thread也可以调用者三个方法）？
		由于每个对象都拥有monitor（即锁），
		所以让当前线程等待某个对象的锁，
		当然应该通过这个对象来操作了。
		而不是用当前线程来操作，
		因为当前线程可能会等待多个线程的锁，
		如果通过线程来操作，就非常复杂了
	 */

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
			System.out.println(System.currentTimeMillis()+"produce resume");
			
			while(true){}
		}
	}
	
	public void consumer(){
	
		synchronized(this){
			System.out.println("consume starting...");
			
			try {
				Thread.sleep(5000);
				
				
				notify();
				
				wait();
				
				
				System.out.println(System.currentTimeMillis()+"consume over");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		final WaitNotifyApp pc = new WaitNotifyApp();
		
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

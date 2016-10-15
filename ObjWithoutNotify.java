package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程在wait后，如果没有进行notify就会永远的等待下去
 * @author lilei3774
 *
 */

public class ObjWithoutNotify {
	
	public void mywait(){
		
		synchronized(this){
			
			System.out.println("wait starting...");
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("wait end!!!");
			
		}
		
	}
	
	public void withoutNotify(){
		
		synchronized (this){
			
			try {
				System.out.println("sleep starting...");
				Thread.sleep(5000);
				System.out.println("sleep end!!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final ObjWithoutNotify on = new ObjWithoutNotify();
		
//		ExecutorService exec = Executors.newFixedThreadPool(2);
//		
//		exec.execute(new Runnable(){
//			
//			public void run() {
//				// TODO Auto-generated method stub
//				on.mywait();
//			}
//		});
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		exec.execute(new Runnable(){
//			
//			public void run() {
//				// TODO Auto-generated method stub
//			
//				on.withoutNotify();
//			}
//		});
//		
//		exec.shutdown();
		
		Thread t1 = new Thread(){
			
			public void run(){
				on.mywait();
			}
		};
		
		t1.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread t2 = new Thread(){
			public void run(){
				on.withoutNotify();
			}
			
		};
		
		t2.start();
	}

}

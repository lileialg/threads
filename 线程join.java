package com.threads;

public class ThreadJoin {
	
	/*
	 * thread.Join把指定的线程加入到当前线程，
	 * 可以将两个交替执行的线程合并为顺序执行的线程。
	 * 比如在线程B中调用了线程A的Join()方法，
	 * 直到线程A执行完毕后，才会继续执行线程B。
	 */

	public static class Join1 implements Runnable
	{
		
		public void run() {
			// TODO Auto-generated method stub
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+" is over!!!");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Join1());
		
		t1.start();
		
		/**
		 * join放在start之后执行
		 */
		
		
		t1.join();
		
		System.out.println(Thread.currentThread().getName()+" is starting...");
		
	}

}

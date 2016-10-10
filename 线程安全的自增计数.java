package com.threads;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;

public class IncreaseNum {

	/**
	 * 数字递增，在多线程安全中，要实现有两种方式： 1、添加同步块synchronized 2、使用Atomic
	 */

	private int num;

	public static AtomicInteger num2 = new AtomicInteger();

	public synchronized int getNum() {
		return num;
	}

	public synchronized void setNum() {
		this.num++;
	}

	public static class ThreadSafe1 implements Runnable {

		private IncreaseNum in;

		private int cnt;

		public ThreadSafe1(IncreaseNum in, int cnt) {
			this.in = in;
			this.cnt = cnt;
		}

		public void run() {
			// TODO Auto-generated method stub

			for (int i = 0; i < cnt; i++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in.setNum();
			}
		}
	}

	public static class ThreadSafe2 implements Runnable {
		private int cnt;

		public ThreadSafe2(int cnt) {
			this.cnt = cnt;
		}

		public void run() {

			for (int i = 0; i < cnt; i++) {

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {

				}

				num2.incrementAndGet();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		
		int cnt = 1000;

//		IncreaseNum in = new IncreaseNum();
//
//		
//
//		Thread t1 = new Thread(new ThreadSafe1(in, cnt));
//		Thread t2 = new Thread(new ThreadSafe1(in, cnt));
//
//		t1.start();
//
//		t2.start();
//
//		t1.join();
//
//		t2.join();
//
//		Assert.assertEquals(in.getNum(), cnt * 2);
		
		Thread t3 = new Thread(new ThreadSafe2( cnt));
		Thread t4 = new Thread(new ThreadSafe2( cnt));

		t3.start();

		t4.start();

		t3.join();

		t4.join();

		Assert.assertEquals(num2.get(), cnt * 2);
		

	}

}

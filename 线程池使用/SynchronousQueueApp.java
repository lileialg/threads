package com.chapter8;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueApp {

	public static class Producer implements Runnable {

		private SynchronousQueue<Integer> queue;

		public Producer(SynchronousQueue<Integer> queue) {
			// TODO Auto-generated constructor stub
			this.queue = queue;
		}

		public void run() {
			// TODO Auto-generated method stub
			Random rand = new Random();

			while (!Thread.currentThread().isInterrupted()) {
				try {
					int value = rand.nextInt();
					
					Thread.sleep(2000);

					System.out.println("生产者添加:" + value);

					queue.put(value);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					
					return;
				}
			}

		}
	}

	public static class Consumer implements Runnable {

		private SynchronousQueue<Integer> queue;

		public Consumer(SynchronousQueue<Integer> queue) {
			// TODO Auto-generated constructor stub
			this.queue = queue;
		}

		public void run() {
			// TODO Auto-generated method stub

			while (!Thread.currentThread().isInterrupted()) {
				try {
					int value = queue.take();

					System.out.println("消费者取到:" + value);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				
					return;
				}
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SynchronousQueue<Integer> sq = new SynchronousQueue<Integer>();

		ExecutorService exec = new ThreadPoolExecutor(2, 2, 60L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		Future<?> f1 = exec.submit(new Producer(sq));
		
		Future<?> f2 = exec.submit(new Consumer(sq));
		
		exec.shutdown();
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()){
			if ("exit".equals(scanner.nextLine())){
				f1.cancel(true);
				f2.cancel(true);
				scanner.close();
				break;
			}
		}

	}

}

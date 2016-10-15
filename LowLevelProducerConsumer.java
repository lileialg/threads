package com.threads;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LowLevelProducerConsumer {

	private Deque<Integer> deq = new LinkedList<Integer>();

	private Object objLock = new Object();

	Random rand = new Random();

	public void produce() {

		while (true) {

			synchronized (objLock) {

				if (deq.size() == 10) {
					try {
						objLock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					deq.add(rand.nextInt(100));

					objLock.notify();

				}
			}

		}

	}

	public void consumer() {

		while (true) {

			synchronized (objLock) {//监视状态

				if (deq.size() < 1) {
					try {
						objLock.wait();//等待状态
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					System.out.print("隊列大小:" + deq.size());

					int value = deq.removeLast();

					System.out.println(",彈出值為：" + value);

					objLock.notify();

				}

			}

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final LowLevelProducerConsumer pc = new LowLevelProducerConsumer();

		ExecutorService service = Executors.newCachedThreadPool();

		service.execute(new Runnable() {

			public void run() {
				pc.produce();
			}
		});

		service.execute(new Runnable() {

			public void run() {
				pc.consumer();
			}
		});

		service.shutdown();

	}

}

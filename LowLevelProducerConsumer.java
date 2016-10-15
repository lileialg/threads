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

			synchronized (objLock) {//����״̬

				if (deq.size() < 1) {
					try {
						objLock.wait();//�ȴ�״̬
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					System.out.print("��д�С:" + deq.size());

					int value = deq.removeLast();

					System.out.println(",����ֵ�飺" + value);

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

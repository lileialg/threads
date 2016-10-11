package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedCodeBlock {
	
	/**
	 * 在同步时，如果多个方法不需要同时锁定，
	 * 使用同步代码块要比同步方法效率高
	 * 
	 * start......
		2784
		list1.size=2000,list2.size=2000
	 */

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private Random rand = new Random();

	// public synchronized void stage1() {
	//
	// try {
	// Thread.sleep(1);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// list1.add(rand.nextInt());
	// }

	public void stage1() {

		synchronized (lock1) {

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list1.add(rand.nextInt());

		}
	}

//	public synchronized void stage2() {
//
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		list2.add(rand.nextInt());
//	}
	
	public void stage2() {

		synchronized (lock2) {

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list2.add(rand.nextInt());

		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stage1();
			stage2();
		}
	}

	public void main() {

		System.out.println("start......");

		long start = System.currentTimeMillis();

		Thread t1 = new Thread() {

			public void run() {
				process();
			}
		};

		Thread t2 = new Thread() {

			public void run() {
				process();
			}
		};

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(System.currentTimeMillis() - start);

		System.out.println("list1.size=" + list1.size() + ",list2.size=" + list2.size());
	}

	public static void main(String[] args) {

		SynchronizedCodeBlock scb = new SynchronizedCodeBlock();

		scb.main();

	}

}

package com.threads;

import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class ExchangeApp {

	/**
	 * Exchanger可以在两个线程之间交换数据， 只能是2个线程，他不支持更多的线程之间互换数据。
	 * 
	 * 当线程A调用Exchange对象的exchange()方法后， 他会陷入阻塞状态，直到线程B也调用了exchange()方法，
	 * 然后以线程安全的方式交换数据，之后线程A和B继续运行
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Exchanger<String> ex = new Exchanger<String>();

		Thread ta = new Thread(new A(ex));
		
		ta.start();
		
		Thread tb = new Thread(new B(ex));
		
		tb.start();
	}

	public static class A implements Runnable {

		private Exchanger<String> ex;

		public A(Exchanger<String> ex) {
			this.ex = ex;
		}

		public void run() {
			// TODO Auto-generated method stub

			Scanner scanner = new Scanner(System.in);

			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				try {
					ex.exchange(line);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public static class B implements Runnable {

		private Exchanger<String> ex;
		
		private String line;

		public B(Exchanger<String> ex) {
			this.ex = ex;
		}

		public void run() {
			// TODO Auto-generated method stub

			while(true){
				try {
					System.out.println("B--->"+ex.exchange(line));
					System.out.println(line); //null
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}

package com.threads;

import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class ExchangeApp {

	/**
	 * Exchanger�����������߳�֮�佻�����ݣ� ֻ����2���̣߳�����֧�ָ�����߳�֮�以�����ݡ�
	 * 
	 * ���߳�A����Exchange�����exchange()������ ������������״̬��ֱ���߳�BҲ������exchange()������
	 * Ȼ�����̰߳�ȫ�ķ�ʽ�������ݣ�֮���߳�A��B��������
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

package com.threads;

public class ThreadJoin {
	
	/*
	 * thread.Join��ָ�����̼߳��뵽��ǰ�̣߳�
	 * ���Խ���������ִ�е��̺߳ϲ�Ϊ˳��ִ�е��̡߳�
	 * �������߳�B�е������߳�A��Join()������
	 * ֱ���߳�Aִ����Ϻ󣬲Ż����ִ���߳�B��
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
		 * join����start֮��ִ��
		 */
		
		
		t1.join();
		
		System.out.println(Thread.currentThread().getName()+" is starting...");
		
	}

}

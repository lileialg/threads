package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalApp {
	
	/**
	 * ��ʹ��ThreadLocalά������ʱ��
	 * ThreadLocalΪÿ��ʹ�øñ������߳��ṩ�����ı���������
	 * ����ÿһ���̶߳����Զ����ظı��Լ��ĸ�����
	 * ������Ӱ�������߳�����Ӧ�ĸ�����
	 * ��JDK5.0�У�ThreadLocal�Ѿ�֧�ַ��ͣ�����������Ѿ���ΪThreadLocal<T>
	 * @author lilei3774
	 *
	 */
	
	public static class MyClient{
		
//		private int tl = 0;
		
		private ThreadLocal<Integer> tl = new ThreadLocal<Integer>(){
			
			@Override
			protected Integer initialValue() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		public int getNext(){
			tl.set(tl.get()+1);
			return tl.get();
		}
		
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final MyClient mc = new MyClient();

		ExecutorService exec = Executors.newFixedThreadPool(10);
		
		for(int i=0;i<10;i++){
			exec.execute(new Runnable(){
				
				public void run() {
					// TODO Auto-generated method stub
					
					for(int i=0;i<10;i++)
						mc.getNext();
					
					System.out.println(Thread.currentThread().getName()+"--"+mc.getNext());
					
				}
				
			});
		}
		
		exec.shutdown();
	}

}

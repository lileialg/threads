package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DiffOfExecute2Submit {

	/**
	 * execute(Runnable x) û�з���ֵ��
	 * ����ִ������
	 * ���޷��ж������Ƿ�ɹ���ɡ�
	 * 
	 * submit(Runnable x) ����һ��future��
	 * ���������future���ж������Ƿ�ɹ����
	 */
	
	public static class Run implements Runnable{
		
		
		public void run(){
			
			System.out.println(Thread.currentThread().getName()+" starting...");
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+" completing...");
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
//		ExecutorService service = Executors.newFixedThreadPool(10);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		Future future = service.submit(new Run());
		
		service.execute(new Run());//void�޷���ֵ
		
		service.shutdown();
		
		while(!future.isDone()){
			Thread.sleep(500);
			
			System.out.println("still running ...");
		}
		
		System.out.println(future.get()+"***");
		
	}
}

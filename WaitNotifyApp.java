package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyApp {
	
	/**
	 * 1��wait()��notify()��notifyAll()�����Ǳ��ط���������Ϊfinal�������޷�����д��

����2������ĳ�������wait()�������õ�ǰ�߳����������ҵ�ǰ�̱߳���ӵ�д˶����monitor��������

����3������ĳ�������notify()�����ܹ�����һ�����ڵȴ���������monitor���̣߳�����ж���̶߳��ڵȴ���������monitor����ֻ�ܻ�������һ���̣߳�

����4������notifyAll()�����ܹ������������ڵȴ���������monitor���̣߳�
		
		Ϊ������������Thread�������еķ�����
		����Object���������ķ�������Ȼ����Thread��̳���Object�࣬
		����ThreadҲ���Ե�����������������
		����ÿ������ӵ��monitor����������
		�����õ�ǰ�̵߳ȴ�ĳ�����������
		��ȻӦ��ͨ����������������ˡ�
		�������õ�ǰ�߳���������
		��Ϊ��ǰ�߳̿��ܻ�ȴ�����̵߳�����
		���ͨ���߳����������ͷǳ�������
	 */

	public void producer(){
		
		synchronized (this) {
			System.out.println("produce starting ...");
			try {
				Thread.sleep(20000);

				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+"produce resume");
			
			while(true){}
		}
	}
	
	public void consumer(){
	
		synchronized(this){
			System.out.println("consume starting...");
			
			try {
				Thread.sleep(5000);
				
				
				notify();
				
				wait();
				
				
				System.out.println(System.currentTimeMillis()+"consume over");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		final WaitNotifyApp pc = new WaitNotifyApp();
		
		service.execute(new Runnable(){
			
			public void run(){
				pc.producer();
			}
			
		});
		
		service.execute(new Runnable(){
			
			public void run(){
				pc.consumer();
			}
			
		});
		
		service.shutdown();
		
	}
	
}

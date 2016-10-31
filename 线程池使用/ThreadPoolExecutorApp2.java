package com.chapter8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorApp2 {
	
	/**
	 * 
	ThreadPoolExecutor.AbortPolicy()�׳�java.util.concurrent.RejectedExecutionException�쳣 ��ֹ������Ĭ�ϵı��Ͳ��ԣ�
	ThreadPoolExecutor.CallerRunsPolicy()���׳�RejectedExecutionException�쳣ʱ�����rejectedExecution���� ���������в���ʵ����һ�ֵ��ڻ��ƣ��ò��ԼȲ�����������Ҳ���ᱬ���쳣�����ǽ������˻ظ������ߣ��Ӷ����������������
	ThreadPoolExecutor.DiscardOldestPolicy()�����ɵ����񣻵����ύ�������޷����浽�����еȴ�ִ��ʱ��������ɵ�����Ȼ�����ύ����������ȴ�������һ�����ȼ����У�������ɵĲ��Խ������������ȼ���ߵ��������AbortPolicy��ò�Ҫ�����ȼ�����һ��ʹ�á�
	ThreadPoolExecutor.DiscardPolicy()������ǰ������
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(1);
		
		RejectedExecutionHandler handler1 = new AbortPolicy();
		
		RejectedExecutionHandler handler2 = new CallerRunsPolicy();
		
		RejectedExecutionHandler handler3 = new DiscardOldestPolicy();
		
		RejectedExecutionHandler handler4 = new DiscardPolicy();
		
		ExecutorService exec = new ThreadPoolExecutor(1,2,60l,TimeUnit.SECONDS,workQueue,
				 Executors.defaultThreadFactory(),handler3);
		
		//����ύ�߳���>(maximumPoolSize + workQueue.size())��ᴥ�� RejectedExecutionHandler 
		for(int i=0;i<5;i++){
			
			//���ΪCallerRunsPolicy�����񳬹�ָ����������ڴ˵ȴ�
			exec.execute(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
				
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		
		try {
			 exec.awaitTermination(2, TimeUnit.SECONDS);
			
			System.out.println(workQueue.size());
			
			exec.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

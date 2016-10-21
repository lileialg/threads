package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalApp {
	
	/**
	 * 当使用ThreadLocal维护变量时，
	 * ThreadLocal为每个使用该变量的线程提供独立的变量副本，
	 * 所以每一个线程都可以独立地改变自己的副本，
	 * 而不会影响其它线程所对应的副本。
	 * 在JDK5.0中，ThreadLocal已经支持泛型，该类的类名已经变为ThreadLocal<T>
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

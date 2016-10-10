package com.threads;

public class DeadLockTest {

	public static String str1 = "ABC";
	
	public static String str2 = "BCD";
	
	public static class DeadThread implements Runnable
	{
		private String str1;
		
		private String str2;
		
		public DeadThread(String str1,String str2){
			this.str1 = str1;
			this.str2 = str2;
		}
		
		
		public void run(){
			
			synchronized(str1){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(str2){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) {

		
		Thread t1 = new Thread(new DeadThread(str1,str2));
		
		Thread t2 = new Thread(new DeadThread(str2,str1));
		
		t1.start();
		
		t2.start();
	}

}

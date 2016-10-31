package com.chapter8;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueApp {
	
	public static class MyData implements Comparable<MyData>{
		
		private int value;
		public MyData(int value) {
			// TODO Auto-generated constructor stub
		
			this.value = value;
		}
		
		

		public int getValue() {
			return value;
		}



		public int compareTo(MyData o) {
			// TODO Auto-generated method stub
			
				return -value + o.getValue();
			
		}
		
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		PriorityBlockingQueue<MyData> queue = new PriorityBlockingQueue<MyData>();
		
		for(int i=0;i<10;i++){
			queue.put(new MyData(i));
		}
		
		while(!queue.isEmpty()){
			System.out.println(queue.take().getValue());
		}
	}

}

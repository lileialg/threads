package ci;

import java.util.Scanner;

public class ThreadIsInterrupteApp1 {

	public static class CalThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			int sum = 0;

			while(!Thread.currentThread().isInterrupted()){
				sum++;
			}
			
			System.out.println("yes");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread t = new Thread(new CalThread());
		
		t.start();
		
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()	){
			if ("stop".equals(scanner.nextLine())){
				t.interrupt();
				break;
			}
		}
		
		scanner.close();
	}

}

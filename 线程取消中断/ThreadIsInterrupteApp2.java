package ci;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadIsInterrupteApp2 {

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

		
		ExecutorService exec = Executors.newSingleThreadExecutor();
		
		Future<?> future = exec.submit(new CalThread());
		
		exec.shutdown();
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()	){
			if ("stop".equals(scanner.nextLine())){
				future.cancel(true);
				break;
			}
		}
		
		scanner.close();
	}

}

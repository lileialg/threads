package ci;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCancelApp2 {
	
	
	private static volatile boolean isRun = true;
	
	public static class MyRun implements Runnable{
		
		 
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
		
			while(isRun){
				
			}
			
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService exec = Executors.newFixedThreadPool(1);
		
		exec.submit(new MyRun());
		
		exec.shutdown();
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if ("exit".equals(line)){
				isRun = false;
				
				
				break;
			}
		}
		
		scanner.close();
		
	}

}

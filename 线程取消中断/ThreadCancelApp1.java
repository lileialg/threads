package ci;

import java.util.Scanner;

public class ThreadCancelApp1 {
	
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

		Thread t = new Thread(new MyRun());
		
		t.start();
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			
			if ("exit".equals(line)){
				isRun = false;
				
				scanner.close();
				
				break;
			}
		}
	}

}

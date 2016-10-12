package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
	
	public static class C1 implements Callable<String>{

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("yeal");
			
			Thread.sleep(5000);
			
			return "ok";
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FutureTask<String> f1 = new FutureTask<String>(new C1());
		
		Thread t1 = new Thread(f1);
		
		t1.start();
		
		try {
			//等待线程执行完毕
			System.out.println(f1.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("yes");
	}

}

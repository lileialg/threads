package threads;

public class InterruptedTest {

	public void test() throws InterruptedException{
		
		int sum = 0;
		
		while(true){
			
			for(int i=0;i<Integer.MAX_VALUE;i++)
			sum +=1;
			
				Thread.sleep(1);
			
			System.out.println(System.currentTimeMillis());
		}
		
	}
	
	public static void main(String[] args) {
		
		final InterruptedTest it = new InterruptedTest();
		
		Thread t1 = new Thread(){
			
			@Override
			public void run() {
				
				try {
					it.test();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
			}
		};
		
		t1.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("call t1 interrupted,this will not work unless there is a sleep in thread...");
		
		t1.interrupt();
		
		System.out.println("call interrupted over!");
		
	}
	
}

package threads;

public class VolatileUse {
	
	private  boolean isShutdown = false;
	
	public void doWork()
	{
		new Thread(){
			public void run()
			{
				while(!isShutdown)
				{
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
					System.out.println(System.currentTimeMillis());
				}
			}
			
		}.start();
	}
	
	public void shutdown(){
		isShutdown = true;
	}
	
	
	public void main(){
		
		doWork();
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shutdown();
		
		
	}

	public static void main(String[] args) {

		VolatileUse vu = new VolatileUse();
		
		vu.main();
	}

}

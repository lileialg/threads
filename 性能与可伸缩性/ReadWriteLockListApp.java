package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockListApp {

	private List list = new ArrayList();
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private Lock writeLock = lock.writeLock();
	
	private Lock readLock = lock.readLock();
	
	public Object read(int i){
		
		try {
			System.out.println(Thread.currentThread().getName()+" read start");

			
			readLock.lock();
			
			Thread.sleep(4444);
			
			return list.get(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			readLock.unlock();
			
			System.out.println(Thread.currentThread().getName()+" read over");

		}
		
		return null;
		
	}
	
	
	public void write(Object obj){
		
		try {
			System.out.println(Thread.currentThread().getName()+" write start");
			
			writeLock.lock();
			
			Thread.sleep(5000);
			
			list.add(obj);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writeLock.unlock();
			
			System.out.println(Thread.currentThread().getName()+" write over");
		}
		
		
		
	}

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		final ReadWriteLockListApp app = new ReadWriteLockListApp();
		
		ExecutorService exec = Executors.newFixedThreadPool(2);
		
		for(int i=0;i<2;i++){
			exec.execute(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					app.write(null);
				}
				
			});
		}
		
		
		Thread.sleep(20000);
		System.out.println("***********************************************************");
		
		for(int i=0;i<2;i++){
			final int v = i;
			exec.execute(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					app.read(v);
				}
				
			});
		}
		
		exec.shutdown();
	}

}

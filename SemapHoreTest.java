package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapHoreTest {
	
	/**
	 * Semaphore当前在多线程环境下被扩放使用，
	 * 操作系统的信号量是个很重要的概念，在进程控制方面都有应用。
	 * Java 并发库 的Semaphore 可以很轻松完成信号量控制，
	 * Semaphore可以控制某个资源可被同时访问的个数，
	 * 通过 acquire() 获取一个许可，如果没有就等待，
	 * 而 release() 释放一个许可。
	 * 比如在Windows下可以设置共享文件的最大客户端访问个数。
	 * Semaphore维护了当前访问的个数，提供同步机制，控制同时访问的个数。
	 * 在数据结构中链表可以保存“无限”的节点，用Semaphore可以实现有限大小的链表。
	 * 另外重入锁 ReentrantLock 也可以实现该功能，但实现上要复杂些。 
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		final Semaphore smap = new Semaphore(10);

		ExecutorService exec = Executors.newFixedThreadPool(100);

		for (int i = 0; i < 100; i++) {
			exec.submit(new Runnable() {
				public void run() {
					
					System.out.println(Thread.currentThread().getName()+" require starting...");
					try {
						smap.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					System.out.println(Thread.currentThread().getName()+" require end,sleep starting...");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" sleep end...");
					smap.release();
					
				}
			});
		}
		
		exec.shutdown();
	}

}

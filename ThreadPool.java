package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	public static class Processor implements Runnable {

		private int i;

		public Processor(int i) {
			// TODO Auto-generated constructor stub
			this.i = i;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			System.out.println(i + " starting...");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(i + " complete!!!");

		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ExecutorService service = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			service.submit(new Processor(i));
		}

		service.shutdown();

		// while(!service.awaitTermination(1, TimeUnit.SECONDS ／／隔指定时间调用
		// )){
//		while (!service.isTerminated()) { ／／判断是否已经终止
		while (!service.isShutdown()) {  //判断是否已经提交关闭
			System.out.println("仍在运行。。。");
		}

	}

}

package com.threads;

//�����̳߳�
public class TestThreadPool {
	public static void main(String[] args) {
		// ����3���̵߳��̳߳�
		ThreadPool t = ThreadPool.getThreadPool(3);
		t.execute(new Runnable[] { new Task(), new Task(), new Task() });
		t.execute(new Runnable[] { new Task(), new Task(), new Task() });
		System.out.println(t);
		t.destroy();// �����̶߳�ִ����ɲ�destory
		System.out.println(t);
	}

	// ������
	static class Task implements Runnable {
		private static volatile int i = 1;

		
		public void run() {// ִ������
			System.out.println("���� " + (i++) + " ���");
		}
	}
}
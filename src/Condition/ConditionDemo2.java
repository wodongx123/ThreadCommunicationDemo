package Condition;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo2 {
	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	
	
	public static void main(String[] args) throws IOException {
		//用lock在不同的代码块中进行同步，传递lock对象和condition对象
		new ThreadA(0, lock, condition).start();
		new ThreadB(0, lock, condition).start();

	}

}

class ThreadA extends Thread{
	int i;
	Lock lock;
	Condition condition;
	public ThreadA(int i, Lock lock, Condition condition) {
		this.i = i;
		this.lock = lock;
		this.condition = condition;
		System.out.println(lock.toString());

	}
	
	@Override
	public void run() {
		
		lock.lock();
		try {
			for (; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + "   " + i);
				//线程A数到50就await，让B线程先跑
				if (i== 50) {
					condition.await();
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
}

class ThreadB extends Thread{
	int i;
	Lock lock;
	Condition condition;
	public ThreadB(int i, Lock lock, Condition condition) {
		this.i = i;
		this.lock = lock;
		this.condition = condition;
		System.out.println(lock.toString());
	}
	
	@Override
	public void run() {
		
		lock.lock();
		try {
			for (; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + "   " + i);
				//这边的i设为多少都不会影响结果
				if (i == 1) {
					condition.signal();
					condition.await();
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
}

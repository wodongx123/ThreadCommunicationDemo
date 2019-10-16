package Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo extends Thread{
	
	private Num num;
	
	public ConditionDemo() {
		num = new Num();
	}

	//声明为静态变量。。不然new两个新线程指不到同一个lock，或者implements Runnable
	private static Lock lock = new ReentrantLock();
	private static Condition conditon = lock.newCondition();
	
	@Override
	public void run() {
		while (num.getI() < 100) {
			lock.lock();
			try {
				num.add();
				System.out.println(Thread.currentThread().getName() + "   " + num.getI());
				conditon.signal();
				conditon.await();

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		new ConditionDemo().start();
		new ConditionDemo().start();
	}

}

class Num {
	private int i = 0;
	
	public int getI() {
		return i;
	}
	
	public void add(){
		i++;
	}
	
	public void sub() {
		if (i>0) {
			i--;
		}
	}
}

package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TakeBlockDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
		
		queue.put(1);
		System.out.print("1");

		queue.put(2);
		System.out.print("2");

		//因为已满所以3无法put，阻塞
		queue.put(3);
		System.out.print("3");

		
	}
	

}

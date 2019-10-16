package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PutBlockDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
		
		queue.put(1);
		queue.put(2);
		System.out.print(queue.take());
		System.out.print(queue.take());
		
		//因为为空，所以无法取出，阻塞
		System.out.print(queue.take());


		
	}
	

}

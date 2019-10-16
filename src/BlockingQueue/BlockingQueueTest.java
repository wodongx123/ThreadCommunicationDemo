package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		
		//使用生产者消费者模式，一个存入元素，一个取出元素
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		producer.start();
		consumer.start();
	}
}

class Producer extends Thread{
	BlockingQueue<Integer> queue;
	
	public Producer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for (int q= 0; q < 100; q++) {
			try {
				queue.put(q);
				//q等于50的时候阻塞，消费者会因为无法取出元素同样阻塞
				if (q == 50) {
					sleep(5000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread{
	BlockingQueue<Integer> queue;
		
	public Consumer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for (int q= 0; q < 100; q++) {
			try {
				System.out.println(queue.take());
				//q等于70的时候阻塞，生产者会因为无法存入元素在80阻塞
				if (q == 70) {
					sleep(5000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
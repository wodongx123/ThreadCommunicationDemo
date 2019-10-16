package volatileDemo;

import java.io.IOException;

public class VolatileDemo extends Thread{
	
	private volatile static int num = 0;
	
	@Override
	public void run() {
		for (num = 0; num < 1000; num++) {
			System.out.println(Thread.currentThread().getName() + "   " + num);
		}

	}

	
	public static void main(String[] args) throws  IOException {
		new VolatileDemo().start();
		new VolatileDemo().start();
		
		System.in.read();

        System.out.println(num);
	}

}

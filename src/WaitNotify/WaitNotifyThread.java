package WaitNotify;


public class WaitNotifyThread implements Runnable{
	Num num;
	
	public WaitNotifyThread(Num num){
		this.num = num;
	}

	public void run() {
		while (num.getI() < 100) {
			synchronized (num) {
				System.out.println(Thread.currentThread().getName()+ " " + num.getI());
				num.add();
				
				num.notify();
				try {
					num.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		Num num = new Num();
		
		WaitNotifyThread st = new WaitNotifyThread(num);
		new Thread(st, "thread-1").start();
		new Thread(st, "thread-2").start();
		
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

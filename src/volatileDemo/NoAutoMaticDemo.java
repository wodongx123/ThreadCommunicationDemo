package volatileDemo;

public class NoAutoMaticDemo {
    public volatile int inc = 0;
     
    public void increase() {
        inc++;
    }
     
    public static void main(String[] args) {
        final NoAutoMaticDemo test = new NoAutoMaticDemo();
        
        //10个线程对加1000次，本应该得到10000，实际上会因为无法保证变量操作是原子性，经常达不到10000
        //而如果换成synchronized或者lock都可以得到10000
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();

        System.out.println(test.inc);
    }
}
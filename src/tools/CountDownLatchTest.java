package tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    static int threads = 200;
    static int count = 5000;
    //static AtomicInteger sum = new AtomicInteger(0);
    static int sum = 0;
    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newFixedThreadPool(threads);
        CountDownLatch cdl = new CountDownLatch(count);
        for(int i = 0; i < count; i++){
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    synchronized (CountDownLatchTest.class){
                        sum++;
                    }
                    //sum++;
                    //sum.incrementAndGet();
                    cdl.countDown();
                }
            });
        }

        cdl.await();

        //System.out.println("结果是："+sum.get());
        System.out.println("结果是："+sum);
        exec.shutdown();
    }
}

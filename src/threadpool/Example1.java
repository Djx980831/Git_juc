package threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Example1 {

    static int count = 5000;
    static int threads = 200;
    //static int sum = 0;
    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(threads);
        CountDownLatch cdl = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
//                    synchronized (Example1.class) {
//                        //sum++;
//                    }
                    sum.incrementAndGet();
                    cdl.countDown();
                }

            });
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结果：" + sum.get());

        exec.shutdown();
    }
}

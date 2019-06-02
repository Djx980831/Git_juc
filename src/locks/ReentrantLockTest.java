package locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static int threads = 200;
    static int count = 5000;

    static int sum = 0;

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(threads);
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for(int i = 0;i < count; i++){
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try{
                        sum++;
                        countDownLatch.countDown();
                    }finally {
                        lock.unlock();
                    }
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结果是："+sum);

        exec.shutdown();
    }

}

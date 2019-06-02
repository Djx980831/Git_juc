package tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    static int threadcount = 3;
    static int execcount = 50;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(threadcount);

        CountDownLatch countDownLatch = new CountDownLatch(execcount);

        for(int i = 0; i < execcount; i++){
            final int temp = i;//线程封闭
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    try {

                        semaphore.acquire(); //获得许可

                        ecexTask(temp);

                        countDownLatch.countDown();
                        semaphore.release();    //释放许可
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        countDownLatch.await();

        System.out.println("运行结束！");


        exec.shutdown();




    }

    public static void ecexTask(int count){
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+":"+count);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

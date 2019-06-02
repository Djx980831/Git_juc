package tools;

import java.util.concurrent.*;

public class CyclicBarrierTest {
    static int times = 50;
    static CountDownLatch countDownLatch = new CountDownLatch(20);
    static CyclicBarrier cb = new CyclicBarrier(3);

    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(20);

        for(int i = 0; i < times; i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int temp = i;
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    execTask(temp);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
        System.out.println("执行完毕！");
    }



    public static void execTask(int count){
        System.out.println("readying...."+Thread.currentThread().getName()+":"+count);
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("execing...."+Thread.currentThread().getName()+":"+count);
    }
}

package locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MakerAndProducer {


    public static void main(String[] args) {
        Shop shop = new Shop();
        new Maker(shop).start();
        new Consumer(shop).start();
    }
}
class Shop{

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    int sum = 0;

    //生产
    public void maker(){
        lock.lock();
        try{
            while(sum <= 0){
                Thread.sleep(200);
                condition.await();
                sum++;
                System.out.println("生产了一个面包，面包数是："+sum);
            }
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //消费
    public void consume(){
        lock.lock();
        try{
            while (sum > 0){
                Thread.sleep(200);
                condition.await();
                sum--;
                System.out.println("消费了一个面包，面包数是："+sum);
                System.out.println("-----------------------");
            }
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class Maker extends Thread{
    Shop shop;
    public Maker(Shop shop){
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true){
            shop.maker();
        }
    }
}

class Consumer extends Thread{
    Shop shop;
    public Consumer(Shop shop){
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true){
            shop.consume();
        }
    }
}
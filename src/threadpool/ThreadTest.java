package threadpool;

public class ThreadTest {
    public static void main(String args[]) {
        Tz tz = new Tz();
        Wg wg = new Wg();
        Thread t1 = new Thread(tz);
        Thread t2 = new Thread(wg);
        t1.start();
        t2.start();
        while(tz.getJl() < wg.getJl()){
            System.out.println("我领先乌龟："+(wg.getJl() - tz.getJl())+"m.我跑得快,睡一觉");
            try {
                t1.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(tz.getJl() > wg.getJl()){
            System.out.println("乌龟领先兔子："+(tz.getJl() - wg.getJl())+"m.");
        }
//        if (tz.getJl() < wg.getJl()) {
//            System.out.println("我领先乌龟："+(wg.getJl() - tz.getJl())+"m.我跑得快,睡一觉");
////            try {
////                t1.sleep(2000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//
//        }else{
//
//            System.out.println("乌龟领先兔子："+(tz.getJl() - wg.getJl())+"m.");
//        }
    }
}

class Tz implements Runnable {
    int jl = 50;

    @Override
    public void run() {
        while (jl > 0) {
            jl = jl - 10;
            System.out.println("兔子距离终点" + jl + "单位");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (jl == 0)
            System.out.println("兔子到终点了");
    }

    public int getJl() {
        return jl;
    }

}

class Wg implements Runnable {
    int jl = 50;

    @Override
    public void run() {
        while (jl > 0) {
            jl--;
            System.out.println("乌龟距离终点" + jl + "单位");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (jl == 0)
            System.out.println("乌龟到终点了");
    }

    public int getJl() {
        return jl;
    }

}
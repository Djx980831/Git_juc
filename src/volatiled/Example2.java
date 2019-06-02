package volatiled;

public class Example2 {

    static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
            }
        }).start();

        while (true){
            if(flag){
                break;
            }
        }

        System.out.println("---end---");
    }
}

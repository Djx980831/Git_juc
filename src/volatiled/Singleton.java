package volatiled;

public class Singleton {
    static volatile Singleton singleton = null;

    //私有的构造
    private Singleton(){
        System.out.println("私有的构造方法。");
    }

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                    //1.开辟空间
                    //2、调用构造方法，实例化
                    //3、返回对象的引用
                }
            }
        }
        return singleton;
    }

}

package DesignPatterns.singleton;

public class LazySingletonClass {

    static LazySingletonClass INSTANCE;
    private  LazySingletonClass() {
    }
    
    public static synchronized LazySingletonClass getInstance(){
        if (INSTANCE == null){
                    INSTANCE = new LazySingletonClass();
        }
        return INSTANCE;
    }
}

package DesignPatterns.singleton;

public class EarlySingletonClass {
    
    static final EarlySingletonClass INSTANCE = new EarlySingletonClass();
    private EarlySingletonClass(){}

    public static EarlySingletonClass getInstance(){
        return INSTANCE;
    }
}

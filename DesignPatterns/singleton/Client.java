package DesignPatterns.singleton;

public class Client {
    public static void main(String[] args){

        //early singleton - as soon as class loaded
        EarlySingletonClass esc1 = EarlySingletonClass.getInstance();
        EarlySingletonClass esc2 = EarlySingletonClass.getInstance();
        //check the both instance is same or not
        System.out.println(esc1 == esc2);


        //lazy singleton - on demand create
        LazySingletonClass lsc1 = LazySingletonClass.getInstance();
        LazySingletonClass lsc2 = LazySingletonClass.getInstance();
        System.out.println(lsc1 == lsc2);
    }
}
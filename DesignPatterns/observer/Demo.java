package DesignPatterns.observer;
import java.util.concurrent.TimeUnit;
public class Demo {
    
    public static void main(String[] args) {
        System.out.println("observer pattern demo 3");


        //create observersal class
        iotOberservals observals = new iotOberservals();

        //observers
        IObserver subject = new weatherObervers("delhi station");
        IObserver subject2 = new weatherObervers("gurgaon station");
        
        observals.registerObservers(subject);
        // observals.registerObservers(subject2);

        observals.notifyUpdatesToObservers("tempreture update to 30*");
        try {
            TimeUnit.SECONDS.sleep(1);
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        observals.notifyUpdatesToObservers("tempreture update to 35*");
    }
}

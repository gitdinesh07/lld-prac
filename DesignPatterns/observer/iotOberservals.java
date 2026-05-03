package DesignPatterns.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class iotOberservals {
    List<IObserver> observers = new ArrayList<>();

    void registerObservers(IObserver observer){
        this.observers.add(observer);
    }

    void notifyUpdatesToObservers(String newMessage){
        for(IObserver o : this.observers){
            newMessage = newMessage+" at "+new Date().toString();
            o.update(newMessage);
        }
    }
}

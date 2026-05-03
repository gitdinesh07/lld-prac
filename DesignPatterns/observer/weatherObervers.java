package DesignPatterns.observer;


public class weatherObervers implements IObserver {
    private String nameOfTheObserver;
    public weatherObervers(String name) {
        this.nameOfTheObserver = name;
    }
    @Override
    public void update(String message){
        System.out.println(this.nameOfTheObserver+" -> updated message: "+message+" received..!");
    }
}

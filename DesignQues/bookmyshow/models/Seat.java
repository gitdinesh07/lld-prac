package DesignQues.bookmyshow.models;


public abstract class Seat {
    
    public Seat(int id){
        this.SeatId = id;
    }
    public int SeatId;

    public abstract int getPrice();

    public abstract SeatType GetType();

}

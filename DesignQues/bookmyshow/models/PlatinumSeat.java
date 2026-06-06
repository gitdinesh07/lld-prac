package DesignQues.bookmyshow.models;

public class PlatinumSeat extends Seat{

    public PlatinumSeat(int id){
        super(id);
    }
    @Override
    public int getPrice() {
       return 200;
    }

    @Override
    public SeatType GetType() {
        return SeatType.PLATINUM;
    }
}

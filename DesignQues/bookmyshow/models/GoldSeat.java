package DesignQues.bookmyshow.models;

public class GoldSeat extends Seat{

    public GoldSeat(int id) {
        super(id);
    }

    @Override
    public int getPrice() {
       return 100;
    }

    @Override
    public SeatType GetType() {
        return SeatType.GOLD;
    }
}

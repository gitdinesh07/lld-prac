package DesignQues.bookmyshow.models;

import java.util.List;

public class Screen {
    private  int ScreenId;
    private final  List<Seat> Seats;

    public Screen(int screenId,List<Seat> seats) {
        ScreenId = screenId;
        Seats = seats;
    }

    public void AddSeat(Seat s){
        this.Seats.add(s);
    }

    public int getScreenId() {
        return ScreenId;
    }

    public Seat getSeat(int seatId){
        return this.Seats.stream().filter(x-> x.SeatId == seatId).findFirst().get();
    }
}

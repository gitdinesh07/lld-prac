package DesignQues.bookmyshow.models;

import java.time.LocalDateTime;
import java.util.List;


public class Booking {
    
    private final String BookingId;

    private final int userId;
    
    private final Show ShowDetial;

    private final List<Integer> SeatIds;

    private  BookingStatus Status;

    private final LocalDateTime CreateAt;

    private final LocalDateTime UpdateAt;

    private final int Cost;

    public int getCost() {
        return Cost;
    }


    public Booking(String BookingId, LocalDateTime CreateAt, List<Integer> seatIds, Show ShowDetial, BookingStatus Status, LocalDateTime UpdateAt, int userId, int cost) {
        this.BookingId = BookingId;
        this.CreateAt = CreateAt;
        this.SeatIds = seatIds;
        this.ShowDetial = ShowDetial;
        this.Status = Status;
        this.UpdateAt = UpdateAt;
        this.userId = userId;
        this.Cost = cost;
    }


    public String getBookingId() {
        return BookingId;
    }

    public int getUserId() {
        return userId;
    }

    public Show getShowDetial() {
        return ShowDetial;
    }

    public List<Integer> getSeatIds() {
        return SeatIds;
    }

    public BookingStatus getStatus() {
        return Status;
    }

    public BookingStatus setStatus(BookingStatus newStatus) {
        return this.Status = newStatus;
    }

    public LocalDateTime getCreateAt() {
        return CreateAt;
    }

    public LocalDateTime getUpdateAt() {
        return UpdateAt;
    }

}
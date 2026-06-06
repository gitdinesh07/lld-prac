package DesignQues.bookmyshow.repositories;

import DesignQues.bookmyshow.models.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingRepo {
    
    List<Booking> bookingList = new ArrayList<>();


    int lastBookingId = 0;
    public Booking Create(Booking booking){
        // booking.BookingId = ++lastBookingId;
        // booking.CreateAt = LocalDateTime.now();
        // booking.UpdateAt = LocalDateTime.now();
        // booking.Status = BookingStatus.PENDING;
        bookingList.add(booking);
        
        System.out.println( "New booking with id: "+booking.getBookingId() +" booking created successfully..\n"
        +" showId:"+booking.getShowDetial().ShowId+"\n"
        +" movie: "+booking.getShowDetial().MovieData.Title+" \n"
        +" seats:"+booking.getSeatIds());
        return booking;
    }

    public Booking GetBookingById(String id){
        Booking getBooking = null;
        for(Booking b:bookingList){
            if (b.getBookingId().equals(id)){
                getBooking = b;
                break;
            }
        }
        return getBooking;
    }

    
    
}

package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.interfaces.LockProvider;
import DesignQues.bookmyshow.interfaces.PaymentStrategy;
import DesignQues.bookmyshow.models.Booking;
import DesignQues.bookmyshow.models.BookingStatus;
import DesignQues.bookmyshow.models.PaymentType;
import DesignQues.bookmyshow.models.Show;
import DesignQues.bookmyshow.repositories.BookingRepo;
import DesignQues.bookmyshow.utils.commonUtills;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingService {
    BookingRepo bookingRepo;
    LockProvider lockProvider;
    private static final int TTL = 5000;

    public BookingService(BookingRepo repo, LockProvider lock){
        this.bookingRepo = repo;
        this.lockProvider = lock;
    }
    
    public Booking CreateBooking(int userId, Show show, List<Integer> seats) throws SeatNotFoundException{
        
        int totalSeatPrice = 0;
        if (userId > 0 && show.ShowId > 0 && !seats.isEmpty()){
            for (int s : seats){
                var key = commonUtills.GetUniqeLockKey(s, show.ShowId);
                if(!lockProvider.TryLock(key, String.valueOf(userId), TTL)) {
                    throw new SeatNotFoundException("Seat "+s + " is temporarily unavailable..");
                }
                totalSeatPrice +=show.Theatre.Screens.get(show.ScreenId).getSeat(s).getPrice();
            }
        }


        Booking b = new Booking(
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            seats,
            show,
            BookingStatus.CREATED,
            LocalDateTime.now(),
            userId,
            totalSeatPrice
        );

        b = this.bookingRepo.Create(b);
        System.out.println("Booking created: "+b.getBookingId()+" for user:"+b.getUserId());
        return b;
    }

    public boolean ConfirmBooking(Booking booking, PaymentType paymentType) throws SeatNotFoundException{
        
        if(booking.getStatus() != BookingStatus.CREATED){
            throw new IllegalStateException("Booking is not valid state for confirmation");
        }
        
        //check all seats either lock presist or expired and also lock by same user
        for(Integer s : booking.getSeatIds()){
            var key = commonUtills.GetUniqeLockKey(s, booking.getShowDetial().ShowId);
            if(lockProvider.IsLockExpired(key) || !lockProvider.IsLockedBy(key, String.valueOf( booking.getUserId()))){
                throw new SeatNotFoundException("User: "+ booking.getUserId()+" Seat "+s + " is temporarily unavailable..");
            }
        }
        
        PaymentStrategy paymentObj = new PaymentStrategyFactory().GetStrategy(paymentType);
        boolean isPaid = paymentObj.Pay(booking);
        
        if(isPaid){
            for(Integer s: booking.getSeatIds()){
                var key = commonUtills.GetUniqeLockKey(s, booking.getShowDetial().ShowId);
                lockProvider.Unlock(key);
            }
        }

        booking.setStatus(BookingStatus.CONFIRM);
        System.out.println(" User:"+booking.getUserId() +" Booking confirm: "+ booking.getBookingId() + " with amount: "+ booking.getCost() );
        return true;
    }
}

package DesignQues.bookmyshow;

import DesignQues.bookmyshow.interfaces.LockProvider;
import DesignQues.bookmyshow.models.*;
import DesignQues.bookmyshow.repositories.BookingRepo;
import DesignQues.bookmyshow.repositories.MovieRepostory;
import DesignQues.bookmyshow.repositories.ShowRepostory;
import DesignQues.bookmyshow.repositories.TheatreRepo;
import DesignQues.bookmyshow.services.BookingService;
import DesignQues.bookmyshow.services.InMemoryLockProvider;
import DesignQues.bookmyshow.services.SeatNotFoundException;
import DesignQues.bookmyshow.services.ShowService;
import DesignQues.bookmyshow.services.TheatreService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDemo {
    public static void main(String[] args) throws SeatNotFoundException, InterruptedException {

        //repos 
        TheatreRepo theatreRepo = new TheatreRepo();
        MovieRepostory movieRepo = new MovieRepostory();
        ShowRepostory showRepo = new ShowRepostory();
        BookingRepo bookingRepo = new BookingRepo();


        //lock provider
        LockProvider lockProvider = new InMemoryLockProvider();

        //services
        var theatreService = new TheatreService(theatreRepo);
        var showService = new ShowService(showRepo);
        var bookingService = new BookingService(bookingRepo, lockProvider);

        //create theatre and screens
        Theatre newTheatre = new Theatre(1, "T-1");
        Screen screen = new Screen(1,  new ArrayList<>());
        

        //add seats into screen
        theatreService.AddTheatre(newTheatre);
        theatreService.AddScreenToTheatre(newTheatre.TheatreId, screen);

        //platinum seat
        theatreService.AddSeat(newTheatre.TheatreId, screen.getScreenId(),  new PlatinumSeat(1));
        theatreService.AddSeat(newTheatre.TheatreId, screen.getScreenId(),  new PlatinumSeat(2));
        theatreService.AddSeat(newTheatre.TheatreId, screen.getScreenId(),  new PlatinumSeat(3));

        //gold seats
        theatreService.AddSeat(newTheatre.TheatreId, screen.getScreenId(),  new GoldSeat(4));
        theatreService.AddSeat(newTheatre.TheatreId, screen.getScreenId(),  new GoldSeat(5));


        //create movie
        Movies movie = new Movies(1, "Superman-1", MovieType.SCIFI, 120);

        //create show of "Superman-1"
        var showStartTime = LocalDateTime.of(2026, 06, 20, 18, 30 ,0);
        var show1 = showService.createShow(newTheatre,screen.getScreenId(), movie, showStartTime);


        //create users
        User u1 = new User(1, "u-1");
        User u2 = new User(2, "u-2");

        System.out.println("==== DEMO - 1: Search shows for Movie ======");
        var shows = showService.getShowByMovieTitle("Superman-1");
        shows.forEach(x-> System.out.println(x.MovieData.MovieId +  " "  + x.MovieData.Title));

        // System.out.println("==== DEMO- 2: 1 user book seats ==========");
        // Booking booking1 = bookingService.CreateBooking(u1.Id, show1, List.of(1,5)); 
        // bookingService.ConfirmBooking(booking1, PaymentType.CARD);


        // System.out.println("==== DEMO- 3: 2 users try to book overlapping seats concurrently ==========");
        // {
        //     ExecutorService executor = Executors.newFixedThreadPool(2);
        //     System.out.println("start 2 users thread\n\n\n");
        //     executor.submit(()->{
        //         try {
        //             Booking booking11 = bookingService.CreateBooking(u1.Id, show1, List.of(1,5)); 
        //             Thread.sleep(2000);
        //             bookingService.ConfirmBooking(booking11, PaymentType.CARD);
                    
        //         } catch (Exception e) {
        //             System.out.println("user-1 failed:"+e.getMessage());
        //         }
        //     });

        //     executor.submit(()->{
        //     try {
        //         Booking booking12 = bookingService.CreateBooking(u2.Id, show1, List.of(3,2,5)); 
        //         Thread.sleep(2000);
        //         bookingService.ConfirmBooking(booking12, PaymentType.CARD);
                
        //     } catch (Exception e) {
        //         System.out.println("user-2 failed:"+e.getMessage());
        //     }
        // });
        // }

        try {
            Booking booking11 = bookingService.CreateBooking(u1.Id, show1, List.of(1,5)); 
            System.out.println("user1 created booking but did not pay");
            Thread.sleep(6000);


            System.out.println("user2 trying to book same seats after ttl");
            Booking booking12 = bookingService.CreateBooking(u2.Id, show1, List.of(1,5)); 

            try {
                System.out.println("now user1 trying to pay agter ttl");
                bookingService.ConfirmBooking(booking11, PaymentType.CARD);
                
            } catch (Exception e) {
                System.out.println("user-1 failed:"+e.getMessage());
                booking11.setStatus(BookingStatus.CANCEL);
            }
            System.out.println("now user2 trying to pay agter ttl");
            bookingService.ConfirmBooking(booking12, PaymentType.CARD);

        } catch (Exception e) {
            System.out.println("user-2 failed:"+e.getMessage());
        }
    }
}

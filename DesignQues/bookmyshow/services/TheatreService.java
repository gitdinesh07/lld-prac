package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.models.Screen;
import DesignQues.bookmyshow.models.Seat;
import DesignQues.bookmyshow.models.Theatre;
import DesignQues.bookmyshow.repositories.TheatreRepo;

public class TheatreService {
    TheatreRepo repo ;

    public TheatreService(TheatreRepo repo) {
       this.repo = repo;
    }
    
    public void AddTheatre(Theatre newTheatre){
        this.repo.save(newTheatre);
    }

    public void AddScreenToTheatre(int theatreId, Screen s){
        if(theatreId > 0){
            var theatres = this.repo.get(theatreId);
            if(theatres.TheatreId == theatreId){
                theatres.Screens.put(s.getScreenId(), s);
            }
        }
    }

    public void AddSeat(int theatreId,int screenId, Seat seat){
        if(theatreId > 0){
            var theatres = this.repo.get(theatreId);
            var screen = theatres.Screens.get(screenId);
            if(screen != null){
                screen.AddSeat(seat);
            }
        }
    }
}

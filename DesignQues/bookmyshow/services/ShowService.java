package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.models.*;
import DesignQues.bookmyshow.repositories.ShowRepostory;
import java.time.*;
import java.util.List;

public class ShowService {
    
    ShowRepostory showRepo;
    
    public ShowService(ShowRepostory repo){
        this.showRepo = repo;
    }

    public Show createShow(Theatre theatre,int screenId, Movies movie,LocalDateTime startAt){
        var newShow = new Show();
        
        newShow.ShowId = this.showRepo.getAll().size()+1;
        newShow.Theatre = theatre;
        newShow.MovieData = movie;
        newShow.StartTime = startAt;
        newShow.EndTime = startAt.plusMinutes(movie.DurationInMin);
        newShow.ScreenId = screenId;

        this.showRepo.save(newShow);
        return newShow;
    }

    public Show getShow(int showId){
        var found = showRepo.get(showId);
        return found.isPresent() ? found.get(): null;
    }

    public List<Show> getShowByMovieTitle(String title){
        return showRepo.getAll().stream().filter(x-> x.MovieData.Title.equals(title)).toList();
    }
}

package DesignQues.bookmyshow.repositories;

import DesignQues.bookmyshow.models.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShowRepostory {
    
   private final Map<Integer,Show> shows = new HashMap<>();

    public void save(Show newShow){
      this.shows.put(newShow.ShowId, newShow);
    }

    public List<Show> getAll(){
       return this.shows.values().stream().toList();
    }

    public Optional<Show> get(int id){
        return Optional.ofNullable(this.shows.get(id));
    }
}

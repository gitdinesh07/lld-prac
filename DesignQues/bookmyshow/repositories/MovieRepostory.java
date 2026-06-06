package DesignQues.bookmyshow.repositories;

import DesignQues.bookmyshow.models.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRepostory {
   
    private final Map<Integer, Movies> movies = new HashMap<>();

    public void save(Movies newMovie){
        newMovie.MovieId = this.movies.size()+1;
        movies.put(newMovie.MovieId, newMovie);
    }

    public List<Movies> getAll(){
       return this.movies.values().stream().toList();
    }

    public Movies get(int id){
        return this.movies.get(id);
    }
}

package DesignQues.bookmyshow.repositories;

import DesignQues.bookmyshow.models.Theatre;
import java.util.HashMap;
import java.util.Map;

public class TheatreRepo {
    private final Map<Integer,Theatre> theatreMap = new HashMap<>();
    
    public void save(Theatre newTheatre){
        theatreMap.put(newTheatre.TheatreId, newTheatre);
    }

    public Theatre get(int id){
        return theatreMap.get(id);
    }
}

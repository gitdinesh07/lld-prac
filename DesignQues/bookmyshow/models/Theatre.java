package DesignQues.bookmyshow.models;

import java.util.HashMap;
import java.util.Map;

public class Theatre {
    
    public Theatre(int id, String name){
        this.TheatreId = id;
        this.Name = name;
        this.Screens = new HashMap<>();
    }
    
    public int TheatreId;
    public String Name;
    public  Map<Integer, Screen> Screens ;
}

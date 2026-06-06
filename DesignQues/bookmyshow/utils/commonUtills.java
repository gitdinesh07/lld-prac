package DesignQues.bookmyshow.utils;

public class commonUtills {
    
    public static String GetUniqeLockKey(int seatId, int showId){
        return showId+":"+seatId;
    }
}

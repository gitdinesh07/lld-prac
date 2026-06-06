package DesignQues.bookmyshow.models;

import java.time.LocalDateTime;

public class Show {
    public int ShowId;
    public int ScreenId;
    public Movies MovieData;
    public Theatre Theatre;
    public LocalDateTime StartTime;
    public LocalDateTime EndTime;
}

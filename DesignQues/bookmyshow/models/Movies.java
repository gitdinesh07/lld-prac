package DesignQues.bookmyshow.models;

public class Movies {

    public Movies(int id, String title, MovieType type, int duration){
        this.MovieId = id;
        this.Title = title;
        this.MovieType = type;
        this.DurationInMin= duration;
    }
    public int MovieId;
    public String Title;
    public MovieType MovieType;
    public int DurationInMin;
}

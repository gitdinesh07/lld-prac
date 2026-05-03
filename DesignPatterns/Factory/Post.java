package DesignPatterns.Factory;

public class Post {
    private String author;

    public String getAuthor(){
        return this.author;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }

    public void DoPost(){
        System.out.println("something post..!");
    }
}
package DesignPatterns.Factory;

public class BlogPost extends Post{
    public void DoPost(){
        System.out.println("some blog has been posted by "+this.getAuthor());
    }
}
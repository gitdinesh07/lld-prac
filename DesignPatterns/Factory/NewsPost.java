package DesignPatterns.Factory;

public class NewsPost extends Post {
   
    public void DoPost(){
        System.out.println("some news has been posted by "+this.getAuthor());
    }
}

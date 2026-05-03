package DesignPatterns.Factory;

public class SimpleFactory {
    
    public static Post createPostObj(String type){
        switch (type) {
            case "news" :
                return new NewsPost();
            case "blog":
                return new BlogPost();
            default:
                throw new AssertionError();
        }
    }
}

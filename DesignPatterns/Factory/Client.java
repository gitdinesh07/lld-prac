package DesignPatterns.Factory;

public class Client {
    public static void main(String[] args) {
        SimpleFactory sf = new SimpleFactory();
        Post post = sf.createPostObj("blog");
        post.setAuthor("dinesh");
        post.DoPost();
    }
}

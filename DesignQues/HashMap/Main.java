package DesignQues.HashMap;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("left: "+(1 << 30));
        CustomHashMap<Integer, String> myHashMap = new CustomHashMap<>();
        myHashMap.put(1, "Test-1");
        myHashMap.put(2, "Test-2");
        myHashMap.put(3, "Test-3");
        myHashMap.put(4, "Test-4");
        myHashMap.put(5, "Test-5");
        myHashMap.put(3, "Test-3-5");
        myHashMap.put(6, "Test-6");
        myHashMap.put(7, "Test-7");


        var findKey = 3;
        var getVal = myHashMap.get(3);
        System.out.println("key-"+findKey+" value is: "+getVal);
        var isDeleted = myHashMap.remove(5);
        System.out.println("is deleted: "+ isDeleted);
    }
}

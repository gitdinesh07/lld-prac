package CollectionFrameworks;

import java.util.ArrayList;

public class ArrayListClass {


    //add elements
    public static void main(String[] args) {
        ArrayList<String> ary = new ArrayList<>();
        
        //add items
        ary.add("item1");
        ary.add("item2");
        ary.clear();
        ary.add("item3");
        ary.add("item4");

        ary.addAll(ary);
        
        ary.addFirst("item0");
        ary.add("item4"); //repeat
        ary.addLast("item999");
        ary.add("item4"); //repeat

        ary.set(2, "elementAt2");
        
        ary.get(1); //get by index
        ary.getFirst();
        System.out.println("last value:"+ ary.getLast());

        ary.remove(1); //remove by index
        ary.remove("item2"); //remove by value

        boolean hasValue = ary.contains("item0");
        boolean hasEqualArray = ary.equals(ary);

        int lasIndexOf =  ary.lastIndexOf("item4");
        
        System.out.println(ary.indexOf("item400"));
        
        ary.forEach(x-> System.out.println(x));

        // for( String val: ary){
        //     System.out.println(val);
        // }
        System.out.println(ary);


        // ArrayList to array
        var arr = ary.toArray(new Integer[0]);
        System.out.println(arr);

    }
}

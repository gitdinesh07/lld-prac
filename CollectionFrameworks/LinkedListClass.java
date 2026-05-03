package CollectionFrameworks;

import java.util.LinkedList;
import java.util.List;

public class LinkedListClass {
    public static void main(String[] args) {
        

        //all method present in List / ArrayList will be available to LinkedList also bcz both implement same List interface
        List<Integer> lnkLst = new LinkedList<>();
        lnkLst.add(1);
        lnkLst.add(4);
        lnkLst.add(8);

        
        var iterator = lnkLst.listIterator();
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next()); //next = item[index++]   //first return then inc
        System.out.println(iterator.previous());// previous = item[-- index] //first decrement then return

        // System.out.println(lnkLst);

        // for(int item:lnkLst) //int -> unboxking here (Interger -> int)
        //     System.out.println(item);


    }
}

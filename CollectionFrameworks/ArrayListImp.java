package CollectionFrameworks;

import java.util.Iterator;

public class ArrayListImp {
    public static void main(String[] args) {
        OurSlefArrayList<Integer> items = new OurSlefArrayList<>();
        items.add(1);
        items.add(5);
        items.add(0);
        items.add(91);


        // ------- iteratate using iterable interface
        // Iterator<Integer> iterator = items.iterator();
        // while(iterator.hasNext())
        //     System.out.println(iterator.next());
    

        for(Integer val : items)
            System.out.println(val);
        
    }
}


class OurSlefArrayList<T> implements Iterable<T>{
    private  T[] items;
    private int size;

    public OurSlefArrayList() {
        this.items =(T[]) new Object[100];
        this.size = 0;
    }

    public void add(T item){
        items[size++]= item;
    }

    public T getItemAtIndex(int index){
        return items[index];
    }

    @Override
    public Iterator<T> iterator(){
        return new OurGenericListIterator(this);
    }

    private class OurGenericListIterator implements Iterator<T>{

        OurSlefArrayList<T> itemObj;
        int index = 0;
        public OurGenericListIterator(OurSlefArrayList<T> itemObj) {
            this.itemObj = itemObj;
        }
        @Override
        public boolean hasNext() {
            return  index < this.itemObj.size;
        }

        @Override
        public T next() {
            return this.itemObj.items[index++];
        }
    }
}
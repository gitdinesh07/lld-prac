package DesignQues.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithSTL<K, V>  extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCacheWithSTL(int capacity){
        super(capacity,0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size() > capacity;
    }



    public static void main(String[] args) {

        var lru = new LRUCacheWithSTL<>(3);
        lru.put(1, "cache-1");
        lru.put(2, "cache-2");
        lru.get(1);
        System.out.println(lru);
        lru.put(3, "cache-3");
        lru.put(4, "cache-4");
        lru.get(1);
        System.out.println(lru);
        lru.put(5, "cache-5");

        System.out.println(lru);
    }

}

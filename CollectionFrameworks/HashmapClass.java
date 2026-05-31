package CollectionFrameworks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;;

public class HashmapClass {
    public static void main(String[] args) {
        
        //1. HashMap -> ❌ Not thread-safe | 
        Map<String, Integer> inventory = new HashMap<>();

        // Basic operations
        inventory.put("Apples", 10);
        inventory.put("Oranges", 5);
        // Allows one null key and multiple null values
        inventory.put(null, 0); 

        System.out.println("Inventory: " + inventory);
        System.out.println("Apple count: " + inventory.get("Apples"));

        //traversal using forloop
        for(Map.Entry<String,Integer> entry: inventory.entrySet()){
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        //traversal using Lambda func
        inventory.forEach((key,value)->{
            System.out.println("Key: " + key + ", Value: " + value);
        });


        //get keys - Iterating over Keys Only:
        for (String key : inventory.keySet()) {
            System.out.println("Key: " + key);
        }

        // Iterating over Values Only:
        for(Integer val : inventory.values()){
            System.out.println("value: "+val);
        }

        //2. ConcurrentHashMap -> ✅ Thread-safe
        Map<String, Integer> scores = new ConcurrentHashMap<>();
        
        scores.put("Player1", 100);
        scores.put("Player2", 150);

        // ConcurrentHashMap DOES NOT allow null keys or values
        // scores.put(null, 50); // This would throw a NullPointerException

        // Example of a thread-safe atomic update
        scores.computeIfPresent("Player1", (key, val) -> val + 10);

        System.out.println("Final Scores: " + scores);
    }

}

package DesignQues.HashMap;


class Node<K,V>{
    K key;
    V value;
    Node next;
    // Node prev; // help while deleting any Node bcz their req prev Node to map to next
    
    public Node(K key, V val){
        this.key = key;
        this.value = val;
        this.next = null;
        // this.prev = null;
    }
}

public class CustomHashMap <K,V>{
    private final int INITIAL_SIZE = 2;
    private final int MAX_CAPACITY = 1 << 30; // 1073741824 - max int => 1 << 30 (2^30) => The maximum allowed capacity for positive - if 31 then its going to negetive -2147483648
    private final float LOAD_FACTOR = 0.75f;
    private int countOfNodes = 0;

    private Node[] map;

    public CustomHashMap(){
        this.map = new Node[INITIAL_SIZE];
        // for(int i=0;i<5; i++){
        //     this.map[i] = new Node(null, null);
        // }
    }

    public V get(K key){
        Node<K,V> n = findNode(key);
        return n == null ? null : n.value;
    }

    public void put(K key, V val){
        Node n = findNode(key);
        if (n!= null){
            n.value = val;
            return;
        }
        
        Node newNode = new Node(key, val);
        int bucket = getKeyIndexPosition(key);
        n = map[bucket];
        
        if (n == null){
            map[bucket] = newNode;
        }
        else{
            while(n.next != null){
                n = n.next;
            }
            n.next = newNode;
        }
        countOfNodes++;

        if (needToRehash()){
            rehash(map.length * 2);
        }
    }

    public boolean remove(K key){
        int bucket = getKeyIndexPosition(key);
        Node head = map[bucket];
        
        if (head == null){
            return false;
        }

        Node prev = null;
        boolean removed = false;
        while(head != null){
            if(head.key != null && head.key.equals(key)){
                if(prev == null){
                    head = head.next;
                }else{
                    prev.next = head.next;
                }
                removed = true;
                break;
            }
            prev = head;
            head = head.next;
        }
        return removed;
    }

    private Node findNode(K key){
        Node n = null;
        int bucket = getKeyIndexPosition(key);
        n = map[bucket];
        if (n!= null){
            while(n != null){
                if(n.key != null && n.key.equals(key)){
                    return n;
                }
                n = n.next;
            }
        }
        return n;
    }

    private int getKeyIndexPosition(K key){
        return key.hashCode() % map.length;
    }

    private void rehash(int newSize){
        if(newSize > MAX_CAPACITY){
            System.out.println("Hashmap is exceeding the max capacity");
        }

        Node[] newMap = new Node[newSize];
        
        for(Node<K,V> curr:map){
             var old = curr;
             while(old != null){
                var nextNode = old.next;
                int newBucketIndex = old.key.hashCode() % newMap.length;
                Node indexNode = newMap[newBucketIndex] ;
                
                if (indexNode == null){
                    newMap[newBucketIndex] = old;
                }else{
                    while(indexNode.next != null){
                        indexNode = indexNode.next;
                    }
                    // old.next = null;
                    indexNode.next = old;
                }
                old = nextNode;
             }
        }
        map = newMap;
    }

    private boolean needToRehash(){
        return countOfNodes > (LOAD_FACTOR * map.length);
    }
}




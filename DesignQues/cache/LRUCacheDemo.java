package DesignQues.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheDemo {
    
    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);
    Map<Integer,Node> unorderMap = new HashMap<>();
    int capacity;



    public static void main(String[]args){
        var lruObj  = new LRUCacheDemo(2);
        lruObj.Put(1, 1);
        lruObj.Put(2, 2);
        lruObj.Get(1);
        lruObj.Put(3, 3);
        System.out.println(lruObj.toString());
        lruObj.Put(3, -3);
        lruObj.Put(4, -4);
        System.out.println(lruObj.toString());
    }

    public LRUCacheDemo(int capacity){
        this.capacity = capacity;
        head.next= tail;
        tail.prev = head;
    }

    public int Get(int key){
        var dataNode = this.unorderMap.get(key);
        if (dataNode !=null){
            RecentAccessNodeShiftToFront(key);
        }
        return dataNode == null ? -1 : dataNode.data;
    }

    public void Put(int key, int value){
        //if key already present in map then just change the value in double ll and shift to front; do not create new one
        if(this.unorderMap.containsKey(key)){
            var thisKeyNode = this.unorderMap.get(key);
            thisKeyNode.data = value;
            RecentAccessNodeShiftToFront(key);
        }else{
            //check and clear existing to add new one
            ClearNodeToAddNew();
            var newAddedNode = this.AddNewNode(key, value);
            this.unorderMap.put(key, newAddedNode);
        }

    }

    private void RecentAccessNodeShiftToFront(int key){
        if(this.unorderMap.containsKey(key)){
            var thisKeyNode = this.unorderMap.get(key);
            var prevNode = thisKeyNode.prev;
            var nextNode = thisKeyNode.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            var headNext =  this.head.next;
            this.head.next = thisKeyNode;
            thisKeyNode.next = headNext;
            headNext.prev = thisKeyNode;
        }
    }

    private Node AddNewNode(int key,int value){
        var newNode = new Node(key, value);

        var tempOldNext = head.next;

        head.next =  newNode;
        newNode.prev = head;
        newNode.next = tempOldNext;

        tempOldNext.prev = newNode;
        return newNode;
    }

    @Override
    public String toString(){
        String returnString = "";
       for(Map.Entry<Integer, Node> entry: this.unorderMap.entrySet()){
            returnString += "\nKey: " + entry.getKey() + ", Value: " + entry.getValue().data;
        }
        return returnString;
    }
    
    private void ClearNodeToAddNew(){
        while( !unorderMap.isEmpty() && unorderMap.size()>=this.capacity){
            var lastNode = this.tail.prev;

            lastNode.prev.next = this.tail;
            this.tail.prev = lastNode.prev;

            this.unorderMap.remove(lastNode.key);
        }
    }
    // create Node for Doubly LL - to manage first and last order 
    class Node{
        int data, key;
        Node next;
        Node prev;
        Node(int  key,int value){
            this.key = key;
            this.data = value;
            this.next= null;
            this.prev = null;
        }
        
    }
}

package CollectionFrameworks;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;


public class QueueClass {
    public static void main(String[] args) {
        

        //======= using LinkedList implementation  ================

        // 1. Create a Queue of Strings
        Queue<String> queue = new LinkedList<>();

        // 2. Add elements (Enqueue)
        queue.add("Document_1.pdf");
        queue.offer("Photo_01.jpg"); // offer() is safer for capacity-restricted queues
        queue.add("Invoice.docx");

        System.out.println("Current Queue: " + queue);

        // 3. Peek at the front element without removing it
        String head = queue.peek();
        System.out.println("Front element is: " + head);

        // 4. Remove elements (Dequeue)
        String removed = queue.poll(); // poll() returns null if empty; remove() throws exception
        System.out.println("Removed: " + removed);

        // 5. Final state
        System.out.println("Updated Queue: " + queue);



        //======== using ArrayDeque ==============
        queue = new ArrayDeque<>();

        queue.add("Document_1.pdf");
        queue.offer("Photo_01.jpg"); // offer() is safer for capacity-restricted queues
        queue.add("Invoice.docx");

        System.out.println("Current Queue: " + queue);

        // 5. Final state
        System.out.println("Updated Queue: " + queue);


        //Common Implementations of Queue Interface
        //LinkedList: Implements List and Deque interfaces, allows null elements,
        //           and can be used as a FIFO queue when used through the Queue interface.

        //ArrayDeque: A resizable array-based queue that is faster than LinkedList and does not allow nulls.

        //PriorityQueue: A queue where elements are processed according to their priority instead of insertion order.

        //ConcurrentLinkedQueue: A thread-safe, non-blocking queue suitable for concurrent environments.

        //BlockingQueue: A thread-safe queue that supports blocking operations for producer-consumer scenarios.

    }
}

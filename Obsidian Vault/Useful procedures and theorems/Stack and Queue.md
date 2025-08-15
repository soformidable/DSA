

Stack:

```
        Stack<Integer> s = new Stack<>();

        // Push elements onto the stack
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        // Pop elements from the stack
        while(!s.isEmpty()) {
            System.out.println(s.pop());
            
    static void stack_peek(Stack<Integer> stack)
    {
        Integer element = (Integer) stack.peek();
        System.out.println("Element on stack top: " + element);
    }
    
    // Searching element in the stack
    static void stack_search(Stack<Integer> stack, int element)
    {
        Integer pos = (Integer) stack.search(element);

        if(pos == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element is found at position: " + pos);
    }



```

Queue:

- ****add(element)****: Adds an element to the rear of the queue. If the queue is full, it throws an exception.
- ****offer(element):**** Adds an element to the rear of the queue. If the queue is full, it returns false.
- ****remove()****: Removes and returns the element at the front of the queue. If the queue is empty, it throws an exception.
- ****poll():**** Removes and returns the element at the front of the queue. If the queue is empty, it returns null.
- ****element():**** Returns the element at the front of the queue without removing it. If the queue is empty, it throws an exception.
- ****peek()****: Returns the element at the front of the queue without removing it. If the queue is empty, it returns null.

```
import java.util.LinkedList;
import java.util.Queue;


        // Create a Queue of Integers using LinkedList
        Queue<Integer> q = new LinkedList<>();
        
        System.out.println("Queue elements: " + q);
       Queue<String> queue = new LinkedList<>();

        // add elements to the queue
        queue.add("apple");
        queue.add("banana");
        queue.add("cherry");

		
```
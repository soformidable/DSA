
https://leetcode.com/problems/reverse-linked-list/description/

Code : https://github.com/soformidable/DSA/blob/main/LinkedList/Easy/LLReverse.java

To return the head of the reverse linked list (LAST NODE)
	A --> B --> C --> D --> NULL
NULL <-- A <-- B <-- C <-- D (NEW HEAD)


A) **Using STACK**

1. Push each node down the stack
	1. While(current.next!=null)
2. Reiterate over the stack ==WHILE CHANGING THE VALUES STARTING FROM HEAD==
3. Return the head 

B) OPTIMAL -- ==***Using previous links***==
1.  Initialize prev = null
2. Before loop set current = head
3. Start loop and initialize front = current.next
4. Set current.next = prev
5. move prev = current
6. move current = front

![[Pasted image 20250809175849.png]]


![[Pasted image 20250809175904.png]]

![[Pasted image 20250809175925.png]]

Code:

```
  public static ListNode reverseList(ListNode head) {

    if(head==null)

        return null;

  

    ListNode prev  = null;

    ListNode current = head;

  
  

    while(current!=null){

      ListNode front = current.next;

  

      current.next = prev;

  

      prev = current;

  

      current = front;

    }

  

    return prev;

  }
```

C) OPTIMAL -- ***==Using Recursion:==***

1. Base case: If head is empty or last node
	1. return head
2. recurse the function with newHead = function(head.next)
3. Set front = head.next
	1.  **A --> B**
4. front.next = head
	1. **B --> A**
5. set head.next = NULL (TO AVOID LOOPS)
	1. **NULL <-- A**
6. Return newHead (Start of the reversedLinkedList)

![[Pasted image 20250809180405.png]]



Code:

```
   public static ListNode recurseReverseList(ListNode head) {

      if(head == null || head.next == null)

          return head;

      ListNode newHead = recurseReverseList(head.next);

  

      // A --> B

      ListNode front = head.next;

  

      //B --> A

      front.next = head;

  

      //NULL <-- A

      head.next = null;

  
  

      return newHead;

   }
```
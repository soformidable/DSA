
problem: https://leetcode.com/problems/rotate-list/description/
code:  https://github.com/soformidable/DSA/blob/main/LinkedList/Medium/RotateList.java


Approach 1 (rotating part  = k * len): 

1. Calculate the length of the list
2. Check effective rotation
	1. When k == len then the list remains the same
	2. Thus k = k%len
3. Run a for loop from i = 1  to i <=k
	1. each time start from head and reach the last second node
	2. Change the pointers and next 
	3. make the last node as head
4. Finally return the head

Code:
```
    public ListNode rotate(ListNode head, int k){

        if(head == null || head.next == null)

            return head;

        ListNode current = head;

        int len = 0;

        while(current != null){

            len++;

            current = current.next;
        }

        // If k == len then the same list is returned. Thus calculating the remainder when k > len

        k = k%len;

  

        for(int i = 1 ; i <= k ; i++){

            current = head;

            while(current.next != null && current.next.next != null){

            current = current.next;

            }

            current.next.next = head;

            head = current.next;

            current.next = null;

        }

        return head;

    }
```



Approach 2:

1. Calculate the length and effective rotation (k = k % len)
2. Calculate the newTail --> This is where the link is supossed to cut and the newTail.next = newHead
3. change the newTail.next = null
4. return newHead

Another approach is joining the last node and first node.
Then traverse the list till end = 0 (end = len - k) 
That is the newTail

![[Pasted image 20250921174923.png]]

Code:
```
    public ListNode rotateOP(ListNode head, int k){

            if (head == null || head.next == null || k == 0) return head;

    // Calculate length and find tail

    int len = 1;

    ListNode tail = head;

    while (tail.next != null) {

        tail = tail.next;

        len++;

    }

    k = k % len;

    if (k == 0) return head;

    // Find the new tail (at position len - k)

    ListNode newTail = head;

    for (int i = 1; i < len - k; i++) {

        newTail = newTail.next;

    }

    // Rearrange pointers

    ListNode newHead = newTail.next;

    newTail.next = null;

        return newHead;

    }
```
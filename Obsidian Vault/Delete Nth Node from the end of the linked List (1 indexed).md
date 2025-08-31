
https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

Code : https://github.com/soformidable/DSA/blob/main/LinkedList/Medium/DeleteNfromEnd.java

Brute Force:

1. Calculate the total number of nodes
2. Calculate from end variable : (len - n) +1
3. use a dummy node to create a new list head
4. initialise a newCurrent and restart current from head
5. use another length to store final linked list length
6. run a while loop (len <= oldLength)
7. if (check len == fromEnd)
8. else --> move forward ; link the nodes
9. Finally return dummy.next:

```
public ListNode removeNthFromEnd(int n) {
        if (head == null)
            return null;      
        int len = 1;
        ListNode current = head;
        while(current.next!=null){
            current = current.next;
            len++;
        }
        System.out.println("Length is "+ len);
        int fromEnd = (len - n) + 1;
        System.out.println("From End is "+ fromEnd);
        int oldLen = len;
        len = 1;       
        ListNode dummy = new ListNode(-1);
        ListNode newListCurrent = dummy;
        current = head;

        while(len <= oldLen){
            if(len == fromEnd){
                current = current.next;
                len++;
            }
            else{
            newListCurrent.next = new ListNode(current.val);
            current = current.next;
            newListCurrent = newListCurrent.next;
            len++;
            }
        }
        return dummy.next;
    }

```

Optimal: 

1. dummy = new List Node(-1)
2. Fast and Slow pointers initialised to dummy
3. dummy.next = head
4. run a loop from 0 to n+1
	1. move only the fast (fast = fast.next)
5. Then run a while loop (fast != null) 
	1. slow = slow.next
	2. fast = fast.next
6.  ==The slow will ALWAYS be n+1 nodes away from fast==
7. Once the fast will be at the end, slow will be at n+1 from the end (1 before the required deletion node)
8. Link slow to next to the required node
9. slow.next = slow.next. Next;
10. Return dummy.next (returns the head of LinkedList

```
public ListNode removeNthFromEndOp(int n){
        if(head == null)
            return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for(int i = 0 ; i <= n ; i++){
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

```





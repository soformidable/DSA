https://leetcode.com/problems/middle-of-the-linked-list/



Using Count

1. Count the total number of nodes
2. reset current to head
3. calculate middle = (count/2)+1 --> both even and odd
4. set count = 1 
5. run another while(count != middle)
6. return current

Code:

```
    public static ListNode middle(ListNode head){

        if(head == null || head.next == null)

            return head;

        int count = 1;

  

        ListNode current = head;

  

        while(current.next!=null){

            current = current.next;

            count++;

        }

        //System.out.println("Count: "+count);

        //System.out.println("Middle: "+(count%2==0?(count/2)+1:(count/2)+1));

        int middle = (int)(count/2) + (int)1;

  

        current = head;

        count = 1;

        while(count != middle){

            current = current.next;

            count++;

        }

        //System.out.println(count);

        return current;

    }
```


Using Slow and Fast pointers:

1. When fast is at end the slow will be at the middle
2. fast moves two steps at a time
3. slow moves one step at a time
4. while(fast!= null (for even) && fast.next!=null (for odd) && slow.next
5. !=null)

Code:

```
    public static ListNode middleFS(ListNode head){

        if(head == null || head.next == null)

            return head;

        ListNode slow = head;

        ListNode fast = head;

  

        while(fast != null && fast.next!=null && slow.next!=null){

            fast = fast.next.next;

            slow = slow.next;

        }

        return slow;

    }
```
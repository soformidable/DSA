
Problem: https://leetcode.com/problems/linked-list-cycle/description/
Code:  https://github.com/soformidable/DSA/blob/main/LinkedList/Medium/HasCycleLL.java

Optimal approach:

1. Create two pointers fast and slow
2. fast moves two nodes at a time
3. slow moves one node at a time
4. run a while checking fast null then fast.next null (same for slow)
5. check if fast == slow
	1. return true
6. return false by default


Code:
```
    public boolean hasCycle(ListNode head){

        ListNode fast = head;

        ListNode slow = head;

  
        while(fast != null && fast.next != null && slow != null && slow.next != null){

            fast = fast.next.next;

            slow = slow.next;

            if(fast == slow)

                return true;

        }

        return false;

    }
```
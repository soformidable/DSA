
problem: https://leetcode.com/problems/linked-list-cycle-ii/description/
code:


Approach 1 (Usong O(N) space --> Hashset)

1. Add current to the hashset
2. Return the first occurrence of if(hs.contains(current))
3. Return null as default

Approach 2 

1. Initialize slow and fast
2. Run a loop (fast != null and fast.next != null)
3. if(fast == null)
	1. Move slow back to head (To cover the distance between fast and slow)
	2. Now run a while(fast != slow)
		1. move slow 1 ahead
		2. ==move fast 1 ahead==
	3. return slow after the loop exits
4. return null as default

Code:
```
    public ListNode detectCycleOP(ListNode head){

        ListNode slow = head;

        ListNode fast = head;

        while(fast != null && fast.next != null){

            fast = fast.next.next;

            slow = slow.next;

            if(fast == slow){

                slow = head;

                while(slow != fast){

                    fast = fast.next;

                    slow = slow.next;

                }

                return slow;

            }

        }

        return null;

    }
```
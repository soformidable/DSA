
Problem: https://leetcode.com/problems/intersection-of-two-linked-lists/description/
Code: https://github.com/soformidable/DSA/blob/main/LinkedList/Medium/LLIntersection.java

#### Approach 1 : Using nested loop
O (m * n)

1. Run a while loop (headA != null)
2. Run another loop under it that runs from headB to null every time
3. if headA == headB --> return headA
4. else default return null



#### Approach 2 : Using HashSet
O (m+n) // extra space

1. Use a hashset to keep a track of all the visited nodes from headA
2. run another loop for headB and check if the node has been visited through hashset
3. return null if no node is found



#### Approach 3  : Two pointers and exchange head
O(m + n) // no extra space

1. create two dummy heads A and B pointing to headA and headB 
2. run a loop on the condition (A != B)
3. move A and B forward simultaneously
4. ==whenever A || B reaches null==
	1. ==EXCHANGE the HEAD==
	2. A --> headB
	3. B --> headA
5. Now A and B are in level (As if the lengths of the lists are same)
6. EXCHANGING WORKS BECAUSE:
	1. When a head reaches null before other, it means there is a length difference
	2. This length difference is eliminated by exchanging heads
	3. Now as they are level, we can return any head in the end as the values would be
		1. Either correct intersection
		2. NULL

![[Pasted image 20250913172051.png]]


Here when the next iteration happens,
d2 = 1 and d1 = 3  ---> SAME LEVEL

Code:

```
    public ListNode getInterListNodeHeadChange(ListNode headA, ListNode headB){

        ListNode A = headA;

        ListNode B = headB;

  
        while(A != B){

            A = A == null ? headB : A.next;

            B = B == null ? headA : B.next;

        }
        return A;

    }
```

#### Approach 4 : Calculate length
O(m+n) // no extra space

1. Calculate lengths of both linked lists
2. Iterate through the large one until length == length_larger - length - smaller
3. This will skip the earlier nodes and bring them level
4. Once level, they can be moved forward while checking equality

Code:

```
    public ListNode getInterListNodeLength(ListNode headA, ListNode headB){

        if (headA == null || headB == null) return null;

    // Get lengths of both lists

    int lenA = getLength(headA);

    int lenB = getLength(headB);

    // Move longer list pointer ahead by difference

    ListNode ptrA = headA;

    ListNode ptrB = headB;

    if (lenA > lenB) {

        for (int i = 0; i < lenA - lenB; i++) {

            ptrA = ptrA.next;

        }

    } else {

        for (int i = 0; i < lenB - lenA; i++) {

            ptrB = ptrB.next;

        }

    }

    // Move both pointers until they meet

    while (ptrA != ptrB) {

        ptrA = ptrA.next;

        ptrB = ptrB.next;

    }

    return ptrA;

    }

  

    private int getLength(ListNode head) {

        int length = 0;

        while (head != null) {

            length++;

            head = head.next;

        }

        return length;

    }
    
```
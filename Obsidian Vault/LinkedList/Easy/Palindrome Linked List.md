Problem: https://leetcode.com/problems/palindrome-linked-list/description/
Code: https://github.com/soformidable/DSA/blob/main/LinkedList/Easy/PalindromeLL.java

Approach 1 : Using String and StringBuilder

1. Append current nodes to string till the end
2. Pass string to string builder
3. reverse the stringbuilder
	1. if it equal to original str
	2. return true
4. default return false

Approach 2: Using fast and slow and reversing half of the linked list

1. Run fast and slow till fast is null
2. Slow will be at the middle
3. recurseReverse the list by passing slow.next
4. first = head and second = newHead --> reversedHead
5. run a while to check each value of the node 
	1. while first.val == second.null
	2. if not then return false
6. return true by default

Code:
```
   public boolean isPalindromeOP(ListNode head){

        if(head == null || head.next == null)

            return true;

  

        ListNode fast = head;

        ListNode slow = head;

  

        while(fast.next != null && fast.next.next != null){

            fast = fast.next.next;

            slow = slow.next;

        }

        ListNode newHead = recurseReverseList(slow.next);

        ListNode first = head;

        ListNode second = newHead;

  

        while(second != null){

            if(second.val != first.val){

                recurseReverseList(newHead);

                return false;

            }

            first = first.next;

            second = second .next;

        }

        recurseReverseList(newHead);

        return true;

    }

  

       public static ListNode recurseReverseList(ListNode head) {

  

    // BASE CASE

      if(head == null || head.next == null)

          return head;

  

    // This will run until the last node is reached which is the new head of the reversed list

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
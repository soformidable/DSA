
problem : https://leetcode.com/problems/delete-node-in-a-linked-list/description/

Code: https://github.com/soformidable/DSA/blob/main/LinkedList/Medium/DeleteGivenNode.java

1. Set the current.val = current.next.val
2. set current.next = current.next.next;
	1. Make sure current.next.next != null
3. (OPTIONAL) set front = givenNode.next
4. (OPTIONAL) front.next = null

```
    public void deleteNode(ListNode node) {

            ListNode current = node;

            ListNode front = current.next;

            current.val = current.next.val;

            if(current.next.next != null){

                current.next = current.next.next;

                front.next = null;

            }

            else

                current.next = null;

    }
```
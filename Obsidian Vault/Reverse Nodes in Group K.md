
problem: https://leetcode.com/problems/reverse-nodes-in-k-group/description/
code: https://github.com/soformidable/DSA/blob/main/LinkedList/Hard/ReverseNodesKGroup.java

1. Driver function with original signature
2. create temp = head
3. create variables to keep track of end of the segment and start of new segment
4. Two functions
	1. getKthnode(head, k) (null if list.length < k)
	2. reverse(head) (iterative implementation)
5. Check if getknode is null
	1. if yes then it means segment is less than k
		1. if prevSegmentend = null then do nothing
		2. else prevSegmentend.next = temp (temp goes to the end after reverse)
6. Before breaking the segment .next =null --> use nextNode = knode.next
7. knode.next = null (breaking the segment)
8. reverse(temp)
9. If first rum then (temp == head)
	1. Thus head = knode (get the first node to return for the new final list)
10. Else its a second run.
	1. prevSegmentend.next = knode (new head of the current segment)
11. prevSegmentend = temp (moving forward)
12. temp = nextNode (moving forward)
13. return head

Code:
```
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode temp = head;

        ListNode prevNode = null;

  

        while(temp != null){

            ListNode knode = findKNode(temp,k);

            if(knode == null){

                if(prevNode != null){

                    prevNode.next = temp;

                }

                break;

            }

            ListNode nextNode = knode.next;

  

            knode.next = null;

            reverse(temp);

  

            if(temp == head){

                head = knode;

            }

            else{

                prevNode.next = knode;

            }

  

            prevNode = temp;

            temp = nextNode;

  

        }

  
  

        return head;

    }

  

    public ListNode findKNode(ListNode head, int k){

        ListNode current = head;

        k--;

        while(k>0 && current != null){

            k--;

            current = current.next;

        }

  

        return current;

    }

  

    public ListNode reverse(ListNode head){

        if(head == null)

            return null;

        ListNode current = head;

        ListNode prev = null;

  

        while(current != null){

  

            ListNode front = current.next;

  

            current.next = prev;

  

            prev = current;

  

            current = front;

        }

        return prev;

    }
```

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }


public class RotateLLByK {
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;

        if(head == null)
            return head;

        ListNode current = head;

        while(current != null){
            len++;
            current = current.next;
        }

        k = k%len;

        for(int i = 1; i <= k ; i++){
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

}

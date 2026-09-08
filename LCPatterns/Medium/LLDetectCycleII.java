import java.util.HashSet;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }


public class LLDetectCycleII {
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return head;

        ListNode current = head;

        HashSet<ListNode> set = new HashSet<>();

        while(current != null){
            if(set.contains(current)){
                return current;
            }

            set.add(current);
            current = current.next;
        }

        return null;
    }

}

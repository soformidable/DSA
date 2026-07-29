class ListNode {
      int val;
     ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
          return head;

        ListNode dummy = new ListNode(-1);
        ListNode previous = dummy;
        dummy.next = head;
        ListNode current = head;

        while(current != null && current.next != null){
          ListNode first = current;
          ListNode second = current.next;

          first.next = second.next;
          second.next = first;
          previous.next = second;

          previous = first;
          current = first.next;

        }

        return dummy.next;
    }
}

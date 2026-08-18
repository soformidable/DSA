
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }


public class ReverseLL2 {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;


        // setting the prev to the node just before left
        for(int i = 1 ; i < left; i ++){
            prev = prev.next;
        }

        ListNode current = prev.next;
        ListNode reversePrev = null;
        ListNode next = null;

        // reversing the list for the sublist left -> right
        for(int i = 0 ; i <= Math.abs(left - right) ; i++){
            next = current.next;
            current.next = reversePrev;
            reversePrev = current;
            current = next;
        }
        

        // Connect the reversed portion back

        // Connect end of reversed portion to remaining list
        prev.next.next = current;

        // Connect beginning to reversed portion
        prev.next = reversePrev;
        

        return dummy.next;
    }

    public static void display(ListNode head){
      if(head == null)
        System.out.println("No nodes in the Linked List");
      else{
        ListNode current = head;
        while(current!=null){
          System.out.print(current.val + " ---> ");
          current= current.next;
        }
        System.out.print("NULL\n");
      }
    }

    public static void main(String[] args) {
            LinkedList ll = new LinkedList();
            ll.addNode(1);
            ll.addNode(2);
            ll.addNode(3);
            ll.addNode(4);
            ll.addNode(5);
            //ll.display();
            display(ll.head);
            ListNode head = reverseBetween(ll.head,2,4);
            display(head);
    }
}

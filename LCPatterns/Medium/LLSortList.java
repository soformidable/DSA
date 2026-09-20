class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class LLSortList {
    public static ListNode sortList(ListNode head) {

        if(head == null || head.next == null)
            return head;

        ListNode mid = findMid(head);
        ListNode rightHead = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        
        return merge(left,right);
    }

    private static ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode merge(ListNode l1, ListNode l2){

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while(l1!=null && l2 != null){
            if(l1.val <= l2.val){
                current.next = l1;
                l1 = l1.next;
            }
            else{
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if(l1!=null) current.next = l1;
        if(l2!=null) current.next = l2;

        return dummy.next;
    }

    public static void display(ListNode head){
        if(head == null){
            System.out.println("NULL");
            return;
        }

        ListNode current = head;

        while(current != null){
            System.out.print(current.val + " --> ");
            current = current.next;
        }
        System.out.print("NULL \n");

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);

        display(head);

        sortList(head);

        display(head);
    }

}

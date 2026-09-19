import java.util.Stack;
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class LLReorderList{

    public static void reorderList(ListNode head) {

        if (head == null) return;

        Stack<ListNode> stack =  new Stack<>();

        ListNode current = head;

        while(current != null){
            stack.push(current);
            current = current.next;
        }

        int len = stack.size();

        current = head;
        for (int i = 0; i < len / 2; i++) {
            ListNode top = stack.pop();
            ListNode next = current.next;
            top.next = next;
            current.next = top;
            current = next;
        }
        current.next = null;

    }

    public static void main(String[] args) {
        
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        reorderList(head);

    }
    // 1 -> 2 -> 3 -> 4 -> x
    // 1 -> 4 -> 2 -> 3 -> x

}
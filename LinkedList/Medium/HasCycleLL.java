class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class LinkedList {
    ListNode head = null;
    
    public void addNode(int val) {
        ListNode newNode = new ListNode(val);
        if (head == null)
            head = newNode;
        else {
            ListNode current = head;
            while (current.next != null) {  // Added braces for proper indentation
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    public void display() {
        if (head == null)
            System.out.println("Empty linked list");
        else {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " ---> ");
                current = current.next;
            }
            System.out.print("NULL \n");
        }
    }
    
    public void displayHead(ListNode head) {
        if (head == null)
            System.out.println("Empty linked list");
        else {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " ---> ");
                current = current.next;
            }
            System.out.print("NULL \n");
        }
    }

    public boolean hasCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null && slow != null && slow.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                return true;
        }
        return false;
    }
}

public class HasCycleLL {
    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = head.next.next;
        System.out.println(ll.hasCycle(head));
    }
}

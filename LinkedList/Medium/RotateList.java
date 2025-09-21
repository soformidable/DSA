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

    public ListNode rotate(ListNode head, int k){

        if(head == null || head.next == null)
            return head;
        
        ListNode current = head;
        int len = 0;

        while(current != null){
            len++;
            current = current.next;
        }
        // If k == len then the same list is returned. Thus calculating the remainder when k > len
        k = k%len;

        for(int i = 1 ; i <= k ; i++){
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

    public ListNode rotateOP(ListNode head, int k){
            if (head == null || head.next == null || k == 0) return head;
    
    // Calculate length and find tail
    int len = 1;
    ListNode tail = head;
    while (tail.next != null) {
        tail = tail.next;
        len++;
    }
    
    k = k % len;
    if (k == 0) return head;
    
    // Find the new tail (at position len - k)
    ListNode newTail = head;
    for (int i = 1; i < len - k; i++) {
        newTail = newTail.next;
    }
    
    // Rearrange pointers
    ListNode newHead = newTail.next;
    newTail.next = null;
        return newHead;
    }

}

public class RotateList {
    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        ll.displayHead(head);
        ll.displayHead(ll.rotate(head, 2000000000));
        //System.out.println(ll.rotate(head,1).val);
    }
}

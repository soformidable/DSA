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

    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return false;

        ListNode current = head;
        String str = "";
        while(current != null){
            str= str + String.valueOf(current.val);
            current = current.next;
        }
        StringBuilder rev = new StringBuilder(str);
        if(str.equals(rev.reverse().toString()))
            return true;
        return false;
    }

    public boolean isPalindromeOP(ListNode head){
        if(head == null || head.next == null)
            return true;

        ListNode fast = head;
        ListNode slow = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode newHead = recurseReverseList(slow.next);
        ListNode first = head;
        ListNode second = newHead;

        while(second != null){
            if(second.val != first.val){
                recurseReverseList(newHead);
                return false;
            }
            first = first.next;
            second = second .next;
        }
        recurseReverseList(newHead);
        return true;
    }

       public static ListNode recurseReverseList(ListNode head) {

    // BASE CASE
      if(head == null || head.next == null)
          return head;

    // This will run until the last node is reached which is the new head of the reversed list
      ListNode newHead = recurseReverseList(head.next);


      // A --> B
      ListNode front = head.next;

      //B --> A
      front.next = head;

      //NULL <-- A
      head.next = null;


      return newHead;
   }


}

public class PalindromeLL {
    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        // head.next.next = new ListNode(2);
        // head.next.next.next = new ListNode(1);
        System.out.println(ll.isPalindromeOP(head));
    }
}

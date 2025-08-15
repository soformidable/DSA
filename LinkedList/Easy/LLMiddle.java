    import java.util.Stack;

    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    class LinkedList{
    ListNode head = null;
    public void addNode(int value){
      ListNode newnode = new ListNode(value);
      if(head == null)
        head = newnode;
      else{
        ListNode current = head;
        while(current.next != null)
          current = current.next;
        current.next = newnode;
      }      
    }
    public void display(){
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

  }

  public class LLMiddle {

    public static ListNode middleFS(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next!=null && slow.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public static ListNode middle(ListNode head){
        if(head == null || head.next == null)
            return head;
        int count = 1;

        ListNode current = head;

        while(current.next!=null){
            current = current.next;
            count++;
        }
        //System.out.println("Count: "+count);
        //System.out.println("Middle: "+(count%2==0?(count/2)+1:(count/2)+1));
        int middle = (int)(count/2) + (int)1;

        current = head;
        count = 1;
        while(count != middle){
            current = current.next;
            count++;
        }
        //System.out.println(count);
        return current;
    }

   public static ListNode recurseReverseList(ListNode head) {
      if(head == null || head.next == null)
          return head;
      ListNode newHead = recurseReverseList(head.next);

      // A --> B
      ListNode front = head.next;

      //B --> A
      front.next = head;

      //NULL <-- A
      head.next = null;


      return newHead;
   }

  public static ListNode reverseList(ListNode head) {
    if(head==null)
        return null;

    ListNode prev  = null;
    ListNode current = head;


    while(current!=null){
      ListNode front = current.next;

      current.next = prev;

      prev = current;

      current = front;
    }
    

    return prev;
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

  public static void main(String args[]){
    LinkedList ll = new LinkedList();
    ll.addNode(0);
    ll.addNode(1);
    ll.addNode(2);
    ll.addNode(3);
    ll.addNode(5);
    //ll.display();
    //display(ll.head);
    ListNode head = reverseList(ll.head);
    //display(head);
    System.out.println(middle(head));
  }
}

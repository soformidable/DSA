import java.util.*;

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

  public class MergeSortedLL {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      if(list1 == null)
        return list2;
      if(list2 == null)
        return list1;
      List<Integer> lst = new ArrayList<Integer>();

      while(list1 != null){
        lst.add(list1.val);
        list1 = list1.next;
      }
      while(list2 != null){
        lst.add(list2.val);
        list2 = list2.next;
      }

      Collections.sort(lst);
      ListNode dummynode = new ListNode(-1);
      ListNode current = dummynode;

      for(int x : lst){
        current.next = new ListNode(x);
        current = current.next;
      }


      return dummynode.next;
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
    LinkedList ll1 = new LinkedList();
    ll1.addNode(0);
    ll1.addNode(2);
    ll1.addNode(5);
    ll1.addNode(6);
    ll1.addNode(8);
    //ll.display();
    //display(ll.head);
    //ListNode head = reverseList(ll.head);
    display(mergeTwoLists(ll.head,ll1.head));
  }
}

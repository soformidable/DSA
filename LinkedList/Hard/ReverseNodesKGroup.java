import java.util.*;

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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prevNode = null;

        while(temp != null){
            ListNode knode = findKNode(temp,k);
            if(knode == null){
                if(prevNode != null){
                    prevNode.next = temp;
                }
                break;
            }
            ListNode nextNode = knode.next;

            knode.next = null;
            reverse(temp);

            if(temp == head){
                head = knode;
            }
            else{
                prevNode.next = knode;
            }

            prevNode = temp;
            temp = nextNode;

        }


        return head;
    }

    public ListNode findKNode(ListNode head, int k){
        ListNode current = head;
        k--;
        while(k>0 && current != null){
            k--;
            current = current.next;
        }

        return current;
    }

    public ListNode reverse(ListNode head){
        if(head == null)
            return null;
        ListNode current = head;
        ListNode prev = null;

        while(current != null){

            ListNode front = current.next;

            current.next = prev;

            prev = current;

            current = front;
        }
        return prev;
    }

    public ListNode reverseStack(ListNode head){
        Stack<ListNode> st = new Stack<ListNode>();
        ListNode current = head;

        while(current != null){
            st.push(current);
            current = current.next;
        }
        ListNode dummy = new ListNode(-1);
        current = dummy;
        while(!st.empty()){
            current.next = st.pop();
            current = current.next;
        }

        //CRITICAL TO AVOID LAST NODE POINTING BACK TO THE HEAD AND INFINITE LOOP
        current.next = null;
        
        return dummy.next;
    }
}

public class ReverseNodesKGroup {
    public static void main(String args[]){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        //head.next.next = new ListNode(3);
        //head.next.next.next = new ListNode(4);
        LinkedList ll = new LinkedList();
        ll.displayHead(head);
        ll.displayHead(ll.reverseKGroup(head,3));
        // ListNode node = ll.findKNode(head, 3);
        // System.out.println(node.val);
    }
}


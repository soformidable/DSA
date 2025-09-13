import java.util.HashSet;

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
            while (current.next != null) {  
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        
        while(headA != null){
            ListNode current = headB;
            while(current != null){
                if(current == headA)
                    return headA;
                current = current.next;
            }
            headA = headA.next;
        }

        return null;
    }

    public ListNode getInterListNodeHash(ListNode headA, ListNode headB){
        HashSet<ListNode> visited = new HashSet<ListNode>();

        while(headA != null){
            visited.add(headA);
            headA = headA.next;
        }

        while(headB != null){
            if(visited.contains(headB)) return headB;
            headB = headB.next;
        }

        return null;
    }

    public ListNode getInterListNodeHeadChange(ListNode headA, ListNode headB){
        ListNode A = headA;
        ListNode B = headB;

        while(A != B){
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }

        return A;
    }

    public ListNode getInterListNodeLength(ListNode headA, ListNode headB){
        if (headA == null || headB == null) return null;
    
    // Get lengths of both lists
    int lenA = getLength(headA);
    int lenB = getLength(headB);
    
    // Move longer list pointer ahead by difference
    ListNode ptrA = headA;
    ListNode ptrB = headB;
    
    if (lenA > lenB) {
        for (int i = 0; i < lenA - lenB; i++) {
            ptrA = ptrA.next;
        }
    } else {
        for (int i = 0; i < lenB - lenA; i++) {
            ptrB = ptrB.next;
        }
    }
    
    // Move both pointers until they meet
    while (ptrA != ptrB) {
        ptrA = ptrA.next;
        ptrB = ptrB.next;
    }
    
    return ptrA;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    
}

public class LLIntersection {
    public static void main(String args[]){

        LinkedList ll = new LinkedList();
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = new ListNode(4);
        ll.displayHead(headA);

        ListNode headB = new ListNode(5);
        //headB.next = new ListNode(6);
        headB.next = headA.next.next;
        ll.displayHead(headB);

        System.out.println("Intersection CALL");
        ll.displayHead(ll.getIntersectionNode(headA, headB));
    }
    
}

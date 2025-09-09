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

    public void deleteNode(ListNode node) {
        // Edge case: cannot delete if node is null or it's the last node
        if (node == null || node.next == null) {
            System.out.println("Cannot delete node (either null or last node)");
            return;
        }
        
        // Copy the value from next node
        node.val = node.next.val;
        // Skip the next node
        node.next = node.next.next;
    } 
}

public class DeleteGivenNode {
    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        
        // Create linked list: 1 -> 2 -> 3 -> 4
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        
        System.out.println("Original list:");
        ll.displayHead(head1);
        
        // Test deleteNode - delete node with value 2 (the second node)
        ListNode nodeToDelete = head1.next; // This is node with value 2
        
        System.out.println("\nDeleting node with value: " + nodeToDelete.val);
        ll.deleteNode(nodeToDelete);
        
        System.out.println("List after deletion:");
        ll.displayHead(head1);
        
        // Test deleting another node - node with value 3 (now second node)
        ListNode anotherNode = head1.next; // This should now be node with value 3
        System.out.println("\nDeleting node with value: " + anotherNode.val);
        ll.deleteNode(anotherNode);
        
        System.out.println("List after second deletion:");
        ll.displayHead(head1);
    }
}
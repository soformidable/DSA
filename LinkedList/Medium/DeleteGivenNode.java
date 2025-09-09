class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){this.val = val;}
        ListNode(int val,ListNode next){this.val = val; this.next =next;}
    }

    class LinkedList{
        ListNode head = null;
        public void addNode(int val){
            ListNode newNode = new ListNode(val);
            if (head == null)
                head = newNode;
            else{
                ListNode current = head;
                while(current.next != null)
                    current = current.next;
                    current.next = newNode;
            }
        }
        public void display(){
            if (head == null)
                System.out.println("Empty linked list");
            else{
                ListNode current = head;
                while(current!=null){
                    System.out.print(current.val + " ---> ");
                    current = current.next;
                }
                System.out.print(" ---> NULL \n");
            }
        }
                public void displayHead(ListNode head){
            if (head == null)
                System.out.println("Empty linked list");
            else{
                ListNode current = head;
                while(current!=null){
                    System.out.print(current.val + " ---> ");
                    current = current.next;
                }
                System.out.print("NULL \n");
            }
        }

        public void deleteNode(ListNode node) {
            ListNode current = node;
            ListNode front = current.next;
            current.val = current.next.val;
            if(current.next.next != null){
                current.next = current.next.next;
                front.next = null;
            }
            else
                current.next = null;   
        } 
    }

public class DeleteGivenNode {
    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        ll.displayHead(head1);
    }
}

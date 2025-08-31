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
                System.out.print(" ---> NULL \n");
            }
        }

    public ListNode removeNthFromEnd(int n) {
        if (head == null)
            return null;      
        int len = 1;
        ListNode current = head;
        while(current.next!=null){
            current = current.next;
            len++;
        }
        System.out.println("Length is "+ len);
        int fromEnd = (len - n) + 1;
        System.out.println("From End is "+ fromEnd);
        int oldLen = len;
        len = 1;       
        ListNode dummy = new ListNode(-1);
        ListNode newListCurrent = dummy;
        current = head;

        while(len <= oldLen){
            if(len == fromEnd){
                current = current.next;
                len++;
            }
            else{
            newListCurrent.next = new ListNode(current.val);
            current = current.next;
            newListCurrent = newListCurrent.next;
            len++;
            }
        }
        return dummy.next;
    }

    public ListNode removeNthFromEndOp(int n){
        if(head == null)
            return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for(int i = 0 ; i <= n ; i++){
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    }

public class DeleteNfromEnd {
    public static void main(String args[]){
            LinkedList ll = new LinkedList();
            ll.addNode(1);
            ll.addNode(2);
            ll.addNode(3);
            // ll.addNode(4);
            // ll.addNode(5);
            ll.display();
            ll.displayHead(ll.removeNthFromEnd(3));
            ll.displayHead(ll.removeNthFromEndOp(3));
    }
}

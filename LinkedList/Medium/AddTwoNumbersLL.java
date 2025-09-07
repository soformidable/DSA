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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode current1 = l1;
        ListNode current2 = l2;
        StringBuilder str1 = new StringBuilder("");
        StringBuilder str2 = new StringBuilder("");

        while(current1!= null){
            str1.append(String.valueOf(current1.val));
            current1 = current1.next;
        }
        while(current2!= null){
            str2.append(String.valueOf(current2.val));
            current2 = current2.next;
        }
        str1.reverse();
        str2.reverse();

        int i = str1.length()-1;
        int j = str2.length()-1;
        int carry  = 0;
        StringBuilder str3 = new StringBuilder("");

        while(i>=0 || j>=0 || carry>0){
            int digit1 = (i>=0)?Character.getNumericValue(str1.charAt(i--)):0;
            int digit2 = (j>=0)?Character.getNumericValue(str2.charAt(j--)):0;

            int sum = digit1 + digit2 + carry;
            str3.append(sum % 10);
            carry = sum / 10;
        }

        String fn = str3.toString();

        ListNode newHead = new ListNode(-1);
        ListNode current = newHead;

        for(int k = 0 ; k < fn.length() ; k++){
            current.next = new ListNode(Character.getNumericValue(str3.charAt(k)));
            current = current.next;
        }

        return newHead.next;
    }

    public ListNode addTwoNumbersOP(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;

        while(l1!=null || l2!=null || carry!=0){
            int sum = carry;
            if(l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            current.next = new ListNode(sum%10);
            carry = sum/10;
            current = current.next;
        }
        return dummy.next;
    }



    }

public class AddTwoNumbersLL {
    public static void main(String args[]){
    ListNode head1 = new ListNode(2);
    head1.next = new ListNode(4);
    head1.next.next = new ListNode(3);
    ListNode head2 = new ListNode(5);
    head2.next = new ListNode(6);
    head2.next.next = new ListNode(4);
    LinkedList ll = new LinkedList();

    ll.displayHead(ll.addTwoNumbers(head1,head2));
    ll.displayHead(ll.addTwoNumbersOP(head1,head2));
    }
}

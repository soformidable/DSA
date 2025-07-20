class Node{
    int value = 0;
    Node next;

    public Node(int value){
        this.value = value;
        this.next = null;
    }

}

class LinkedList{

    Node head = null;
    public void addNode(int value){
        Node newNode = new Node(value);
        if(head == null)
            head = newNode;       
        else{
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        
    }

    public void display(){
        if(head == null)
            System.out.println("Linked list is empty");
        else{
            Node current = head;
            while(current != null){
                System.out.print(current.value+" --> ");
                current = current.next;
            }
            System.out.print("NULL");
        }
    }

    public boolean hasCycle(){
        if(head == null)
            return false;
        else{
            Node slow = head;
            Node fast = head;
            while(fast!=null && slow!=null && fast.next!=null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    return true;
                }
            }
        }
        return false;
    }

}

class LL {
    public static void main(String args[]){

        LinkedList lst = new LinkedList();
        lst.addNode(1);
        lst.addNode(2);
        lst.addNode(3);
        lst.addNode(4);
        lst.addNode(5);
        lst.head.next.next = lst.head.next;
        //lst.display();

        System.out.println("\nHas cycle : "+ lst.hasCycle());
    }
}

"# Linked List Basics (Singly Linked List)

## Overview

A **Singly Linked List** is a linear data structure where each element (node) contains:
- `value`: the data stored in the node
- `next`: a reference (pointer) to the **next** node in the sequence

The list ends when `next` is `null`.

This note summarizes the implementation from `Basics/LL.java`, covering node structure, insertion, traversal, and cycle detection.

---

## Node Structure

```java
class Node {
    int value = 0;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}
```

- Each node holds an integer value and a reference to the next node.
- Constructor initializes the value and sets `next` to `null`.

---

## Linked List Operations

### 1. Adding a Node (at the end)

```java
public void addNode(int value) {
    Node newNode = new Node(value);
    if (head == null) {
        head = newNode;           // First node → becomes head
    } else {
        Node current = head;
        while (current.next != null) {
            current = current.next;  // Traverse to last node
        }
        current.next = newNode;      // Link last node → new node
    }
}
```

**Key ideas:**
- If the list is empty, the new node becomes the `head`.
- Otherwise, traverse to the **last** node (where `next == null`) and attach the new node there.
- Time complexity: **O(n)** for each add (must traverse to the end).
- To make it O(1), keep a `tail` pointer that always references the last node.

### 2. Displaying the List

```java
public void display() {
    if (head == null) {
        System.out.println("Linked list is empty");
    } else {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " --> ");
            current = current.next;
        }
        System.out.print("NULL");
    }
}
```

- Traverses from `head` to `null`, printing each node's value.
- Output format: `1 --> 2 --> 3 --> NULL`

### 3. Cycle Detection (Floyd's Tortoise & Hare)

```java
public boolean hasCycle() {
    if (head == null)
        return false;

    Node slow = head;   // moves 1 step at a time
    Node fast = head;   // moves 2 steps at a time

    while (fast != null && slow != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            return true;   // they met → cycle exists
        }
    }
    return false;  // fast reached null → no cycle
}
```

**How it works:**
- Use two pointers: `slow` (moves 1 step) and `fast` (moves 2 steps).
- If there's a **cycle**, the fast pointer will eventually **lap** the slow pointer and they will meet.
- If there's **no cycle**, the fast pointer will reach `null` first.

**Why it works (visual intuition):**
- Imagine a circular track. A faster runner will eventually catch up to a slower runner from behind.
- In a linked list, once both pointers enter the cycle, fast gains on slow by 1 node per iteration, guaranteeing they'll meet within `O(cycle length)` steps.

**Time complexity:** O(n)  
**Space complexity:** O(1)

---

## Main Method (Test / Demo)

```java
public class LL {
    public static void main(String args[]) {
        LinkedList lst = new LinkedList();
        lst.addNode(1);
        lst.addNode(2);
        lst.addNode(3);
        lst.addNode(4);
        lst.addNode(5);

        // Create a cycle: node 5's next points to node 2
        lst.head.next.next = lst.head.next;

        System.out.println("Has cycle : " + lst.hasCycle());
        // Output: Has cycle : true
    }
}
```

**Cycle creation:** `lst.head.next.next = lst.head.next`  
- `head` = Node 1  
- `head.next` = Node 2  
- `head.next.next` = Node 3  
- Setting `Node3.next = Node2` creates a cycle: `1 → 2 → 3 → 2 → 3 → 2 → ...`

---

## Key Points for Revisit

| Operation | Code Pattern | Time |
|-----------|-------------|------|
| **Add (end)** | Traverse to tail, link new node | O(n) |
| **Add (start)** | `newNode.next = head; head = newNode;` | O(1) |
| **Traverse** | `while (current != null) { ... current = current.next; }` | O(n) |
| **Cycle detection** | Floyd's Tortoise & Hare (slow + fast pointers) | O(n) |
| **Search** | Traverse and compare values | O(n) |
| **Delete** | Traverse to previous node, update `prev.next = target.next` | O(n) |

- **Head pointer** is the entry point — always start operations from `head`.
- **Null checks** are critical before accessing `current.next` to avoid `NullPointerException`.
- **Cycle detection** is a classic interview problem — remember the slow/fast pointer pattern.
- For O(1) append, maintain a separate `tail` reference pointing to the last node.

**Related files in this vault:**
- `../LinkedList/Easy/` — various linked list problems (reverse, palindrome, middle)
- `../LinkedList/Medium/` — cycle detection, intersection, rotate, delete node
- `../LinkedList/Hard/` — reverse nodes in K groups

**Source file:** `Basics/LL.java`"
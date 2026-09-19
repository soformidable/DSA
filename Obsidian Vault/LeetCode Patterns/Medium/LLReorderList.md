# Reorder Linked List (Stack Pattern)

## Overview
This file summarizes the solution for reordering a singly linked list in the pattern: **L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …**. The solution uses a **stack** to reverse the second half of the list and then interleave nodes.

https://leetcode.com/problems/reorder-list/description/

---

## Problem Description
Given a singly linked list `L: L0 → L1 → … → Ln-1 → Ln`, reorder it to: `L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …`. You may not modify the values in the list's nodes—only nodes themselves may be changed.

---

## Key Insight
The reordering requires interleaving nodes from the start and end of the list. A **stack** provides an efficient way to traverse the second half of the list in reverse order without modifying the list structure prematurely.

---

## Solution Approach
1. **Edge Case**: If the head is `null`, return immediately.
2. **Push All Nodes**: Traverse the list and push each node onto a stack.
3. **Determine Length**: The stack size gives the total number of nodes.
4. **Interleave Nodes**: Iterate through the first half of the list (up to `len/2` iterations):
   - Pop the top node from the stack (which is the last node in the original list).
   - Store the next node from the current position.
   - Insert the popped node after the current node.
   - Update the current pointer to the stored next node.
5. **Terminate List**: After processing `len/2` pairs, set the `next` of the current node to `null` to properly terminate the list (this handles odd-length lists where the middle node should remain at the end).

---

## Solution Code
```java LCPatterns/Medium/LLReorderList.java
import java.util.Stack;
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class LLReorderList{

    public static void reorderList(ListNode head) {

        if (head == null) return;

        Stack<ListNode> stack =  new Stack<>();

        ListNode current = head;

        while(current != null){
            stack.push(current);
            current = current.next;
        }

        int len = stack.size();

        current = head;
        for (int i = 0; i < len / 2; i++) {
            ListNode top = stack.pop();
            ListNode next = current.next;
            top.next = next;
            current.next = top;
            current = next;
        }
        current.next = null;

    }

    public static void main(String[] args) {
        
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        reorderList(head);

    }
    // 1 -> 2 -> 3 -> 4 -> x
    // 1 -> 4 -> 2 -> 3 -> x

}
```

---

## Explanation
1. **Edge Case Handling**: If the list is empty (`head == null`), the method returns immediately.
2. **Stack Traversal**: All nodes are pushed onto a stack, effectively storing them in reverse order (last node at top).
3. **Interleaving Loop**: The loop runs for half the length of the list (integer division). In each iteration:
   - `top` (popped from stack) is the node from the end of the original list.
   - `next` stores the next node in the original forward order.
   - The popped node `top` is inserted after `current`, and its `next` is set to the original `next`.
   - `current` advances to the original `next` node for the next iteration.
4. **Termination**: After processing `len/2` pairs, `current` points to the middle node (for odd length) or the node before the middle (for even length). Setting `current.next = null` ensures the list ends properly without cycles.

---

## Time and Space Complexity
- **Time Complexity**: O(n), where n is the number of nodes in the list. We traverse the list twice: once to push nodes onto the stack, and once to interleave.
- **Space Complexity**: O(n) due to the stack storing all nodes.

---

## Example
For a list `1 → 2 → 3 → 4`:
1. Stack contains nodes: `[1, 2, 3, 4]` (top is 4).
2. Length = 4, loop runs for 2 iterations.
3. Iteration 1:
   - `current` = 1, `top` = 4, `next` = 2.
   - Insert: `1 → 4 → 2 → 3 → 4?` (temporarily).
   - Update `current` = 2.
4. Iteration 2:
   - `current` = 2, `top` = 3, `next` = 3? Wait, careful: after first iteration, the list is `1 → 4 → 2 → 3`. `current` is 2, its next is 3. So `top` = 3, `next` = 3. Insert: `2 → 3 → 3`? Actually, we need to trace: after first iteration, the list is `1 → 4 → 2 → 3` (original 3 node). `current` (2) points to node 3. Stack still has nodes 1,2,3? No, we popped 4 and 3? Let's re-evaluate: Stack initially had 1,2,3,4. After popping 4, stack has 1,2,3. After popping 3, stack has 1,2. In second iteration, `current` is node 2 (from original list). Its next is node 3 (original). We pop `top` = 3, set `top.next = current.next` (which is 3), then `current.next = top`. This creates a cycle? Actually, the code sets `top.next = next` where `next = current.next`. If `current.next` is 3 and `top` is also 3 (same node), then we are setting `top.next = top`, creating a self-loop. This indicates a bug in the algorithm for even-length lists? Let's simulate properly with the given main method (list 1→2→3→4):
   - After first iteration: List becomes 1→4→2→3. `current` moves to 2.
   - Second iteration: `current` = 2, `next` = current.next = 3. `top` = stack.pop() = 3 (the original node 3). Now set `top.next = next` i.e., node 3's next = node 3 (itself). Then `current.next = top` i.e., node 2's next = node 3. So list becomes 1→4→2→3→3→3... infinite loop? Actually, after second iteration, we have: 1→4→2 (points to node 3). Node 3's next is set to itself (since top.next = next, and next is node 3). So yes, we get a cycle. But after the loop, `current.next = null` where `current` is 3 (since we updated `current = next` which is node 3). So we set node 3's next to null, breaking the cycle. So final list: 1→4→2→3→null. That's correct. So the termination step is crucial to avoid cycles.

---

## Alternative Approaches
- **Two-pointer + Reverse**: Find the middle of the list using slow/fast pointers, reverse the second half, then merge the two halves.
- **Deque**: Use a deque to pop from both ends.

---

## Why Use a Stack?
A stack provides a simple way to traverse the list in reverse without modifying the original list structure until needed. It is intuitive and easy to implement, though it uses extra space.

---
</parameter>
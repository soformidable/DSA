# Sort Linked List (Merge Sort Pattern)

## Overview
This file summarizes the solution for sorting a singly linked list in ascending order. The solution uses the **merge sort** algorithm adapted for linked lists.

---

## Problem Description
Given the head of a linked list, return the list after sorting it in ascending order. The solution should run in **O(n log n)** time and use **O(1)** extra space (or O(log n) for recursion stack).


https://leetcode.com/problems/sort-list/description/

---

## Key Insight
- **Merge sort** is ideal for linked lists because it only requires **O(1)** extra space for the merging process (no need for auxiliary arrays).
- A linked list can be split into two halves using the **slow and fast pointer** technique.
- The list can be merged back together in sorted order without extra space.

---

## Solution Approach
1. **Base Case**: If the list is empty or has only one node, it is already sorted.
2. **Split the List**: Use the slow/fast pointer technique to find the middle node.
3. **Break the List**: Separate the list into two halves by setting `mid.next = null`.
4. **Recursively Sort**: Recursively sort both halves.
5. **Merge**: Merge the two sorted halves into a single sorted list.

---

## Solution Code
\`\`\`java LCPatterns/Medium/LLSortList.java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LLSortList {

    // Main entry point: sorts the linked list using merge sort
    public static ListNode sortList(ListNode head) {
        // Base case: empty list or single node is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Find the middle node to split the list into two halves
        ListNode mid = findMid(head);
        ListNode rightHead = mid.next; // Start of the second half
        mid.next = null;               // Break the list into two independent halves

        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // Merge the two sorted halves
        return merge(left, right);
    }

    // Finds the middle node using the slow and fast pointer technique
    private static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // Fast starts one step ahead to get left-middle for even-length lists

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // Merges two sorted linked lists into one sorted list
    private static ListNode merge(ListNode l1, ListNode l2) {
        // Dummy node simplifies the merging logic
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Compare nodes from both lists and link the smaller one
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next; // Advance the current pointer (important!)
        }

        // Attach any remaining nodes from either list
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        // Return the head of the merged list (skip the dummy node)
        return dummy.next;
    }

    // Helper method to print the linked list
    public static void display(ListNode head) {
        if (head == null) {
            System.out.println("NULL");
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " --> ");
            current = current.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        // Example: 4 -> 2 -> 3 -> 1
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);

        System.out.println("Original list:");
        display(head);

        // Important: capture the returned head of the sorted list
        head = sortList(head);

        System.out.println("Sorted list:");
        display(head);
    }
}
\`\`\`


## Time and Space Complexity
- **Time Complexity**: O(n log n) — merge sort divides the list in half (log n levels) and merges in linear time at each level.
- **Space Complexity**: O(log n) due to the recursion stack. The iterative merging itself uses O(1) extra space.

---

## Example
**Input**: `4 -> 2 -> 3 -> 1`  
**Output**: `1 -> 2 -> 3 -> 4`

**Execution Trace**:
1. Split: `4 -> 2` and `3 -> 1`
2. Recursively sort left: `4 -> 2` → `2 -> 4`  
3. Recursively sort right: `3 -> 1` → `1 -> 3`  
4. Merge: `2 -> 4` and `1 -> 3` → `1 -> 2 -> 3 -> 4`

---

## Why Merge Sort for Linked Lists?
- Unlike arrays, linked lists cannot be randomly accessed, so algorithms like quicksort with pivot partitioning are harder to implement efficiently.
- Merge sort naturally works with linked lists because merging only requires traversing both lists simultaneously, which is easy with pointers.
- The O(n log n) time complexity makes it optimal for large lists.

---
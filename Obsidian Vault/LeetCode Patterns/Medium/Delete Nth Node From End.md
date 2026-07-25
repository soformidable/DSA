# Delete Nth Node From End of Linked List

**Problem Statement:**  
Given the head of a linked list, remove the nth node from the end of the list and return its head.

## Problem : 
https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

---

## Key Insight

This classic linked list problem tests your ability to manipulate pointers efficiently. There are two main approaches:

1. **Two-pass approach** - Calculate length, then find and remove the node
2. **One-pass approach** - Use two pointers with a fixed distance between them

---

## Solution 1: Two-Pass Approach

**Algorithm:**
1. First pass: Calculate the total length of the linked list
2. Calculate the position from the beginning: `position = length - n + 1`
3. Second pass: Traverse to the node before the target and remove it
4. Create a new list without the target node

**Why it works:**
- Knowing the length allows us to directly compute the 1-based index from the start
- The target node is at position `(length - n + 1)` from the beginning

---

## Solution 2: Two-Pointer Approach (Optimal)

**Algorithm:**
1. Create a dummy node pointing to the head (handles edge case of removing head)
2. Initialize two pointers: `fast` and `slow`, both pointing to dummy
3. Move `fast` pointer `n+1` steps ahead
4. Move both pointers until `fast` reaches the end
5. `slow` will be just before the node to remove
6. Delete by setting `slow.next = slow.next.next`

**Why it works:**
- The gap of `n+1` nodes ensures `slow` is always one node before the target
- When `fast` reaches the end, `slow` is at the correct position
- Dummy node handles the edge case of removing the head node

---

## Code Implementation

### Two-Pass Approach

```java
public ListNode removeNthFromEnd(int n) {
    if (head == null)
        return null;
    
    // First pass: calculate length
    int len = 1;
    ListNode current = head;
    while (current.next != null) {
        current = current.next;
        len++;
    }
    
    int fromEnd = (len - n) + 1;
    
    // Second pass: build new list without target node
    int oldLen = len;
    len = 1;
    ListNode dummy = new ListNode(-1);
    ListNode newListCurrent = dummy;
    current = head;

    while (len <= oldLen) {
        if (len == fromEnd) {
            current = current.next;
            len++;
        } else {
            newListCurrent.next = new ListNode(current.val);
            current = current.next;
            newListCurrent = newListCurrent.next;
            len++;
        }
    }
    return dummy.next;
}
```

### One-Pass Two-Pointer Approach

```java
public ListNode removeNthFromEndOp(int n) {
    if (head == null)
        return null;
    
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode fast = dummy;
    ListNode slow = dummy;

    // Move fast n+1 steps ahead
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }

    // Move both until fast reaches end
    while (fast != null) {
        slow = slow.next;
        fast = fast.next;
    }

    // Remove the node
    slow.next = slow.next.next;

    return dummy.next;
}
```

---

## Complexity Analysis

### Two-Pass Approach
- **Time Complexity:** O(n) - Two passes through the list
- **Space Complexity:** O(n) - Creating new nodes for the result list

### Two-Pointer Approach
- **Time Complexity:** O(n) - Single pass through the list
- **Space Complexity:** O(1) - Only using constant extra space

---

## Example Walkthrough

**Input:** `head = [1,2,3,4,5]`, `n = 2`

### Two-Pass Approach:
1. Calculate length: 5
2. Position from start: 5 - 2 + 1 = 4
3. Remove 4th node (value 4)
4. **Output:** `[1,2,3,5]`

### Two-Pointer Approach:
1. Dummy → 1 → 2 → 3 → 4 → 5 → null
2. Fast moves 3 steps: dummy → 1 → 2 → 3
3. Move both: slow and fast move together
4. When fast reaches end (null), slow is at 3
5. Remove: slow.next = slow.next.next (skip 4)
6. **Output:** `1 → 2 → 3 → 5 → null`

---

## Edge Cases

- Removing the **head node** (n equals list length)
- Removing the **tail node** (n = 1)
- List with only **one node**
- **Empty list** (null head)

---

## Related Problems

- **Reverse Linked List** - Basic linked list manipulation
- **Merge Two Sorted Lists** - Combining linked lists
- **Linked List Cycle** - Detecting cycles in linked lists
- **Remove Linked List Elements** - Removing nodes by value

---

## Common Mistakes

1. **Off-by-one errors** - Remember the gap is `n+1`, not `n`
2. **Not using dummy node** - Fails when removing the head
3. **Forgetting to return** - Return `dummy.next`, not `head`
4. **Null pointer exceptions** - Check for empty list

---

## Tips & Tricks

- **Dummy node pattern:** Always use a dummy node when removing from the head
- **Two-pointer technique:** Powerful for single-pass solutions
- **Gap strategy:** Moving one pointer ahead by `n+1` creates the right distance

---

## Tags

#leetcode #linked-list #two-pointers #medium

---
*Source: LCPatterns/Medium/DeleteNfromEnd.java*
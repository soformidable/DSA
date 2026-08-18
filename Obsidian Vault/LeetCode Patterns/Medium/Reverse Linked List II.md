# Reverse Linked List II

## Problem Statement
Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return the reversed list.

## Example
```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Input: head = [5], left = 1, right = 1
Output: [5]
```

## Key Insight
We need to:
1. Navigate to position `left - 1` (node before reversal starts)
2. Reverse the sublist from `left` to `right`
3. Reconnect the reversed portion back to the original list

## Approach: Three-Pointer with Dummy Node

### Core Idea:
1. **Use a dummy node** to handle edge cases (reversing from head)
2. **Find the node before `left`** position (`prev`)
3. **Reverse the sublist** using standard linked list reversal
4. **Reconnect** the reversed sublist to the rest of the list

## Algorithm
```
reverseBetween(head, left, right):
    1. Create dummy node, point to head
    2. Move prev to node at position (left - 1)
    3. Set current = prev.next (start of reversal)
    4. Reverse (right - left + 1) nodes:
       - Use three pointers: reversePrev, current, next
       - Standard linked list reversal technique
    5. Reconnect:
       - prev.next.next = current (connect end of reversed to rest)
       - prev.next = reversePrev (connect start to reversed head)
    6. Return dummy.next
```

## Visual Walkthrough

### Example: head = [1,2,3,4,5], left = 2, right = 4

**Initial State:**
```
dummy → 1 → 2 → 3 → 4 → 5 → NULL
        ↑
       prev
```

**Step 1: Move prev to position left-1 = 1**
```
dummy → 1 → 2 → 3 → 4 → 5 → NULL
        ↑
       prev
       current = prev.next = 2
```

**Step 2: Reverse nodes from position 2 to 4 (3 nodes)**
```
Iteration 1: (i=0)
  next = current.next = 3
  current.next = reversePrev = NULL
  reversePrev = current = 2
  current = next = 3

List: 1 → 2 → NULL | 3 → 4 → 5 → NULL
      ↑              ↑
    prev          current

Iteration 2: (i=1)
  next = current.next = 4
  current.next = reversePrev = 2
  reversePrev = current = 3
  current = next = 4

List: 1 → 2 → NULL | 3 → 2 → NULL | 4 → 5 → NULL
                    ↑                ↑
                 reversePrev      current

Iteration 3: (i=2)
  next = current.next = 5
  current.next = reversePrev = 3
  reversePrev = current = 4
  current = next = 5

List: 1 → 2 → NULL | 4 → 3 → 2 → NULL | 5 → NULL
                    ↑                    ↑
                 reversePrev          current
```

**Step 3: Reconnect the reversed portion**
```
prev.next.next = current  → 2.next = 5
prev.next = reversePrev   → 1.next = 4

Final: dummy → 1 → 4 → 3 → 2 → 5 → NULL
```

## Code Implementation

### Solution from ReverseLL2.java
```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Edge case: empty list or single node
        if(head == null || head.next == null)
            return head;
        
        // Create dummy node to handle reversing from head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        
        // Move prev to node at position (left - 1)
        for(int i = 1; i < left; i++) {
            prev = prev.next;
        }
        
        // Start of reversal
        ListNode current = prev.next;
        ListNode reversePrev = null;
        ListNode next = null;
        
        // Reverse (right - left + 1) nodes
        for(int i = 0; i <= Math.abs(left - right); i++) {
            next = current.next;
            current.next = reversePrev;
            reversePrev = current;
            current = next;
        }
        
        // Reconnect the reversed portion
        prev.next.next = current;  // Connect end of reversed to rest
        prev.next = reversePrev;   // Connect start to reversed head
        
        return dummy.next;
    }
}
```

### Alternative Cleaner Implementation
```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right)
            return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        // Move to node before left position
        for(int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        
        ListNode curr = prev.next;
        
        // Reverse nodes between left and right
        for(int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        
        return dummy.next;
    }
}
```

### Recursive Solution
```java
class Solution {
    ListNode successor = null;
    
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Base case: left = 1, reverse first 'right' nodes
        if(left == 1) {
            return reverseN(head, right);
        }
        
        // Recurse: advance to position left
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }
    
    // Reverse first N nodes
    ListNode reverseN(ListNode head, int n) {
        if(n == 1) {
            successor = head.next;
            return head;
        }
        
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        
        return last;
    }
}
```

## Complexity Analysis

### Time Complexity: O(n)
- Single pass to find position `left`
- Single pass to reverse nodes from `left` to `right`
- Overall: O(n)

### Space Complexity
- **Iterative**: O(1) - only pointers
- **Recursive**: O(n) - recursion stack depth

## Edge Cases

1. **Reverse entire list**: left = 1, right = n
2. **Reverse single node**: left = right (no change needed)
3. **Reverse from head**: left = 1 (dummy node helps)
4. **Reverse to tail**: right = n
5. **Empty list**: head = null
6. **Single node list**: head.next = null

## Step-by-Step Example

### Input: [1,2,3,4,5], left = 1, right = 5 (reverse all)
```
Initial: dummy → 1 → 2 → 3 → 4 → 5 → NULL

After reversal:
dummy → 5 → 4 → 3 → 2 → 1 → NULL
```

### Input: [3,5], left = 1, right = 2
```
Initial: dummy → 3 → 5 → NULL

After reversal:
dummy → 5 → 3 → NULL
```

## Pattern Recognition
This problem combines:
1. **Linked List Traversal** - Navigate to specific position
2. **Linked List Reversal** - Standard reversal technique
3. **Dummy Node Pattern** - Handle edge cases elegantly

## Related Problems
- [[Reverse Linked List]] (206) - Reverse entire list
- [[Swap Nodes in Pairs]] (24) - Swap adjacent nodes
- [[Remove Nth Node From End]] (19) - Position-based operations
- [[Palindrome Linked List]] (234) - Uses partial reversal

## Common Mistakes to Avoid

### ❌ Mistake 1: Off-by-One Errors
```java
// WRONG: Moving to wrong position
for(int i = 0; i < left; i++)  // Goes to 'left' instead of 'left-1'

// CORRECT:
for(int i = 1; i < left; i++)  // Goes to 'left-1'
```

### ❌ Mistake 2: Losing Connection to Rest of List
```java
// WRONG: Not reconnecting properly
prev.next = reversePrev;  // Missing prev.next.next = current

// CORRECT:
prev.next.next = current;  // Connect end of reversed portion
prev.next = reversePrev;   // Connect start to reversed head
```

### ❌ Mistake 3: Not Using Dummy Node
```java
// WRONG: Doesn't handle left = 1 case
ListNode prev = head;

// CORRECT: Use dummy node
ListNode dummy = new ListNode(-1);
dummy.next = head;
ListNode prev = dummy;
```

## Tips & Tricks

1. **Always use a dummy node** for linked list problems - simplifies edge cases
2. **Draw the pointer movements** on paper before coding
3. **Understand the three key connections**:
   - `prev.next` → should point to reversed head
   - `prev.next.next` → should point to node after reversed portion
   - `reversePrev` → tracks the new head of reversed portion
4. **Count carefully**: `right - left + 1` nodes to reverse

#LeetCode #Medium #LinkedList #Reversal #TwoPointers
</contents>
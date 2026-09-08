# Linked List Cycle II

## Problem Statement
Given the `head` of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

A cycle exists when a node's `next` pointer points to a previously visited node in the list.

https://leetcode.com/problems/linked-list-cycle-ii/description/

## Example
```
Input: head = [3,2,0,-4], pos = 1
Output: Node with value 2

        3 → 2 → 0 → -4
              ↑________↓
Cycle starts at node 2
```

## Key Insight
Two main approaches:
1. **HashSet**: Track visited nodes, first duplicate is cycle start
2. **Floyd's Tortoise and Hare**: Two pointers with mathematical proof for cycle start

## Approach 1: HashSet (Simple Solution)

### Code Implementation
```java
class Solution {
    public ListNode detectCycle(ListNode head) {
        // Edge case: empty list has no cycle
        if(head == null)
            return null;
        
        ListNode current = head;
        // Store visited nodes to detect cycle back-reference
        HashSet<ListNode> visited = new HashSet<>();
        
        while(current != null) {
            // If we've seen this node before, we found the cycle start
            if(visited.contains(current)) {
                return current;
            }
            // Mark this node as visited
            visited.add(current);
            current = current.next;
        }
        
        // No cycle found - reached the end of the list
        return null;
    }
}
```

### Visual Walkthrough
```
List: 3 → 2 → 0 → -4 ↻ (back to node 2)

visited = {}, current = 3
Step 1: 3 not in set → add 3, set = {3}, current = 2
Step 2: 2 not in set → add 2, set = {3,2}, current = 0
Step 3: 0 not in set → add 0, set = {3,2,0}, current = -4
Step 4: -4 not in set → add -4, set = {3,2,0,-4}, current = 2
Step 5: 2 IS in set! → return node 2 ✅
```

## Approach 2: Floyd's Tortoise and Hare (Optimal - O(1) Space)

### Core Idea (The Math)
- **Phase 1**: Use slow/fast pointers to detect cycle and find meeting point
- **Phase 2**: Reset one pointer to head, move both at same speed → they meet at cycle start

When they meet:
- Slow has traveled: `d + k` (distance to meeting point)
- Fast has traveled: `d + k + m×C` (lapped the cycle m times)
- Since fast is twice as fast: `2(d+k) = d + k + m×C`
- Solving: `d = m×C - k`
- This proves: distance from head to cycle start = distance from meeting point to cycle start

### Code Implementation
```java
class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;
        
        // Phase 1: Find if cycle exists and meeting point
        ListNode slow = head;
        ListNode fast = head;
        
        // Move slow by 1 step, fast by 2 steps
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast)  // Meeting point found!
                break;
        }
        
        // No cycle if fast reached end
        if(fast == null || fast.next == null)
            return null;
        
        // Phase 2: Find cycle start
        slow = head;  // Reset slow to head
        
        // Both move 1 step at a time - they meet at cycle start
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;  // Cycle start node
    }
}
```

### Visual Walkthrough
```
List: 3 → 2 → 0 → -4 ↻ (cycle at node 2)

Phase 1 - Meeting Point Detection:
slow=3, fast=3
Step 1: slow=2, fast=0
Step 2: slow=0, fast=2  
Step 3: slow=-4, fast=-4 → MEET at node -4

Phase 2 - Finding Cycle Start:
Reset slow=head=3, fast stays at -4
Step 1: slow=2, fast=2 → MEET! Return node 2 ✅

Math: d=1 (3→2), k=2 (2→0→-4), C=3 (cycle length)
d = m×C - k → 1 = 1×3 - 2 ✅
```

## Complexity Analysis

| Approach | Time | Space |
|----------|------|-------|
| HashSet | O(n) | O(n) |
| Floyd's | O(n) | O(1) |

## Common Mistakes

### ❌ Mistake 1: Confusing Meeting Point with Cycle Start
```java
// WRONG: Meeting point ≠ cycle start
return fast;

// CORRECT: Must complete Phase 2
```

### ❌ Mistake 2: Not Checking Null Before Accessing next
```java
// WRONG: Can cause NullPointerException
fast = fast.next.next;

// CORRECT: Check bounds first
while(fast != null && fast.next != null)
```

### ❌ Mistake 3: Comparing Values Instead of References
```java
// WRONG: Different nodes can have same value
if(slow.val == fast.val)

// CORRECT: Compare node references
if(slow == fast)
```

## Pattern Recognition

This is a classic **Two Pointers** problem:
- Slow/Fast pointers for cycle detection
- Uses mathematical property: distances from head to cycle = distance from meeting point to cycle
- Related to: [[Linked List Cycle]], [[Find the Duplicate Number]]

## Related Problems
- [[Linked List Cycle]] - detects if cycle exists
- [[Merge Two Sorted Lists]] - also uses two pointers
- [[Remove Nth Node From End]] - two pointers technique

## Practice Tips
1. Understand the mathematical proof behind Floyd's algorithm
2. Practice both approaches - HashSet is intuitive, two pointers is optimal
3. Always draw the cycle diagram to visualize
4. Test edge cases: single node cycles, cycles at head, no cycles

#LeetCode #Medium #LinkedList #CycleDetection #TwoPointers #HashSet
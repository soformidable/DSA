# Swap Nodes in Pairs

**Problem Statement:**  
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

---

## Key Insight

This problem requires rearranging node connections rather than swapping values. The key is to maintain proper pointer connections while swapping pairs of adjacent nodes.

**Important:** We use a dummy node to simplify handling the head node during swaps.

---

## Solution: Iterative Pair Swapping

**Algorithm:**
1. Create a dummy node pointing to the head (handles edge case of swapping head)
2. Maintain `previous` pointer (last node of previous pair) and `current` pointer (first node of current pair)
3. For each pair:
   - Save `first` (current) and `second` (current.next)
   - Rearrange pointers to swap the pair
   - Move `previous` and `current` forward
4. Continue until no more pairs can be formed

---

## The Critical While Loop Condition Explained

```java
while(current != null && current.next != null)
```

This loop has **two conditions** that handle different scenarios:

### Condition 1: `current != null`
- **Purpose:** Ensures there is at least one node remaining
- **When needed:** For **odd-length lists**
- **Example:** List `1→2→3→4→5` (length 5)
  - After swapping pairs (1,2) and (3,4), we reach node 5
  - `current` points to node 5, `current.next` is null
  - This condition ensures we don't access null pointer when checking `current.next`

### Condition 2: `current.next != null`
- **Purpose:** Ensures there is a second node to form a pair
- **When needed:** For **both even and odd-length lists**
- **Example:** List `1→2→3→4` (length 4)
  - At node 3, `current.next` points to node 4 (valid pair)
  - At node 4 (after swap), `current.next` is null → stop

---

## Detailed Explanation: Why Both Conditions Are Necessary

### For Even-Length Lists:
**Example:** `1→2→3→4` (length 4)

| Step | current | current.next | Both Conditions | Action |
|------|---------|--------------|-----------------|--------|
| 1 | Node 1 | Node 2 | ✅ true & ✅ true | Swap (1,2) |
| 2 | Node 3 | Node 4 | ✅ true & ✅ true | Swap (3,4) |
| 3 | Node 4 | null | ✅ true & ❌ false | **STOP** |

- Condition `current != null`: Still true (node 4 exists)
- Condition `current.next != null`: **False** (node 4 has no next)
- Result: Loop stops correctly after swapping all pairs

### For Odd-Length Lists:
**Example:** `1→2→3→4→5` (length 5)

| Step | current | current.next | Both Conditions | Action |
|------|---------|--------------|-----------------|--------|
| 1 | Node 1 | Node 2 | ✅ true & ✅ true | Swap (1,2) |
| 2 | Node 3 | Node 4 | ✅ true & ✅ true | Swap (3,4) |
| 3 | Node 5 | null | ✅ true & ❌ false | **STOP** |

- After swapping (3,4), `current` moves to node 5
- `current` is not null (node 5 exists)
- `current.next` **is null** (node 5 has no next node)
- Result: Node 5 remains as-is (unpaired node stays in place)

---

## Code Implementation

```java
public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null)
        return head;

    ListNode dummy = new ListNode(-1);
    ListNode previous = dummy;
    dummy.next = head;
    ListNode current = head;

    while (current != null && current.next != null) {
        ListNode first = current;
        ListNode second = current.next;

        // Swap the pair
        first.next = second.next;
        second.next = first;
        previous.next = second;

        // Move pointers forward
        previous = first;
        current = first.next;
    }

    return dummy.next;
}
```

### Pointer Rearrangement Steps:

1. **Before swap:**
   ```
   previous → first → second → rest
   ````

2. **After swap:**
   ```
   previous → second → first → rest
   ```

3. **Pointer updates:**
   - `first.next = second.next` (first points to rest)
   - `second.next = first` (second points to first)
   - `previous.next = second` (previous points to second)

---

## Step-by-Step Walkthrough

**Input:** `1→2→3→4`

**Initial Setup:**
- dummy → 1 → 2 → 3 → 4 → null
- previous = dummy, current = Node 1

**Iteration 1:**
- first = Node 1, second = Node 2
- first.next = Node 3, second.next = Node 1
- dummy.next = Node 2
- Result: dummy → 2 → 1 → 3 → 4
- Update: previous = Node 1, current = Node 3

**Iteration 2:**
- first = Node 3, second = Node 4
- first.next = null, second.next = Node 3
- previous.next = Node 4
- Result: dummy → 2 → 1 → 4 → 3 → null
- Update: previous = Node 3, current = null

**Loop Ends:** current is null

**Return:** `2→1→4→3`

---

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through the list
- **Space Complexity:** O(1) - Only using constant extra space

---

## Edge Cases

- **Empty list** (`head == null`): Return null
- **Single node** (`head.next == null`): Return head as-is
- **Two nodes**: Swap and return new head
- **Odd-length lists**: Last node remains in place
- **Even-length lists**: All nodes are swapped

---

## Common Mistakes

1. **Only checking `current != null`**:
   - Would cause `NullPointerException` when accessing `current.next`
   - Even-length lists would try to swap last node with null

2. **Only checking `current.next != null`**:
   - Would cause `NullPointerException` when current is null
   - Odd-length lists would crash when reaching the end

3. **Forgetting dummy node**:
   - Head changes after swap, making it difficult to return new head
   - Dummy node provides a stable reference point

4. **Wrong pointer updates**:
   - Must update `previous` to `first` (not `second`)
   - Must update `current` to `first.next` (next unprocessed node)

---

## Recursive Solution

```java
public ListNode swapPairsRecursive(ListNode head) {
    if (head == null || head.next == null)
        return head;
    
    ListNode first = head;
    ListNode second = head.next;
    
    first.next = swapPairsRecursive(second.next);
    second.next = first;
    
    return second;
}
```

**Key differences:**
- No dummy node needed
- Recursion naturally handles the remaining list
- O(n) space due to recursion stack

---

## Related Problems

- **Reverse Nodes in k-Group** - Generalization of this problem
- **Swap Nodes in Linked List** - Swap nodes by position
- **Reverse Linked List** - Basic linked list reversal
- **Odd Even Linked List** - Separate odd and even positioned nodes

---

## Visual Summary: Why Two Conditions?

```
EVEN LENGTH LIST: 1→2→3→4
[1] current=1, next=2 ✅✅ → swap
[3] current=3, next=4 ✅✅ → swap
[4] current=4, next=null ✅❌ → STOP (last node is head of pair but no partner)

ODD LENGTH LIST: 1→2→3→4→5
[1] current=1, next=2 ✅✅ → swap
[3] current=3, next=4 ✅✅ → swap
[5] current=5, next=null ✅❌ → STOP (unpaired node stays in place)
```

**The magic of two conditions:**
- `current != null`: "Is there a node to process?"
- `current.next != null`: "Does this node have a partner to swap with?"

Both are needed to safely handle all cases!

---

## Tags

#leetcode #linked-list #recursion #medium

---
*Source: LCPatterns/Medium/SwapNodesInPairs.java*
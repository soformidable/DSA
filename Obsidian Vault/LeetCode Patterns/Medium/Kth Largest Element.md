# Kth Largest Element

**Problem Statement:**
Given an integer array `nums` and an integer `k`, return the `kth` largest element in the array. Note that it is the `kth` largest element in sorted order, not the `kth` distinct element.

---

## Problem:
https://leetcode.com/problems/kth-largest-element-in-an-array/description/

## Key Insight

Instead of sorting the entire array (O(n log n)), we can use a **min-heap of size `k`** to track only the `k` largest elements seen so far:

1. **Min-Heap Size `k`:** Maintain a min-heap that holds at most `k` elements. The root of this min-heap is the **smallest** among the `k` largest elements — i.e., the **kth largest**.

2. **Streaming Elimination:** As we iterate through the array, we push each element into the heap. If the heap exceeds size `k`, we **poll (remove) the smallest** element. This guarantees the heap always contains the `k` largest elements seen so far.

3. **Why Min-Heap instead of Max-Heap?** A min-heap of size `k` lets us efficiently discard the smallest candidate. A max-heap would require popping `k-1` elements at the end — less efficient.

4. **Result:** After processing all elements, `heap.peek()` returns the kth largest.

---

## Solution Approaches

### 1. Min-Heap of Size K (Optimal)

**Algorithm:**
1. Create a `PriorityQueue` (min-heap by default in Java).
2. Iterate through each number in the array:
   - Offer (add) the number to the heap.
   - If the heap size exceeds `k`, poll (remove) the smallest element.
3. Return `heap.peek()` — the kth largest.

**Why this works:**
- The min-heap always holds the `k` largest elements encountered.
- The root is the minimum of those `k` elements = the kth largest overall.
- Each offer/poll is O(log k), done `n` times → O(n log k).

---

## Code Implementation: Min-Heap of Size K

```java
import java.util.PriorityQueue;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {

        // Min-heap: by default PriorityQueue in Java is a min-heap (smallest at root).
        // We maintain at most k elements, so the root is always the kth largest.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process each number in the array
        for (int num : nums) {
            minHeap.offer(num);              // Add current number to the heap

            // If heap exceeds size k, remove the smallest element
            // This ensures we only track the k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();              // Remove smallest → heap stays at size k
            }
        }

        // Root of the min-heap = kth largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        // Example: nums = [3,2,1,5,6,4], k = 2 → output = 5
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
}
```

---

## Step-by-Step Walkthrough

**Input:** `nums = [3, 2, 1, 5, 6, 4]`, `k = 2`

**Goal:** Find the 2nd largest element.

**Min-Heap Trace (size capped at k = 2):**

| Step | Num | Action | Heap (min-heap) | Size |
|------|-----|--------|-----------------|------|
| 1 | 3 | offer 3 | [3] | 1 |
| 2 | 2 | offer 2 | [2, 3] | 2 |
| 3 | 1 | offer 1 → poll 1 | [2, 3] | 2 |
| 4 | 5 | offer 5 → poll 2 | [3, 5] | 2 |
| 5 | 6 | offer 6 → poll 3 | [5, 6] | 2 |
| 6 | 4 | offer 4 → poll 4 | [5, 6] | 2 |

**Final:** `minHeap.peek()` = **5** ✓ (2nd largest in `[1,2,3,4,5,6]`)

---

**Another Example:** `nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]`, `k = 4`

Sorted: `[1, 2, 2, 3, 3, 4, 5, 5, 6]` → 4th largest = **4**

| Step | Num | Heap after offer/poll |
|------|-----|-----------------------|
| 1 | 3 | [3] |
| 2 | 2 | [2, 3] |
| 3 | 3 | [2, 3, 3] |
| 4 | 1 | [1, 2, 3, 3] |
| 5 | 2 | [1, 2, 2, 3, 3] → poll 1 → [2, 2, 3, 3] |
| 6 | 4 | [2, 2, 3, 4] |
| 7 | 5 | [2, 3, 4, 5] |
| 8 | 5 | [3, 4, 5, 5] |
| 9 | 6 | [4, 5, 5, 6] |

**Result:** `peek()` = **4** ✓

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|-----------------|-------|
| Min-Heap of Size K | O(n log k) | O(k) | n = nums.length, k = target rank |

**Where:**
- **n** = `nums.length`
- **k** = target rank

**Time Complexity Explanation:**
- Each of the `n` elements is inserted into the heap: O(log k) per insertion.
- When heap size exceeds `k`, one poll: O(log k).
- Total: O(n log k)

**Space Complexity Explanation:**
- The heap stores at most `k` elements: O(k)

---

## Alternative Approaches (for reference)

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Sort and index | O(n log n) | O(1) or O(n) | `nums[n - k]` after sorting |
| Max-Heap of size n | O(n + k log n) | O(n) | Pop `k-1` times, then peek |
| Quickselect | O(n) avg, O(n²) worst | O(1) | Partition-based, like QuickSort |
| Min-Heap of size k | **O(n log k)** | **O(k)** | **Optimal for streaming / large n** |

---

## Key Techniques

1. **Bounded Min-Heap:** Keep only the `k` largest elements using a size-limited min-heap.
2. **Online / Streaming Algorithm:** Processes elements one at a time without storing the full array — ideal for data streams.
3. **Peek vs Poll:** `peek()` is O(1) and non-destructive; `poll()` removes and returns the root.

---

## Edge Cases

- **`k = 1`:** Return the maximum element.
- **`k = nums.length`:** Return the minimum element.
- **Duplicates:** Handles duplicates correctly (e.g., `[3, 3, 3]`, `k = 2` → 3).
- **Negative numbers:** Works — heap handles any integer.
- **Single element:** Return `nums[0]`.

---

## Visualization

**Input:** `nums = [3, 2, 1, 5, 6, 4]`, `k = 2`

```
Sorted:  [1, 2, 3, 4, 5, 6]
                     ↑  ↑
                   2nd  1st
                   largest largest

Result = 5 (2nd largest)
```

---

## Related Problems

- **Kth Smallest Element in an Array** — Same approach with a max-heap
- **Top K Frequent Elements** — Min-heap on frequencies
- **Kth Largest Element in a Stream** — Streaming variant (LeetCode 703)
- **Find K Pairs with Smallest Sums** — Min-heap on pair sums
- **K Closest Points to Origin** — Min-heap or Quickselect

---

## Common Mistakes

1. **Using a max-heap instead of a min-heap:**
   - A max-heap would need `k-1` polls at the end to reach the kth largest
   - Min-heap of size `k` is more efficient

2. **Forgetting to poll when size > k:**
   - The heap grows unbounded → O(n log n) instead of O(n log k)

3. **Off-by-one:**
   - `k` is 1-indexed: `k = 1` is the largest, `k = nums.length` is the smallest
   - After sorting: answer is `nums[n - k]` (0-indexed)

4. **Using `remove()` instead of `poll()`:**
   - `remove()` throws an exception on an empty heap
   - `poll()` returns `null` safely (or use `poll()` only when size > k)

5. **Returning the wrong heap property:**
   - `peek()` on a min-heap gives the **smallest** of the k largest = **kth largest** ✓
   - Don't confuse with max-heap's `peek()` which gives the overall largest

---

## Tags

#leetcode #heap #priority-queue #sorting #top-k #medium

---

## Additional Notes

### Pattern Recognition

This problem follows the **Top-K Elements** pattern:
- "Find the kth largest/smallest" → bounded heap or Quickselect
- "Top K frequent" → heap on frequency counts
- "K closest" → heap on distance metric

When `k` is small relative to `n`, the min-heap approach is optimal.

### Why Min-Heap of Size K is Elegant

- **Space-efficient:** O(k) instead of O(n)
- **Streaming-friendly:** Works even if `nums` is a data stream
- **Simple invariants:** "Heap holds the k largest seen so far" is easy to reason about

---

## Interview Tips

1. **Clarify the problem:**
   - Is `k` 1-indexed? (Yes, `k = 1` is the largest)
   - Are duplicates allowed? (Yes, handle them)
   - Is the input a stream or an array? (Determines if O(k) space matters)

2. **Start with brute force:**
   - Sort and pick `nums[n - k]` — O(n log n), O(1) space

3. **Optimize:**
   - "We only need the k largest, so use a bounded min-heap."
   - Explain why min-heap (not max-heap) is the right choice

4. **Walk through an example:**
   - Show the heap growing and shrinking at each step

5. **Analyze complexity:**
   - Time: O(n log k), Space: O(k)

6. **Discuss alternatives:**
   - Quickselect for O(n) average-case, in-place
   - Mention trade-offs (worst case O(n²) for Quickselect)

---

*Source: LCPatterns/Medium/KthLargestElement.java*
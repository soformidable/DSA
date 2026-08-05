# Merge Intervals

**Problem Statement:**  
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

## Problem:
https://leetcode.com/problems/merge-intervals/

---

## Key Insight

The key observation is that **overlapping intervals can be merged** if they share a common range. The solution requires:
1. Sorting the intervals by start time
2. Iterating through the sorted intervals and merging when overlaps occur

**Merge Condition:**
Two intervals `[a, b]` and `[c, d]` overlap if `b >= c`

---

## Solution: Sort and Merge

**Algorithm:**
1. Sort intervals by start time
2. Initialize result list with first interval
3. For each subsequent interval:
   - If current interval overlaps with last in result, merge them
   - Else, add current interval to result
4. Return merged intervals

**Why this works:**
- Sorting ensures we process intervals in order
- We only need to compare with the last merged interval
- Overlapping intervals are merged by extending the end time

---

## Code Implementation

```java
public static int[][] merge(int[][] intervals) {
    if (intervals.length <= 1)
        return intervals;

    // Sort intervals by start time
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> result = new ArrayList<>();
    int[] current = intervals[0];
    result.add(current);

    for (int[] interval : intervals) {
        if (current[1] >= interval[0]) {
            // Overlapping intervals, merge them
            current[1] = Math.max(current[1], interval[1]);
        } else {
            // Non-overlapping interval, add to result
            current = interval;
            result.add(current);
        }
    }

    return result.toArray(new int[result.size()][]);
}
```

---

## Step-by-Step Walkthrough

**Input:** `[[1,3],[2,6],[15,18],[13,17]]`

**Step 1:** Sort intervals (already sorted)

**Step 2:** Initialize result with `[1,3]`

**Step 3:** Process each interval:

| Current Interval | Last Merged | Overlap? | Action |
|------------------|-------------|---------|--------|
| [2,6] | [1,3] | Yes | Merge → [1,6] |
| [15,18] | [1,6] | No | Add [15,18] |
| [13,17] | [15,18] | Yes | Merge → [13,18] |

**Result:** `[[1,6],[13,18]]`

---

## Complexity Analysis

- **Time Complexity:** O(n log n) - Sorting dominates
- **Space Complexity:** O(n) - For result storage

---

## Key Techniques

1. **Sorting by start time:** Ensures we process intervals in order
2. **Single pass merge:** Only need to compare with last merged interval
3. **In-place merging:** Updates the end time of the last interval

---

## Edge Cases

- **Empty input** - Return empty array
- **Single interval** - Return as-is
- **Non-overlapping intervals** - Return all intervals
- **Completely overlapping intervals** - Return single merged interval
- **Adjacent intervals** - Merge if end >= next start

---

## Visualization

```
Input Intervals:
┌───────┐   ┌───────┐   ┌───────┐   ┌───────┐
│ 1-3   │   │ 2-6   │   │15-18  │   │13-17  │
└───────┘   └───────┘   └───────┘   └───────┘

After Merging:
┌───────────┐       ┌───────────┐
│ 1-6       │       │13-18      │
└───────────┘       └───────────┘
```

---

## Related Problems

- **Insert Interval** - Insert and merge a new interval
- **Non-overlapping Intervals** - Find maximum non-overlapping intervals
- **Meeting Rooms II** - Find minimum meeting rooms needed
- **Range Module** - Track and query ranges

---

## Common Mistakes

1. **Not sorting intervals:**
   - Without sorting, we can't guarantee order

2. **Incorrect merge condition:**
   - Must check `current[1] >= interval[0]`

3. **Forgetting to update current interval:**
   - After merge, must update `current` reference

4. **Not handling empty input:**
   - Need to check for empty array

---

## Tags

#leetcode #array #sorting #interval #medium

---
*Source: LCPatterns/Medium/MergeIntervals.java*
# Insert Interval

**Problem Statement:**
Given an array of non-overlapping intervals sorted by their start time, insert a new interval into the intervals (merge all overlapping intervals) and return an array of non-overlapping intervals that are sorted by their start time.

## Problem:
https://leetcode.com/problems/insert-interval/description/

---

## Key Insight

The solution involves three main phases:

1. **Add non-overlapping intervals before the new interval**
2. **Merge all overlapping intervals with the new interval**
3. **Add remaining non-overlapping intervals after the new interval**

**Algorithm:**
- Use three while loops to handle the three phases
- Track the current position in the intervals array
- Merge intervals by expanding the new interval's boundaries

---

## Solution: Three-Phase Insertion

**Algorithm:**
1. Add all intervals that end before the new interval starts
2. Merge all intervals that overlap with the new interval
3. Add the merged interval
4. Add all remaining intervals

**Why this works:**
- The input is already sorted, so we can process intervals in order
- We can efficiently find the insertion point and merge overlapping intervals
- The three-phase approach ensures we handle all cases correctly

---

## Code Implementation

```java
public static int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int n = intervals.length;

    // Phase 1: Add intervals before newInterval
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i]);
        i++;
    }

    // Phase 2: Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.add(newInterval);

    // Phase 3: Add remaining intervals
    while (i < n) {
        result.add(intervals[i]);
        i++;
    }

    return result.toArray(new int[result.size()][]);
}
```

---

## Step-by-Step Walkthrough

**Input:** `intervals = [[1,3],[6,9]]`, `newInterval = [2,5]`

**Phase 1:** Add intervals before newInterval
- `intervals[0] = [1,3]` ends at 3 < 2? No → skip
- No intervals added in this phase

**Phase 2:** Merge overlapping intervals
- `intervals[0] = [1,3]` starts at 1 ≤ 5 → merge
  - newInterval becomes [1,5]
- `intervals[1] = [6,9]` starts at 6 > 5 → stop

**Phase 3:** Add remaining intervals
- Add `[6,9]`

**Result:** `[[1,5],[6,9]]`

---

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through the intervals
- **Space Complexity:** O(n) - For the result list

---

## Key Techniques

1. **Three-phase approach:** Handles intervals before, during, and after the new interval
2. **Boundary expansion:** Merges overlapping intervals by expanding boundaries
3. **Efficient merging:** Only requires a single pass through the intervals

---

## Edge Cases

- **Empty intervals array** - Return array with just the new interval
- **New interval before all existing intervals** - Add new interval first
- **New interval after all existing intervals** - Add new interval last
- **New interval completely contained within existing intervals** - Merge with all overlapping
- **New interval completely contains existing intervals** - Merge all into new interval
- **New interval overlaps with multiple existing intervals** - Merge all overlapping

---

## Visualization

**Input:** `[[1,3],[6,9]]`, `newInterval = [2,5]`

```
Before Insertion:
┌───────┐   ┌───────┐
│ 1-3   │   │ 6-9   │
└───────┘   └───────┘

After Insertion:
┌───────┐   ┌───────┐
│ 1-5   │   │ 6-9   │
└───────┘   └───────┘
```

---

## Related Problems

- **Merge Intervals** - Merge all overlapping intervals
- **Non-overlapping Intervals** - Find maximum non-overlapping intervals
- **Meeting Rooms II** - Find minimum meeting rooms needed
- **Interval List Intersections** - Find intersections between two interval lists

---

## Common Mistakes

1. **Not handling empty intervals array:**
   - Need to check for empty input

2. **Incorrect merge condition:**
   - Must check `intervals[i][0] <= newInterval[1]` for overlap

3. **Not updating newInterval boundaries:**
   - Must update both start and end boundaries during merge

4. **Forgetting to add remaining intervals:**
   - Must add all intervals after the merged interval

5. **Not handling cases where newInterval is completely contained:**
   - Must merge with all overlapping intervals

---

## Tags

#leetcode #array #sorting #interval #medium

---

## Additional Notes

This problem builds on the Merge Intervals problem but adds the complexity of inserting a new interval. The key difference is that we need to:

1. First identify where the new interval should be inserted
2. Then merge it with any overlapping intervals
3. Finally, add the remaining intervals

The solution maintains the sorted order of intervals, which is crucial for efficiently finding overlaps.

---

## Example Walkthroughs

**Example 1:**
Input: `intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]]`, `newInterval = [4,8]`

Result: `[[1,2],[3,10],[12,16]]`

**Example 2:**
Input: `intervals = [[1,5]]`, `newInterval = [2,7]`

Result: `[[1,7]]`

**Example 3:**
Input: `intervals = [[1,4],[7,9]]`, `newInterval = [2,5]`

Result: `[[1,5],[7,9]]`

---

*Source: LCPatterns/Medium/InsertInterval.java*
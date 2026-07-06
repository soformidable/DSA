"# Remove Covered Intervals

## Problem
https://leetcode.com/problems/remove-covered-intervals/description/?envType=daily-question&envId=2026-07-06

Given a list of intervals `[start, end]`, an interval `[a, b)` is **covered** by another interval `[c, d)` if `c <= a` and `b <= d` (i.e., the first interval is completely inside the second). Return the **number of remaining intervals** after removing all intervals that are covered by another interval.

## Code:
https://github.com/soformidable/DSA/blob/main/Daily/RemoveCoveredIntervals.java

**Note:** An interval `[a, b)` covers `[c, d)` if and only if the covering interval starts **at or before** the covered one and ends **at or after** the covered one. Overlapping but not containing each other does not count as covering.

## Intuition

The key idea is: after sorting cleverly, we can scan just once and count only those intervals that "extend the frontier" — i.e., intervals whose end point is larger than any end point seen so far.

**Sorting strategy:**
- Sort by **start ascending** (earlier start first).
- If two intervals have the same start, sort by **end descending** (longer interval first).

Why descending end for ties? Consider `[1, 4]` and `[1, 6]` — they start at the same point. The longer one `[1, 6]` should come first because:
- `[1, 4]` is covered by `[1, 6]` (6 >= 4).
- If `[1, 4]` came first, `maxEnd = 4`, then `[1, 6]` has end 6 > 4 → we'd count both, which is wrong.
- If `[1, 6]` comes first, `maxEnd = 6`, then `[1, 4]` has end 4 ≤ 6 → correctly skipped as covered.

After sorting, any interval whose end is **greater than the max end seen so far** is NOT covered by any previous interval (because its start is >= all previous starts but its end exceeds all previous ends). All other intervals are covered by some earlier interval and are removed.

## Algorithm

1. Sort `intervals` by:
   - **Start** ascending (primary key).
   - **End** descending (secondary key — longest interval first when starts tie).
2. Initialize `count = 0` and `maxEnd = 0`.
3. For each interval `[start, end]`:
   - If `end > maxEnd`:
     - This interval is **not covered** by any previous interval. Increment `count`.
     - Update `maxEnd = end`.
   - Else (`end <= maxEnd`):
     - This interval is covered by a previous interval (which started at or before this one and ends at or after this one). Skip it.
4. Return `count`.

## Java Implementation

```java
import java.util.Arrays;

public class RemoveCoveredIntervals {

    public static int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // Sort by start ascending, then end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];   // start ascending
            return b[1] - a[1];       // end descending (longer first)
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                count++;              // not covered
                maxEnd = interval[1]; // extend frontier
            }
            // else: covered → skip
        }

        return count;
    }

    public static void main(String args[]) {
        int arr[][] = new int[][]{{1, 4}, {3, 6}, {2, 8}};
        System.out.println(removeCoveredIntervals(arr));
        // Output: 2 (only [1,4] and [2,8] remain; [3,6] is covered by [2,8])
    }
}
```

## Example Walkthrough

**Input:** `[[1, 4], [3, 6], [2, 8]]`

| Step | Sorted Intervals | maxEnd (before) | interval[1] | `> maxEnd`? | count after | maxEnd (after) |
|------|-----------------|-----------------|-------------|-------------|-------------|----------------|
| 1    | `[1, 4]`        | 0               | 4           | Yes         | 1           | 4              |
| 2    | `[2, 8]`        | 4               | 8           | Yes         | 2           | 8              |
| 3    | `[3, 6]`        | 8               | 6           | No (6 ≤ 8)  | 2           | 8              |

**Result:** 2 (only `[1, 4]` and `[2, 8]` remain).

### Why `[3, 6]` is covered by `[2, 8]`

Check: `c = 2 <= a = 3` and `b = 6 <= d = 8` → **Yes**.

## Key Points for Revisit

- **Sorting is critical** — start ascending, end descending for ties.
- **Single pass** — after sorting, just check if the current interval's end extends the frontier.
- **Time complexity:** `O(N log N)` due to sorting, `O(1)` space (excluding input).
- **Space complexity:** `O(1)` (in-place sorting).
- **Edge case:** Empty input → return 0.
- **Note:** The problem assumes inclusive ranges `[start, end]` — adjust the condition if ranges are exclusive.

**Related problems:**
- Merge intervals
- Non-overlapping intervals
- Interval list intersections

**File:** `Daily/RemoveCoveredIntervals.java`"
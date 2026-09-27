# Find Peak Element (Binary Search / Linear Scan Pattern)

## Overview
This file summarizes the solution for finding a peak element in an array. A peak element is an element that is **strictly greater than its neighbors**. The solution includes both a **linear scan** and an optimised **binary search** approach.

---

## Problem Description
A peak element is an element that is strictly greater than its neighbors. Given an integer array `nums`, find a peak element, and return its index. If the array contains multiple peaks, return the index of any one of them. The solution must run in **O(log n)** time.

Note: `nums[-1]` and `nums[n]` are treated as `-∞`, so elements at the boundaries can also be peaks.


https://leetcode.com/problems/find-peak-element/description/

---

## Key Insight
- The array is **not necessarily sorted**, but a binary search still works because moving towards the side with a larger neighbour guarantees finding a peak.
- If `nums[mid] < nums[mid + 1]`, then there exists a peak in the **right half** (because the sequence is increasing at `mid`).
- Otherwise, there exists a peak in the **left half** (including `mid`).

---

## Solution Approach

### 1. Linear Scan (Simpler, O(n))
1. Handle edge cases: single element, first element being a peak, last element being a peak.
2. Iterate from index `1` to `n - 2`:
   - Track the previous element.
   - If the current element is greater than both its previous and next neighbours, return its index.
   - Otherwise, update `prev`.

### 2. Binary Search (Optimal, O(log n))
1. Handle the empty-array edge case.
2. Initialize `left = 0` and `right = nums.length - 1`.
3. While `left < right`:
   - Compute `mid`.
   - If `nums[mid] < nums[mid + 1]`, the peak lies to the right → `left = mid + 1`.
   - Else, the peak lies to the left or at `mid` → `right = mid`.
4. When `left == right`, that index is a peak.

---

## Solution Code
\`\`\`java LCPatterns/Medium/PeakElementLogn.java
public class PeakElementLogn {

    // Linear scan approach - O(n) time, O(1) space
    public static int findPeakElement(int[] nums) {
        // Single element is always a peak
        if (nums.length == 1)
            return 0;

        // Check if the first element is a peak
        if (nums[0] > nums[1])
            return 0;

        // Check if the last element is a peak
        if (nums[nums.length - 1] > nums[nums.length - 2])
            return nums.length - 1;

        int prev = nums[0];

        // Scan the middle elements
        for (int i = 1; i < nums.length - 1; i++) {
            // Current element is greater than both neighbours → peak found
            if (nums[i] > nums[i + 1] && nums[i] > prev)
                return i;
            else
                prev = nums[i]; // Update previous for the next iteration
        }

        return -1; // There is always a peak per problem constraints, so this shouldn't be reached
    }

    // Binary search approach - O(log n) time, O(1) space
    public static int findPeakElementBS(int[] nums) {
        if (nums.length == 0)
            return 0;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + ((right - left) / 2); // Avoid overflow

            // If the element to the right of mid is larger,
            // the peak must be in the right half
            if (nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid; // Peak is in the left half (including mid)
        }

        return left; // left == right points to a peak
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(findPeakElementBS(new int[]{1, 2, 3, 5}));
    }
}
\`\`\`

---

## Explanation

### Linear Scan
- Uses the fact that a peak just needs to be greater than its immediate neighbours.
- Handles boundary cases separately because boundary elements only have one neighbour.
- Time: O(n) — checks every element in the worst case.

### Binary Search
- Works because of the **monotonic property**: if `nums[mid] < nums[mid + 1]`, the sequence is increasing at that point, so a peak is guaranteed to exist on the right side. Otherwise, a peak exists on the left side (including `mid`).
- Narrowing down the search space by half each iteration gives O(log n) time.
- No need to compare both neighbours — comparing `nums[mid]` with `nums[mid + 1]` is sufficient to decide the direction.

---

## Time and Space Complexity

| Approach       | Time Complexity | Space Complexity |
|----------------|-----------------|------------------|
| Linear Scan    | O(n)            | O(1)             |
| Binary Search  | O(log n)        | O(1)             |

---

## Example

**Input**: `[1, 2, 1, 3, 5, 6, 4]`

- Peaks exist at index `1` (value `2`) and index `5` (value `6`).
- `findPeakElement` returns `1` (the first peak found by the linear scan).
- `findPeakElementBS` may return `5` depending on the search path; both are valid answers.

**Input**: `[1, 2, 3, 5]`

- The array is strictly increasing, so the last element (`5`) is the only peak.
- `findPeakElementBS` returns `3`.

---

## Edge Cases
- Single-element array: that element is the peak.
- Strictly increasing array: the last element is the peak.
- Strictly decreasing array: the first element is the peak.
- Array with multiple peaks: any valid peak index is acceptable.

---

## Related Patterns
- **Binary Search on Unsorted Arrays** — using local comparisons to narrow the search space.
- Problems like *Search in Rotated Sorted Array* and *Find Minimum in Rotated Sorted Array* follow a similar pattern of comparing neighbours to decide the search direction.

---
# Find Minimum in Rotated Sorted Array (Binary Search Pattern)

## Overview
This file summarizes the solution for finding the minimum element in a rotated sorted array. The solution uses a **binary search** approach to achieve O(log n) time complexity.

---

## Problem Description
Suppose an array of length `n` sorted in ascending order is rotated between `1` and `n` times. For example, the array `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`. Given the rotated array `nums`, return the **minimum element** of this array.

https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/

The solution must run in **O(log n)** time.

---

## Key Insight
- In a rotated sorted array, the minimum element is the **pivot point** where the rotation occurred.
- Comparing the middle element with the **rightmost element** tells us which half contains the minimum:
  - If `nums[mid] > nums[end]`, the minimum lies in the **right half** (because the rotation point is somewhere after `mid`).
  - Otherwise, the minimum lies in the **left half** (including `mid`).

---

## Solution Approach
1. **Edge Case**: If the array has only one element, that element is the minimum.
2. **Binary Search**: Use two pointers, `start` and `end`, to define the search range.
3. **Mid Calculation**: Compute `mid = start + (end - start) / 2` to avoid integer overflow.
4. **Compare with `nums[end]`**:
   - If `nums[mid] > nums[end]`, move `start` to `mid + 1` (minimum is in the right half).
   - Else, move `end` to `mid` (minimum is in the left half, possibly `mid` itself).
5. **Termination**: When `start == end`, that index points to the minimum element.

---

## Solution Code (Fixed & Commented)
\`\`\`java LCPatterns/Medium/MinimumInRotatedSortedArray.java
public class MinimumInRotatedSortedArray{
    public static int findMin(int[] nums) {
        // Edge case: single element array
        if (nums.length == 1) {
            return nums[0];
        }

        int start = 0, end = nums.length - 1;

        // Binary search for the minimum element
        // The minimum is the element that has a greater element to its right
        // (i.e., the "pivot" point where rotation happened)
        while (start < end) {
            int mid = start + (end - start) / 2;

            // If mid element is greater than the rightmost element,
            // the minimum lies in the right half (excluding mid)
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            }
            // Otherwise, the minimum lies in the left half (including mid)
            else {
                end = mid;
            }
        }

        // start == end, pointing to the minimum element
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2})); // Output: 1
    }
}
\`\`\`

---

## Bug in the Original Code
- The original version compared `nums[mid]` with `nums[start]`, which is incorrect for a rotated sorted array. This can lead to an infinite loop or wrong results.
- The correct approach is to compare with `nums[end]` to determine which half contains the rotation point.

---

## Time and Space Complexity
- **Time Complexity**: O(log n), where `n` is the length of the array.
- **Space Complexity**: O(1), as only a few integer variables are used.

---

## Example
**Input**: `[3, 4, 5, 1, 2]`

| Step | start | end | mid | nums[mid] | nums[end] | Action |
|------|-------|-----|-----|-----------|-----------|--------|
| 1    | 0     | 4   | 2   | 5         | 2         | start = 3 |
| 2    | 3     | 4   | 3   | 1         | 2         | end = 3 |
| 3    | 3     | 3   | —   | —         | —         | loop ends, return nums[3] = 1 |

**Output**: `1` ✅

---

## Edge Cases
- Array with no rotation: `[1, 2, 3, 4]` → returns `1`.
- Array rotated `n` times (same as original): `[1, 2, 3, 4]` → returns `1`.
- Array with all equal elements: `[2, 2, 2]` → returns `2`.
- Array with a single element: `[5]` → returns `5`.

---

## Related Patterns
This is a classic **binary search on a rotated array** problem. The same principle can be extended to:
- Finding a target element in a rotated sorted array.
- Searching for a target in a rotated sorted array with duplicates.
- Finding the peak element in an array.

---
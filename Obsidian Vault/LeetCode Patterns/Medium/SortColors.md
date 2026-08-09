# Sort Colors (Dutch National Flag Pattern)

## Overview
This file summarizes the solution for the problem of sorting an array of integers containing only `0`s, `1`s, and `2`s. The solution uses the **Dutch National Flag algorithm**, which efficiently sorts the array in a single pass with constant space complexity.

## Problem:
https://leetcode.com/problems/sort-colors/description/

---

## Problem Description
Given an array `nums` containing only integers `0`, `1`, and `2`, sort the array in-place such that all `0`s come before all `1`s, and all `2`s come after them. The solution should be efficient and use constant extra space.

---

## Key Insight
The Dutch National Flag algorithm is a partitioning technique that sorts the array in a single traversal. It uses three pointers:
- `low`: Tracks the boundary of the `0`s section.
- `mid`: The current element being processed.
- `high`: Tracks the boundary of the `2`s section.

The algorithm ensures that:
- All elements before `low` are `0`s.
- All elements after `high` are `2`s.
- The elements between `low` and `high` are `1`s.

---

## Solution Approach
1. Initialize three pointers: `low = 0`, `mid = 0`, and `high = nums.length - 1`.
2. Traverse the array with `mid`:
   - If `nums[mid] == 0`, swap it with `nums[low]` and increment both `low` and `mid`.
   - If `nums[mid] == 1`, simply increment `mid` (no swap needed).
   - If `nums[mid] == 2`, swap it with `nums[high]` and decrement `high` (do not increment `mid` because the swapped element from `high` needs to be rechecked).
3. Repeat until `mid` surpasses `high`.

---

## Solution Code
```java LCPatterns/Medium/SortColors.java
public class SortColors {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
                // Do not increment mid because the swapped element needs to be rechecked
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

---

## Explanation
1. **Initialization**: The pointers `low`, `mid`, and `high` are set to the start, start, and end of the array, respectively.
2. **Traversal**: The loop runs while `mid <= high`:
   - If `nums[mid]` is `0`, it is swapped with `nums[low]`, and both `low` and `mid` are incremented. This ensures `0`s are moved to the left side.
   - If `nums[mid]` is `1`, `mid` is simply incremented, as `1`s belong in the middle.
   - If `nums[mid]` is `2`, it is swapped with `nums[high]`, and `high` is decremented. The `mid` pointer is **not** incremented because the element swapped from `high` could be `0` or `1` and needs to be rechecked.
3. **Termination**: The loop terminates when `mid` surpasses `high`, indicating the array is fully sorted.

---

## Time and Space Complexity
- **Time Complexity**: O(n), where `n` is the number of elements in the array. The algorithm processes each element exactly once.
- **Space Complexity**: O(1), as the sorting is done in-place with only a constant amount of additional space used for the pointers and temporary variables.

---

## Why This Approach?
The Dutch National Flag algorithm is optimal for this problem because:
- It sorts the array in a single pass.
- It uses constant space, making it memory-efficient.
- It handles all three possible values (`0`, `1`, and `2`) in a unified manner.

---

## Example
Consider the input array `[2, 0, 2, 1, 1, 0]`. After running the algorithm, the array becomes `[0, 0, 1, 1, 2, 2]`.

---
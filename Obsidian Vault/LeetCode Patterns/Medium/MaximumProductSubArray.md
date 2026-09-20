# Maximum Product Subarray (Dynamic Programming Pattern)

## Overview
This file summarizes the solution for finding the contiguous subarray within an array that has the largest product. The solution uses a **dynamic programming** approach that tracks both the maximum and minimum product ending at each position.

---

## Problem Description
Given an integer array `nums`, find a contiguous non-empty subarray within the array that has the largest product, and return the product. The test cases are generated so that the answer will fit in a 32-bit integer.

https://leetcode.com/problems/maximum-product-subarray/description/

---

## Key Insight
- A negative number multiplied by a negative number produces a positive result, so when we encounter a negative number, the **minimum product** ending at the current position can become the new maximum.
- To handle this, we track **both** the maximum and minimum product ending at each index. When the current element is negative, the roles of max and min swap.

---

## Solution Approach
1. **Edge Case**: If the array has only one element, that element is the answer.
2. **Initialize Variables**:
   - `minProd`: Minimum product ending at the current position (initialized to `nums[0]`).
   - `maxProd`: Maximum product ending at the current position (initialized to `nums[0]`).
   - `result`: Overall maximum product (initialized to `nums[0]`).
3. **Iterate Through the Array** (from index 1):
   - For each element `current`:
     - Consider three candidates:
       1. `current` alone (start a new subarray).
       2. `maxProd * current` (extend the previous max-product subarray).
       3. `minProd * current` (extend the previous min-product subarray).
     - Update `maxProd` to the maximum of the three candidates.
     - Update `minProd` to the minimum of the three candidates.
   - Update `result` to the maximum of the current `maxProd` and the previous `result`.
4. **Return** `result`.

---

## Solution Code
\`\`\`java LCPatterns/Medium/MaximumProductSubArray.java
public class MaximumProductSubArray {

    public static int maxProduct(int[] nums) {
        // Edge case: single element array
        if (nums.length == 1) {
            return nums[0];
        }

        int minProd = nums[0]; // Minimum product ending at current index
        int maxProd = nums[0]; // Maximum product ending at current index
        int result = nums[0];  // Overall maximum product found so far

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // Candidates for the new max/min product ending at i
            int candidate1 = current;          // Start a new subarray
            int candidate2 = maxProd * current; // Extend previous max-product subarray
            int candidate3 = minProd * current; // Extend previous min-product subarray

            // Update max and min products
            maxProd = Math.max(candidate1, Math.max(candidate2, candidate3));
            minProd = Math.min(candidate1, Math.min(candidate2, candidate3));

            // Update the global result
            result = Math.max(maxProd, result);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2, 3, -4})); // Output: 24
    }
}
\`\`\`

---

## Explanation
1. **Why track the minimum?** If the current number is negative, multiplying it by the minimum product (which is also negative) could yield a large positive number. Therefore, we must keep both the maximum and minimum products.
2. **Candidate 1 (`current`)**: Sometimes it's better to start a fresh subarray from the current element, especially if the previous product was negative or zero.
3. **Candidate 2 (`maxProd * current`)**: Extends the best positive product subarray.
4. **Candidate 3 (`minProd * current`)**: Extends the most negative product subarray; this becomes useful when `current` is negative.
5. **Updating `result`**: The global maximum is updated with the current `maxProd` after each step.

---

## Time and Space Complexity
- **Time Complexity**: O(n), where `n` is the length of the array. Each element is processed exactly once.
- **Space Complexity**: O(1), as only three integer variables are used for tracking.

---

## Example
**Input**: `[-2, 3, -4]`

| i | current | candidate1 | candidate2 | candidate3 | maxProd | minProd | result |
|---|---------|-------------|-------------|-------------|----------|----------|--------|
| 0 | -2      | —           | —           | —           | -2       | -2       | -2     |
| 1 | 3       | 3           | -6          | -6          | 3        | -6       | 3      |
| 2 | -4      | -4          | -12         | 24          | 24       | -12      | 24     |

**Output**: `24` (subarray `[-2, 3, -4]`)

---

## Edge Cases
- Array with only one element: `[5]` → returns `5`.
- Array with zeros: `[-2, 0, -1]` → returns `0` (the largest product is `0` or `-1`, so `0` is correct).
- Array with all negative numbers: `[-1, -2, -3]` → returns `6` (subarray `[-1, -2, -3]`).

---

## Related Patterns
This problem is a classic variation of **Kadane's Algorithm**, adapted for multiplication instead of addition. The key difference is tracking both the max and min because of the sign-flipping property of multiplication.

---
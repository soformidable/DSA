# Maximum Element After Decrementing and Rearranging

## Problem
https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/description/?envType=daily-question&envId=2026-06-28

## Solution:


Given an array of positive integers, you can perform two operations any number of times:
1. **Decrement** any element by 1 (can be done multiple times on the same element).
2. **Rearrange** the array in any order.

Goal: Maximize the **largest value** in the array after making the array **strictly increasing** (i.e., `arr[i] < arr[i+1]` for all `i`) with the constraint that `arr[0]` must be `1`.

## Intuition

Think of building a sequence like `1, 2, 3, 4, ...` as high as possible:

- The first element **must** be `1` (since we can decrement any number to 1).
- For each subsequent position, we want the value to be **as large as possible** while still being **exactly 1 more than the previous** (to keep it strictly increasing).
- We can only **decrease** numbers, never increase them. So the value at position `i` is limited by both:
  - The original value `arr[i]` (can't go above it)
  - The requirement `arr[i] = arr[i-1] + 1` (must be exactly 1 more than previous)

This leads to a greedy approach: after sorting, each element can be at most `min(original_value, previous_value + 1)`.

## Algorithm

### Approach 1: In-place modification (less elegant)

1. Sort the array.
2. Set `arr[0] = 1` (first element must be 1).
3. For each subsequent element:
   - If `|prev - arr[i]| <= 1`, keep `arr[i]` as is (it's already valid or off by 1).
   - Otherwise, set `arr[i] = prev + 1` (decrement it down to exactly 1 more than previous).
4. Track the maximum value seen.

### Approach 2: Optimized (elegant one-liner)

```java
Arrays.sort(arr);
int result = 1;  // First element must be 1
for (int i = 1; i < arr.length; i++) {
    result = Math.min(arr[i], result + 1);
}
return result;
```

**Why this works:**
- `result` always holds the maximum possible value at the current position.
- `result + 1` is the ideal next value (strictly increasing by 1).
- `arr[i]` is the ceiling (can't go above original value).
- Taking `min` ensures we never exceed either constraint.
- After the loop, `result` is the maximum possible last element.

## Java Implementation (`MaxElementIncDec.java`)

```java
import java.util.Arrays;

public class MaxElementIncDec {

    // Optimized version
    public static int maximumElementAfterDecrementingAndRearranging_optmized(int[] arr) {
        Arrays.sort(arr);
        
        int result = 1;  // First element must be 1
        
        for (int i = 1; i < arr.length; i++) {
            result = Math.min(arr[i], result + 1);
        }
        return result;
    }

    public static void main(String args[]) {
        int arr[] = new int[]{78, 93, 9};
        System.out.println(maximumElementAfterDecrementingAndRearranging_optmized(arr));
        // Output: 3  (sequence becomes [1, 2, 3])
    }
}
```

## Example Walkthrough

Input: `[78, 93, 9]`

| Step | Sorted arr | result | `Math.min(arr[i], result + 1)` | Explanation |
|------|------------|--------|--------------------------------|-------------|
| Init | `[9, 78, 93]` | 1 | – | First element must be 1 |
| i=1  | `[9, 78, 93]` | 2 | `min(78, 1+1) = 2` | Can only go up to 2 |
| i=2  | `[9, 78, 93]` | 3 | `min(93, 2+1) = 3` | Can only go up to 3 |

**Result:** 3 → final array `[1, 2, 3]`.

Another example: `[1, 2, 2, 100]`

| Step | result | `min(arr[i], result + 1)` |
|------|--------|----------------------------|
| Init | 1      | – |
| i=1  | 2      | `min(2, 1+1) = 2` |
| i=2  | 2      | `min(2, 2+1) = 2` (can't go above original 2) |
| i=3  | 3      | `min(100, 2+1) = 3` |

**Result:** 3 → final array `[1, 2, 2, 3]` (wait — this isn't strictly increasing! But the problem actually allows `arr[i] <= arr[i+1]`? Let me check...)

**Correction:** The problem statement says "strictly increasing" but the optimized solution actually produces a **non-decreasing** sequence (allows equal values). The key insight is that we want to **maximize the last element**, and the constraint is `|arr[i] - arr[i-1]| <= 1` (difference at most 1), not strictly increasing. So `[1, 2, 2, 3]` is valid because adjacent differences are `1, 0, 1` — all ≤ 1.

## Key Points for Revisit

- **Sort first** — always the starting point for greedy rearrangement problems.
- **First element must be 1** — decrement the smallest element to 1.
- **Greedy formula:** `result = Math.min(arr[i], result + 1)` — each step can only go up by at most 1 from the previous value, and can never exceed the original number.
- **Result is the answer** — after the loop, `result` is the maximum possible last element (and thus the maximum element in the array).
- **Complexity:** `O(N log N)` due to sorting, `O(1)` extra space.
- **Edge case:** Single element array → always return 1.
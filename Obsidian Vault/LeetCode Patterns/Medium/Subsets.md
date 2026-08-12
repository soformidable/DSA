# Subsets (Backtracking Pattern)

## Overview
This file summarizes the solution for generating all possible subsets of a given set of integers. The solution uses a **backtracking** approach to explore all subsets efficiently.

---

## Problem Description
Given an integer array `nums`, return **all possible subsets** (the power set). The solution should not contain duplicates, and the subsets should be sorted in ascending order.

## Problem:
https://leetcode.com/problems/subsets/description/

---

## Key Insight
- **Subsets** are all possible combinations of elements from the given set, including the empty set and the set itself.
- **Backtracking** is a natural fit for this problem because it allows us to explore all possible subsets by recursively including or excluding elements.

---

## Solution Approach
1. **Backtracking Function**: Define a recursive function to build subsets:
   - Start from a given index to avoid reusing the same elements in different orders.
   - At each step, include the current element in the current subset and recursively explore further.
   - After exploring, backtrack by removing the current element to explore subsets without it.
3. **Base Case**: The recursion naturally terminates when all elements have been processed.
4. **Recursive Case**: For each element starting from the current index, include it in the current subset, recurse, and then backtrack (remove it) to explore other possibilities.

---

## Solution Code
```java LCPatterns/Medium/Subsets.java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset to the result
        result.add(new ArrayList<>(current));
        
        // Iterate through the remaining elements
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]); // Include nums[i] in the current subset
            backtrack(nums, i + 1, current, result); // Recurse with the next index
            current.remove(current.size() - 1); // Backtrack: remove nums[i]
        }
    }
}
```

---

## Explanation
1. **Backtracking Function**: The `backtrack` function is called recursively:
   - `nums`: The input array.
   - `start`: The starting index to avoid revisiting the same elements.
   - `current`: The current subset being built.
   - `result`: The list to store all valid subsets.
3. **Base Case**: The current subset is added to `result` at the beginning of each recursive call. This ensures all subsets, including the empty set, are captured.
4. **Recursive Case**: For each index `i` starting from `start`, the element `nums[i]` is added to `current`, and the function recurses with `i + 1` to ensure no duplicates. After the recursive call, the element is removed (backtracked) to explore subsets without it.

---

## Time and Space Complexity
- **Time Complexity**: O(2^n), where `n` is the number of elements in `nums`. This is because there are 2^n possible subsets for a set of size `n`. Each subset is generated in O(n) time.
- **Space Complexity**: O(n) for the recursion stack (due to backtracking) and O(n * 2^n) to store the result.

---

## Example
For `nums = [1, 2, 3]`, the subsets are:
- `[]` (empty set)
- `[1]`
- `[1, 2]`
- `[1, 2, 3]`
- `[1, 3]`
- `[2]`
- `[2, 3]`
- `[3]`

---

## Relation to Combinations
- **Subsets** are a generalization of **combinations** where the size of the subset can vary from `0` to `n`. In contrast, combinations are subsets of a specific size `k`.

---
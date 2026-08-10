# Combinations (Backtracking Pattern)

## Overview
This file summarizes the solution for generating all possible combinations of size `k` from a int `n` from **1 to n**. The solution uses a **backtracking** approach to explore all possible combinations efficiently.

---

## Problem Description
Given an integer `n` and an integer `k`, return **all possible subsets** of size `k`. The solution should not contain duplicates, and the subsets should be sorted in ascending order.

## Problem:
https://leetcode.com/problems/combinations/description/

---

## Key Insight
- **Combinations** are subsets of a given size where the order of elements does not matter. For example, `[1, 2]` and `[2, 1]` are considered the same combination.
- **Backtracking** is a natural fit for this problem because it allows us to explore all possible subsets by recursively including or excluding elements.

---

## Solution Approach
1. **Sort the Input Array**: Sorting ensures that combinations are generated in ascending order, avoiding duplicates.
2. **Backtracking Function**: Define a recursive function to build combinations:
   - Start from a given index to avoid reusing the same elements in different orders (which would lead to duplicate combinations).
   - If the current combination size reaches `k`, add it to the result list.
   - Otherwise, iterate through the remaining elements, include them in the current combination, and recursively explore further.
3. **Base Case**: When the combination size equals `k`, add it to the result.
4. **Recursive Case**: For each element starting from the current index, include it in the combination, recurse, and then backtrack (remove it) to explore other possibilities.

---

## Solution Code
```java LCPatterns/Medium/CombinationNK.java
    public static List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();

        combination(new ArrayList<Integer>(), result, n, k, 1);

        return result;
    }

    public static void combination(List<Integer> cand, List<List<Integer>> result, int n, int k,  int start){
        if(cand.size() == k){
            result.add(new ArrayList<>(cand));
            return;
        }


        for(int i = start; i <=n ; i++){

            cand.add(i);
            combination(cand, result, n, k, i + 1);
            cand.remove(cand.size() - 1);
        }

}

```

---

## Explanation
2. **Backtracking Function**: The `backtrack` function is called recursively:
   - `nums`: The input array.
   - `k`: The target combination size.
   - `start`: The starting index to avoid revisiting the same elements.
   - `current`: The current combination being built.
   - `result`: The list to store all valid combinations.
3. **Base Case**: When `current.size() == k`, the current combination is added to `result`.
4. **Recursive Case**: For each index `i` starting from `start`, the element `nums[i]` is added to `current`, and the function recurses with `i + 1` to ensure no duplicates. After the recursive call, the element is removed (backtracked) to explore other combinations.

---

## Time and Space Complexity
- **Time Complexity**: O(C(n, k)), where C(n, k) is the binomial coefficient representing the number of combinations. This is because there are C(n, k) possible combinations, and each combination is generated in O(k) time.\n- **Space Complexity**: O(k) for the recursion stack (due to backtracking) and O(C(n, k) * k) to store the result.

---

## Difference Between Combinations and Permutations
Combinations and permutations are both ways to select items from a set, but they differ in whether the **order** of selection matters:

### Combinations
- **Order does not matter**: The selection `[1, 2]` is the same as `[2, 1]` and is counted only once.
- **Use Case**: When the problem requires subsets of a specific size without regard to the order of elements.
- **Example**: Choosing 2 fruits from `[apple, banana, cherry]` gives combinations like `[apple, banana]`, `[apple, cherry]`, and `[banana, cherry]`.

### Permutations
- **Order matters**: The selection `[1, 2]` is different from `[2, 1]` and is counted separately.
- **Use Case**: When the problem requires arrangements where the order of elements is significant.
- **Example**: Arranging 2 fruits from `[apple, banana, cherry]` gives permutations like `[apple, banana]`, `[banana, apple]`, `[apple, cherry]`, `[cherry, apple]`, etc.

---

## Example
For `nums = [1, 2, 3]` and `k = 2`, the combinations are:
- `[1, 2]`
- `[1, 3]`
- `[2, 3]`

For the same input, the permutations would include:
- `[1, 2]`, `[2, 1]`, `[1, 3]`, `[3, 1]`, `[2, 3]`, `[3, 2]`

---
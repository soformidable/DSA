# Permutations

**Problem Statement:**  
Given an integer array `nums`, return all possible permutations. You may not use the same element twice. Return all the possible orderings.

---
## Problem : 
https://leetcode.com/problems/permutations/description/

## Key Insight

A **permutation** is an ordering of elements where each element appears exactly once. For a set of `n` distinct elements, there are exactly `n!` (n factorial) permutations.

**Backtracking Approach:**
- Build permutations character by character
- Use a `used[]` boolean array to track which elements have been placed
- Explore all possible positions for each element

---

## Solution: Backtracking

**Algorithm:**
1. Sort the array (optional but helpful)
2. Initialize a boolean `used[]` array to track which elements are in current path
3. Start with an empty current path
4. For each position, try every unused element:
   - Mark element as used
   - Add to current path
   - Recurse to fill next position
   - Backtrack by unmarking and removing
5. When path length equals `nums.length`, add to result

---

## Code Implementation

```java
public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    backtrack(nums, new ArrayList<>(), result, used);
    return result;
}

private static void backtrack(int[] nums, List<Integer> path, List<List<Integer>> result, boolean[] used) {
    if (path.size() == nums.length) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }
        
        used[i] = true;
        path.add(nums[i]);
        backtrack(nums, path, result, used);
        path.remove(path.size() - 1);
        used[i] = false;
    }
}
```

---

## Example Walkthrough

**Input:** `nums = [1, 2, 3]`

**Execution tree:**
```
                          Start
                    ┌───────┴───────┐
                  [1]             [2]             [3]
              ┌───┴───┐       ┌───┴───┐       ┌───┴───┐
            [1,2]     [1,3]   [2,1]     [2,3]   [3,1]     [3,2]
            └────┬────┘     └────┬────┘     └────┬────┘     └────┬────┘
               [1,2,3]        [1,3,2]        [2,1,3]        [2,3,1]        [3,1,2]        [3,2,1]
```

**Output:** `[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]`

---

## Complexity Analysis

- **Time Complexity:** O(n × n!)  
  - There are n! permutations
  - Each permutation takes O(n) to build and copy

- **Space Complexity:** O(n)  
  - Recursos stack depth: O(n)
  - Used array: O(n)
  - Path storage: O(n)

---

## Key Techniques

1. **Boolean used[] array:** Tracks which indices are in the current permutation
2. **Backtracking:** Systematically explores all paths
3. **ArrayList copy:** Creates new list when adding complete permutation

---

## Related Problems

- **Permutations II (Unique Permutations)** - With duplicate elements

- **Combination Sum** - Backtracking with constraints

- **Subsets** - Generating all subsets

- **Letter Combinations of Phone Number** - String-based backtracking

---

## Common Mistakes

1. **Modifying the path directly:** Always use `new ArrayList<>(path)` when adding to result
2. **Not backtracking properly:** Must remove element and unmark as used
3. **Using element values instead of indices:** With duplicates, use indices to avoid confusion

---

## Tags

#leetcode #backtracking #array #medium

---
*Classic Permutation Problem*
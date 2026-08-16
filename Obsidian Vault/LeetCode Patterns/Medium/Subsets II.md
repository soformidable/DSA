# Subsets II

## Problem Statement
Given an integer array `nums` that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

## Problem:
https://leetcode.com/problems/subsets-ii/description/

## Example
```
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
```

## Key Insight
The main challenge is handling duplicates. If we simply generate all subsets like in [[Subsets]], we'll get duplicate subsets when the input contains duplicate elements.

**Solution**: Sort the array first, then use a clever skipping technique during backtracking to avoid generating duplicate subsets.

## Approach: Backtracking with Duplicate Skipping

### Core Idea:
1. **Sort the array** to bring duplicates together
2. **Skip duplicates** at the same recursion level using `if(i > index && nums[i-1] == nums[i]) continue;`
3. **Backtrack** to explore all valid subsets

### Why This Works:
- After sorting, duplicates are adjacent
- When we encounter a duplicate at the same level, we skip it
- This ensures each unique subset is generated only once

## Algorithm
```
subsetsWithDup(nums):
    sort(nums)
    result = []
    backtrack([], result, nums, 0)
    return result

backtrack(current, result, nums, start):
    add current to result
    
    for i from start to len(nums)-1:
        // Skip duplicates at same level
        if i > start AND nums[i] == nums[i-1]:
            continue
        
        // Include nums[i]
        current.add(nums[i])
        backtrack(current, result, nums, i+1)
        current.removeLast()  // Backtrack
```

## Code Implementation

### Solution 1: Classic Backtracking (from SubsetII.java)
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Sort to bring duplicates together
        Arrays.sort(nums);
        
        generate(new ArrayList<>(), result, nums, 0);
        return result;
    }
    
    private void generate(List<Integer> cand, List<List<Integer>> result, 
                          int nums[], int index) {
        // Add current subset to result
        result.add(new ArrayList<>(cand));
        
        // Base case: reached end of array
        if(index >= nums.length)
            return;
        
        for(int i = index; i < nums.length; i++) {
            // Skip duplicates: if current element equals previous AND
            // we're at the same recursion level (i > index)
            if(i > index && nums[i-1] == nums[i]) 
                continue;
            
            // Include nums[i]
            cand.add(nums[i]);
            // Recurse with next index
            generate(cand, result, nums, i + 1);
            // Backtrack
            cand.remove(cand.size() - 1);
        }
    }
}
```

### Solution 2: Alternative Approach with HashSet
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> temp, 
                          int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        
        for(int i = start; i < nums.length; i++) {
            // Skip duplicates
            if(i > start && nums[i] == nums[i-1]) 
                continue;
            
            temp.add(nums[i]);
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
```

## Visual Walkthrough

### Input: [1, 2, 2]
**Step 1: Sort array** → [1, 2, 2]

**Step 2: Generate subsets**
```
Start with empty subset: []
├── Include 1: [1]
│   ├── Include 2: [1,2]
│   │   └── Include 2: [1,2,2]
│   └── Skip second 2 (duplicate at same level)
├── Include 2: [2]
│   └── Include 2: [2,2]
└── Skip second 2 (duplicate at same level)

Result: [[], [1], [1,2], [1,2,2], [2], [2,2]]
```

### Input: [4, 4, 4, 1, 4]
**Step 1: Sort** → [1, 4, 4, 4, 4]

**Step 2: Generate subsets**
```
Start: []
├── Include 1: [1]
│   ├── Include 4: [1,4]
│   │   ├── Include 4: [1,4,4]
│   │   │   ├── Include 4: [1,4,4,4]
│   │   │   │   └── Include 4: [1,4,4,4,4]
│   │   │   └── Skip (duplicate)
│   │   └── Skip (duplicate)
│   └── Skip (duplicate)
├── Include 4: [4]
│   ├── Include 4: [4,4]
│   │   ├── Include 4: [4,4,4]
│   │   │   └── Include 4: [4,4,4,4]
│   │   └── Skip (duplicate)
│   └── Skip (duplicate)
└── Skip (duplicate)
```

## Complexity Analysis

### Time Complexity: O(N × 2^N)
- **Sorting**: O(N log N)
- **Generating subsets**: O(N × 2^N)
  - There are at most 2^N subsets
  - Each subset takes O(N) time to copy

### Space Complexity: O(N)
- **Recursion stack**: O(N) depth
- **Current subset storage**: O(N)
- **Output space**: O(N × 2^N) for storing all subsets (not counted in auxiliary space)

## Comparison: Subsets vs Subsets II

| Aspect | Subsets | Subsets II |
|--------|---------|------------|
| Duplicates | No duplicates | May have duplicates |
| Sorting Required | No | Yes |
| Duplicate Handling | Not needed | Skip duplicates at same level |
| Result Count | Exactly 2^N | Less than 2^N (unique only) |
| Key Line | N/A | `if(i > start && nums[i] == nums[i-1]) continue` |

## Common Mistakes to Avoid

### ❌ Mistake 1: Forgetting to Sort
```java
// WRONG: Without sorting, duplicates aren't adjacent
if(i > start && nums[i] == nums[i-1]) continue;  // Won't work correctly
```

### ❌ Mistake 2: Skipping Duplicates at Different Levels
```java
// WRONG: This skips ALL duplicates, even when they should be in different subsets
if(i > 0 && nums[i] == nums[i-1]) continue;  // Too aggressive
```

### ✅ Correct: Skip Only at Same Level
```java
// CORRECT: Only skip when we're at the same recursion level (i > start)
if(i > start && nums[i] == nums[i-1]) continue;
```

## Pattern Recognition
This problem uses the **Backtracking with Pruning** pattern:
1. **Sort** to enable duplicate detection
2. **Choose** an element to include
3. **Explore** recursively
4. **Un-choose** (backtrack)
5. **Skip** duplicates to prune invalid branches

## Related Problems
- [[Subsets]] - No duplicates version (easier)
- [[Permutations]] - All arrangements
- [[Permutations II]] - Permutations with duplicates
- [[Combination Sum]] - Subsets with target sum
- [[Combination Sum II]] - Combination Sum with duplicates

## Practice Tips
1. **Always sort first** when dealing with duplicates
2. **Understand the difference** between `i > start` vs `i > 0`
3. **Draw the recursion tree** to visualize duplicate skipping
4. **Test with examples** that have multiple duplicates: `[1,1,1]`, `[1,2,2,3,3]`

#LeetCode #Medium #Array #Backtracking #Subsets #Duplicates
</contents>
# Unique Permutations II

**Problem Statement:**  
Given a collection of integers that may contain duplicates, return all possible unique permutations.

---
## Problem : 
https://leetcode.com/problems/permutations-ii/description/

## Key Insight

When the input array contains **duplicate elements**, simply removing duplicates from the result is inefficient. Instead, we need to **prevent generating duplicate permutations in the first place.**

**Key Technique:**  
When we have duplicate elements, we should only use one of them first. This is done by:  
- Sorting the array (groups duplicates together)
- Using a condition: `if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) skip`

---

## Solution: Backtracking with Duplicate Handling

**Algorithm:**
1. **Sort the array first** - Groups duplicates together for easy detection
2. **Use boolean `used[]` array** - Tracks which indices are in current path
3. **Skip duplicates properly** - Use the key condition:
   ```java
   if (i > 0 && nums[i] == nums[i-1] && !used[i-1])
       continue;
   ```
4. **Apply backtracking** - Build permutations systematically

---

## The Critical Duplicate-Skipping Condition Explained

```java
if (i > 0 && nums[i] == nums[i-1] && !is_used[i - 1])
    continue;
```

**Why this works:**

### Scenario: `nums = [1, 1, 2]` (sorted)

| i | Used Array | Can Use Element? | Reason |
|---|------------|------------------|--------|
| 0 | [F, F, F] | ✅ Yes | First occurrence |
| 1 | [T, F, F] | ✅ Yes | Previous same element IS used |
| 2 | [T, F, F] | ✅ Yes | Element 2 is different |

| i | Used Array | Can Use Element? | Reason |
|---|------------|------------------|--------|
| 0 | [T, F, F] | N/A | Already used |
| 1 | [F, F, F] | ❌ No | `i=1>`+ `nums[1]==nums[0]`+ `!used[0]` |
| 2 | [F, F, F] | ✅ Yes | Element 2 is different |

---

## Visualizing the Difference

### Without Duplicate Handling:

```
Position 0: Choose 1 (first)
Position 1: Choose 1 (second) → [1,1,2]
Position 2: Choose 2 → [1,1,2]

Position 0: Choose 1 (second)
Position 1: Choose 1 (first) → [1,1,2] ❌ DUPLICATE!
Position 2: Choose 2 → [1,1,2]
```

### With Duplicate Handling:

```
Position 0: Choose 1 (first)
Position 1: Choose 1 (second) → [1,1,2]
Position 2: Choose 2 → [1,1,2] ✓

Position 0: Choose 1 (second) → SKIPPED!  (why? previous same is unused)
  ```java
  i=1: nums[1]==nums[0]==1 && !used[0] → skip
  ```
```

**Result:** Only one `[1,1,2]` instead of duplicates!

---

## Code Implementation

```java
public static List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);  // Sort to group duplicates
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    getPermutation(nums, new ArrayList<Integer>(), result, used);
    return result;
}

public static void getPermutation(int[] nums, List<Integer> path, 
                                   List<List<Integer>> result, boolean[] used) {
    if (path.size() == nums.length) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i])
            continue;

        // Skip: same as.previous AND previous is NOT used
        // This ensures we only use a duplicate when the previous one is available
        if (i > 0 && nums[i] == nums[i-1] && !used[i-1])
            continue;

        used[i] = true;
        path.add(nums[i]);
        getPermutation(nums, path, result, used);
        path.remove(path.size() - 1);
        used[i] = false;
    }
}
```

---

## Example Walkthrough

**Input:** `nums = [1, 1, 2]`

**Step 1:** Sort → `[1, 1, 2]`

**Step 2:** Generate permutations:

| Path | Used | Next choice |
|------|------|-------------|
| `[]` | [F,F,F] | 2 permutations possible |

| `[1]` | [T,F,F] | Next: can use index 1 (same as used) or 2 |
| `[1,1]` | [T,T,F] | Next: must use 2 |
| `[1,1,2]` | [T,T,T] | ✅ Complete |

| `[1]` | [F,T,F] | Skip index 1 (duplicate, prev unused) |
| `[1,2]` | [F,T,T] | Next: must use index 0 (value 1) |
| `[1,2,1]` | [T,T,T] | ✅ Complete |

| `[2]` | [F,F,T] | Next: can use index 0 or 1 (both 1) |
| `[2,1]` | [F,T,T] | Next: must use index 0 (value 1) |
| `[2,1,1]` | [T,T,T] | ✅ Complete |

**Result:** `[[1,1,2], [1,2,1], [2,1,1]]`

---

## Understanding the Skip Condition

**When `nums[i] == nums[i-1]` and `!used[i-1]`:**

Imagine two identical balls labeled "A" and "A". They are indistinguishable.

- **First "A" used, then second "A":** `[A₁, A₂, ...]` - Valid
- **Second "A" used, skipping first "A":** `[A₂, A₁, ...]` - Same as above, skip it!

**When `nums[i] == nums[i-1]` and `used[i-1]`:**

- **First "A" used, then place second "A" in order:** `[A₁, A₂, ...]`
- The permutation is built by having the first duplicate appear before the second
- This is the **canonical order** for this duplicate pair

---

## Complexity Analysis

- **Time Complexity:** O(n! × n)  
  - Number of unique permutations is bounded by n!
  - Each takes O(n) to build

- **Space Complexity:** O(n)  
  - Recursion depth: O(n)
  - Used array: O(n)
  - Path storage: O(n)

---

## Key Differences from Standard Permutations

| Aspect | Permutations (No Duplicates) | Unique Permutations (With Duplicates) |
|--------|-------------------------------|----------------------------------------|
| Sorting | Optional | Required |
| Duplicate Handling | Not needed | Skip with condition |
| Result Size | n! | n! / (product of factorials of counts) |
| Code Addition | None | Add skip condition |

---

## Common Mistakes

1. **Reversing the skip condition:**
   ```java
   // WRONG: i > 0 && nums[i] == nums[i-1] && used[i-1]
   // This skips when it shouldn't!
   ```

2. **Forgetting to sort:**  
   - Without sorting, duplicates aren't adjacent!

3. **Modifying path directly:**
   - Always use `new ArrayList<>(path)` when adding

---

## Related Problems

- **Permutations** - Standard permutation problem

- **Combination Sum II** - Handle duplicates in combinations

- **Subsets II** - Generate all unique subsets with duplicates

- ** permute Unique** - This exact problem

---

## Tags

#leetcode #backtracking #array #medium

---
*Source: LCPatterns/Medium/PermutationII.java*
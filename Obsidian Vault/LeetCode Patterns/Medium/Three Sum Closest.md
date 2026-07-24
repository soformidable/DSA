# Three Sum Closest

**Problem Statement:**  
Given an integer array `nums` of length `n` and an integer `target`, find three integers in `nums` such that the sum is closest to `target`. Return the sum of the three integers.

## Problem:
https://leetcode.com/problems/3sum-closest/description/

You may assume that each input would have exactly one solution.

---

## Key Insight

This problem is a variation of the classic 3Sum problem. Instead of finding a sum equal to a target, we need to find the sum that is closest to the target.

**Approach:**
1. Sort the array to enable the two-pointer technique
2. Use two pointers to explore sums while tracking the closest one
3. Early exit if we find the exact target

---

## Solution: Sorting + Two Pointers

**Algorithm:**
1. Sort the array to allow two-pointer approach
2. Initialize `closestSum` with the sum of first three elements
3. Iterate through each element as the first number of the triplet
4. Use two pointers (`j` and `k`) to find the other two numbers
5. Update `closestSum` when a closer sum is found
6. Move pointers based on comparison with target

**Why this works:**
- Sorting allows us to know which direction to move pointers
- If current sum < target → move left pointer right (increase sum)
- If current sum > target → move right pointer left (decrease sum)
- This guarantees we don't miss the closest sum

---

## Code Implementation

```java
public static int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    
    int closestSum = nums[0] + nums[1] + nums[2]; // Initialize with first triplet
    
    for (int i = 0; i < nums.length - 2; i++) {
        int j = i + 1;
        int k = nums.length - 1;
        
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            
            // Found exact match — can't get closer than this
            if (sum == target) {
                return sum;
            }
            
            // Update closest if this sum is nearer to target
            if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                closestSum = sum;
            }
            
            // Move pointers based on comparison with target
            if (sum < target) {
                j++;  // Need larger sum
            } else {
                k--;  // Need smaller sum
            }
        }
    }
    
    return closestSum;
}
```

---

## Complexity Analysis

- **Time Complexity:** O(n²)  
  - Sorting: O(n log n)
  - Two nested loops: O(n²)
  - Overall: O(n²)

- **Space Complexity:** O(1) or O(n) depending on sorting algorithm
  - The algorithm uses constant extra space, but sorting may require O(log n) space

---

## Example Walkthrough

**Input:** `nums = [-1, 2, 1, -4]`, `target = 1`

**Step 1:** Sort array → `[-4, -1, 1, 2]`

**Step 2:** Initialize `closestSum = -4 + (-1) + 1 = -4`

**Iteration 1:** `i = 0` (element -4)
- `j = 1`, `k = 3` → sum = `-4 + (-1) + 2 = -3`
- Distance: `|-3 - 1| = 4` vs `|-4 - 1| = 5` → update `closestSum = -3`
- Since `-3 < 1` → `j++`

**Iteration 2:** `i = 1` (element -1)
- `j = 2`, `k = 3` → sum = `-1 + 1 + 2 = 2`
- Distance: `|2 - 1| = 1` vs `|-3 - 1| = 4` → update `closestSum = 2`
- Since `2 > 1` → `k--`

**Result:** `2` (closest sum to target 1)

---

## Related Problems

- **3Sum** - Find all unique triplets that sum to zero
- **3Sum Smaller** - Count triplets with sum less than target
- **4Sum** - Find all unique quadruplets that sum to target

---

## Common Mistakes

1. **Forgetting to sort** - Required for two-pointer technique
2. **Not handling duplicates** - While not required for this problem, be aware of duplicate elements
3. **Wrong pointer movement** - Must move based on current sum vs target comparison

---

## Edge Cases

- Array with all negative numbers
- Array with all positive numbers
- Target exactly equal to a possible sum
- Array with minimum size (3 elements)

---

## Tags

#leetcode #arrays #two-pointers #sorting #medium

---
*Source: LCPatterns/Medium/ThreeSumClosest.java*
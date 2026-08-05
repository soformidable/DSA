# Jump Game

**Problem Statement:**  
Given an integer array `nums`, you are initially positioned at the first index of the array. Each element in the array represents your maximum jump length at that position. Determine if you can reach the last index.

## Problem
https://leetcode.com/problems/jump-game/description/

---

## Key Insight

This problem can be solved efficiently with a **greedy approach** that tracks the farthest position we can reach at each step. The key observation is that we don't need to explore all possible paths - we just need to know if we can reach the end.

**Greedy Approach:**
- Start from the end and work backwards
- Track the goal position we need to reach
- For each position, check if it can reach the current goal
- If yes, update the goal to this position
- Continue until we reach the start

---

## Solution: Greedy Approach

**Algorithm:**
1. Initialize `goal` to the last index
2. Iterate from the second-to-last index to the first
3. For each index `i`:
   - If `i + nums[i] >= goal`, update `goal = i`
4. After loop, check if `goal == 0`

**Why this works:**
- We're working backwards from the end
- We only care about the farthest position we can reach at each step
- If we can reach the current goal from position `i`, we update the goal to `i`
- This ensures we find the optimal path

---

## Code Implementation

```java
public static boolean canJump(int[] nums) {
    if (nums.length == 1 && nums[0] > 0)
        return true;

    int goal = nums.length - 1;

    for (int i = nums.length - 2; i >= 0; i--) {
        if (i + nums[i] >= goal)
            goal = i;
    }
    return goal == 0;
}
```

---

## Step-by-Step Walkthrough

**Input:** `[3, 2, 1, 0, 4]`

**Initialization:**
- goal = 4 (last index)

**Iteration 1:**
- i = 3, nums[3] = 0
- 3 + 0 = 3 < 4 → goal remains 4

**Iteration 2:**
- i = 2, nums[2] = 1
- 2 + 1 = 3 < 4 → goal remains 4

**Iteration 3:**
- i = 1, nums[1] = 2
- 1 + 2 = 3 < 4 → goal remains 4

**Iteration 4:**
- i = 0, nums[0] = 3
- 0 + 3 = 3 < 4 → goal remains 4

**Final Check:**
- goal = 4 ≠ 0 → return false

**Result:** `false`

---

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(1) - Only using constant extra space

---

## Dynamic Programming Solution

While the greedy approach is optimal for this problem, a DP solution is also possible:

**DP Approach:**
- `dp[i]` represents whether we can reach index `i`
- Initialize `dp[0] = true` (start position)
- For each index `i`:
  - Check all previous indices `j` to see if we can reach `i` from `j`
  - If `dp[j]` is true and `j + nums[j] >= i`, then `dp[i] = true`
- Return `dp[n-1]`

**DP Code Implementation:**

```java
public static boolean canJumpDP(int[] nums) {
    int n = nums.length;
    boolean[] dp = new boolean[n];
    dp[0] = true;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && j + nums[j] >= i) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[n-1];
}
```

**DP Complexity:**
- **Time Complexity:** O(n²) - Nested loops
- **Space Complexity:** O(n) - DP array

**When to use DP:**
- When you need to explore all possible paths
- When the problem has overlapping subproblems
- When the greedy approach isn't obvious

**When to use Greedy:**
- When you can find a local optimal solution that leads to global optimum
- When the problem has optimal substructure
- When the greedy approach is more efficient

---

## Key Differences Between Approaches

| Aspect | Greedy Approach | DP Approach |
|--------|------------------|-------------|
| Time Complexity | O(n) | O(n²) |
| Space Complexity | O(1) | O(n) |
| Approach | Backwards | Forwards |
| Optimal Substructure | Yes | Yes |
| Overlapping Subproblems | No | Yes |

---

## Edge Cases

- **Single element array** - Return true if element > 0
- **All zeros except first** - Can reach end if first element ≥ length-1
- **All zeros** - Can't reach end (except if length=1)
- **Large jump values** - Can reach end in one step
- **Small jump values** - Need to make many small jumps

---

## Common Mistakes

1. **Starting from the beginning:**
   - The greedy approach works backwards, not forwards
   - Forward approach would require tracking all possible positions

2. **Not handling single element array:**
   - Need special case for length=1

3. **Incorrect goal update condition:**
   - Must check `i + nums[i] >= goal`, not just `nums[i] >= goal`

4. **Forgetting to check final goal:**
   - After loop, must verify if goal reached start

---

## Related Problems

- **Jump Game II** - Find minimum number of jumps
- **Minimum Path Sum** - Find path with minimum sum
- **Unique Paths** - Count all possible paths
- **House Robber** - Dynamic programming with constraints

---

## Tags

#leetcode #greedy #array #dynamic-programming #medium

---
*Source: LCPatterns/Medium/JumpGame.java*
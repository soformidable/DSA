# House Robber

**Problem Statement:**
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

---

## Problem:
https://leetcode.com/problems/house-robber/description/

## Key Insight

This problem is a classic example of **dynamic programming** with the following key observations:

1. **Decision at each house:** At each house, you have two choices - rob the house (and skip the next one) or skip the house (and consider the next one).

2. **Optimal Substructure:** The maximum amount that can be robbed from house `i` onwards depends on whether we rob house `i` or not.

3. **Overlapping Subproblems:** When solving recursively, we repeatedly solve the same subproblems, making memoization or DP ideal.

4. **Recurrence Relation:** 
   - If we rob house `i`: `nums[i] + dp[i+2]`
   - If we skip house `i`: `dp[i+1]`
   - `dp[i] = max(nums[i] + dp[i+2], dp[i+1])`

---

## Solution Approaches

### 1. Recursive with Memoization (Top-Down DP)

**Algorithm:**
1. Start from index 0
2. At each index, we have two choices:
   - Rob current house and add its value to the result from `index + 2`
   - Skip current house and take the result from `index + 1`
3. Return the maximum of these two choices
4. Memoize the result to avoid recomputation

**Why this works:**
- Breaks down the problem into smaller subproblems
- Uses memoization to store results of already computed indices
- Avoids exponential time complexity of pure recursion

---

## Code Implementation: Recursive with Memoization

```java
public static int rob(int[] nums) {
    if(nums.length == 1)
        return nums[0];

    int memo[] = new int[nums.length + 1];
    Arrays.fill(memo, -1);

    return rob(nums, memo, 0);
}

private static int rob(int nums[], int[] memo, int index){
    if(index >= nums.length)
        return 0;

    if(memo[index] >= 0)
        return memo[index];

    int result = Math.max(nums[index] + rob(nums, memo, index + 2), 
                          rob(nums, memo, index + 1));
    memo[index] = result;
    return result;
}
```

---

## Solution 2: Dynamic Programming (Bottom-Up)

**Algorithm:**
1. Create a DP array of size `n + 1`
2. Initialize `dp[0] = 0` (no houses)
3. Initialize `dp[1] = Math.max(nums[0], nums[1])` (first two houses)
4. For each subsequent house `i`:
   - `dp[i] = max(dp[i-1], dp[i-2] + nums[i])`
5. Return `dp[n-1]`

**Why this works:**
- Builds solutions from the ground up
- Avoids recursion stack limits
- More space efficient than memoization approach

---

## Code Implementation: Dynamic Programming

```java
public static int robDP(int nums[]){
    if(nums.length == 1)
        return nums[0];

    int dp[] = new int[nums.length + 1];

    dp[0] = 0;
    dp[1] = Math.max(nums[0], nums[1]);

    for(int i = 2; i < nums.length; i++){
        dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    }

    return dp[nums.length - 1];
}
```

---

## Solution 3: Space-Optimized Dynamic Programming

**Algorithm:**
1. Track only the last two computed values instead of entire array
2. Initialize `prev1 = 0` and `prev2 = Math.max(nums[0], nums[1])`
3. For each subsequent house `i`:
   - `current = max(prev1, prev2 + nums[i])`
   - Update `prev2 = prev1` and `prev1 = current`
4. Return `prev1`

**Why this works:**
- Only the last two values are needed to compute the next value
- Reduces space complexity from O(n) to O(1)
- Same time complexity as DP approach

---

## Code Implementation: Space-Optimized DP

```java
public static int robDPSpace(int[] nums){
    if(nums.length == 1)
        return nums[0];

    int prev1 = 0;
    int prev2 = Math.max(nums[0], nums[1]);

    for(int i = 2; i < nums.length; i++){
        int current = Math.max(prev1, prev2 + nums[i]);
        prev2 = prev1;
        prev1 = current;
    }

    return prev1;
}
```

---

## Step-by-Step Walkthrough

**Input:** nums = [2, 1, 1, 2]

**Recursive with Memoization Approach:**

| Index | Action | Calculation | Result |
|-------|--------|-------------|--------|
| 0 | Rob house 0 | 2 + rob(2) | 2 + 2 = 4 |
| 0 | Skip house 0 | rob(1) | 3 |
| 1 | Rob house 1 | 1 + rob(3) | 1 + 2 = 3 |
| 1 | Skip house 1 | rob(2) | 2 |
| 2 | Rob house 2 | 1 + rob(4) | 1 + 0 = 1 |
| 2 | Skip house 2 | rob(3) | 2 |
| 3 | Rob house 3 | 2 + rob(5) | 2 + 0 = 2 |
| 3 | Skip house 3 | rob(4) | 0 |

**Final Result:** max(4, 3) = **4**

**DP Table Visualization:**

| i | nums[i] | dp[i] | Calculation |
|---|---------|-------|-------------|
| 0 | 2 | 0 | Base case |
| 1 | 1 | 2 | max(2, 1) |
| 2 | 1 | 2 | max(dp[1], dp[0] + nums[2]) = max(2, 0+1) = 2 |
| 3 | 2 | 4 | max(dp[2], dp[1] + nums[3]) = max(2, 2+2) = 4 |

**Result:** 4

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| Recursive with Memoization | O(n) | O(n) | Uses recursion stack + memo array |
| Dynamic Programming | O(n) | O(n) | Uses DP array |
| Space-Optimized DP | O(n) | O(1) | Most efficient |

---

## Key Techniques

1. **Memoization:** Stores results of subproblems to avoid recomputation
2. **Dynamic Programming:** Builds solutions from smaller subproblems
3. **Decision Making:** At each step, choose between two options (rob or skip)
4. **Optimal Substructure:** Problem can be broken down into optimal subproblems

---

## Edge Cases

- **Single house:** Rob that house
- **Two houses:** Rob the one with more money
- **Empty array:** Return 0 (not applicable per constraints)
- **All houses have same value:** Rob every other house
- **Alternating high-low values:** Consider patterns like [2, 1, 1, 2]

---

## Visualization

For nums = [2, 1, 1, 2]:

```
Houses:     H0 (2)    H1 (1)    H2 (1)    H3 (2)
              ↓         ↓         ↓         ↓
Decision:   Rob/Skip   Rob/Skip   Rob/Skip   Rob/Skip

Optimal Path: Rob H0, Skip H1, Skip H2, Rob H3 = 2 + 2 = 4

Alternative:  Skip H0, Rob H1, Skip H2, Rob H3 = 1 + 2 = 3
              Rob H0, Skip H1, Rob H2, Skip H3 = 2 + 1 = 3
              Skip H0, Rob H1, Rob H2, Skip H3 = Invalid (adjacent)
```

---

## Related Problems

- **House Robber II** - Houses arranged in a circle
- **Delete and Earn** - Similar decision-making pattern
- **Paint House** - Similar DP structure
- **Minimum Cost Climbing Stairs** - Similar approach
- **Fibonacci Number** - Base case for DP problems

---

## Common Mistakes

1. **Incorrect base cases:**
   - Forgetting to handle single house case
   - Not properly initializing DP for first two houses

2. **Off-by-one errors:**
   - Using wrong indices for DP array
   - Incorrect indexing when accessing `nums[i]` and `dp[i-2]`

3. **Not handling adjacent constraint:**
   - Robbing two adjacent houses
   - Forgetting to skip the next house after robbing

4. **Incorrect recurrence relation:**
   - Using wrong formula for DP calculation
   - Not considering both options (rob vs skip)

5. **Space optimization errors:**
   - Not properly updating `prev1` and `prev2`
   - Using incorrect initial values

---

## Tags

#leetcode #dynamic-programming #1d-dp #medium

---

## Additional Notes

### Pattern Recognition

This problem follows the **1D DP** pattern where:
- We have a linear sequence of elements
- Each element has a value
- We need to make decisions at each element
- Decisions affect future elements (adjacent constraint)

### Similar Pattern Problems

1. **Climbing Stairs:** Each step can be reached from 1 or 2 steps back
2. **Min Cost Climbing Stairs:** Similar to House Robber with costs
3. **House Robber II:** Circular arrangement adds complexity
4. **Delete and Earn:** Points with adjacent constraint

### Mathematical Formulation

Let `dp[i]` be the maximum amount that can be robbed from houses `0` to `i`:

```
dp[i] = max(dp[i-1], dp[i-2] + nums[i])
```

Where:
- `dp[i-1]`: Skip house `i`, take maximum from previous house
- `dp[i-2] + nums[i]`: Rob house `i`, add to maximum from two houses back

### Recurrence Tree Visualization

For nums = [2, 1, 1, 2]:

```
                    rob(0)
                   /      \
            2+rob(2)      rob(1)
            /    \        /    \
      2+1+rob(4) 2+rob(3) 1+rob(3) rob(2)
         |       /    \    /    \    /    \
         0   2+2+0  2+0  1+2+0  1+0  1+0  0
             =4    =2   =3    =1   =1   =0
```

### Optimization Insight

The key optimization insight is that we only need to track the maximum amount robbed up to the previous two houses, not all previous houses. This allows us to reduce space complexity from O(n) to O(1).

---

## Example Walkthroughs

**Example 1:** nums = [1, 2, 3, 1]
- Result: 4
- Rob house 1 (money = 1) and house 3 (money = 3)
- Total = 1 + 3 = 4

**Example 2:** nums = [2, 7, 9, 3, 1]
- Result: 12
- Rob house 1 (money = 2), house 3 (money = 9), and house 5 (money = 1)
- Total = 2 + 9 + 1 = 12

**Example 3:** nums = [2, 1, 1, 2]
- Result: 4
- Rob house 1 (money = 2) and house 4 (money = 2)
- Total = 2 + 2 = 4

**Example 4:** nums = [1, 3, 1]
- Result: 3
- Rob house 2 (money = 3)
- Total = 3

---

## Interview Tips

1. **Clarify constraints:** Can values be negative? What's the array size limit?
2. **Start with brute force:** Explain recursive solution first
3. **Identify overlapping subproblems:** Show why memoization helps
4. **Optimize space:** Mention that we only need last two values
5. **Discuss edge cases:** Single house, two houses, all same values
6. **Time complexity analysis:** All solutions are O(n) time

---

*Source: LCPatterns/Medium/HouseRobber.java*
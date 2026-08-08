# Unique Paths

**Problem Statement:**
A robot is located at the top-left corner of an m x n grid. The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid. How many unique paths are there?

---

## Problem:
https://leetcode.com/problems/unique-paths/description/

## Key Insight

This problem is a classic example of **combinatorics** and **dynamic programming**. The key observations are:

1. **Combinatorial Approach:** The number of unique paths is equivalent to the number of ways to arrange `m-1` down moves and `n-1` right moves in any order, which is the binomial coefficient C(m+n-2, m-1).

2. **Dynamic Programming Approach:** The number of paths to reach cell (i,j) is the sum of paths to reach cell (i-1,j) and cell (i,j-1).

3. **Recursive Structure:** The problem has overlapping subproblems and optimal substructure, making it ideal for dynamic programming.

---

## Solution Approaches

### 1. Recursive with Memoization (Top-Down DP)

**Algorithm:**
1. Base cases:
   - If robot reaches bottom or right boundary, return 0
   - If robot reaches destination, return 1
2. If current cell's value is already computed, return it
3. Otherwise, recursively compute paths from right and bottom cells, store result, and return

**Why this works:**
- Uses memoization to avoid recalculating the same subproblems
- Breaks down the problem into smaller subproblems
- Combines results from subproblems to get the final answer

---

## Code Implementation: Recursive with Memoization

```java
public static int uniquePaths(int m, int n) {
    if (m <= 0 || n <= 0)
        return 0;

    Integer memo[][] = new Integer[m][n];
    return generatePath(0, 0, m, n, memo);
}

public static int generatePath(int i, int j, int m, int n, Integer memo[][]) {
    // Base case: out of grid
    if (i >= m || j >= n)
        return 0;
    
    // Base case: reached destination
    if (i == m - 1 && j == n - 1)
        return 1;
    
    // Return memoized result if available
    if (memo[i][j] != null)
        return memo[i][j];
    
    // Recursive case: sum of paths from right and bottom
    memo[i][j] = generatePath(i, j + 1, m, n, memo) + 
                 generatePath(i + 1, j, m, n, memo);
    
    return memo[i][j];
}
```

---

## Solution 2: Dynamic Programming (Bottom-Up)

**Algorithm:**
1. Create a DP table of size m x n initialized with 1s
2. Fill the table using the recurrence relation: dp[i][j] = dp[i-1][j] + dp[i][j-1]
3. Return dp[m-1][n-1]

**Why this works:**
- Builds solutions from the bottom up
- Avoids recursion stack limits
- More efficient for larger grids

---

## Code Implementation: Dynamic Programming

```java
public static int uniquePathsDP(int m, int n) {
    if (m <= 0 || n <= 0)
        return 0;

    int[][] dp = new int[m][n];
    
    // Initialize first row and column with 1s
    for (int i = 0; i < m; i++)
        dp[i][0] = 1;
    for (int j = 0; j < n; j++)
        dp[0][j] = 1;
    
    // Fill the DP table
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    
    return dp[m-1][n-1];
}
```

---

## Solution 3: Mathematical (Combinatorial)

**Algorithm:**
1. Calculate the binomial coefficient C(m+n-2, m-1) or C(m+n-2, n-1)
2. This represents the number of ways to arrange (m-1) down moves and (n-1) right moves

**Why this works:**
- Directly computes the answer using combinatorics
- Most efficient for large grids
- Avoids recursion and DP table storage

---

## Code Implementation: Mathematical

```java
public static int uniquePathsMath(int m, int n) {
    if (m <= 0 || n <= 0)
        return 0;
    
    // Calculate binomial coefficient C(m+n-2, m-1)
    int total = m + n - 2;
    int k = Math.min(m-1, n-1);
    
    // Compute C(total, k)
    long result = 1;
    for (int i = 1; i <= k; i++) {
        result = result * (total - k + i) / i;
    }
    
    return (int) result;
}
```

---

## Step-by-Step Walkthrough

**Input:** m=3, n=7

**Recursive with Memoization Approach:**

1. Start at (0,0)
2. From (0,0), can move to (0,1) or (1,0)
3. Continue recursively until reaching (2,6)
4. Memoization stores results of subproblems

**DP Table Visualization:**

|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|
|0 |1 |1 |1 |1 |1 |1 |1 |
|1 |1 |2 |3 |4 |5 |6 |7 |
|2 |1 |3 |6 |10|15|21|28|

**Result:** 28 unique paths

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| Recursive with Memoization | O(m×n) | O(m×n) | Uses recursion stack |
| Dynamic Programming | O(m×n) | O(m×n) | More efficient for larger grids |
| Mathematical | O(min(m,n)) | O(1) | Most efficient for large grids |

---

## Key Techniques

1. **Memoization:** Stores results of subproblems to avoid recomputation
2. **Dynamic Programming:** Builds solutions from smaller subproblems
3. **Combinatorics:** Uses binomial coefficients to directly compute the answer
4. **Grid Traversal:** Models the problem as a grid with movement constraints

---

## Edge Cases

- **Single row or column:** Only one path exists
- **m=1 or n=1:** Only one path (straight line)
- **m=2 or n=2:** Two paths (diagonal or straight)
- **Large grid:** Need efficient solution to avoid stack overflow

---

## Visualization

For m=3, n=3:

```
Start: (0,0) ------------------------> (0,2)
       |                              |
       |                              |
       |                              |
End:   (2,0) ------------------------> (2,2)
```

Unique paths:
1. Right, Right, Down, Down
2. Right, Down, Right, Down
3. Right, Down, Down, Right
4. Down, Right, Right, Down
5. Down, Right, Down, Right
6. Down, Down, Right, Right

---

## Related Problems

- **Unique Paths II** - Grid with obstacles
- **Minimum Path Sum** - Find path with minimum sum
- **Triangles** - Find maximum path sum
- **Climbing Stairs** - Similar combinatorial problem
- **Combination Sum** - Generating combinations

---

## Common Mistakes

1. **Incorrect base cases:**
   - Forgetting to return 0 when out of grid
   - Not handling m=1 or n=1 cases

2. **Off-by-one errors:**
   - Using wrong indices for grid boundaries
   - Incorrect binomial coefficient calculation

3. **Not using memoization:**
   - Pure recursion leads to exponential time complexity

4. **Incorrect DP table initialization:**
   - Forgetting to initialize first row and column with 1s

5. **Integer overflow:**
   - Using int instead of long for combinatorial calculations

---

## Tags

#leetcode #dynamic-programming #combinatorics #grid #medium

---

## Additional Notes

### Space Optimization

The DP solution can be optimized to use O(min(m,n)) space by using a 1D array instead of a 2D table:

```java
public static int uniquePathsSpaceOptimized(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[j] += dp[j-1];
        }
    }
    
    return dp[n-1];
}
```

### Mathematical Explanation

The number of unique paths is equivalent to the number of ways to arrange (m-1) down moves and (n-1) right moves in any order. This is given by the binomial coefficient:

C(m+n-2, m-1) = (m+n-2)! / ((m-1)! × (n-1)!)

This is because we need to choose (m-1) positions out of (m+n-2) total positions for the down moves (the rest will be right moves).

---

## Example Walkthroughs

**Example 1:** m=3, n=3
- Result: 6 paths
- C(4,2) = 6

**Example 2:** m=3, n=7
- Result: 28 paths
- C(8,2) = 28

**Example 3:** m=5, n=3
- Result: 10 paths
- C(6,2) = 15

**Example 4:** m=3, n=2
- Result: 3 paths
- C(3,1) = 3

---

*Source: LCPatterns/Medium/UniquePaths.java*
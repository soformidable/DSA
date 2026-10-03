# Number of Islands

**Problem Statement:**
Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

---

## Problem:
https://leetcode.com/problems/number-of-islands/description/

## Key Insight

This is a classic **graph traversal** problem where:

1. **Grid as Graph:** Each cell is a node, and adjacent cells (up, down, left, right) are edges.

2. **Connected Components:** An island is a connected component of `'1'` cells. The number of islands equals the number of connected components of `'1'` cells.

3. **DFS Flood Fill:** When we encounter a `'1'`, we increment the count and use DFS to mark all connected `'1'`s as visited (by setting them to `'0'`).

4. **In-place Modification:** We can modify the grid in-place to track visited cells, avoiding extra space for a visited array.

---

## Solution Approaches

### 1. DFS (Depth-First Search) - Flood Fill

**Algorithm:**
1. Iterate through every cell in the grid
2. When we find a `'1'`, increment the island count
3. Perform DFS from that cell to mark all connected `'1'`s as `'0'` (visited)
4. Continue scanning the grid
5. Return the total count

**Why this works:**
- Each island is counted exactly once when its first `'1'` is encountered
- DFS marks all cells belonging to that island, preventing double-counting
- The in-place modification eliminates the need for a separate visited array

---

## Code Implementation: DFS

```java
public static int numIslands(char[][] grid) {
    if (grid == null)
        return 0;

    int count = 0;

    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == '1') {
                count++;
                dfs(grid, i, j);
            }
        }
    }
    
    return count;
}

private static void dfs(char grid[][], int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
        return;

    grid[i][j] = '0';  // Mark as visited
    dfs(grid, i - 1, j);  // Up
    dfs(grid, i + 1, j);  // Down
    dfs(grid, i, j - 1);  // Left
    dfs(grid, i, j + 1);  // Right
}
```

---

## Step-by-Step Walkthrough

**Input Grid:**
```
1 0 0
1 1 0
0 0 1
```

**DFS Traversal:**

| Step | Position | Action | Grid State | Count |
|------|----------|--------|------------|-------|
| 1 | (0,0) | Found '1', count++, start DFS | 0 0 0<br>1 1 0<br>0 0 1 | 1 |
| 2 | (0,0) | Mark visited, explore up/down/left/right | 0 0 0<br>1 1 0<br>0 0 1 | 1 |
| 3 | (1,0) | Mark visited, explore neighbors | 0 0 0<br>0 1 0<br>0 0 1 | 1 |
| 4 | (1,1) | Mark visited, explore neighbors | 0 0 0<br>0 0 0<br>0 0 1 | 1 |
| 5 | Continue scanning | All connected '1's marked as '0' | 0 0 0<br>0 0 0<br>0 0 1 | 1 |
| 6 | (2,2) | Found '1', count++, start DFS | 0 0 0<br>0 0 0<br>0 0 0 | 2 |
| 7 | (2,2) | Mark visited, explore all neighbors (out of bounds) | 0 0 0<br>0 0 0<br>0 0 0 | 2 |

**Final Result:** 2 islands

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| DFS | O(m × n) | O(m × n) worst case | Space is recursion stack depth for skewed traversal |

**Where:**
- **m** = number of rows in the grid
- **n** = number of columns in the grid

**Time Complexity Explanation:**
- Each cell is visited at most once (when it's `'1'`)
- The outer loops scan all m × n cells
- Total work is O(m × n)

**Space Complexity Explanation:**
- In the worst case (all `'1'`s forming a snake pattern), the recursion stack can go up to O(m × n)
- No additional data structures are used (in-place modification)

---

## Key Techniques

1. **DFS (Depth-First Search):** Explore all connected cells before moving on
2. **Flood Fill:** Mark connected cells as visited by changing `'1'` to `'0'`
3. **In-place Modification:** Avoid extra space by modifying the grid
4. **Boundary Checking:** Ensure we don't go out of grid bounds
5. **Directional Exploration:** Check up, down, left, right neighbors

---

## Edge Cases

- **Empty grid (grid == null):** Return 0
- **All water:** Return 0
- **All land:** Return 1
- **Single cell grid:** Return 1 if '1', else 0
- **Single row grid:** Islands separated by water horizontally
- **Single column grid:** Islands separated by water vertically
- **Grid with diagonal connections only:** Diagonals don't count as connected

---

## Visualization

**Example 1:**
```
1 1 0
1 0 0
0 0 1
```
Islands: 2 (top-left block, bottom-right single)

**Example 2:**
```
1 1 1
0 1 0
1 0 1
```
Islands: 3 (all '1's are separated by at least one '0')

**Example 3:**
```
1 0 1
0 1 0
1 0 1
```
Islands: 5 (each '1' is isolated - diagonal doesn't connect)

**Example 4:**
```
1 1 1 1
0 1 0 1
1 1 0 1
0 0 1 0
```
Islands: 2 (large blob on top-left, vertical strip on right)

---

## Related Problems

- **Max Area of Island** - Find the largest island
- **Number of Distinct Islands** - Count unique island shapes
- **Surrounded Regions** - Capture regions surrounded by 'X'
- **Pacific Atlantic Water Flow** - Similar grid DFS pattern
- **Flood Fill** - Classic flood fill algorithm

---

## Common Mistakes

1. **Not handling null grid:**
   - Accessing `grid.length` on null reference
   - Forgetting the null check

2. **Incorrect boundary checks:**
   - Not checking all four boundaries (up, down, left, right)
   - Using wrong comparison operators (< vs <=)

3. **Not marking visited cells:**
   - Forgetting to set `grid[i][j] = '0'` after visiting
   - This causes infinite recursion or double counting

4. **Wrong traversal direction:**
   - Including diagonal directions (should only be 4-directional)
   - Missing one of the four directions

5. **Modifying grid vs. creating visited array:**
   - Creating extra space unnecessarily
   - Not understanding in-place modification is acceptable

---

## Tags

#leetcode #graph #dfs #matrix #connected-components #medium

---

## Additional Notes

### Pattern Recognition

This problem follows the **Grid DFS** pattern:
- Grid is treated as an implicit graph
- Each cell is a node
- Edges connect adjacent cells
- DFS/BFS explores connected components

### Alternative: BFS Approach

You can also solve this using BFS with a queue:

```java
public static int numIslandsBFS(char[][] grid) {
    if (grid == null || grid.length == 0)
        return 0;

    int count = 0;
    int m = grid.length, n = grid[0].length;
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '1') {
                count++;
                grid[i][j] = '0';
                queue.offer(new int[]{i, j});
                
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    for (int[] dir : dirs) {
                        int r = cell[0] + dir[0];
                        int c = cell[1] + dir[1];
                        if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == '1') {
                            grid[r][c] = '0';
                            queue.offer(new int[]{r, c});
                        }
                    }
                }
            }
        }
    }
    return count;
}
```

### Space Optimization

The DFS approach modifies the grid in-place, achieving O(1) additional space (excluding recursion stack). This is a key optimization to mention in interviews.

### Union-Find Alternative

Another approach uses Union-Find (Disjoint Set Union):
- Initially count every '1' as an island
- Union adjacent '1' cells, decrementing count on each union
- Time: O(m × n × α(m×n)) where α is the inverse Ackermann function

### Visualizing the DFS Call Stack

For a 3x3 grid starting at (0,0):

```
dfs(0,0)
├── dfs(-1,0) → return (out of bounds)
├── dfs(1,0)
│   ├── dfs(0,0) → return (already '0')
│   ├── dfs(2,0) → return (water)
│   ├── dfs(1,-1) → return (out of bounds)
│   └── dfs(1,1)
│       ├── dfs(0,1) → return (water)
│       ├── dfs(2,1) → return (water)
│       ├── dfs(1,0) → return (already '0')
│       └── dfs(1,2) → return (water)
├── dfs(0,-1) → return (out of bounds)
└── dfs(0,1) → return (water)
```

---

## Example Walkthroughs

**Example 1:**
```
Grid:
1 1 0
0 0 0
0 0 0
```
- Start at (0,0): count=1, DFS marks (0,0) and (0,1)
- No more '1's found
- Result: 1

**Example 2:**
```
Grid:
1 0 0
0 1 0
0 0 1
```
- Start at (0,0): count=1, DFS marks only (0,0)
- Start at (1,1): count=2, DFS marks only (1,1)
- Start at (2,2): count=3, DFS marks only (2,2)
- Result: 3 (no adjacent lands)

**Example 3:**
```
Grid:
1 1 1
0 0 0
1 1 0
```
- Start at (0,0): count=1, DFS marks all three in row 0
- Start at (2,0): count=2, DFS marks (2,0) and (2,1)
- Result: 2

**Example 4:**
```
Grid:
0 0 0
0 0 0
0 0 0
```
- No '1's found
- Result: 0

---

## Interview Tips

1. **Clarify the problem:**
   - Are diagonals considered connected? (No, only horizontal/vertical)
   - Can we modify the input grid? (Yes, in-place is acceptable)
   - What if grid is null or empty?

2. **Start with brute force:**
   - Explain scanning every cell and checking connectivity
   - Show why we need traversal (DFS/BFS)

3. **Explain the DFS insight:**
   - When we find a '1', it's a new island
   - DFS marks the entire island as visited
   - This prevents double-counting

4. **Mention in-place optimization:**
   - Modifying grid to '0' avoids a visited array
   - Space complexity stays O(1) besides recursion stack

5. **Analyze complexity:**
   - Time: O(m × n) - each cell visited once
   - Space: O(m × n) worst case for recursion stack

6. **Discuss alternative approaches:**
   - BFS uses explicit queue
   - Union-Find is another option
   - Each has trade-offs in time/space

---

*Source: LCPatterns/Medium/NumberOfIslands.java*
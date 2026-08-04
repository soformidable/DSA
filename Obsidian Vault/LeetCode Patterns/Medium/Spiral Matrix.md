# Spiral Matrix

**Problem Statement:**  
Given an m x n matrix, return all elements of the matrix in spiral order.

## Problem:
https://leetcode.com/problems/spiral-matrix/description/

---

## Key Insight

The spiral order traversal requires maintaining **four boundaries** of the current spiral layer:
- `top` - topmost unvisited row
- `right` - rightmost unvisited column
- `bottom` - bottommost unvisited row
- `left` - leftmost unvisited column

**Algorithm:**
1. Traverse from left to right along the top boundary
2. Traverse from top to bottom along the right boundary
3. Traverse from right to left along the bottom boundary (if top <= bottom)
4. Traverse from bottom to top along the left boundary (if left <= right)
5. Move all boundaries inward
6. Repeat until all elements are visited

---

## Solution: Boundary Shrinking Approach

**Algorithm:**
1. Initialize boundaries: `top=0`, `right=cols-1`, `bottom=rows-1`, `left=0`
2. While `top <= bottom` and `left <= right`:
   - Traverse top row left to right
   - Traverse right column top to bottom
   - Traverse bottom row right to left (if top <= bottom)
   - Traverse left column bottom to top (if left <= right)
   - Move all boundaries inward
3. Return the result list

---

## Code Implementation

```java
public static List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix == null || matrix.length == 0)
        return result;

    int top = 0;
    int right = matrix[0].length - 1;
    int left = 0;
    int bottom = matrix.length - 1;

    while (top <= bottom && left <= right) {
        // Traverse top row left to right
        for (int i = left; i <= right; i++) {
            result.add(matrix[top][i]);
        }
        top++;

        // Traverse right column top to bottom
        for (int i = top; i <= bottom; i++) {
            result.add(matrix[i][right]);
        }
        right--;

        // Traverse bottom row right to left (if top <= bottom)
        if (top <= bottom) {
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
        }

        // Traverse left column bottom to top (if left <= right)
        if (left <= right) {
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
    }
    return result;
}
```

---

## Step-by-Step Walkthrough

**Input:**
```
[
 [1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]
]
```

**Initial Boundaries:**
- top=0, right=2, bottom=2, left=0

**Iteration 1:**
- Traverse top row: [1, 2, 3]
- top=1
- Traverse right column: [6, 9]
- right=1
- Traverse bottom row: [8, 7]
- bottom=1
- Traverse left column: [4]
- left=1

**Iteration 2:**
- top=1, right=1, bottom=1, left=1
- top > bottom → exit loop

**Result:** `[1, 2, 3, 6, 9, 8, 7, 4, 5]`

---

## Complexity Analysis

- **Time Complexity:** O(m × n) - Visits each element exactly once
- **Space Complexity:** O(1) - Only using constant extra space (excluding result storage)

---

## Key Techniques

1. **Boundary Shrinking:** Gradually reduces the spiral layer
2. **Conditional Traversal:** Only traverse rows/columns if boundaries are valid
3. **Four-Phase Approach:** Left-to-right, top-to-bottom, right-to-left, bottom-to-top

---

## Edge Cases

- **Empty matrix** - Return empty list
- **Single row** - Traverse left to right
- **Single column** - Traverse top to bottom
- **Square matrix** - All four directions are used
- **Rectangular matrix** - Only three directions are used in last iteration

---

## Visualization

```
Initial Matrix:
┌───────┬───────┬───────┐
│   1   │   2   │   3   │
├───────┼───────┼───────┤
│   4   │   5   │   6   │
├───────┼───────┼───────┤
│   7   │   8   │   9   │
└───────┴───────┴───────┘

After First Iteration:
┌───────┬───────┬───────┐
│   1   │   2   │   3   │
├───────┼───────┼───────┤
│   4   │   5   │   6   │
├───────┼───────┼───────┤
│   7   │   8   │   9   │
└───────┴───────┴───────┘

Result: [1, 2, 3, 6, 9, 8, 7, 4, 5]
```

---

## Related Problems

- **Spiral Matrix II** - Generate spiral matrix from number
- **Rotate Image** - Rotate matrix by 90 degrees
- **Matrix Block Sum** - Calculate block sums in matrix
- **Game of Life** - Simulate cellular automaton

---

## Common Mistakes

1. **Forgetting boundary checks:**
   - Must check `top <= bottom` before bottom row traversal
   - Must check `left <= right` before left column traversal

2. **Off-by-one errors:**
   - Remember to move boundaries after traversal

3. **Not handling rectangular matrices:**
   - Need to handle cases where rows ≠ columns

4. **Modifying the matrix:**
   - Should only read elements, not modify

---

## Tags

#leetcode #matrix #simulation #medium

---
*Source: LCPatterns/Medium/SpiralMatrix.java*
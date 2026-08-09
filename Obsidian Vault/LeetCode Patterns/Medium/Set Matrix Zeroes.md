# Set Matrix Zeroes

**Problem Statement:**
Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's. You must do it in-place.

## Problem
https://leetcode.com/problems/set-matrix-zeroes/description/

---

## Key Insight

The challenge is to **mark rows and columns to be zeroed** while **preserving the original matrix state** until all markings are processed. The solution requires:

1. **Tracking which rows and columns need to be zeroed**
2. **Using the matrix itself as a marker** when possible
3. **Efficiently applying the zeroing** in a single pass

**Approach:**
- Use two boolean arrays to track which rows and columns need to be zeroed
- First pass: Mark rows and columns based on zeros found
- Second pass: Apply the zeroing based on the marks

---

## Solution: Two-Pass Approach

**Algorithm:**
1. Create two boolean arrays `row` and `col` to track which rows and columns need to be zeroed
2. First pass: Iterate through the matrix and mark rows/columns where zeros are found
3. Second pass: Zero out all elements in marked rows and columns

**Why this works:**
- The two arrays act as a lookup table for which rows/columns need zeroing
- The first pass identifies all zeros and their positions
- The second pass applies the zeroing based on the marks

---

## Code Implementation

```java
public static void setZeroes(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;

    // Create arrays to track which rows and columns need to be zeroed
    boolean[] r = new boolean[row];
    boolean[] c = new boolean[col];

    // First pass: Mark rows and columns to be zeroed
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (matrix[i][j] == 0) {
                r[i] = true;
                c[j] = true;
            }
        }
    }

    // Second pass: Zero out marked rows and columns
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (r[i] || c[j]) {
                matrix[i][j] = 0;
            }
        }
    }
}
```

---

## Space-Optimized Solution

**Algorithm:**
1. Use the first row and column as markers
2. First pass: Mark the first row and column based on zeros
3. Use a boolean variable to track if the first row needs to be zeroed
4. Second pass: Zero out marked rows and columns
5. Third pass: Apply the first row and column zeroing if needed

**Why this works:**
- Uses the matrix itself to store markers, reducing space complexity
- First row and column can be used to mark which rows/columns need zeroing
- Special handling needed for the first row and column themselves

---

## Code Implementation: Space-Optimized

```java
public static void setZeroesOptimized(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;
    boolean firstRowZero = false;
    boolean firstColZero = false;

    // Check if first row needs to be zeroed
    for (int j = 0; j < col; j++) {
        if (matrix[0][j] == 0) {
            firstRowZero = true;
            break;
        }
    }
    
    // Check if first column needs to be zeroed
    for (int i = 0; i < row; i++) {
        if (matrix[i][0] == 0) {
            firstColZero = true;
            break;
        }
    }
    
    // Use first row and column as markers
    for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    
    // Zero out marked rows and columns (excluding first row and column)
    for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    // Zero out first row if needed
    if (firstRowZero) {
        for (int j = 0; j < col; j++) {
            matrix[0][j] = 0;
        }
    }
    
    // Zero out first column if needed
    if (firstColZero) {
        for (int i = 0; i < row; i++) {
            matrix[i][0] = 0;
        }
    }
}
```

---

## Step-by-Step Walkthrough

**Input:**
```
[
 [1,1,1],
 [1,0,1],
 [1,1,1]
]
```

**Two-Pass Approach Walkthrough:**

1. **First Pass:**
   - Mark row 1 and column 1 as needing zeroing
   - `r = [false, true, false]`
   - `c = [false, true, false]`

2. **Second Pass:**
   - Zero out all elements in row 1 and column 1
   - Result:
     ```
     [
      [1,0,1],
      [0,0,0],
      [1,0,1]
     ]
     ```

**Space-Optimized Approach Walkthrough:**

1. **Check first row and column:**
   - First row is not zeroed
   - First column is not zeroed

2. **Mark using first row and column:**
   - Set matrix[1][0] = 0 and matrix[0][1] = 0
   - Result:
     ```
     [
      [1,0,1],
      [0,0,1],
      [1,1,1]
     ]
     ```

3. **Zero out marked rows and columns:**
   - Zero out row 1 and column 1
   - Result:
     ```
     [
      [1,0,1],
      [0,0,0],
      [1,0,1]
     ]
     ```

4. **Zero out first row and column if needed:**
   - No action needed in this case

**Final Result:**
```
[
 [1,0,1],
 [0,0,0],
 [1,0,1]
]
```

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| Two-Pass with Arrays | O(m×n) | O(m+n) | Uses additional arrays |
| Space-Optimized | O(m×n) | O(1) | Uses first row/column as markers |

---

## Key Techniques

1. **Two-Pass Approach:** Uses separate arrays to track which rows/columns need zeroing
2. **Space Optimization:** Uses the matrix itself to store markers
3. **First Row/Column Handling:** Special handling needed for first row and column
4. **In-Place Modification:** Modifies the matrix without using extra space (space-optimized version)

---

## Edge Cases

- **Empty matrix:** Return immediately
- **Single row or column:** Zero entire row or column
- **All zeros:** Entire matrix should be zeroed
- **No zeros:** Matrix remains unchanged
- **First row or column contains zeros:** Need special handling

---

## Visualization

**Input Matrix:**
```
┌───────┬───────┬───────┐
│ 1     │ 1     │ 1     │
├───────┼───────┼───────┤
│ 1     │ 0     │ 1     │
├───────┼───────┼───────┤
│ 1     │ 1     │ 1     │
└───────┴───────┴───────┘
```

**After Processing:**
```
┌───────┬───────┬───────┐
│ 1     │ 0     │ 1     │
├───────┼───────┼───────┤
│ 0     │ 0     │ 0     │
├───────┼───────┼───────┤
│ 1     │ 0     │ 1     │
└───────┴───────┴───────┘
```

---

## Related Problems

- **Transpose Matrix:** Rotate matrix by 90 degrees
- **Rotate Image:** Rotate matrix in-place
- **Spiral Matrix:** Traverse matrix in spiral order
- **Matrix Block Sum:** Calculate block sums in matrix
- **Word Search:** Search for words in 2D grid

---

## Common Mistakes

1. **Not handling first row and column correctly:**
   - Need special variables to track if first row/column needs zeroing

2. **Overwriting markers:**
   - In space-optimized solution, must process first row/column separately

3. **Off-by-one errors:**
   - Need to be careful with array indices

4. **Not using the matrix itself for markers:**
   - In space-optimized solution, must use first row/column as markers

5. **Forgetting to zero out first row/column:**
   - Must apply zeroing to first row/column if needed

---

## Tags

#leetcode #array #matrix #medium

---

## Additional Notes

### Handling Edge Cases in Space-Optimized Solution

For matrices with all zeros in the first row or column:

```java
public static void setZeroesOptimized(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;
    boolean firstRowZero = false;
    boolean firstColZero = false;

    // Check if first row needs to be zeroed
    for (int j = 0; j < col; j++) {
        if (matrix[0][j] == 0) {
            firstRowZero = true;
            break;
        }
    }
    
    // Check if first column needs to be zeroed
    for (int i = 0; i < row; i++) {
        if (matrix[i][0] == 0) {
            firstColZero = true;
            break;
        }
    }
    
    // Use first row and column as markers (skip first element)
    for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    
    // Zero out marked rows and columns (excluding first row and column)
    for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    // Zero out first row if needed
    if (firstRowZero) {
        for (int j = 0; j < col; j++) {
            matrix[0][j] = 0;
        }
    }
    
    // Zero out first column if needed
    if (firstColZero) {
        for (int i = 0; i < row; i++) {
            matrix[i][0] = 0;
        }
    }
}
```

### Alternative Space-Optimized Approach

Another approach uses a single variable to mark the first row and column:

```java
public static void setZeroesOptimizedAlt(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;
    boolean firstRowZero = false;
    boolean firstColZero = false;

    // Check if first row needs to be zeroed
    for (int j = 0; j < col; j++) {
        if (matrix[0][j] == 0) {
            firstRowZero = true;
            break;
        }
    }
    
    // Check if first column needs to be zeroed
    for (int i = 0; i < row; i++) {
        if (matrix[i][0] == 0) {
            firstColZero = true;
            break;
        }
    }
    
    // Use first row and column as markers (skip first element)
    for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    
    // Zero out marked rows and columns (excluding first row and column)
    for (int i = 1; i < row; i++) {
        for (int j = 1; j < col; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    // Zero out first row if needed
    if (firstRowZero) {
        for (int j = 0; j < col; j++) {
            matrix[0][j] = 0;
        }
    }
    
    // Zero out first column if needed
    if (firstColZero) {
        for (int i = 0; i < row; i++) {
            matrix[i][0] = 0;
        }
    }
}
```

---

## Example Walkthroughs

**Example 1:**
Input:
```
[
 [0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]
]
```

Result:
```
[
 [0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]
]
```

**Example 2:**
Input:
```
[
 [1,2,3],
 [4,0,6],
 [7,8,9]
]
```

Result:
```
[
 [1,0,3],
 [0,0,0],
 [1,0,3]
]
```

**Example 3:**
Input:
```
[
 [0,0,0],
 [0,1,2],
 [3,4,5]
]
```

Result:
```
[
 [0,0,0],
 [0,0,0],
 [0,0,0]
]
```

---

*Source: LCPatterns/Medium/SetZeroesMatrix.java*
# Search Matrix Binary Search Pattern

## Overview
This file summarizes the solution for the problem where you need to search for a target value in a 2D matrix that is sorted in ascending order both row-wise and column-wise. The solution leverages binary search for efficient searching.

## Problem:
https://leetcode.com/problems/search-a-2d-matrix/description/

## Problem Description
Given an `m x n` matrix where each row and column is sorted in ascending order, and an integer `target`, determine if the target exists in the matrix. If it does, return `true`; otherwise, return `false`.

## Key Insight
The matrix is sorted both row-wise and column-wise, which allows us to use a clever binary search approach. Instead of treating the matrix as a 2D array, we can treat it as a 1D sorted array and apply binary search.

## Solution Approach
1. **Flatten the Matrix Conceptually**: Treat the 2D matrix as a 1D array where the index of an element at `(row, col)` is calculated as `row * n + col` (where `n` is the number of columns).
2. **Binary Search**: Use binary search to find the target in this conceptual 1D array.
3. **Index Calculation**: Convert the mid index from the 1D array back to 2D coordinates to access the matrix element.

## Solution Code
```java LCPatterns/Medium/SearchMatrixBin.java
public class SearchMatrixBin {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
```

## Explanation
1. **Edge Case Handling**: The method first checks if the matrix is empty or if any of its dimensions are zero, returning `false` immediately in such cases.
2. **Binary Search Setup**: The binary search is initialized with `left` set to `0` and `right` set to the last index of the conceptual 1D array (`rows * cols - 1`).
3. **Mid Calculation**: The mid index is calculated as `left + (right - left) / 2` to avoid potential overflow.
4. **2D Index Conversion**: The mid index is converted back to 2D coordinates using `mid / cols` for the row and `mid % cols` for the column.
5. **Comparison**: The value at the calculated 2D coordinates is compared with the target. Depending on whether the value is less than or greater than the target, the search space is adjusted.
6. **Termination**: If the target is found, the method returns `true`. If the loop terminates without finding the target, it returns `false`.

## Time and Space Complexity
- **Time Complexity**: O(log(m * n)), where `m` is the number of rows and `n` is the number of columns. This is because binary search is applied to a conceptual 1D array of size `m * n`.
- **Space Complexity**: O(1), as no additional space is used apart from a few variables for indices and comparisons.

## Alternative Approach
Another common approach is to start from the top-right corner of the matrix and move left or down based on comparisons with the target. This approach also runs in O(m + n) time but uses O(1) space.

---
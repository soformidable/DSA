

Consider a two dimensional array row x col -> nums\[row]\[col]

Transpose:

1. First loop runs from row = 0 to row = nums.length
2. Second loop runs from col = row to col < nums.length
3. Swap nums\[row]\[col] with nums\[col]\[row]

Code:

```
        for(int row = 0 ; row < nums.length ; row++){
            for(int col = row ; col < nums.length ; col++){
            
                int temp = nums[col][row];
                nums[col][row] = nums[row][col];
                nums[row][col] = temp;
                
            }
        }
```

## NOTE

1. **Square matrices** can be transposed **in-place** (swapping across diagonal)
    
2. **Non-square matrices** must create a **new matrix** with swapped dimensions

Code for non square matrix transpose (A new matrix is required as the row x col becomes col x row)

```
public static int[][] transpose(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    // Create new matrix with swapped dimensions
    int[][] result = new int[cols][rows];
    
    for(int i = 0; i < rows; i++) {
        for(int j = 0; j < cols; j++) {
            result[j][i] = matrix[i][j];
        }
    }
    
    return result;
}
```

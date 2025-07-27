

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
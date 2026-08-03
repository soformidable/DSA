https://leetcode.com/problems/rotate-image/description/


To rotate a matrix 90 degrees :

1. Transpose the matrix -> [[Transposing a matrix]]
2. Reverse each row (column from  col = 0 to col.length/2) -> this is because the elements after middle element are already swapped.
	1. Condition -> nums\[row]\[col] = nums\[row]\[nums\[0].length - col - 1]


Key Idea:
1. Transpose
2. Swap elements in row up to row.len/2




```
    public void rotate(int[][] nums) {              

        for(int row = 0 ; row < nums.length ; row++){

            for(int col = row ; col < nums.length ; col++){

                int temp = nums[col][row];

                nums[col][row] = nums[row][col];

                nums[row][col] = temp;

            }

        }

  

        for(int row = 0 ; row < nums.length ; row++)

            for(int col = 0 ; col < nums[row].length/2 ; col++){

                int temp = nums[row][col];
				//// [row][length - 1 - index from end(moves inward towards middle)]
                nums[row][col] = nums[row][nums[row].length - 1 - col];

                nums[row][nums[row].length - 1 - col] = temp;

            }

    }
```


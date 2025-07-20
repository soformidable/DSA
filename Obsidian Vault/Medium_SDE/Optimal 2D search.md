
https://leetcode.com/problems/search-a-2d-matrix/description/

Given a 2D array (m x n) where each row is globally sorted, i.e. last element of each row is less than the first element of the next row.


O(m + logn) ->

1. check the first occurrence of target < matrix\[row]\[matrix\[row].length - 1]
2. Perform a binary search on that row by taking the index (row number)


Optimal approach:
O(log (m x  n))


Idea : Treat the entire 2D arrays a flattened single dimensional array 
where low = 0 and high = (m x n) - 1 and do a binary search

\[\[1 ,2 ,3],\[3 ,4 ,5],\[6, 7 ,8]]  ----> \[1, 2, 3, 4, 5, 6, 7, 8, 9]


```
    public static boolean searchMatrixLog(int[][] matrix, int target){

        int row = matrix.length;

        int col = matrix[0].length;

  

        int low = 0;

        int high = (row * col) - 1;

        int mid = 0;

  

        while(low <= high){

            mid = (low + (high - low))/2;

            int r = mid / col;

            int c = mid % col;

            int val = matrix[r][c];

            if(val == target)

                return true;

            else if (target < val)

                low = mid + 1;

            else

                high = mid - 1;

        }

  

        return false;

    }
```







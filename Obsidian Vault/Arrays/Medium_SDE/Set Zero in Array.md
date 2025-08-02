
https://leetcode.com/problems/set-matrix-zeroes/description/

Brute force code:


Space complexity: O(1)
Time complexity O((N*M)+(N+M))

************************************************************************

Using separate rows\[size = i] and column array\[size = j] and then marking them ith row = 1 and jth column = 1.
Then finally using row i == 1 or column j == 1 to turn all the cells zero


**Code**

```
class Solution {

    public void setZeroes(int[][] matrix) {

        int row = matrix.length;

        int column = matrix[0].length;

  

        int r[] = new int[row];

        int c[] = new int[column];

  

        for(int i=0;i<row;i++){

            for(int j=0;j<column;j++){

                if(matrix[i][j]==0){

                    r[i] = 1;

                    c[j] = 1;

                }

            }

        }

  

        for(int i=0;i<row;i++){

            for(int j=0;j<column;j++){

                if(r[i]==1 || c[j]==1)

                    matrix[i][j]=0;

            }

        }

  

    }

}
```
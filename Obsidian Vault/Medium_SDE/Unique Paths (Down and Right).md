https://leetcode.com/problems/unique-paths/description/

Given m x n (Grid size)


1. Brute Force with recursion (exponential complexity)

Traces all the paths starting from (0,0)

Code:



```
    public static int uniquePaths(int m , int n){

        if(m == 1 && n == 1)

            return 1;

        //int count = drivePath(0, 0 , m , n);

  

        int memo[][] = new int[m][n];

        int count = drivePathMemo(0, 0, m, n, memo);

        return count;

    }

  

    public static int drivePath(int i , int j, int m , int n){

        if(i == m-1 && j == n-1)

            return 1;

        if( i == m || j == n)

            return 0;

 
        return drivePath(i+1, j , m , n) + drivePath(i, j+1 , m , n);

    }
```


2. Dp and Memoization

Keeps a track of overlapping states and adds them in the memo\[i]\[j]

Complexity O(m x n)

![[Pasted image 20250719122805.png]]



Code:

```
    public static int drivePathMemo(int i , int j , int m , int n, int memo[][]){

  

        if(i == m -1 && j == n - 1)

            return 1;

        if(i == m || j == n)

            return 0;

        if(memo[i][j]!=0) return memo[i][j];

  

        memo[i][j] = drivePathMemo(i+1, j, m, n, memo) + drivePathMemo(i, j+1, m, n, memo);

        return memo[i][j];

    }
```


3. Combinatorics:

![[Pasted image 20250719122849.png]]


Code : 

```
    public static int uniquePathsCombi(int m , int n){

        long res = 1L;

        for(int i = 1 ; i <= m-1 ; i++)

            res = res * (n - 1 + i)/i;

        return (int)res;

    }
```
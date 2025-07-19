public class UniquePath {
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

    public static int drivePathMemo(int i , int j , int m , int n, int memo[][]){

        if(i == m -1 && j == n - 1)
            return 1;
        if(i == m || j == n)
            return 0;
        if(memo[i][j]!=0) return memo[i][j];

        memo[i][j] = drivePathMemo(i+1, j, m, n, memo) + drivePathMemo(i, j+1, m, n, memo);
        return memo[i][j];
    }

    public static int uniquePathsCombi(int m , int n){
        long res = 1L;

        for(int i = 1 ; i <= m-1 ; i++)
            res = res * (n - 1 + i)/i;

        return (int)res;
    }

    public static void main(String args[]){
        System.out.println(uniquePaths(3, 2));
    }
}   

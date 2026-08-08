public class UniquePaths {
    public static int uniquePaths(int m, int n) {

        if(m <= 0 || n <= 0)
            return 0;

        Integer memo[][] = new Integer[m][n];

        return generatePath(0,0,m,n,memo);
    }
    public static int generatePath(int i,int j, int m, int n, Integer memo[][]){
        if(i>=m || j >=n)
            return 0;

        if(i == m - 1 && j == n - 1)
            return 1;

        if(memo[i][j]!=null)
            return memo[i][j];

        memo[i][j] = generatePath(i, j+1, m, n, memo) + generatePath(i+1, j, m, n, memo);     

        return memo[i][j];
    }
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

}

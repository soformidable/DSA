class NumberOfIslands{
    public static int numIslands(char[][] grid) {
        
        if(grid == null)
            return 0;

        int count = 0;

        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
     
        return count;
    }

    private static void dfs(char grid[][], int i , int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return; 

        grid[i][j] = '0';
        dfs(grid, i - 1 , j);
        dfs(grid, i + 1 , j);
        dfs(grid, i  , j - 1);
        dfs(grid, i , j + 1);

    }
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{{'1','0','0'},{'1','1','0'},{'0','0','1'}}));
    }
}
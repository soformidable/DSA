
public class WordSearch{
    public static boolean exist(char[][] board, String word) {

        for(int i = 0 ; i < board.length; i ++)
            for(int j = 0 ; j < board[0].length; j++){
                if(dfs(board, 0, i, j, word))
                    return true;
        }
        return false;
    }   

    private static boolean dfs(char board[][], int index, int row, int col, String word){
        if(index == word.length())
            return true;

        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index))
            return false;

        char temp = board[row][col];
        board[row][col] = '#';


        boolean exists = dfs(board, index + 1, row + 1, col, word) || dfs(board, index + 1, row - 1, col, word) || dfs(board, index + 1, row , col + 1, word) || dfs(board, index + 1, row, col - 1, word);

        board[row][col] = temp;
            
        return exists;
    }

    public static void main(String[] args) {
        //System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
        System.out.println(exist(new char[][]{{'a','b'},{'c','d'}}, "abcd"));
    }
}
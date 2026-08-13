# Word Search

## Problem Statement
Given a 2D board and a word, find if the word exists in the grid. The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

## Approach: Backtracking with DFS
The solution uses depth-first search (DFS) with backtracking to explore all possible paths in the grid.

### Key Concepts:
1. **Starting Points**: Iterate through each cell in the board as a potential starting point
2. **DFS Exploration**: For each starting point, recursively explore all 4 directions (up, down, left, right)
3. **Backtracking**: Mark visited cells with '#' to prevent revisiting, then restore the original character when backtracking
4. **Termination Conditions**:
   - ✅ If index equals word length: entire word found
   - ❌ If out of bounds or character doesn't match: invalid path

### Algorithm:
```
exist(board, word):
    for each cell (i, j) in board:
        if dfs(board, 0, i, j, word):
            return true
    return false

dfs(board, index, row, col, word):
    if index == word.length: return true
    
    if (out of bounds) or (board[row][col] != word[index]):
        return false
    
    // Mark as visited
    temp = board[row][col]
    board[row][col] = '#'
    
    // Explore 4 directions
    result = dfs(row+1, col) or dfs(row-1, col) or 
             dfs(row, col+1) or dfs(row, col-1)
    
    // Backtrack
    board[row][col] = temp
    
    return result
```

## Code Implementation
```java title="WordSearch.java"
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, 0, i, j, word))
                    return true;
            }
        return false;
    }   

    private static boolean dfs(char board[][], int index, int row, int col, String word) {
        if(index == word.length())
            return true;

        // Boundary and character check
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index))
            return false;

        char temp = board[row][col];
        board[row][col] = '#';  // Mark visited

        // Explore all 4 directions
        boolean exists = dfs(board, index + 1, row + 1, col, word) || 
                        dfs(board, index + 1, row - 1, col, word) || 
                        dfs(board, index + 1, row, col + 1, word) || 
                        dfs(board, index + 1, row, col - 1, word);

        board[row][col] = temp;  // Backtrack
        return exists;
    }
}
```

## Complexity Analysis
- **Time Complexity**: O(N × M × 4^L) where:
  - N = number of rows
  - M = number of columns  
  - L = length of word
  - 4^L because at each position we can move in 4 directions

- **Space Complexity**: O(L) for the recursion stack (where L is the word length)

## Example
```java
Input: 
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED"

Output: true (path: A→B→C→C→E→D)
```

## Notes
- The same cell cannot be reused in the same path
- We mark cells with '#' to track visited status during DFS
- The cell is restored during backtracking to allow other paths to use it

## Related Problems
- [[Word Search II]] (Hard)
- [[Number of Islands]]
- [[Surrounded Regions]]

#LeetCode #Medium #Array #Backtracking #DFS #Matrix
</contents>
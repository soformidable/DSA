import java.util.Arrays;

public class RotateMatrix90{
    public static void rotate(int[][] matrix) {
        int temp = 0;
        int n = matrix.length;

        for(int row = 0 ; row < n ; row++){
            for(int col = row; col < n ; col++){
                temp = matrix[col][row];
                matrix[col][row] = matrix[row][col];
                matrix[row][col] = temp;
            }
        };

        for(int row = 0; row < n ; row++){
            for(int col = 0; col < n/2; col++){
                temp = matrix[row][col];
                // [row][length - 1 - index from end(moves inward towards middle)]
                matrix[row][col] = matrix[row][n - 1 - col];
                matrix[row][n - 1 - col] = temp;
            }
        }

    }
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(arr);
        for(int x[] : arr){
            System.out.println(Arrays.toString(x));
        }
    }
}
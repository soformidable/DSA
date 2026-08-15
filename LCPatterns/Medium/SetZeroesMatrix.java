import java.util.*;

public class SetZeroesMatrix {
    public static void setZeroes(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int r[] = new int[row];
        int c[] = new int[col];

        for(int i = 0 ; i< matrix.length; i++){
            for(int j = 0; j< matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    r[i] = 1;
                    c[j] = 1;
                }
            }
        }

        for(int i = 0 ; i < row; i++){
            for(int j = 0 ; j < col ; j++){
                if(r[i] == 1 || c[j] == 1)
                    matrix[i][j] = 0;
            }
        }
    }
    public static void main(String[] args) {
        int arr[][] = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(arr);
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
        System.out.println(Arrays.deepToString(arr));
    }

}

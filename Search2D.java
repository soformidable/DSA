import java.util.*;

public class Search2D {

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

    public static boolean searchMatrix(int[][] matrix, int target) {
        int arr[] = new int[matrix.length];
        int index = 0;

        if(matrix == null || matrix.length == 0)
            return false;

        for(int i = 0 ; i < matrix.length ; i ++)
            arr[i] = matrix[i][matrix[i].length - 1];
        

        for(int i = 0 ; i < arr.length ; i++){
            if(target <= arr[i]){
                index = i;
                break;
            } 
        }

        int low = 0;
        int high = matrix[index].length-1;
        System.out.println(low + " " + high);
        int mid = 0;
        while(low <= high){
            mid = (int)(low + high)/2;
            if(matrix[index][mid] == target)
                return true;
            else if(target < matrix[index][mid])
                high = mid;
            else
                low = mid + 1;

        }
        
        return false;
    }
    public static void main(String args[]){
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,37,60}} , 7));
    }
}

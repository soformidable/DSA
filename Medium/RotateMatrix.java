import java.util.*;

public class RotateMatrix {
    public static int[][] rotate90(int nums[][]){
        if(nums.length == 0 || nums == null)
            return new int[0][0];
        boolean arr[][] = new boolean[nums.length][nums[0].length];

        for(int row = 0; row < nums.length ; row++){
            for(int col = 0 ; col < nums[row].length ; col++){
                //if(!arr[col][-])
                nums[col][nums.length - row - 1] = nums[row][col];
                    arr[row][col] = true;
            }
        }
        return nums;
    }





    public static void rotateMatrix(int nums[][]){
       
        for(int row = 0 ; row < nums.length ; row++){
            for(int col = row ; col < nums.length ; col++){
                int temp = nums[col][row];
                nums[col][row] = nums[row][col];
                nums[row][col] = temp;
            }
        }

        for(int row = 0 ; row < nums.length ; row++)
            for(int col = 0 ; col < nums[row].length/2 ; col++){
                int temp = nums[row][col];
                nums[row][col] = nums[row][nums[row].length - col - 1];
                nums[row][nums[row].length - col - 1] = temp;
            }
    }
    public static void printArr(int arr[][]){
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[i].length ; j++)
                System.out.print(arr[i][j] + "\t");
            System.out.println();
        }
    }

    public static void main(String args[]){
        int arr[][] = new int[][]{{1,2,3,4},{4,5,6,8},{7,8,9,9}};
        printArr(arr);
        System.out.println("After");
        rotateMatrix(arr);
        printArr(arr);
    }
}

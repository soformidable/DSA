public class SearchInMatrixBin {
    public static boolean searchMatrix(int[][] matrix, int target) {

        int arr[] = new int[matrix.length];
        int index = -1;

        for(int i = 0 ; i < matrix.length ; i ++){
            arr[i] = matrix[i][matrix[i].length - 1];
        }

        for(int i = 0 ; i < matrix.length ; i ++)
            if(target <= arr[i]){
                index = i;
                break;
            }

        for(int i = 0 ; i < matrix[index].length; i++ ){
            if(target == matrix[index][i])
                return true;
        }

        return false;
    }
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5},{6,7,8},{9,10,11}}, 4));
    }
}

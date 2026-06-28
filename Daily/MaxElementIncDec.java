import java.util.Arrays;

public class MaxElementIncDec{
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr){
        
        if(arr.length == 1)
            return 1;

        Arrays.sort(arr);

        int largest = -1;
        int index = 0;

        if (arr[index] != 1)
            arr[index] = 1;

        for(int i = 1 ; i < arr.length ; i++){

            int prev = arr[index];

            if(Math.abs(prev - arr[i]) <= 1)
                largest = Math.max(largest,arr[i]);
            else
                arr[i] = prev + 1;
            largest = Math.max(largest,arr[i]);
            index++;
        }
        return largest;
    }

    //Optimize code
    public static int maximumElementAfterDecrementingAndRearranging_optmized(int[] arr) {
    Arrays.sort(arr);
    
    int result = 1;  // First element must be 1
    
    for (int i = 1; i < arr.length; i++) {
        result = Math.min(arr[i], result + 1);
        }
    return result;
    }

    public static void main(String args[]){
        int arr[] = new int[]{78,93,9};
        System.out.println(maximumElementAfterDecrementingAndRearranging(arr));
    }
}
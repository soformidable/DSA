import java.util.*;

public class ReversePairs {

    public static void merge(int nums[] , int p , int q , int r){
        int n1 = q - p + 1;
        int n2  = r - q;

        int arr1[] = new int[n1];
        int arr2[] = new int[n2];

        for(int i = 0 ; i < n1; i ++)
            arr1[i] = nums[p + i];
        for(int i = 0 ; i < n2; i++)
            arr2[i] = nums[q+1+i];

        int i = 0, j = 0 , k = p;

        while(i < n1 && j < n2){
            if(arr1[i] <= arr2[j]){
                nums[k] = arr1[i];
                i++;
            }
            else{
                nums[k] = arr2[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            nums[k] = arr1[i];
            i++;
            k++;
        }
        while(j < n2){
            nums[k] = arr2[j];
            j++;
            k++;
        }
    }

    public static int countPairs(int arr[], int start, int mid , int end){
        int count = 0;

        // right is used to start the second array on the right
        int right = mid + 1;

        for(int i = start ; i <= mid ; i ++){
            // (long) For cases where arr[i] = 2147483647 and 2 x arr[i] causes integer overflow
            while(right <= end && (long)arr[i] > ((long)2 * (long)arr[right]))
                right++;           

            //  right  - mid + 1 is not correct. Use Brackets to convert it to right - mid - 1 
            count+= (right - (mid + 1)) ;
            }
        return count;
    }


    public static int mergeSort(int nums[], int start , int end){
        int count = 0;

        //(BASE CASE)
        if(start >= end) return count;

            int mid = (start + end)/2;
            count+=mergeSort(nums, start, mid);
            count+=mergeSort(nums, mid+1 , end);
            count+=countPairs(nums, start, mid, end);
            merge(nums, start, mid, end);

        return count;
    }

    public static int reversePairs(int nums[]){
        if(nums.length == 0 || nums == null)
            return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    public static void main(String args[]){

        System.out.println(reversePairs(new int[]{1,3,2,3,1}));
        int arr[] = {1,3,2,3,1};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

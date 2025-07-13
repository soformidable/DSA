public class MergeSort {
    //(array, lower, mid, upper)
    public static void merge(int arr[],int p, int q, int r){

        //mid - lower + 1
        int n1 = q - p + 1;
        //upper - mid
        int n2 = r - q;
        int L[] = new int[n1];
        int M[] = new int[n2];

        for(int i = 0 ; i < n1; i++)
           L[i] = arr[p+i];
        for(int j = 0; j < n2; j++)
            M[j] = arr[q+1+j];

        int i=0,j=0;
        int k=p;

        while(i < n1 && j < n2){

            if(L[i] <= M[j]){
                arr[k] = L[i];
                i++;
            }
            else{
                arr[k] = M[j];
                j++;
            }
            k++;
        }
        // These 2 while loops are for placing the leftover elements left after one of the sublist is exhausted
        while(i<n1){
            arr[k]=L[i];
            i++;k++;
        }     
        while(j<n2){
            arr[k]=M[j];
            j++;k++;
        }
    }

    public static void mergeSort(int arr[], int p, int r){
        if(p<r){  // this condition is to make sure the recursion call is only done for sub-arrays with more than 1 element
            int q = (p+r)/2;

            mergeSort(arr, p, q);
            mergeSort(arr, q+1, r);
            
            merge(arr, p, q, r);
        }
    }

    public static void printArr(int arr[]){
        for(int x : arr)
            System.out.print(" "+x+" ");
        System.out.println(" ");
    }

    public static void main(String args[]){
        int arr[] = new int[] {3, 4, 1, 5, 6, 7, 11, 10, 9, 2};
        System.out.println("Before sorting : ");
        printArr(arr);
        mergeSort(arr,0,arr.length-1);
        System.out.println("After sorting : ");
        printArr(arr);
    }
}

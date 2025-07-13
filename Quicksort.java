public class Quicksort {    
    public static int partition(int arr[], int low, int high){

        int pivot = arr[high];
        int i = low - 1;

        for(int j=low;j<high;j++){
            if(arr[j]<=pivot){
                i++;

                //swapping smaller element with higher
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

            }
        }
        //swapping the pivot
        int temp = arr[high];
        arr[high] = arr[i+1];
        arr[i+1] = temp;

        return(i+1);
    }

    public static void quicksort(int arr[],int low, int high){
        if(low<high){

            int pi = partition(arr, low, high);
            quicksort(arr, low, pi-1);
            quicksort(arr, pi+1, high);
            
        }
    }

    public static void printArr(int arr[]){
        for(int x: arr)
            System.out.print(" "+x+" ");

        System.out.println(" ");
    }

    public static void main(String args[]){
        int arr[] = new int[] {2,5,3,7,4,1};
        printArr(arr);
        quicksort(arr, 0, arr.length-1);
        printArr(arr);
    }
}

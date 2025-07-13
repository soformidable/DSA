
public class HeapDS {

    public static void heapify(int arr[], int len, int i) {
        int largest = i;
        int lnode = 2 * i + 1;
        int rnode = 2 * i + 2;
    
        if (lnode < len && arr[lnode] > arr[largest])
            largest = lnode;
        if (rnode < len && arr[rnode] > arr[largest])
            largest = rnode;
    
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;    
            heapify(arr, len, largest);  // inside the if ffs !!!!!!!!!!!            
        }
    }
    
    public static void buildHeap(int arr[],int len){
        int index = (len/2)-1;   // (size/2 - 1 is to find the last non leaf node in the complete binary) 

        for(int i=index;i>=0;i--)
            heapify(arr, len, i);
    }

    public static void heapSort(int arr[],int len){

        buildHeap(arr, len);
        //printHeap(arr);

        for(int i=len-1;i>=0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }


    public static void printHeap(int arr[]){
        for(int i=0;i<arr.length;++i){
            System.out.print(arr[i]+" ");
            
        }
        System.out.println(" ");
    }

	public static void main(String args[]){
        int arr[] = new int[] {1, 4, 6, 2, 13, 9};
        int len = arr.length;
        System.out.println("Before sorting");
        printHeap(arr);
        heapSort(arr, len);
        System.out.println("After sorting");
        printHeap(arr);
    }
}

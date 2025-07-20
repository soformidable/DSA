public class SelectionSort {
    public static void select(int arr[]){

        int min = arr[0];

        for(int i=0;i<arr.length;i++) {
            min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }

    }
    public static void printArr(int arr[]){
        for(int x : arr)
            System.out.print(" "+x+" ");
        System.out.println("");
    }

    public static void main(String args[]){
        int arr[] = new int[] {4,3,2,7,1};
        printArr(arr);
        select(arr);
        printArr(arr);
    }
}

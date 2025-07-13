public class Bubble {
    public static void bSort(int arr[]){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                    if(arr[j]>arr[j+1]){
                        int temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
            }
        }
    }
    public static void printArr(int arr[]){
        for(int x : arr)
            System.out.print(" "+x+" ");
        System.out.println("");
    }

    public static void main(String args[]){
        int arr[] = new int[] {4,3,1,6,2};
        printArr(arr);
        bSort(arr);
        printArr(arr);
    }

}

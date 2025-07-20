import java.util.*;

public class LexicalSort {
    public static void sort(List<List<Integer>> lst,int[] arr,int index){
        if(index == arr.length){
            lst.add(convertArrayToList(arr));
            return;
        }
        
        for(int i = index;i<arr.length;i++){
            swap(arr , index, i);

            //Recursive call to create permutations for the next element
            sort(lst , arr , index+1);

            //Backtracking
            swap(arr , index , i);
        } 
    }

    public static List<List<Integer>> permute(int arr[]){
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        sort(lst,arr,0);
        return lst;
    }


    public static void swap(int[] arr,int index, int i){
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    public static List<Integer> convertArrayToList(int[] arr){
        List<Integer> lst = new ArrayList<Integer>();
        for(int x : arr)
            lst.add(x);
        return lst;
    }

    public static void nextPermutation(int arr[]){
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        int[] copy = new int[arr.length];
        copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);
        lst = permute(arr);
        for(int i = 0;i<lst.size();i++){
            if(lst.get(i).contains(copy)){
             // Not implemented because linear search adds more complexity   
            }
                
        }   
        
    }

    public static int[] reverse(int arr[],int index){
        int ptr = arr.length - 1;
        while(index <= ptr){
            swap(arr,index,ptr);
            index++;
            ptr--;
        }
        return arr;
    }

    public static void nextPermutation_optimized(int arr[]){
        int index = -1;
        for(int i = arr.length-2;i>=0;i--){
            // Find the breaking point
            if(arr[i]<arr[i+1]){
                index = i;
                break;
            }
            
        }
        if(index == -1){
            reverse(arr,0);
            return;
        }


        for(int i = arr.length-1;i>index;i--){
            if(arr[i]>arr[index]){
                swap(arr,i,index);
                break;
            }
        }

        reverse(arr,index+1);

    }


    public static void main(String args[]){
        int arr[] = new int[]{3,2,1};
        //Arrays.sort(arr);
        nextPermutation_optimized(arr);
        System.out.println(Arrays.toString(arr));
        //System.out.println(permute(arr));


    }
}

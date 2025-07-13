import java.util.*;




public class DivideArrayThree {

    public static int[][] convertListTo2DArray(List<List<Integer>> lst) {
                return lst.stream()
              .map(innerList -> innerList.stream()
                                        .mapToInt(Integer::intValue)
                                        .toArray())
              .toArray(int[][]::new);
            }


        public static int[][] divideArray(int[] nums, int k) {
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        int arr[] = new int[]{4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11};
        int group_size = 3;
        Arrays.sort(arr);
        int i =0;
        while(i+2 < arr.length){
            if(arr[i+2] - arr[i] <=k){
                lst.add(Arrays.asList(arr[i],arr[i+1],arr[i+2]));
                i+=group_size;
            }
            else{
                System.out.println("Cannot form pairs");
                return(new int[0][0]);
            }
        }
        int arr_f[][] = convertListTo2DArray(lst);
        return(arr_f);
    }
    public static void main(String args[]){
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        int arr[] = new int[]{4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11};
        //int group_size = 3;
        int k = 14;
        //Arrays.sort(arr);
        // int i =0;
        // while(i+2 < arr.length){
        //     if(arr[i+2] - arr[i] <=k){
        //         lst.add(Arrays.asList(arr[i],arr[i+1],arr[i+2]));
        //         i+=group_size;
        //     }
        //     else{
        //         System.out.println("Cannot form pairs");
        //         return;
        //     }
        // }


        // for(int i = 0;i<arr.length;i+=group_size){
        //     List<Integer> temp = new ArrayList<Integer>();
        //     for(int j = i;j<i + group_size  && j<arr.length;j++)
        //         temp.add(arr[j]);
        //     lst.add(temp);
        // }
        
        System.out.println(Arrays.toString(divideArray(arr, k)));
        
    }


}

import java.util.*;


public class RankTransformArray{
    public static int[] arrayRankTransform(int[] arr) {

        if(arr == null || arr.length == 0)
            return new int[]{};

        int arr_cp[] = Arrays.copyOf(arr, arr.length);
        HashMap<Integer,Integer> map = new HashMap<>();

        Arrays.sort(arr);
        int rank = 1;
        int prev = arr[0];
        map.put(prev, rank);

        for(int i = 1 ; i < arr.length ; i ++){
            // if prev == arr[i] is useless because the key:value pair already exists in the hashmap
            if(prev != arr[i]){
                rank++;
                map.put(arr[i],rank);
            }
            prev = arr[i];
        }

        for(int i = 0 ; i < arr_cp.length ; i ++){
            arr_cp[i] = map.get(arr_cp[i]);
        }

        return arr_cp;
    }
    public static void main(String args[]){
        System.out.println(Arrays.toString(arrayRankTransform(new int[]{37,12,28,9,100,56,80,5,12})));
    }
}
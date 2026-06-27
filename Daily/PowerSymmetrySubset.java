import java.util.*;

public class PowerSymmetrySubset {
    public static int maximumLength(int len[]){
        if(len == null || len.length < 0)
            return 0;
        Map<Long,Integer> map = new HashMap<Long,Integer>();

        for(int x : len){
            map.merge((long) x,1,Integer::sum);
        }
        int Onecnt = map.getOrDefault(1L, 0);

        // if number of 1 is odd then return the same count else round it down to last odd number
        int ans = (Onecnt & 1) == 1 ? Onecnt : Onecnt - 1;

        map.remove(1L);

        for(long i : map.keySet()){
            int res = 0;
            long x = i;

                while(map.containsKey(x) && map.get(x) > 1){
                    res+=2;
                    x*=x;
                }
            // either answer is in 1s or the dupes + peak. Peak is already calculated in the last iteration x*=x; if yes then add 1 else remove 1.
            ans = Math.max(ans,res + (map.containsKey(x)? 1 : -1));
        }


        return ans;
    }




    public static void main(String args[]){
        int arr[] = new int[]{1,2,3,4,2,5,6};
        // After sorting --> 1,2,2,3,4,5,6
        System.out.print("Length of the susbset: " + maximumLength(arr));
        }

}

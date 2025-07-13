import java.util.*;

public class RepeatMissNumber {
    public static List<Integer> findNumbers(int nums[]){
        List<Integer> lst = new ArrayList<>();
        int arr[] = new int[nums.length+1];
        Arrays.fill(arr,0);
        for(int i = 0 ; i < nums.length ; i ++)
            arr[nums[i]] += 1;
        
        lst.add(0, 0);
        lst.add(1, 0);
            
        for(int i = 0 ; i < arr.length -1 ; i++){
            if(arr[i+1] == 0)
                lst.set(0,i+1);
            if(arr[i+1] > 1)
                lst.set(1 , nums[i]);
        }
        System.out.println(Arrays.toString(arr));
        return lst;
    }

    public static int[] findMissingNumber(int nums[]){
        int n = nums.length;

        long s = 0 , sn = 0 , s2 = 0 , s2n = 0;
        for(int i = 0 ; i < n ; i++){
            s+=nums[i];
            s2+=nums[i]*nums[i];
        }
        sn = (n * (n+1))/2;
        s2n = (n * (n + 1) * ((2 * n) + 1))/6;

        // X - Y
        long val1 = s - sn;

        //X^2 - Y^2
        long val2 = s2 - s2n;

        // (X+Y) = (X^2 - X^2)/(X - Y)
        val2 = val2 / val1;


        //Find X and Y: X = ((X+Y)+(X-Y))/2 and Y = X-(X-Y),
        // Here, X-Y = val1 and X+Y = val2:
        long x = (val1 + val2)/2;
        long y = x - val1;


        return new int[]{(int)x,(int)y};
    }

    public static int[] missingNumberXOR(int nums[]){


        return new int[0];
    }


    public static void main(String args[]){
        System.out.println(Arrays.toString(findMissingNumber(new int[]{3,1,2,5,3})));

        System.out.println(1^1^1^1^2);
    }
}

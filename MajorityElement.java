import java.util.*;
import java.util.stream.Collectors;

public class MajorityElement {
    public static int majorityMoore(int[] nums){
        int count = 0, element = 0;

        for(int i = 0 ; i < nums.length ; i++){
            if(count == 0)
                element = nums[i];
            if(element == nums[i])
                count++;
            else 
                count--;
        }


        return element;
    }

    public static List<Integer> majorityElementN3(int nums[]){
        int e1 = 0 , e2 = 0, c1 = 0 , c2 = 0;
        List<Integer> lst = new ArrayList<Integer>();

        for(int x : nums){
            if(x == e1)
                c1++;
            else if (x == e2)
                c2++;
            else if (c1 == 0){
                e1 = x;
                c1 = 1;
            }
            else if (c2 == 0){
                e2 = x;
                c2 = 1;
            }
            else{
                c1--;
                c2--;
            }
            }

        int cn1=0, cn2=0;
        for(int x : nums){
            if(x == e1) cn1++;
            if(x == e2) cn2++;
        }
        if(cn1 > nums.length/3) lst.add(e1);
        if(cn2 > nums.length/3  && !lst.contains(e2)) lst.add(e2);

        return lst;
    }

    public static List<Integer> majorityElement(int[] nums) {
        if(nums.length == 2)
            return Arrays.stream(nums).boxed().collect(Collectors.toList());

        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        List<Integer> lst = new ArrayList<>();

        for(int x : nums){
            hm.put(x,hm.getOrDefault(x,0)+1);
            if(hm.get(x) > nums.length/3 && !lst.contains(x))
                    lst.add(x);           
        }
        return lst;
    }
    public static void main(String args[]){
        //System.out.println(majorityMoore(new int[]{2,2,1,1,1,2,2}));
        //System.out.println(majorityElement(new int[]{1,1,1,2,2,3,2,3}));
        System.out.println(majorityElementN3(new int[]{1,2}));
    }

}

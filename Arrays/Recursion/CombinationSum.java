import java.util.*;
import java.util.stream.Collectors;

// 2,3,4  target -> 5

public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<Integer> lst = Arrays.stream(candidates).boxed().collect(Collectors.toList());
        List<Integer> cnd = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<>();


        getSum(lst, cnd, 0, target,0,result);
        return result;
    }


    public static void getSum(List<Integer> lst  , List<Integer> cand, int index, int target, int sum, List<List<Integer>> result){
        if(target == sum){
            result.add(new ArrayList<>(cand));
            return;
        }   
        if(index >= lst.size() || sum > target)
            return;

        cand.add(lst.get(index));
        getSum(lst,cand,index,target,lst.get(index) + sum,result);
        
        cand.remove(cand.size() - 1);

        getSum(lst,cand,index + 1,target,sum,result);
        
    }

    public static void main(String args[]){
        // List<Integer> lst = Arrays.asList(new Integer[] {2,3,5});
        int candidates[] = new int[] {2,3,5};

        int target = 8;
        // Set<List<Integer>> result = new HashSet<>();

        System.out.println(combinationSum(candidates,target));

        // getSum(lst, cnd, 0, target,0,result);
        // System.out.println(result);

    }
}

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinationSumII {

        public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

            Arrays.sort(candidates);

            List<Integer> lst = Arrays.stream(candidates).boxed().collect(Collectors.toList());

            List<Integer> cnd = new ArrayList<Integer>();
            List<List<Integer>> result = new ArrayList<List<Integer>>();


            getSum(lst, cnd, 0, target, 0, result);
        
            return result;

    }

    public static void getSum(List<Integer> lst, List<Integer> cnd, int index, int target, int sum, List<List<Integer>> result){

        if(sum == target){
            result.add(new ArrayList<Integer>(cnd));
            return;
        }

        if(index >= lst.size() || sum > target)
            return;

        cnd.add(lst.get(index));
        getSum(lst, cnd, index + 1, target, sum + lst.get(index), result);

        cnd.remove(cnd.size() - 1);

        int nextIndex = index + 1;
        while(nextIndex < lst.size() && lst.get(nextIndex).equals(lst.get(index)))
            nextIndex++;

        getSum(lst, cnd, nextIndex, target, sum, result);

}
    public static void main(String args[]){
        System.out.println(combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
        System.out.println(combinationSum2(new int[] {2,5,2,1,2}, 5));
    }

}

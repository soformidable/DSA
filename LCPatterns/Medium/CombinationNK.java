import java.util.*;

public class CombinationNK{
    public static List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();

        combination(new ArrayList<Integer>(), result, n, k, 1);

        return result;
    }

    public static void combination(List<Integer> cand, List<List<Integer>> result, int n, int k,  int start){
        if(cand.size() == k){
            result.add(new ArrayList<>(cand));
            return;
        }


        for(int i = start; i <=n ; i++){

            cand.add(i);
            combination(cand, result, n, k, i + 1);
            cand.remove(cand.size() - 1);
        }

    }
    public static void main(String[] args) {
        System.out.println(combine(1,1));
    }
}
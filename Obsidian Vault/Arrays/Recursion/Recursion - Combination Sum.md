
Problem: https://leetcode.com/problems/combination-sum/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Recursion/CombinationSum.java



Approach:

1. For printing a sequence or subsets always choose "PICK or NOT PICK"
2. create a getSum helper function
	1. it has source integer list (candidates), current list, index, target, sum, result list
3. base case if (sum == target)
	1. add the sublist to result list
4. add index to sublist
5. make a recursive call to include the list(index) --> index   ;; sum + lst.get(index)
6. backtrack --> lst.remove(lst.size() - 1)
7. make another recursive call to not include the element --> index ;; sum

Code:

```
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

  

        List<Integer> lst = Arrays.stream(candidates).boxed().collect(Collectors.toList());

        List<Integer> cnd = new ArrayList<Integer>();

        List<List<Integer>> result = new ArrayList<>();

  
  

        getSum(lst, cnd, 0, target,0,result);

        return result;

    }

  
  

    public static void getSum(List<Integer> lst  , List<Integer> cand, int index, int target, int sum, List<List<Integer>> result){

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
```

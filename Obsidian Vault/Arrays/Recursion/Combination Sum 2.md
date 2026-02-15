
Problem: https://leetcode.com/problems/combination-sum/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Recursion/CombinationSumII.java


Approach:

1. Same as Palindrome combination 1
2. Link: https://github.com/soformidable/DSA/blob/main/Obsidian%20Vault/Arrays/Recursion/Recursion%20-%20Combination%20Sum.md
3. Either include the index value in current list or don't (sum // sum + lst.get(index)) with index + 1 (keep moving forward)
4. Additional check to skip through the duplicates (to avoid using set and duplicate sub lists)
	1. Use nextIndex = index + 1
	2. use while to check if nextIndex == index and move nextIndex Forward


Code:

```
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
```
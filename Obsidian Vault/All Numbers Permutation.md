
Problem: https://leetcode.com/problems/permutations/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Recursion/NumberAllPermutation.java

Approach:

1. Use backtrack(array, list, boolean array, result) function to call from the controller function
2. Use a used\[] boolean array to keep a track of used and not used elements
3. Do not use index to control the recursion
4. Base case is current_list.size() == nums.length
	1. add to result
	2. return
5. run a for loop from 0 to nums.length to iterate through all the elements
6. check if used\[i] is true
	1. Then continue to move forward
7. add the nums\[i] --> current_list
8. call backtrack() again with the updated used and current_list
	1. This will create branches of 1 --> (1,2) , (1,3) --> (1,2,3), (1,3,2)
9. backtrack to remove the last added element from cand (cand.remove)
10. set used\[i] to false again for other branches


Code:

```
    public static List<List<Integer>> permute(int[] nums) {

  

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        boolean used[] = new boolean[nums.length];

  

        getPermutations(nums, new ArrayList<Integer>(), used, result);

  

        return result;

    }

  

    public static void getPermutations(int[] nums, List<Integer> cand, boolean[] used,  List<List<Integer>> result){

        if(cand.size() == nums.length){

            result.add(new ArrayList<>(cand));

            return;

        }

        for(int i = 0 ; i < nums.length ; i ++){

            if(used[i]) continue;

            used[i] = true;

            cand.add(nums[i]);

            getPermutations(nums, cand, used, result);

            cand.remove(cand.size() - 1);

            used[i] = false;

        }

  
  

    }
```

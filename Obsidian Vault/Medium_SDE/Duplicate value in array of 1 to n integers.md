
https://leetcode.com/problems/find-the-duplicate-number/


Idea:
1. create a boolean array from size n+1 and fill it with false
2. for every value encounters in nums -->
		Set bool_arr\[i] = true;
3. If there is a value already set to true, that is the duplicate value.



Code: 

```
    public int findDuplicate(int[] nums) {

                if(nums.length == 0 || nums == null)

            return 0;

        boolean[] arr = new boolean[nums.length];

        for(int i = 0 ; i < nums.length ; i ++){

            if(arr[nums[i]])

                return nums[i];

            else

                arr[nums[i]] = true;

    }

    return 0;

    }

```
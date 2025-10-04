
Problem: https://leetcode.com/problems/max-consecutive-ones/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Easy/MaxConsecutiveOnes.java

Two pointer approach
==O(n) --> i = index==

Code:
```
    public static int findMaxConsecutiveOnes(int[] nums) {

        int max = 0;

        for(int i = 0 ; i < nums.length ; i ++){

            int streak = 0;

            int index = i;

            while(index < nums.length && nums[index] == 1){

                index++;

                streak++;

            }

            i = index;

            max = Math.max(max,streak);

        }

        return max;

    }
```
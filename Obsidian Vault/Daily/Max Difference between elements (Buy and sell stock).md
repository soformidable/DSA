
Instead using a variable to loop through the array, We keep a track of the lowest element and use max(max , nums[i]-lowest) when nums[i]>lowest. 
If not then we replace lowest with lowest = Lowest_value_between(lowest , nums[i])
**The Loop goes from 1  to n


```
class Solution {

    public int maximumDifference(int[] nums) {

       if(nums.length<2)

            return -1;

        int min = nums[0];

        int max = -1;

        for(int i=1;i<nums.length;i++){

            if(nums[i] > min)

            max = Math.max(max, nums[i]-min);

            else

            min = Math.min(min,nums[i]);

        }

  

        return max;

    }

}
```



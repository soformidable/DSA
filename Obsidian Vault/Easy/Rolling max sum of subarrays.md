

To find the rolling max sum of a subarray,

maintain global_max and local_max.

local_max is either nums\[i] + local_max or just local_max
local_max = max(nums\[i] + local_max , max)
Then check if it exceeds global_max
global_max = max(global_max,local_max)


```
        int max = nums[0];

        int temp_sum = nums[0];

  

        for(int i = 1 ; i<nums.length;i++){

            temp_sum = Math.max(nums[i],temp_sum+nums[i]);
-
            max = Math.max(max,temp_sum);

        }
```
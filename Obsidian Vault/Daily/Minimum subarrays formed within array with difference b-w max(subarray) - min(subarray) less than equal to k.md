


The goal is to find the total (minimum) number of subarrays formed such that
max(subarray) - min(subarray) <= k

1. Idea is to sort the array first
2. The starting from first element maintain two indices i and start such that
	 if (arr\[i] - arr\[start] <=k)
	 increment i till the condition fails
	
3.	Whenever a number is found that is greater nums\[i] - start  then create a new subarray by count++



```
    while (i < nums.length) {
        int start = nums\[i];
        count++;  // start new subarray

        // Expand subarray as far as possible
        while (i < nums.length && nums[i] - start <= k) {
            i++;
        }
    }

    return count;
```

https://takeuforward.org/data-structure/length-of-the-longest-subarray-with-zero-sum/


Given => nums\[]
To Find : Max count of elements such that sum == 0

**First approach**:
Brute Force O(n^2)

Code:
```
    public static int zeroSum(int nums[]){
        int maxCount = 0;

        for(int i = 0 ; i < nums.length ; i ++){
            int sum = 0;
            int index = i;
            int count = 0;
            while(index < nums.length){
                sum += nums[index];
                if(sum == 0)
                    count = (index - i)+1;
                index++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
```

==Second Approach (OPTIMAL):==

Time -> O(n)

1. Use prefix sum to calculate running sum in a for loop
2. Use Hashmap to store (current_sum,index)
3. Check if the sum already exists. 
	1. This means the elements after the sum did not contribute and add up to zero
	2. Example (9, -3, 3)
		1. Total sum is 9 because -3 + 3 = 0
		2. Thus 9 will be encountered twice
	3. maxLength = Math.max(current_index - (hashmap(sum,store_index)));
4. If the sum doesn't exist
	1. Store (current_sum , current_index);
5. Return maxLength


Code:

```
    public static int zeroSumPrefix(int nums[]){

        int sum = 0, maxLength = 0;
        Map<Integer,Integer> hm = new HashMap<Integer,Integer>();

        for(int i = 0 ; i < nums.length ; i ++){

            sum+=nums[i];

            if(sum == 0)

                maxLength = i + 1;

            if(hm.containsKey(sum)){

                maxLength = Math.max((i - hm.get(sum)), maxLength);

            }

            else

                hm.put(sum,i);

        }

        return maxLength;

    }
```





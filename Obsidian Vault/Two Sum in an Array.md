

Given: An array and a target
Answer -> two indices i,j where arr\[i] + arr\[j] == target where i != j
Else -> -1 , -1
Second variant (Return yes/no)



1. Brute force
		Two for loops
	O(n^2)



2. Using HashMap (Works for both variants)
		HashMap<Integer,Integer> hs => (diff/num , index(i))
	Calculate value = target - nums\[i]
		if hs.containsKey(value)
			return (new int\[]{i,hs.get(value)})
		Else return (-1,-1)

Code:
```
    public static int[] twoSumHash(int nums[],int target){

        HashMap<Integer,Integer> hs = new HashMap<Integer,Integer>();

        for(int i = 0 ; i < nums.length ; i++){

            int num = nums[i];

            int diff = target - num;

            if(hs.containsKey(diff))

                return(new int[]{i,hs.get(diff)});

            else

                hs.put(num,i);

        }

        return(new int[]{-1,-1});

    }
```



3. Using two pointers (Only works for boolean return)
		1. Sort the array
		2. start and end
		3. while(start < end)

Code:
```
    //Only works for the variant where answer is boolean (Yes/No)

    public static int[] twoSumTwoPoint(int nums[] , int target){

        Arrays.sort(nums);

        int start = 0 , end = nums.length - 1;

        while(start < end){

            if(nums[end] + nums[start] == target)

                return(new int[]{start,end});

            else if(nums[end] + nums[start] < target)

                end--;

            else

                start++;

        }

        return (new int[]{-1,-1});

    }
```
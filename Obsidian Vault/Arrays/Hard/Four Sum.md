Problem: https://leetcode.com/problems/4sum/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Medium/FourSum.java

Given nums\[] and target


1. Optimal approach

Use two fixed pointers and two moving pointers.
i --> 0 to n-1
	Check(i> 0 && nums\[i] == nums\[i -1 ]) then : continue--> To check duplicates
j --> i+1 to n-1
	Check(j> i+1 && nums\[j] == nums\[j -1 ]) then : continue--> To check duplicates
		Declare k = j+1 and l = nums.length - 1
			while(k < l)
				if((long) sum == target)
					lst.add(Arrays.asList(new Integer\[]{nums\[i],nums\[j],nums\[k],nums\[l]}));
						k++; l--;
						Now, use two while loops to skip over duplicates from the matches
						==l from right to left <-- and k from left to right -->==
				else if(sum > target)
					l--;
				else
					k++;

Code:

```
    public static List<List<Integer>> fourSum(int nums[],int target){

       List<List<Integer>> lst = new ArrayList<>();

        if(nums.length < 4 || nums == null)

            return lst;

        Arrays.sort(nums);

        for(int i = 0 ; i < nums.length ; i ++){

            if(i> 0 && nums[i] == nums[i-1])

                continue;

            for(int j = i+1 ; j < nums.length ; j++){

                if(j > i + 1 && nums[j] == nums[j-1])

                    continue;

                int k = j+1, l = nums.length - 1;

                while(k < l){

                if((long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l] == (long)target){

                    lst.add(Arrays.asList(new Integer[]{nums[i],nums[j],nums[k],nums[l]}));

                    k++; l--;

                    while(k < l && nums[k-1] == nums[k])k++;

                    while(k < l && nums[l+1] == nums[l])l--;

                }

                else if((long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l] > (long)target)

                    l--;

                else

                    k++;

                }

            }

        }

        return lst;

    }
```


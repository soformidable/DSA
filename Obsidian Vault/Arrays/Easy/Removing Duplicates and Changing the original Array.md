

Problem: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Easy/RemoveDuplicates.java


Approach 1 (Using HashSet)

Code:
```
  public static int removeDuplicates(int[] nums) {
        if(nums == null  || nums.length == 0)
            return 0;
        //As the input is already sorted TreeSet is unnecessary to maintain the order
        TreeSet<Integer> hs = new TreeSet<Integer>();
        for(int x : nums)

            hs.add(x);

        int index = 0;

        for(Integer x : hs)

            nums[index++] = x;

        return hs.size();

    }
```


Approach 2 (Two pointer)

1. use uniqueIndex = 0
2. Run the loop i from 1 to nums.length
3. when a new element is encountered
	1. increment uniqueIndex
	2. set nums\[uniqueIndex]  = nums\[i]
4. return (uniqueIndex + 1) ---> 0 indexed

Code:
```
    public static int removeDuplicatesOP(int[] nums){

        if(nums == null && nums.length == 0)

            return 0;

        int uniqueIndex = 0;

        for(int i = 1 ; i < nums.length ; i ++){

            if(nums[uniqueIndex] != nums[i]){

                uniqueIndex++;

                nums[uniqueIndex] = nums[i];

            }

        }

  

        return uniqueIndex + 1;

    }
```
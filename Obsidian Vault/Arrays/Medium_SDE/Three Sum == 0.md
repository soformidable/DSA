
Problem: 
Code:

#### Approach 1 (Using HashSet)

1. Declare a Hashset\<List\<Integer>>
2. Run i  loop
3. Declare another Hashset\<Integer>
4. Run j loop
5. calculate third = -1L * (i + j)
6. if it exists in the second hashset
7. sort the three numbers and add them to the first hashset

Code:
```
    public static List<List<Integer>> triplet(int n, int[] arr) {

        Set<List<Integer>> st = new HashSet<>();

  

        for (int i = 0; i < n; i++) {

            Set<Integer> hashset = new HashSet<>();

            for (int j = i + 1; j < n; j++) {

                //Calculate the 3rd element:

                int third = -(arr[i] + arr[j]);

  

                //Find the element in the set:

                if (hashset.contains(third)) {

                    List<Integer> temp = Arrays.asList(arr[i], arr[j], third);

                    temp.sort(null);

                    st.add(temp);

                }

                hashset.add(arr[j]);

            }

        }

  

        // store the set elements in the answer:

        List<List<Integer>> ans = new ArrayList<>(st);

        return ans;

    }
```


#### Approach 2 (Optimal three pointer)

Same as FourSum where the addition is equal to value k.



1. Sort the array
2. Run loop from i to n-1
3. skip duplicates (i > o && nums\[i-1] == nums\[j]) --> continue;
4. create j = i +1 and k = n -1 
5. skip duplicates in j (j > i  + 1 -----)
6. start while(j < k)
7. if(sum == 0)
	1. add the three to the list
	2. increment j
	3. decrement k
	4. run two while loops to move j forward and k backwards to skip the duplicates
		1. while(j < k && nums\[j] == nums\[j-1]) --> j++;
		2. Same for k except k--;
8. else if (sum > 0)
	1. k--;
9. else 
	1. j++;
10. Return lst

Code:
```
    public static List<List<Integer>> threeSum(int[] nums){

        Arrays.sort(nums);

        List<List<Integer>> lst = new ArrayList<List<Integer>>();

  

        for(int i = 0 ; i < nums.length ; i ++){

            if(i > 0 && nums[i-1] == nums[i])

                continue;

            int j = i + 1 , k = nums.length - 1;

            if(j > i + 1 && nums[j] == nums[j-1])

                continue;

            while(j < k){

            if((long)nums[i] + (long)nums[j] + (long)nums[k] == 0L){

                lst.add(Arrays.asList(nums[i],nums[j],nums[k]));

                j++;k--;

                while(j<k && nums[j-1]==nums[j]) j++;

                while(j<k && nums[k+1]==nums[k]) k--;

            }

            else if ((long)nums[i] + (long)nums[j] + (long)nums[k] > 0L)

                k--;

            else

                j++;

        }

        }

        return lst;

    }
```
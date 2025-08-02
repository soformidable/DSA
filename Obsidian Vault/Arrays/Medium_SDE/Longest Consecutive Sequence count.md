https://leetcode.com/problems/longest-consecutive-sequence/description/



1. Use a HashSet to add all the unique elements from the array.
2. start a loop on nums : hs
3. Start counting only when there is no element smaller than the current element 
	1. Can also be done using no element greater than current element
4. Run a while until there is no greater element (or smaller according to the other case)
5. Check max between currentstreak and longeststreak



Code:
```
    public static int longestConsecutive(int[] nums) {

        HashSet<Integer> hs  = new HashSet<>();

        int longestStreak = 0;

        for(int num : nums)

            hs.add(num);

        for(int x : hs){

            if(!hs.contains(x - 1)){

                int currentnum = x;

                int currentStreak = 1;

                while(hs.contains(currentnum + 1)){

                    currentnum++;

                    currentStreak++;

                }

                longestStreak = Math.max(currentStreak, longestStreak);

            }          

        }

        return longestStreak;

    }
```
import java.util.HashSet;

public class LongestConsecutiveSequence {
        public static int longestConsecutive(int[] nums) {

        if(nums == null || nums.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<Integer>();

        for(int x : nums)
            set.add(x);

        int longestStreak = 0;

        for(int num : set){
            if(!set.contains(num - 1)){
                int currentStreak = 1;
                int currentNum = num;

                while(set.contains(currentNum + 1)){
                    currentStreak++;
                    currentNum++;
                }
                longestStreak = Math.max(currentStreak,longestStreak);
            }           
        }
        return longestStreak;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}

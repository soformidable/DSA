import java.util.*;

public class ConsecutiveSeq {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> hs  = new HashSet<>();
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
    public static void main(String args[]){
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}

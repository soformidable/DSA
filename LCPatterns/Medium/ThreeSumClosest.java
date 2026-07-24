import java.util.Arrays;

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    
    int closestSum = nums[0] + nums[1] + nums[2]; // Initialize with first triplet
    
    for (int i = 0; i < nums.length - 2; i++) {
        int j = i + 1;
        int k = nums.length - 1;
        
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            
            // Found exact match — can't get closer than this
            if (sum == target) {
                return sum;
            }
            
            // Update closest if this sum is nearer to target
            if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                closestSum = sum;
            }
            
            // Move pointers based on comparison with target
            if (sum < target) {
                j++;  // Need larger sum
            } else {
                k--;  // Need smaller sum
            }
        }
    }
    
    return closestSum;
}
public static void main(String[] args) {
    System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
}
}


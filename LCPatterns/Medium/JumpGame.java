public class JumpGame{
    public static boolean canJump(int[] nums) {

        if(nums.length == 1 && nums[0]>0)
            return true;

        int goal = nums.length - 1;

        for(int i = nums.length - 2; i>=0 ; i--){
            if(i + nums[i] >= goal)
                goal = i;
        }
        return goal == 0;

    }
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }
}
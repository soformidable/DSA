public class DuplicateElement {
    public static int duplicate(int nums[]){
        if(nums.length == 0 || nums == null)
            return 0;
        
        boolean[] arr = new boolean[nums.length];
        for(int i = 0 ; i < nums.length ; i ++){
            if(arr[nums[i]])
                return nums[i];
            else
                arr[nums[i]] = true;
    }
    return 0;
    }
    public static void main(String args[]){
        System.out.println(duplicate(new int[]{1,3,4,2,2}));
    }
}

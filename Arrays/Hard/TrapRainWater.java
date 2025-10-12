public class TrapRainWater {
    static int trapN2(int[] arr) {
        int n = arr.length;
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int leftMax = 0, rightMax = 0;
            while (j >= 0) {
                leftMax = Math.max(leftMax, arr[j]);
                j--;
            }
            j = i;
            while (j < n) {
                rightMax = Math.max(rightMax, arr[j]);
                j++;
            }
            waterTrapped += Math.min(leftMax, rightMax) - arr[i];
        }
        return waterTrapped;
    }

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        int storage = 0;

        while(left <= right){
            if(height[left] <= height[right]){

                if(height[left] >= maxLeft)
                    maxLeft = height[left];
                else
                    storage += maxLeft - height[left];
                left++;
            }
            else{

                if(height[right] >= maxRight)
                    maxRight = height[right];
                else
                    storage += maxRight - height[right];
                right--;
            }
        }
        return storage;
    }
    public static void main(String args[]){
        int height[] = new int[]{1,0,0,4};
        System.out.println(trap(height));
    }
}

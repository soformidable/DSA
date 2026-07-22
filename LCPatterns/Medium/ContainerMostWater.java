public class ContainerMostWater{
    public static int maxArea(int[] height) {
        
        int leftBound = 0;
        int rightBound = height.length - 1;
        int maxVolume = 0;

        while(leftBound < rightBound){
            int volume = (rightBound - leftBound) * Math.min(height[leftBound],height[rightBound]);
            maxVolume = Math.max(volume,maxVolume);

            // MOVE THE SHORTER BOUND INWARDS left--> / <--right
            if(height[leftBound] < height[rightBound])
                leftBound++;
            else
                rightBound--;

        }

        return maxVolume;
    }
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,1}));
    }
}
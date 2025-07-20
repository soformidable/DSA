public class StockBuyAndSell {
    public static int maxProfit(int[] nums){
        int max_profit = 0, min_value = Integer.MAX_VALUE;

        for(int i = 0 ; i < nums.length; i++){
            min_value = Math.min(min_value,nums[i]);
            max_profit = Math.max(max_profit , Math.abs(min_value - nums[i]));
        }

        return max_profit;
    }

    public static void main(String args[]){
        int arr[] = new int[] {7,6,4,3,1};
        System.out.println(maxProfit(arr));
    }
}

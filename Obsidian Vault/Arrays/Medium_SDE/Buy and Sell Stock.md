
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/


Given array of prices, we have to maximize the profit by buying on i and selling on i + k such that (i + k) < nums.length


Steps:

1. Keep a single loop
2. Keep a track of the min element encountered (initiliaze as min_value = Integer.MaxValue)
3. Keep a track opf the maxProfit by using Math.max(max, Math.abs(min_value , nums\[i]))
4. Return max_profit



```
        int max_profit = 0, min_value = Integer.MAX_VALUE;

  

        for(int i = 0 ; i < prices.length; i++){

            min_value = Math.min(min_value,prices[i]);

            max_profit = Math.max(max_profit , Math.abs(min_value - prices[i]));

        }
  
        return max_profit;
```
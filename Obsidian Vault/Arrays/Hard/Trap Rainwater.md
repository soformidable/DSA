
problem: https://leetcode.com/problems/trapping-rain-water/description/
code: https://github.com/soformidable/DSA/blob/main/Arrays/Hard/TrapRainWater.java

Approach 1 O(n^2):

1. run a loop from 0 to n-1
2. At every step calculate the min and max by moving 0 <-- j  and j -->n-1
3. At every step calculate storage += Math.min(maxLeft,maxRight) - height\[i]
	1. Minimum gives the level bound |___|
4. return storage

Code:
```
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
```


Approach 2 O(N) no extra space:

1. start left = 0 and right = 0
2. maxLeft = 0 and maxLeft = 0
3. run while(left<=right)
4. check if height of left is less than or equals right
	1. check if maxLeft is still 0
	2. else calculate storage = maxLeft - height\[left]
	3. left++;
5. similar steps for right in else
	1. move right to the left (right--)
6. return storage

Code:
```

class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0; int right = height.length - 1; // Pointers to both ends of the array.
        int maxLeft = 0; int maxRight = 0;
        
        int totalWater = 0;
        while (left < right) {
            // Water could, potentially, fill everything from left to right, if there is nothing in between.
            if (height[left] < height[right]) {
                // If the current elevation is greater than the previous maximum, water cannot occupy that point at all.
                // However, we do know that everything from maxLeft to the current index, has been optimally filled, as we've
                // been adding water to the brim of the last maxLeft.
                if (height[left] >= maxLeft) { 
                    // So, we say we've found a new maximum, and look to see how much water we can fill from this point on.
                    maxLeft = height[left]; 
                // If we've yet to find a maximum, we know that we can fill the current point with water up to the previous
                // maximum, as any more will overflow it. We also subtract the current height, as that is the elevation the
                // ground will be at.
                } else { 
                    totalWater += maxLeft - height[left]; 
                }
                // Increment left, we'll now look at the next point.
                left++;
            // If the height at the left is NOT greater than height at the right, we cannot fill from left to right without over-
            // flowing; however, we do know that we could potentially fill from right to left, if there is nothing in between.
            } else {
                // Similarly to above, we see that we've found a height greater than the max, and cannot fill it whatsoever, but
                // everything before is optimally filled
                if (height[right] >= maxRight) { 
                    // We can say we've found a new maximum and move on.  
                    maxRight = height[right]; 
                // If we haven't found a greater elevation, we can fill the current elevation with maxRight - height[right]
                // water.
                } else {
                    totalWater += maxRight - height[right]; 
                }
                // Decrement left, we'll look at the next point.
                right--;
            }
        }
        // Return the sum we've been adding to.
        return totalWater;
    }
}

```
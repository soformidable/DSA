# Container With Most Water

**Problem Statement:**  
Given `n` non-negative integers `a1, a2, ..., an` where each represents a point at coordinate `(i, ai)`. `n` vertical lines are drawn such that the two endpoints of the line `i` is at `(i, ai)` and `(i, 0)`. Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

## Problem
https://leetcode.com/problems/container-with-most-water/description/


**Key Insight:**  
The area of water that can be held is limited by the shorter of the two lines (due to gravity), and the width between them.

---

## Solution: Two Pointer Approach

**Algorithm:**
1. Initialize two pointers at the beginning (`left`) and end (`right`) of the array.
2. Calculate the area between the two lines.
3. Move the pointer pointing to the shorter line inward.
4. Repeat until the pointers meet.

**Why move the shorter pointer?**
- Moving the longer line inward can only decrease the area (since width decreases and height cannot increase beyond the shorter line).
- Moving the shorter line inward might find a taller line, potentially increasing the area.

---

## Code Implementation

```java
public static int maxArea(int[] height) {
    int leftBound = 0;
    int rightBound = height.length - 1;
    int maxVolume = 0;

    while (leftBound < rightBound) {
        int volume = (rightBound - leftBound) * Math.min(height[leftBound], height[rightBound]);
        maxVolume = Math.max(volume, maxVolume);

        // Move the shorter bound inward
        if (height[leftBound] < height[rightBound])
            leftBound++;
        else
            rightBound--;
    }

    return maxVolume;
}
```

---

## Example Walkthrough

For array `[1, 8, 6, 2, 5, 4, 8, 3, 7]`:

1. **Initial:** left=0 (height 1), right=8 (height 7)
   - Area = min(1, 7) × (8-0) = 1 × 8 = 8
   - Move left (shorter line) → left=1

2. **Next:** left=1 (height 8), right=8 (height 7)
   - Area = min(8, 7) × (8-1) = 7 × 7 = 49
   - Move right (shorter line) → right=7

3. **Continue** until pointers meet...

**Maximum area found:** 49

---

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(1) - Only using a few variables

---

## Related Problems

- **Trapping Rain Water** (Hard) - Similar concept but different approach
- **Largest Rectangle in Histogram** (Hard) - Stack-based approach

---

## Tags

#leetcode #arrays #two-pointers #greedy #medium

---
*Source: LCPatterns/Medium/ContainerMostWater.java*
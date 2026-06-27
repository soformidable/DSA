# Symmetrical Subset of Arrays with powers

## Problem
https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/description/?envType=daily-question&envId=2026-06-27

Code link:

Given an array of integers, find the **maximum size** of a subset that is **power-symmetric**:
- The subset can be organized into chains where each element is the **square** of the previous one: `a → a² → a⁴ → a⁸ → ...`
- **Symmetry rule:** for each number, it must come in **pairs** (two copies) unless it is the **peak** (the last element of its chain), which can appear just once.
- **1** is special because `1² = 1`, creating an infinite self-loop. The count of 1s must be the **largest odd number ≤ total count of 1s**.

## Intuition

Think of building chains of squaring pairs:

```
[2, 2] → [4]        (2 appears twice, 4 is the single peak)    → length 3
[3, 3] → [9]        (3 appears twice, 9 is the peak)          → length 3
```

- Every level **except the peak** needs **two copies** of a number to be valid (a "pair").
- If the chain breaks because the next power does **not exist**, that last pair is incomplete — we discard one of them.
- If the chain ends at a number that **does exist** (even with count 1), it becomes the valid peak.
- **1s** form their own symmetric subset: you can have at most an odd number of 1s (so they can have a central element).

## Algorithm

1. **Count frequencies** of all numbers in a `HashMap<Long, Integer>`.
2. **Handle 1s:** get the count of 1s. If odd → keep all; if even → keep all except one (make it odd). Remove 1 from map.
3. **Explore each remaining number** as the start of a power chain:
   - `res = 0`, `x = i`
   - While `x` exists **and** its frequency > 1:
     - Add 2 to `res` (take a pair)
     - `x = x * x` (move to next power)
   - After loop: if `x` still exists → `ans = max(ans, res + 1)` (peak included)
   - If `x` does **not** exist → `ans = max(ans, res - 1)` (last pair was invalid, discard one)
4. Return the maximum `ans` among all chains and the 1s count.

## Java Implementation (`PowerSymmetrySubset.java`)

```java
import java.util.*;

public class PowerSymmetrySubset {
    public static int maximumLength(int len[]) {
        if (len == null || len.length < 0)
            return 0;
        Map<Long, Integer> map = new HashMap<>();

        for (int x : len) {
            map.merge((long) x, 1, Integer::sum);
        }
        int Onecnt = map.getOrDefault(1L, 0);

        // Largest odd number of 1s
        int ans = (Onecnt & 1) == 1 ? Onecnt : Onecnt - 1;
        map.remove(1L);

        for (long i : map.keySet()) {
            int res = 0;
            long x = i;

            while (map.containsKey(x) && map.get(x) > 1) {
                res += 2;
                x *= x;
            }
            // Chain ends: peak exists (+1) or last pair broken (-1)
            ans = Math.max(ans, res + (map.containsKey(x) ? 1 : -1));
        }

        return ans;
    }

    public static void main(String args[]) {
        int arr[] = new int[]{1, 2, 3, 4, 2, 5, 6};
        System.out.print("Length of the subset: " + maximumLength(arr));
        // Output: 3  (subset could be {2,2,4})
    }
}
```

## Example Walkthrough

Input: `[1, 2, 3, 4, 2, 5, 6]`  
Frequencies: `{1→1, 2→2, 3→1, 4→1, 5→1, 6→1}`

| Step | Chain         | res | x   | Condition                    | ans |
|------|---------------|-----|-----|------------------------------|-----|
| 1    | Handle 1s     | –   | –   | count=1 (odd) → keep all     | 1   |
| 2    | Start at 2    | 0   | 2   |                              | 1   |
| 3    |               | 2   | 4   | 2 exists with count 2 → add pair | 1 |
| 4    |               | 2   | 4   | 4 exists but count=1 → loop ends | 1 |
| 5    |               | –   | –   | map has 4 → peak! res + 1 = 3 | 3   |
| 6    | Others (3,5,6)| 0   | –   | count=1 only → no pair possible | 3   |

**Result:** 3 → subset `{2, 2, 4}`.

## Key Points for Revisit

- **Pairs everywhere, peak only at the end.**
- **1s** are handled separately — must be odd count.
- Chain stops when a number's frequency is **exactly 1** (potential peak) or **missing** (chain broken).
- After the while loop: `map.containsKey(x)` tells you if the chain ended cleanly (peak) or broke (invalid last pair).
- Complexity: `O(N + U * log(max))` where `U` = number of unique keys, because squaring grows very fast.
"# Rank Transform of an Array

## Problem Statement
Given an array of integers `arr`, replace each element with its **rank**. The rank is an integer starting from `1`. The larger the value, the larger the rank. Two equal elements share the same rank, and the ranks should be as small as possible (i.e., no gaps in ranking).

Problem:
https://leetcode.com/problems/rank-transform-of-an-array/description/?envType=daily-question&envId=2026-07-12

Code:
https://github.com/soformidable/DSA/blob/main/Daily/RankTransformArray.java

### Examples
```
Input:  [37, 12, 28, 9, 100, 56, 80, 5, 12]
Output: [5,  3,  4, 2, 9,   7,  8,  1, 3 ]

Sorted: [5, 9, 12, 12, 28, 37, 56, 80, 100]
Rank:   [1, 2,  3,  3,  4,  5,  6,  7,   8]
                  ↑ same value → same rank
```

## Approach

1. **Create a copy** of the original array to preserve the original order.
2. **Sort** the copy.
3. **Assign ranks** to each unique value using a HashMap:
   - Start with `rank = 1` for the smallest element.
   - If the current element equals the previous element → same rank.
   - If it's different → increment rank.
4. **Map back** to the original array: look up each original element's rank in the map and store the result.

## Current Implementation

```java
import java.util.*;

public class RankTransformArray {
    public static int[] arrayRankTransform(int[] arr) {
        int arr_cp[] = Arrays.copyOf(arr, arr.length);
        HashMap<Integer,Integer> map = new HashMap<>();

        Arrays.sort(arr);
        int rank = 1;
        int prev = arr[0];
        map.put(prev, rank);

        for(int i = 1 ; i < arr.length ; i ++) {
            if(prev == arr[i])
                map.put(arr[i], rank);
            else {
                rank++;
                map.put(arr[i], rank);
            }
            prev = arr[i];
        }

        for(int i = 0 ; i < arr_cp.length ; i ++) {
            arr_cp[i] = map.get(arr_cp[i]);
        }

        return arr_cp;
    }
}
```

### Complexity
- **Time:** `O(n log n)` — dominated by `Arrays.sort()`
- **Space:** `O(n)` — for the clone array and the HashMap

## Optimal Version

The current implementation is already optimal in terms of time complexity (`O(n log n)`) since sorting is required to determine relative ordering. However, the code can be made cleaner and more idiomatic:

```java
import java.util.*;

public class RankTransformArray {

    public static int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) return arr;

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int num : sorted) {
            // putIfAbsent ensures first occurrence gets the rank,
            // duplicates are ignored and don't create a gap
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
            arrayRankTransform(new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12})));
    }
}
```

### Improvements in the Optimal Version

| Aspect | Original | Optimal |
|--------|----------|---------|
| **Clone** | `Arrays.copyOf(arr, arr.length)` | `arr.clone()` (shorter and clearer) |
| **Rank assignment** | Manual loop tracking `prev` | Iterate sorted array; increment rank only on first occurrence of each value |
| **Duplicate handling** | `if(prev == arr[i])` check | `map.containsKey(num)` — more explicit |
| **Result array** | Mutates the clone | Creates a fresh `result` array |
| **Edge cases** | Not handled | Handles `null` and empty array |
| **Clarity** | Confusing variable names (`arr_cp` reused) | Clear separation of concerns |

### Why this is still optimal

No algorithm can solve this in better than `O(n log n)` because:
- Ranking requires knowing the **relative ordering** of all elements.
- Sorting is the standard way to establish relative ordering.
- A hash-based approach for ranking requires at least one pass through the sorted array (`O(n)`), which is dominated by the sort.

### Alternative Approach (TreeMap)
You could also use a `TreeMap`, but this adds `O(n log n)` insertion cost on top of the sort, making it slower:

```java
// Not recommended — less efficient
TreeMap<Integer, Integer> map = new TreeMap<>();
for (int num : arr) map.put(num, 0);
int rank = 1;
for (int key : map.keySet()) map.put(key, rank++);
```

## Related Problems
- LeetCode 1331 — Rank Transform of an Array (exact same problem)
- LeetCode 506 — Relative Ranks (top 3 get medal strings instead of numbers)
- LeetCode 1632 — Rank Transform of a Matrix (2D version with dependencies)"
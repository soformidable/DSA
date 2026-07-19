# Find All Duplicates in an Array

## Problem Statement
Given an integer array `nums` where each element appears once or twice, return an array of all the elements that appear twice. 

## Problem:
https://leetcode.com/problems/find-all-duplicates-in-an-array/

### Constraints
- `1 <= nums.length <= 10^5`
- `nums[i]` is in the range `[1, n]` where `n` is the length of `nums`
- Each element appears once or twice

### Examples
```
Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]

Input: nums = [1,1,2]
Output: [1]

Input: nums = [1]
Output: []
```

## Approach 1: HashMap Counting

### Intuition
Use a HashMap to count occurrences of each number. If a number's count reaches 2, it's a duplicate.

### Implementation
```java
import java.util.*;

public class FindDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : nums) {
            int freq = count.getOrDefault(num, 0) + 1;
            count.put(num, freq);
            if (freq == 2) {
                duplicates.add(num);
            }
        }

        return duplicates;
    }
}
```

### Complexity
- **Time:** O(n) - Single pass through the array
- **Space:** O(n) - HashMap storage

## Approach 2: In-Place Marking

### Intuition
Use the array itself as a marker. Since all values are in range `[1, n]`, we can use indices to mark visited elements:
- When visiting a number `num`, mark the element at index `Math.abs(num) - 1` as negative.
- If we encounter a negative value at that index, the number is a duplicate.

### Implementation
```java
import java.util.*;

public class FindDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                duplicates.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }

        return duplicates;
    }
}
```

### Complexity
- **Time:** O(n) - Single pass
- **Space:** O(1) - Only the output list is extra space (not counted in complexity analysis)

## Comparison

| Aspect | HashMap | In-Place Marking |
|--------|---------|------------------|
| **Time** | O(n) | O(n) |
| **Space** | O(n) | O(1) |
| **Readability** | More intuitive | Less obvious |
| **Safety** | Non-destructive | Modifies input array |

## Key Insights

1. **In-place marking works** because array values are guaranteed to be in `[1, n]`.
2. **Index mapping:** value `v` → index `v - 1`.
3. **Negative marking:** once an index is visited, negate the value at that index.
4. **Duplicate detection:** encountering a negative value means the number was already seen.

## Edge Cases

- **No duplicates:** Returns empty list.
- **All duplicates:** Every number appears twice.
- **Single element:** Returns empty list (no duplicates possible).

## Related Problems
- LeetCode 442 - Find All Duplicates in an Array (original problem)
- LeetCode 41 - First Missing Positive
- LeetCode 287 - Find the Duplicate Number (find only one duplicate)
# Checking for Duplicates in an Array

## Problem Statement
Given an array of integers, determine if the array contains any duplicate elements.

## Solutions

### 1. Using a HashMap (Original Solution)

```java
import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.getOrDefault(nums[i], 0) == 0) {
                map.put(nums[i], 1);
            } else {
                return true;
            }
        }
        return false;
    }
}
```

#### Explanation
- **Time Complexity:** O(n) - We traverse the array once.
- **Space Complexity:** O(n) - In the worst case, we store all elements in the HashMap.

### 2. Using a HashSet (Optimal for General Cases)

```java
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
```

#### Explanation
- **Time Complexity:** O(n) - We traverse the array once.
- **Space Complexity:** O(n) - In the worst case, we store all elements in the HashSet.
- **Advantages:**
  - More concise and easier to read.
  - Uses the `add` method of `HashSet` which returns `false` if the element is already present.

### 3. Using a Boolean Array (Optimal for Small Ranges)

```java
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        boolean[] seen = new boolean[max - min + 1];
        for (int num : nums) {
            int index = num - min;
            if (seen[index]) {
                return true;
            }
            seen[index] = true;
        }
        return false;
    }
}
```

#### Explanation
- **Time Complexity:** O(n) - We traverse the array twice (once to find min and max, and once to check for duplicates).
- **Space Complexity:** O(n) - We use a boolean array of size `max - min + 1`.
- **Advantages:**
  - More efficient for small ranges of numbers.
  - Avoids the overhead of hash functions.

## Choosing the Right Solution
- **Use HashSet** for general cases where the range of numbers is unknown or large.
- **Use Boolean Array** for cases where the range of numbers is small and known.
- **Use HashMap** if you need to perform additional operations with the elements, such as counting occurrences.

## Related Problems
- LeetCode 217 - Contains Duplicate
- LeetCode 219 - Contains Duplicate II (with index difference constraint)
- LeetCode 220 - Contains Duplicate III (with value difference constraint)
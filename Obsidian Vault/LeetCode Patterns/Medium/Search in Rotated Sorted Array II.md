# Search in Rotated Sorted Array II

## Problem Statement
There is an integer array `nums` sorted in non-decreasing order (not necessarily with distinct values). Before being passed to your function, `nums` is rotated at an unknown pivot index `k` (0 <= k < nums.length) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (0-indexed).

## Problem:
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/

Given the array `nums` after the rotation and an integer `target`, return `true` if `target` is in `nums`, or `false` if it is not in `nums`.

You must decrease the overall operation steps as much as possible.

## Key Challenges
1. **Rotated Array**: The array is not fully sorted, but partially sorted in two halves
2. **Duplicate Elements**: Unlike Search in Rotated Sorted Array I, this array may contain duplicates
3. **Ambiguity**: When `nums[start] == nums[middle] == nums[end]`, we cannot determine which half is sorted

## Approaches

### Approach 1: HashSet + Binary Search (Simple)
**Idea**: Remove duplicates first using HashSet, then apply standard rotated array binary search.

**Pros**: Simple to implement
**Cons**: O(n) extra space, O(n) time for HashSet operations

### Approach 2: Modified Binary Search (Optimal)
**Idea**: Handle duplicates during binary search by incrementing both pointers when ambiguity occurs.

**Pros**: O(1) space, maintains O(log n) average time
**Cons**: Worst case O(n) when many duplicates exist

## Algorithm

### Standard Rotated Binary Search (without duplicates):
```
1. Find the middle element
2. Determine which half is sorted:
   - If nums[start] <= nums[middle] → left half is sorted
   - Else → right half is sorted
3. Check if target lies in the sorted half:
   - If yes, search that half
   - If no, search the other half
```

### Handling Duplicates:
```
1. If nums[start] == nums[middle] == nums[end]:
   - Cannot determine which half is sorted
   - Increment start and decrement end
   - Continue with next iteration
2. Else: proceed with standard logic
```

## Code Implementation

### Approach 1: Using HashSet
```java
class Solution {
    public boolean search(int[] nums, int target) {
        // Remove duplicates
        Set<Integer> set = new HashSet<>();
        List<Integer> lst = new ArrayList<>();
        
        for(int x : nums) {
            if(set.add(x))
                lst.add(x);
        }
        
        int start = 0;
        int end = lst.size() - 1;
        
        while(start <= end) {
            int middle = start + (end - start) / 2;
            
            if(target == lst.get(middle))
                return true;
            
            // Left half is sorted
            if(lst.get(start) <= lst.get(middle)) {
                if(target >= lst.get(start) && target < lst.get(middle))
                    end = middle - 1;
                else
                    start = middle + 1;
            }
            // Right half is sorted
            else {
                if(target <= lst.get(end) && target > lst.get(middle))
                    start = middle + 1;
                else
                    end = middle - 1;
            }
        }
        return false;
    }
}
```

### Approach 2: Without Extra Space (Optimal)
```java
class Solution {
    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end) {
            int middle = start + (end - start) / 2;
            
            if(nums[middle] == target)
                return true;
            
            // Handle duplicates - cannot determine sorted half
            if(nums[start] == nums[middle] && nums[middle] == nums[end]) {
                start++;
                end--;
            }
            // Left half is sorted
            else if(nums[start] <= nums[middle]) {
                if(target >= nums[start] && target < nums[middle])
                    end = middle - 1;
                else
                    start = middle + 1;
            }
            // Right half is sorted
            else {
                if(target <= nums[end] && target > nums[middle])
                    start = middle + 1;
                else
                    end = middle - 1;
            }
        }
        return false;
    }
}
```

## Complexity Analysis

### Approach 1: HashSet + Binary Search
- **Time Complexity**: O(n) for HashSet + O(log n) for search = **O(n)**
- **Space Complexity**: O(n) for storing unique elements

### Approach 2: Modified Binary Search
- **Time Complexity**: 
  - Average: O(log n)
  - Worst case: O(n) when many duplicates (e.g., `[1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1]`)
- **Space Complexity**: O(1)

## Visual Examples

### Example 1: No Duplicates
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: true

Sorted version: [0,1,2,4,5,6,7]
Rotated at index 4
```

### Example 2: With Duplicates
```
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Duplicate elements: 0, 2
```

### Example 3: Ambiguous Case
```
Input: nums = [1,0,1,1,1], target = 0
Output: true

When start=0, middle=2, end=4:
- nums[start] = 1, nums[middle] = 1, nums[end] = 1
- All equal → cannot determine sorted half
- Increment start, decrement end
```

## Edge Cases to Consider
1. **All elements are the same**: `[1,1,1,1,1]`
2. **Single element**: `[1]` or `[1,1]`
3. **Target not found**: `target = 3` in `[2,2,2,0,1]`
4. **Target at rotation point**: `target = 0` in `[4,5,6,0,1,2]`

## Comparison: Search I vs Search II

| Aspect | Search I | Search II |
|--------|----------|----------|
| Duplicates | No | Yes |
| Best Time | O(log n) | O(log n) avg |
| Worst Time | O(log n) | O(n) |
| Space | O(1) | O(1) |
| Key Insight | Find pivot or direct search | Handle ambiguity with duplicates |

## Related Problems
- [[Search in Rotated Sorted Array]] (33) - No duplicates version
- [[Find Minimum in Rotated Sorted Array]] (153)
- [[Find Minimum in Rotated Sorted Array II]] (154) - With duplicates

## Tips & Tricks
1. **Always check for duplicates first** before determining sorted half
2. **When ambiguous**, shrinking both pointers safely eliminates duplicates
3. **Standard binary search template** still applies, just with extra checks
4. **Consider edge cases** with many duplicates early in problem-solving

#LeetCode #Medium #Array #BinarySearch #RotatedArray
</contents>
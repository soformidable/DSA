# Palindrome Partitioning

## Problem Statement
Given a string `s`, partition `s` such that every substring of the partition is a **palindrome**. Return all possible palindrome partitioning of `s`.

https://leetcode.com/problems/palindrome-partitioning/description/

## Example
```
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Explanation:
- "a" | "a" | "b"  → all three are palindromes
- "aa" | "b"        → "aa" is palindrome, "b" is palindrome
- "a" | "ab"        → "ab" is NOT a palindrome ✗
```

## Key Insight
This is a **backtracking** problem where we:
1. Try every possible prefix of the remaining string
2. If the prefix is a palindrome, include it in the current partition
3. Recurse on the remaining suffix
4. Backtrack (remove the prefix) and try the next longer prefix

## Algorithm: Backtracking with Palindrome Check

### Steps:
1. At each recursive call, we work with the `remaining` string
2. Try all prefixes: `s.substring(0, 1)`, `s.substring(0, 2)`, ..., `s.substring(0, n)`
3. If prefix is **not** a palindrome → skip it
4. If prefix **is** a palindrome:
   - Add it to the current partition path
   - Recurse on the suffix: `s.substring(i)`
   - Backtrack by removing the prefix from the path
5. Base Case: When the string is empty → we've successfully partitioned it → add copy of path to result

## Code Implementation (with Comments)

```java
class PalindromePartition {

    // Main entry point: returns all palindrome partitions of string s
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        // Start the backtracking process with an empty current partition
        partitionHelper(s, result, new ArrayList<String>());

        return result;
    }

    /**
     * Backtracking recursive helper.
     *
     * @param s       the remaining suffix of the original string to partition
     * @param result  the accumulated list of valid partitions (final output)
     * @param current the partition path built so far (list of palindrome substrings)
     */
    private static void partitionHelper(String s, List<List<String>> result, List<String> current) {

        // Base Case: if the remaining string is empty, we've processed the whole
        // input without any invalid partitions. Record a DEEP COPY of current path.
        if(s == null || s.length() == 0){
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible prefix length: 1, 2, ..., s.length()
        for(int i = 1; i <= s.length(); i++){

            // Extract the candidate prefix: first i characters
            String prefix = s.substring(0, i);

            // If this prefix is not a palindrome, it cannot belong to a valid
            // partition, so skip it entirely (pruning)
            if(!isPalindrome(prefix))
                continue;

            // Choose: include the palindrome prefix in the current path
            current.add(prefix);

            // Explore: recursively partition the remaining suffix String(i)
            partitionHelper(s.substring(i), result, current);

            // Backtrack: remove the prefix we just added so that the next
            // iteration can try a different prefix without leftover state
            current.remove(current.size() - 1);
        }
    }

    /**
     * Checks whether a string is a palindrome using two pointers.
     * Compares characters from both ends moving toward the middle.
     */
    private static boolean isPalindrome(String str){
        int left = 0, right = str.length() - 1;

        while(left < right){
            // If characters at both ends don't match, it's not a palindrome
            if(str.charAt(left) != str.charAt(right))
                return false;

            // Move pointers closer to the center
            left++;
            right--;
        }

        // All mirrored characters matched → palindrome
        return true;
    }

    public static void main(String[] args) {
        // Expected output: [["a","a","b"],["aa","b"]]
        System.out.println(partition("aab"));
    }
}
```

## Visual Walkthrough

### Example: s = "aab"

```
Initial: s="aab", current=[]
├── Try prefix "a" (palindrome ✓)
│   ├── current=["a"]
│   └── Recurse on s="ab"
│       ├── Try prefix "a" (palindrome ✓)
│       │   ├── current=["a","a"]
│       │   └── Recurse on s="b"
│       │       ├── Try prefix "b" (palindrome ✓)
│       │       │   ├── current=["a","a","b"]
│       │       │   └── Recurse on s=""
│       │       │       └── Base case: add copy → result=[["a","a","b"]] ✓
│       │       └── Backtrack → current=["a","a"]
│       └── Backtrack → current=["a"]
│
│       Try prefix "ab" (NOT palindrome ✗) → skip
│
├── Backtrack → current=[]
│
├── Try prefix "aa" (palindrome ✓)
│   ├── current=["aa"]
│   └── Recurse on s="b"
│       ├── Try prefix "b" (palindrome ✓)
│       │   ├── current=["aa","b"]
│       │   └── Recurse on s=""
│       │       └── Base case: add copy → result=[["a","a","b"],["aa","b"]] ✓
│       └── Backtrack → current=["aa"]
│
└── Backtrack → current=[]

Final result: [["a","a","b"],["aa","b"]]
```

## Complexity Analysis

### Time Complexity: O(N × 2^N)
- **Generating partitions**: In the worst case, `s` consists of all identical characters
  (e.g., "aaaa"), every substring is a palindrome, and the number of partitions equals
  2^(N-1) → exponential
- **Palindrome check**: Each check is O(N)
- **Total**: O(N × 2^N) worst-case

### Space Complexity: O(N)
- **Recursion stack**: O(N) depth (each recursion reduces string length)
- **Current partition list**: O(N)
- **Result storage**: O(N × 2^N) in worst case (counted separately as output space)

## Edge Cases

| Input | Output | Reasoning |
|-------|--------|-----------|
| `""` | `[[]]` | Empty string → one empty partition |
| `"a"` | `[["a"]]` | Single character is always a palindrome |
| `"ab"` | `[["a","b"]]` | "ab" isn't a palindrome; only "a"|"b" works |
| `"aaa"` | `[["a","a","a"],["a","aa"],["aa","a"],["aaa"]]` | All partitions are palindromes |

## Pattern Recognition

This problem is a classic **Backtracking** on strings:
1. **Choose**: A substring that is a palindrome
2. **Explore**: Recursively partition the remaining suffix
3. **Un-choose**: Remove the chosen substring (backtrack)

It uses the same pattern as:
- [[Subsets]] - choose/explore/un-choose elements
- [[Permutations]] - arrange elements
- [[Combination Sum]] - choose numbers that sum to target

## Related Problems
- [[Palindrome Partitioning II]] (132) - Minimum cuts for palindrome partitioning
- [[Palindrome Linked List]] (234) - Check if a linked list is a palindrome
- [[Distinct Subsequences]] (115) - String partitioning variants

## Common Mistakes to Avoid

### ❌ Mistake 1: Forgetting to Make a Deep Copy at Base Case
```java
// WRONG: Adds the mutable reference to result
result.add(current);  // Later backtracking removes items from current!

// CORRECT: Add a copy
result.add(new ArrayList<>(current));
```

### ❌ Mistake 2: Off-By-One in Substring Extraction
```java
// WRONG: Skips the last character / includes too much
String prefix = s.substring(0, i - 1);  // Current iteration won't try full string

// CORRECT: Try lengths 1..s.length()
String prefix = s.substring(0, i);
```

### ❌ Mistake 3: Not Skipping Non-Palindromes
```java
// WRONG: Never checks palindrome before recursing
current.add(s.substring(0, i));
partitionHelper(s.substring(i), result, current);  // Adds invalid partitions!

// CORRECT: Check first
if(!isPalindrome(s.substring(0, i))) continue;
```

### ❌ Mistake 4: Modifying Current Without Backtracking
```java
// WRONG: Missing the remove call at the end
current.add(prefix);
partitionHelper(...);
// Backtracking line is missing → path keeps growing!

// CORRECT:
current.add(prefix);
partitionHelper(...);
current.remove(current.size() - 1);
```

## Practice Tips

1. **Master the backtracking template**: `choose → explore → un-choose`
2. **Always create new copies** of mutable lists when adding to result
3. **Test with all-same-character strings** (e.g., "aaa", "aaaa") to understand exponential behavior
4. **Dry-run with "aab"** on paper to see how pruning skips invalid prefixes
5. **Understand the recursion depth** equals the number of partitions in the chosen path

#LeetCode #Medium #String #Backtracking #Palindrome #DFS
# Smallest Subsequence of Distinct Characters (Lexicographical Order)

## Problem Statement
Given a string `s`, return the **lexicographically smallest subsequence** of `s` containing **all distinct characters** of `s`.

## Problem 
https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/description/?envType=daily-question&envId=2026-07-19

### Examples
```
Input: "cbacdcbc"
Output: "acdb"

Explanation:
- Sorted unique chars: 'a', 'b', 'c', 'd'
- Lex order: 'a' < 'b' < 'c' < 'd'
- Smallest subsequence: 'a' → 'c' → 'd' → 'b' (last occurrence of 'b')
```

## Approach

1. **Track last occurrences** of each character using an array.
2. **Iterate through the string** while maintaining a stack-like structure (using `StringBuilder` for efficiency):
   - If the current character is already in the result → skip.
   - Otherwise, while the result is not empty and:
     - The current character is smaller than the last character in the result
     - The last character appears again later in the string
     → Remove the last character from the result.
   - Add the current character to the result.

## Current Implementation

```java
import java.util.*;

public class SmallestSubseqCharLex {
    public static String smallestSubsequence(String s) {
        int[] lastseen = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastseen[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen[c - 'a']) {
                continue;
            }

            while (result.length() > 0 &&
                   c < result.charAt(result.length() - 1) &&
                   lastseen[result.charAt(result.length() - 1) - 'a'] > i) {
                seen[result.charAt(result.length() - 1) - 'a'] = false;
                result.deleteCharAt(result.length() - 1);
            }

            result.append(c);
            seen[c - 'a'] = true;
        }

        return result.toString();
    }
}
```

### Complexity
- **Time:** `O(n)` — Each character is processed at most twice (once when added, once when removed).
- **Space:** `O(1)` — Fixed-size arrays (`lastseen` and `seen`) and `StringBuilder`.

## Optimal Version

The current implementation is already optimal. However, we can make it slightly more readable:

```java
import java.util.*;

public class SmallestSubseqCharLex {
    public static String smallestSubsequence(String s) {
        int[] lastseen = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastseen[s.charAt(i) - 'a'] = i;
        }

        boolean[] inResult = new boolean[26];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (inResult[current - 'a']) {
                continue;
            }

            while (result.length() > 0 &&
                   current < result.charAt(result.length() - 1) &&
                   lastseen[result.charAt(result.length() - 1) - 'a'] > i) {
                inResult[result.charAt(result.length() - 1) - 'a'] = false;
                result.deleteCharAt(result.length() - 1);
            }

            result.append(current);
            inResult[current - 'a'] = true;
        }

        return result.toString();
    }
}
```

### Improvements in the Optimal Version

| Aspect | Original | Optimal |
|--------|----------|---------|
| **Variable names** | `seen` | `inResult` (more descriptive) |
| **Character variable** | `c` | `current` (clearer purpose) |
| **Consistency** | Mixed `charAt` and array access | Consistent style |

### Why this is still optimal

The algorithm is optimal because:
- It processes each character exactly once.
- It uses constant space for tracking.
- It ensures the lexicographically smallest sequence by always removing larger characters when possible.

## Related Problems
- LeetCode 316 — Remove Duplicate Letters (same problem)
- LeetCode 1081 — Smallest Subsequence of Distinct Characters
- LeetCode 402 — Remove K Digits (similar greedy removal pattern)

## Key Insights

1. **Last occurrence tracking** is crucial for knowing whether a character can be removed safely.
2. **Greedy removal** ensures the lexicographically smallest sequence by always preferring smaller characters.
3. **Stack-like structure** (using `StringBuilder`) efficiently handles the removal and addition of characters.

## Edge Cases

- **All unique characters:** Returns the sorted string.
- **All same characters:** Returns a single character.
- **Empty string:** Returns empty string.
- **String with spaces:** Treated as any other character (unless specified otherwise).

## Alternative Approach (Using Stack)

```java
public static String smallestSubsequence(String s) {
    int[] lastseen = new int[26];
    for (int i = 0; i < s.length(); i++) {
        lastseen[s.charAt(i) - 'a'] = i;
    }

    Stack<Character> stack = new Stack<>();
    boolean[] inStack = new boolean[26];

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (inStack[c - 'a']) {
            continue;
        }

        while (!stack.isEmpty() &&
               c < stack.peek() &&
               lastseen[stack.peek() - 'a'] > i) {
            inStack[stack.pop() - 'a'] = false;
        }

        stack.push(c);
        inStack[c - 'a'] = true;
    }

    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
        result.insert(0, stack.pop());
    }
    return result.toString();
}
```

This alternative uses an explicit `Stack` and builds the result by popping from the stack, which may be more intuitive for some readers.
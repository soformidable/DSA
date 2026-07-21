# Longest Palindrome Substring

## Problem Statement
Given a string `s`, return the longest palindromic substring in `s`.

## Problem
https://leetcode.com/problems/longest-palindromic-substring/description/

## Code
https://github.com/soformidable/DSA/blob/main/LCPatterns/Medium/LongestPalindromeLen.java

### Examples
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Input: "cbbd"
Output: "bb"
```

## Current Implementation

```java
public class LongestPalindromeLen {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0, maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            // For odd-length palindromes (centered at i)
            int len1 = expandAroundCenter(s, i, i);
            // For even-length palindromes (centered between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - ((len - 1) / 2);
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
```

### Complexity
- **Time:** O(n^2) - Each of the `n` centers expands up to `O(n)` times.
- **Space:** O(1) - Constant extra space.

## Explanation

1. **Odd-length palindromes**:
   - Centered at a single character (e.g., `aba` centered at `b`).
   - Use `expandAroundCenter(s, i, i)`.

2. **Even-length palindromes**:
   - Centered between two characters (e.g., `abba` centered between the two `b`s).
   - Use `expandAroundCenter(s, i, i + 1)`.

3. **Expansion**:
   - The `expandAroundCenter` function expands outward from the center while characters match.
   - Returns the length of the longest palindrome found.

4. **Tracking the longest palindrome**:
   - For each center, calculate the maximum length between odd and even cases.
   - Update `start` and `maxLen` if a longer palindrome is found.

5. **Result extraction**:
   - Use `s.substring(start, start + maxLen)` to get the longest palindrome.

## Why the Start Index Never Goes Negative

For **odd-length palindromes**:
- Maximum leftward expansion: `i` steps.
- Maximum length: `2*i + 1`.
- `start = i - (len - 1)/2`
  - For maximum length: `start = i - (2*i)/2 = 0`.

For **even-length palindromes**:
- Maximum leftward expansion: `i` steps.
- Maximum length: `2*i + 2`.
- `start = i - (len - 1)/2`
  - For maximum length: `start = i - (2*i + 1)/2 = i - i = 0`.

## Comparison with Original Solution

| Aspect | Original | Optimal |
|--------|----------|---------|
| **Time** | O(n^3) | O(n^2) |
| **Space** | O(n) | O(1) |
| **Readability** | More intuitive | More complex |
| **Edge Cases** | Handles empty string | Handles empty string |

## Key Insights

1. **Center expansion** is optimal for this problem with O(n^2) time complexity.
2. **Handling both odd and even lengths** ensures all possible palindromes are considered.
3. **Start index calculation** ensures the substring is correctly extracted.
4. **No extra space** is needed beyond a few variables.

## Related Problems
- LeetCode 5 - Longest Palindromic Substring (original problem)
- LeetCode 647 - Palindromic Substrings (count all palindromic substrings)
- LeetCode 131 - Palindrome Partitioning (partition string into palindromic substrings)
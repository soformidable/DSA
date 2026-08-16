"# Decode Ways

## Problem Statement
A message containing letters from A-Z can be encoded into numbers using the following mapping:
```
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
```

To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).

Given a string `s` containing only digits, return the number of ways to decode it.

## Problem:
https://leetcode.com/problems/decode-ways/description/

## Example
```
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Input: s = "06"
Output: 0
Explanation: "06" cannot be decoded as "F" because of the leading zero.
```

## Key Observations

1. **Single digit decoding**: A digit `d` can be decoded if `1 <= d <= 9`
2. **Two digit decoding**: Two digits `dd` can be decoded if `10 <= dd <= 26`
3. **Leading zeros**: Strings starting with '0' cannot be decoded
4. **Invalid cases**: 
   - "0" alone cannot be decoded
   - "30", "40", etc. cannot be decoded (not in 1-26 range)
   - "06" cannot be decoded (leading zero)

## Approach: Dynamic Programming

This is a classic **1D DP** problem similar to Fibonacci/Climbing Stairs.

### Recurrence Relation
```
dp[i] = number of ways to decode string[0..i-1]

For each position i:
1. If single digit is valid (1-9): dp[i] += dp[i-1]
2. If two digit is valid (10-26): dp[i] += dp[i-2]
```

### Base Cases
- `dp[0] = 1` - Empty string has 1 way (base case for DP)
- `dp[1] = 1` - If first character is not '0', has 1 way

## Algorithm
```
numDecodings(s):
    if s is empty or s[0] == '0':
        return 0
    
    n = len(s)
    dp = array of size n+1, initialized to 0
    
    dp[0] = 1  // Base case
    dp[1] = 1  // First character (if not '0')
    
    for i = 2 to n:
        // Single digit: s[i-1]
        single = int(s[i-1])
        if 1 <= single <= 9:
            dp[i] += dp[i-1]
        
        // Two digits: s[i-2..i-1]
        double = int(s[i-2..i-1])
        if 10 <= double <= 26:
            dp[i] += dp[i-2]
    
    return dp[n]
```

## Code Implementation

### Solution 1: Standard DP (from DecodeWays.java)
```java
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        
        int n = s.length();
        int[] dp = new int[n + 1];
        
        dp[0] = 1;  // Empty string has 1 way to decode
        dp[1] = 1;  // One character has 1 way (if not '0')
        
        for(int i = 2; i <= n; i++) {
            // Single digit window: s[i-1]
            int singleDigit = Integer.parseInt(s.substring(i-1, i));
            
            if(singleDigit >= 1 && singleDigit <= 9)
                dp[i] += dp[i - 1];
            
            // Two digit window: s[i-2..i-1]
            int doubleDigit = Integer.parseInt(s.substring(i-2, i));
            
            if(doubleDigit >= 10 && doubleDigit <= 26)
                dp[i] += dp[i - 2];
        }
        
        return dp[n];
    }
}
```

### Solution 2: Space-Optimized DP (O(1) Space)
```java
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        
        int n = s.length();
        int prev2 = 1;  // dp[i-2]
        int prev1 = 1;  // dp[i-1]
        
        for(int i = 2; i <= n; i++) {
            int current = 0;
            
            // Single digit
            int singleDigit = s.charAt(i-1) - '0';
            if(singleDigit >= 1 && singleDigit <= 9)
                current += prev1;
            
            // Two digits
            int doubleDigit = (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0');
            if(doubleDigit >= 10 && doubleDigit <= 26)
                current += prev2;
            
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}
```

## Visual Walkthrough

### Example: s = "226"
```
Index:    0   1   2   3
String:   2   2   6
DP:     [1] [1] ?   ?

i=2: char='2'
  - Single: 2 (valid) → dp[2] += dp[1] = 1
  - Double: "22" = 22 (valid) → dp[2] += dp[0] = 1
  - dp[2] = 2

i=3: char='6'
  - Single: 6 (valid) → dp[3] += dp[2] = 2
  - Double: "26" = 26 (valid) → dp[3] += dp[1] = 1
  - dp[3] = 3

Result: 3 ways
  - "2 2 6" → "BBF"
  - "22 6" → "VF"
  - "2 26" → "BZ"
```

### Example: s = "10"
```
Index:    0   1   2
String:   1   0
DP:     [1] [1] ?

i=2: char='0'
  - Single: 0 (invalid, 0 cannot be decoded alone)
  - Double: "10" = 10 (valid, 10 is in range 10-26) → dp[2] += dp[0] = 1
  - dp[2] = 1

Result: 1 way → "10" → "J"
```

### Example: s = "27"
```
Index:    0   1   2
String:   2   7
DP:     [1] [1] ?

i=2: char='7'
  - Single: 7 (valid) → dp[2] += dp[1] = 1
  - Double: "27" = 27 (invalid, > 26)
  - dp[2] = 1

Result: 1 way → "2 7" → "BG"
```

## Edge Cases

1. **Leading zero**: `"012"` → 0 (invalid)
2. **Zero in middle**: `"101"` → 1 (only "10 1" = "JA")
3. **Multiple zeros**: `"100"` → 0 (cannot decode)
4. **Large numbers**: `"230"` → 0 ("23" valid, but "30" invalid for single digit, and "0" invalid)
5. **All valid**: `"123"` → 3 ("1 2 3", "12 3", "1 23")

## Complexity Analysis

### Time Complexity: O(n)
- Single pass through the string
- Each iteration does O(1) work

### Space Complexity
- **Standard DP**: O(n) for the dp array
- **Optimized DP**: O(1) using two variables

## Common Mistakes to Avoid

### ❌ Mistake 1: Not Handling Leading Zeros
```java
// WRONG: Doesn't check for '0' at start
if(s.length() == 0) return 0;
```

### ❌ Mistake 2: Parsing Substrings Inefficiently
```java
// INEFFICIENT: Creates new string objects each iteration
int singleDigit = Integer.parseInt(s.substring(i-1, i));
int doubleDigit = Integer.parseInt(s.substring(i-2, i));

// BETTER: Use char arithmetic
int singleDigit = s.charAt(i-1) - '0';
int doubleDigit = (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0');
```

### ❌ Mistake 3: Wrong Base Case
```java
// WRONG: dp[1] should be 1 only if first char is not '0'
dp[1] = 1;  // Should check s.charAt(0) != '0'
```

## Pattern Recognition
This problem uses the **Linear DP** pattern:
1. **Subproblem**: Number of ways to decode `s[0..i]`
2. **Recurrence**: `dp[i] = dp[i-1] + dp[i-2]` (with validity checks)
3. **Base cases**: `dp[0] = 1`, `dp[1] = 1` (if valid)
4. **Result**: `dp[n]`

Similar to [[Climbing Stairs]] and [[Fibonacci Number]].

## Related Problems
- [[Decode Ways II]] (Hard) - With wildcard '*'
- [[Climbing Stairs]] - Similar DP pattern
- [[Word Break]] - String segmentation DP
- [[Unique Paths]] - 2D DP version

## Practice Tips
1. **Draw the DP table** for small examples
2. **Handle edge cases first**: empty string, leading zeros
3. **Understand why dp[0] = 1**: It's a base case for when two-digit decode uses dp[i-2]
4. **Test with tricky cases**: "10", "27", "100", "101"

#LeetCode #Medium #String #DynamicProgramming #DP
</contents>"
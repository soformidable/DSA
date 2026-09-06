"# Word Break

## Problem Statement
Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

https://leetcode.com/problems/word-break/description/


## Example
```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```

## Why Greedy Approach Fails

The commented-out greedy code tries to match the **longest possible prefix** at each step (or simply the first match as soon as it finds one). This fails when multiple valid segmentations exist but a premature match leads to a dead end.

### Classic Failure Case: `s = "aaaaaaa"`, `wordDict = ["aaaa","aaa"]`

- **Greedy behavior**: 
  1. Start at index 0. Find the longest matching word? Or simply find first match? The commented code finds the first match from `start` to `end` incrementally.
  2. At `start=0`, it tries `"a"` (not in dict), `"aa"` (not), `"aaa"` (yes, match), so it advances `start = 3`.
  3. Now at index 3, it tries `"a"`, `"aa"`, `"aaa"` → match, advance to `start = 6`.
  4. At index 6, it tries `"a"` (not), `"aa"` → not in dict. j=6, end goes to length+1, loop exits. `start=6`, not equal to length=7 -> returns `false`.

- **But there is a valid segmentation**: `"aaaa" + "aaa"` (or `"aaa" + "aaaa"`). Greedy fails because it picked the first match `"aaa"` at index 0, leaving `"aaaa"`, which cannot be partitioned further because only `"aaaa"` and `"aaa"` are in dict, and the remainder `"aaaa"` **can** be matched but greedy already consumed it in a different way. Actually let's trace:
  - s = "aaaaaaa" (7 a's)
  - Possible segmentation: "aaaa" (0-3) + "aaa" (4-6) → valid.
  - Greedy might take "aaa" (0-2) then leftover "aaaa" (3-6) → "aaaa" is in dict -> valid, but the greedy algorithm uses a `start` pointer and a sliding `end`; it moves `start` only when finding a match. It will find "aaa" at positions 0-2, then next from position 3 find "aaa" again (positions 3-5), then leftover "a" at position 6, which is not in dict. So it fails.

The key issue: greedy makes a local optimal choice that may not lead to a global solution. DP explores all possibilities.

## Approach: Dynamic Programming

We use a 1D boolean `dp` array where `dp[i]` indicates whether substring `s[0..i-1]` can be segmented. Transition: `dp[i] = true` if exists `j < i` such that `dp[j] == true` and substring `s[j..i-1]` is in wordDict.

## Code Implementation (with Comments)

```java
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class WordBreak {

    // ============================================================
    // GREEDY APPROACH (COMMENTED OUT) – FAILS for "aaaaaaa"
    // ============================================================
    /* 
    public static boolean wordBreak(String s, List<String> wordDict) {
        if(s.length() == 1 && wordDict.contains(s))
            return true;

        int start = 0;
        int end = start + 1;

        // Two-pointer greedy scan: advance 'start' when a word is found
        while(start < s.length() && end < s.length() + 1 && start < end){
            if(breakHelper(s, wordDict, start, end)){
                start = end;   // Move start to after the matched word
                end++;         // Move end one step further
            } else {
                end++;         // Try longer substring
            }
        }

        // If we consumed the entire string, success
        if(start == s.length())
            return true;
        return false;
    }

    // Helper: checks if substring from start to end exists in dictionary
    private static boolean breakHelper(String s, List<String> wordDict, int start, int end){
        if(wordDict.contains(s.substring(start, end)))
            return true;
        return false;
    }
    */

    // ============================================================
    // DYNAMIC PROGRAMMING APPROACH (Correct)
    // ============================================================

    public static boolean wordBreak(String s, List<String> wordDict) {
        // Convert wordDict to a HashSet for O(1) lookup
        HashSet<String> set = new HashSet<String>();
        for(String x : wordDict)
            set.add(x);

        // dp[i] = true if substring s[0..i-1] can be segmented
        boolean dp[] = new boolean[s.length() + 1];

        // Empty string is always segmentable (base case)
        dp[0] = true;

        // Iterate over all possible ending positions i
        for(int i = 1; i <= s.length(); i++) {
            // Check all possible starting positions j (0 <= j < i)
            for(int j = 0; j < i; j++) {
                // If s[0..j-1] is segmentable AND s[j..i-1] is in dictionary
                if(dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check other j once dp[i] is true
                }
            }
        }

        // Return whether the entire string can be segmented
        return dp[s.length()];
    }

    public static void main(String[] args) {
        // Test case that greedy fails: "aaaaaaa" with dict ["aaaa","aaa"]
        System.out.println(wordBreak("aaaaaaa", 
            new ArrayList<>(Arrays.asList(new String[]{"aaaa","aaa"}))));
        // Expected output: true (segmentation: "aaaa" + "aaa")
    }
}
```

## Visual Walkthrough (DP for `"aaaaaaa"`)

**WordDict**: `{"aaaa", "aaa"}`

**DP Table** (size 8, indices 0..7):

```
s:     a a a a a a a
       0 1 2 3 4 5 6 7

dp[0] = true (empty string)

i=1 (substring "a"):
  j=0: dp[0]=true, but s[0..0]="a" not in dict → dp[1]=false

i=2 ("aa"):
  j=0: "aa" not in dict
  j=1: dp[1]=false → skip → dp[2]=false

i=3 ("aaa"):
  j=0: "aaa" IS in dict! → dp[3] = true (segmentation: "aaa")
  break out of inner loop

i=4 ("aaaa"):
  j=0: "aaaa" in dict → dp[4]=true (segmentation: "aaaa")
  break

i=5 ("aaaaa"):
  j=0: "aaaaa" not in dict
  j=1: dp[1]=false → skip
  j=2: dp[2]=false → skip
  j=3: dp[3]=true, s[3..4]="aa" not in dict
  j=4: dp[4]=true, s[4..4]="a" not in dict
  → dp[5]=false

i=6 ("aaaaaa"):
  j=0: "aaaaaa" not in dict
  j=1: dp[1]=false → skip
  j=2: dp[2]=false → skip
  j=3: dp[3]=true, s[3..5]="aaa" in dict → dp[6]=true (seg: "aaa"+"aaa")
  break

i=7 ("aaaaaaa"):
  j=0: "aaaaaaa" not in dict
  j=1: false
  j=2: false
  j=3: dp[3]=true, s[3..6]="aaaa" in dict → dp[7]=true (seg: "aaa"+"aaaa")
  Actually "aaaaaaaa" would be length 8, but we have length 7. Let's recalc:
  s[3..6] is indices 3,4,5,6 → "aaaa". Yes, "aaaa" in dict. So dp[7]=true.

Return true ✅
```

DP explored both possibilities (prefix "aaa" then "aaaa" or prefix "aaaa" then "aaa") and succeeded.

## Complexity Analysis

- **Time Complexity**: O(N²) where N = length of string s. We have nested loops: outer i (1..N) and inner j (0..i-1). Substring extraction `s.substring(j,i)` takes O(N) per call, but overall can be optimized to O(N²) using hash set lookups; typical implementation is O(N³) if substring copying is expensive, but in practice with hash set it's O(N²). For LeetCode constraints (N ≤ 300), this is acceptable.

- **Space Complexity**: O(N + M) where M = size of wordDict. For dp array and HashSet.

## Edge Cases

| Input | Output | Explanation |
|-------|--------|-------------|
| `s=""`, `wordDict=["a"]` | `true` | Empty string always segmentable |
| `s="a"`, `wordDict=[]` | `false` | No words available |
| `s="a"`, `wordDict=["a"]` | `true` | Single character match |
| `s="aaaaaaa"`, `wordDict=["aaaa","aaa"]` | `true` | "aaaa"+"aaa" or "aaa"+"aaaa" |
| `s="catsandog"`, `wordDict=["cats","dog","sand","and","cat"]` | `false` | No valid segmentation |

## Comparison: Greedy vs DP

| Aspect | Greedy | Dynamic Programming |
|--------|--------|---------------------|
| Approach | Earliest/longest match at each step | Explore all possible break points |
| Correctness | May fail when multiple paths exist | Always finds a solution if exists |
| Time Complexity | O(N²) worst case (but may miss answer) | O(N²) guaranteed |
| Memory | O(1) extra | O(N) dp array |

## Pattern Recognition

This is a classic **Dynamic Programming for String Segmentation** problem. The pattern:
- State `dp[i]` indicates whether prefix of length i is breakable.
- Transition: `dp[i] = any(dp[j] && wordDict.contains(s[j:i]))`
- Can also be solved with BFS (treat indices as nodes, edges if substring is in dict).

## Related Problems

- [[Word Break II]] (Hard) - Return all possible sentences
- [[Concatenated Words]] (Hard) - Longest compound word
- [[Palindrome Partitioning]] (Medium) - Similar backtracking segmentation

## Common Mistakes to Avoid

### ❌ Mistake 1: Using greedy
```java
// WRONG - May fail for non-trivial cases
start = end;  // Commits to a match

// CORRECT - Use DP to explore all possibilities
dp[i] = true for any valid break point j
```

### ❌ Mistake 2: Forgetting to use HashSet for O(1) lookups
```java
// SLOW: List.contains() is O(k) per call
if(wordDict.contains(s.substring(j,i)))  // Could be O(k) each time

// FAST: HashSet.contains() is O(1)
if(set.contains(s.substring(j,i)))
```

### ❌ Mistake 3: Off-by-one in DP indices
```java
// WRONG: dp array size mismatch
boolean[] dp = new boolean[s.length()];  // Should be s.length()+1

// CORRECT
boolean[] dp = new boolean[s.length() + 1];
dp[0] = true;  // base case for empty prefix
```

### ❌ Mistake 4: Not breaking inner loop after setting dp[i]=true
```java
// Minor optimization: once dp[i] is true, other j won't change it
if(dp[j] && set.contains(s.substring(j,i))) {
    dp[i] = true;
    break;  // No need to check remaining j
}
```

## Practice Tips

1. **Always draw the DP table** for small strings to understand transitions.
2. **Test with greedy-failing inputs** like "aaaaaaa" with ["aaaa","aaa"] to appreciate DP benefits.
3. **Remember that dp[0] = true** is crucial for matching from the very start.
4. **If the problem asks to return the sentences**, extend to [[Word Break II]] with backtracking.
5. **Time your solution**: O(N²) usually passes for N up to 300.

#LeetCode #Medium #String #DynamicProgramming #HashSet
</contents>"
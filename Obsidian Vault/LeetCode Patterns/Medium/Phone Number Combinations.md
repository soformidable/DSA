# Phone Number Combinations

**Problem Statement:**  
Given a string containing digits from `2-9` inclusive, return all possible letter combinations that the number could represent. Return the answer in any valid order.

A mapping of the digits to letters (just like on the telephone buttons) is defined below. Note that 1 does not map to any letters.

---

## Problem
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

## Key Insight

This problem requires generating all possible combinations of letters for a given sequence of phone digits. The classic approach is **backtracking**, which explores all possible paths and builds combinations character by character.

**Mapping:**
```
2 → abc
3 → def
4 → ghi
5 → jkl
6 → mno
7 → pqrs
8 → tuv
9 → wxyz
```

---

## Solution: Backtracking

**Algorithm:**
1. Handle edge case: empty or null input returns empty list
2. Initialize a mapping array from digits to letters
3. Use backtracking to explore all combinations:
   - For each digit, iterate through its corresponding letters
   - Append each letter to current combination
   - Recurse to process the next digit
   - Backtrack by removing the last letter
4. When we've processed all digits, add the combination to result

**Why backtracking works:**
- Systematically explores all possible paths
- Uses StringBuilder for efficient string manipulation
- Automatically handles all combinations through recursion

---

## Code Implementation

```java
public static List<String> letterCombinations(String digits) {
    if(digits == null || digits.length() == 0)
        return Collections.emptyList();

    List<String> result = new ArrayList<String>();
    backtrack(result, new StringBuilder(), 0, digits);
    return result;
}

private static void backtrack(List<String> result, StringBuilder current, int index, String digits) {
    if(index == digits.length()) {
        result.add(current.toString());
        return;
    }
    
    String letters = MAPPING[digits.charAt(index) - '0'];

    for(char c : letters.toCharArray()) {
        current.append(c);
        backtrack(result, current, index + 1, digits);
        current.deleteCharAt(current.length() - 1);
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(4^n × n)  
  - Where `n` is the length of `digits`
  - Each digit can map to at most 4 letters (digit 7 and 9)
  - For each combination, we build a string of length `n`

- **Space Complexity:** O(4^n × n)  
  - Storing all possible combinations in the result
  - Recursion depth: O(n) for the call stack

---

## Example Walkthrough

**Input:** `digits = "23"`

**Step 1:** Map digits to letters
- `2` → "abc"
- `3` → "def"

**Step 2:** Backtrack execution
1. Start with empty string, index = 0
2. Process digit '2': try 'a', 'b', 'c'
3. For 'a': process digit '3': try 'd', 'e', 'f'
   - Results: "ad", "ae", "af"
4. For 'b': process digit '3': try 'd', 'e', 'f'
   - Results: "bd", "be", "bf"
5. For 'c': process digit '3': try 'd', 'e', 'f'
   - Results: "cd", "ce", "cf"

**Final Output:** `["ad","ae","af","bd","be","bf","cd","ce","cf"]`

---

## Alternative Approaches

### Iterative BFS Approach

Instead of recursion, use a queue to build combinations iteratively:

```java
public List<String> letterCombinationsIterative(String digits) {
    if(digits.isEmpty()) return new ArrayList<>();
    
    List<String> result = new ArrayList<>();
    result.add("");
    
    for(char digit : digits.toCharArray()) {
        String letters = MAPPING[digit - '0'];
        List<String> temp = new ArrayList<>();
        for(String combo : result) {
            for(char letter : letters.toCharArray()) {
                temp.add(combo + letter);
            }
        }
        result = temp;
    }
    return result;
}
```

---

## Related Problems

- **Subsets** - Generate all subsets of a set
- **Permutations** - Generate all permutations of a sequence
- **Generate Parentheses** - Generate valid combinations of parentheses
- **Letter Case Permutation** - Generate all case variations

---

## Common Mistakes

1. **Not handling empty input** - Must check for null/empty string
2. **Forgetting to backtrack** - Must remove the last character after recursion
3. **Incorrect digit-to-letter mapping** - Ensure mapping matches phone keypad
4. **Using String concatenation** - Inefficient; prefer StringBuilder

---

## Edge Cases

- Single digit input (e.g., "2")
- Empty string input
- Maximum length input with digits 7 and 9 (4 letters each)

---

## Tags

#leetcode #string #backtracking #dfs #medium

---
*Source: LCPatterns/Medium/PhoneNumberCombination.java*
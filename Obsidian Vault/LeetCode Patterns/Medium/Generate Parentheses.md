# Generate Parentheses

**Problem Statement:**  
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

---
## Problem:
https://leetcode.com/problems/generate-parentheses/description/

## Key Insight

This problem is a classic backtracking problem where we need to generate all valid combinations of parentheses. The key constraints are:
1. We can add an opening parenthesis `(` if we haven't used all `n` opening parentheses
2. We can add a closing parenthesis `)` only if the number of closing parentheses used so far is less than the number of opening parentheses

This ensures that at any point, the parentheses sequence is valid (i.e., we never close more than we open).

---

## Solution: Backtracking

**Algorithm:**
1. Start with an empty string and counts of open and close parentheses used (both 0)
2. At each step, we can add `(` if `open < n`
3. We can add `)` if `close < open`
4. When the string length equals `2*n`, we have a complete valid sequence
5. Backtrack by removing the last character and trying other paths

**Why backtracking works:**
- Systematically explores all possible valid parentheses sequences
- Uses the constraints to prune invalid paths early
- Builds solutions character by character

---

## Code Implementation

```java
public static List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<String>();
    generate(result, new StringBuilder(), 0, 0, n);
    return result;
}

public static void generate(List<String> result, StringBuilder str, int open, int close, int n) {
    if (str.length() == n * 2) {
        result.add(str.toString());
        return;
    }

    if (open < n) {
        str.append("(");
        generate(result, str, open + 1, close, n);
        str.deleteCharAt(str.length() - 1);
    }

    if (close < open) {
        str.append(")");
        generate(result, str, open, close + 1, n);
        str.deleteCharAt(str.length() - 1);
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(4^n / √n)  
  - The number of valid parentheses sequences is the nth Catalan number: C(n) = (2n choose n) / (n+1)
  - This is bounded by O(4^n / n√n)
  - Each sequence takes O(n) to build
  - Overall: O(4^n / √n)

- **Space Complexity:** O(n)  
  - Recursion depth is O(2n) = O(n)
  - We use StringBuilder for efficient string manipulation
  - Result storage is O(4^n / √n) but not counted in space complexity

---

## Example Walkthrough

**Input:** `n = 2`

**Step 1:** Start with empty string, open=0, close=0

**Step 2:** Add `(` (since open < 2)
- Current: "(" (open=1, close=0)

**Step 3:** Now we have two choices:
   - Add `(` (since open < 2): Current: "((" (open=2, close=0)
     - Add `)` (since close < open): Current: "(()" (open=2, close=1)
       - Add `)` (since close < open): Current: "(())" (open=2, close=2) ✓ COMPLETE
   - Add `)` (since close < open): Current: "()" (open=1, close=1)
     - Add `(` (since open < 2): Current: "()(" (open=2, close=1)
       - Add `)` (since close < open): Current: "()()" (open=2, close=2) ✓ COMPLETE

**Final Output:** `["(())","()()"]`

---

## Alternative Approaches

### Iterative Approach (using Stack)

While backtracking is the most intuitive, an iterative approach using a stack can also work:

```java
public List<String> generateParenthesisIterative(int n) {
    List<String> result = new ArrayList<>();
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[]{0, 0, -1}); // {open, close, lastAdded}
    
    while (!stack.isEmpty()) {
        int[] state = stack.pop();
        int open = state[0], close = state[1], lastAdded = state[2];
        
        if (open == n && close == n) {
            // Build the string from stack history
            StringBuilder sb = new StringBuilder();
            // ... reconstruct string
        }
        
        // Push new states in reverse order
        if (close < open) {
            stack.push(new int[]{open, close + 1, 1}); // Add ')'
        }
        if (open < n) {
            stack.push(new int[]{open + 1, close, 0}); // Add '('
        }
    }
    return result;
}
```

### Closure-based Recursion

Another elegant approach uses closure numbers:

```java
public List<String> generateParenthesisClosure(int n) {
    List<String> result = new ArrayList<>();
    if (n == 0) {
        result.add("");
    } else {
        for (int c = 0; c < n; c++) {
            for (String left : generateParenthesisClosure(c)) {
                for (String right : generateParenthesisClosure(n - 1 - c)) {
                    result.add("(" + left + ")" + right);
                }
            }
        }
    }
    return result;
}
```

---

## Related Problems

- **Letter Combinations of a Phone Number** - Similar backtracking structure
- **Combination Sum** - Generating combinations with constraints
- **Permutations** - Generating all permutations
- **Valid Parentheses** - Checking if parentheses are valid

---

## Common Mistakes

1. **Wrong constraints** - Must check `close < open`, not `close < n`
2. **Forgetting to backtrack** - Must remove the last character after recursion
3. **Off-by-one in length check** - Check `str.length() == n*2`, not `n`
4. **Not using StringBuilder** - String concatenation is inefficient for this problem

---

## Edge Cases

- `n = 0` - Should return empty list or list with empty string
- `n = 1` - Only "()"
- Large `n` - Many combinations (Catalan numbers grow exponentially)

---

## Properties of Valid Parentheses

1. **Balance:** At any point, count of `)` cannot exceed count of `(`
2. **Total length:** Exactly `2n` characters
3. **Equal counts:** Exactly `n` opening and `n` closing parentheses
4. **Prefix property:** Any prefix must have `open >= close`

---

## Tags

#leetcode #string #backtracking #dfs #medium

---
*Source: LCPatterns/Medium/GenerateParenthesis.java*
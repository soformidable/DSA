# Path Sum II

## Problem Statement

Given the `root` of a binary tree and an integer `targetSum`, return all root-to-leaf paths where the sum of the node values in the path equals `targetSum`. Each path should be returned as a list of the node values, not node references.

A **leaf** is a node with no children.

## Problem:
https://leetcode.com/problems/path-sum-ii/description/

## Example

```text
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]

       5
      / \
     4   8
    /   / \
   11  13  4
  /  \     / \
 7    2   5   1
```

## Key Insight

We need to find **all** root-to-leaf paths that sum to the target. This is an extension of [[Path Sum]] (which only checks existence) and requires:

1. **DFS traversal** from root to leaf
2. **Track the current path** list to record node values
3. **Backtrack** (remove the last node) when returning to explore other branches
4. **Add a copy** of the path to the result when a valid path is found

---

## Algorithm: DFS with Backtracking

### Core Idea

1. Start from root with `remainingSum = targetSum`
2. Add the current node's value to the current path
3. If at a leaf and `node.val == remainingSum`, add a copy of the path to the result
4. Otherwise, recurse on the left and right children with `remainingSum - node.val`
5. **Backtrack**: Remove the current node from the path after exploration

### Algorithm

```text
pathSum(root, targetSum):
    result = []
    path = []
    dfs(root, result, path, targetSum)
    return result

dfs(node, result, path, remainingSum):
    if node is null:
        return

    path.append(node.val)

    // Check if leaf and valid sum
    if node.left is null AND node.right is null AND node.val == remainingSum:
        result.add(new ArrayList(path))
    else:
        // Explore left and right with updated remaining sum
        dfs(node.left, result, path, remainingSum - node.val)
        dfs(node.right, result, path, remainingSum - node.val)

    // Backtrack
    path.removeLast()
```

---

## Code Implementation

### Solution from `BTPathSum.java`

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        getSumHelper(root, result, new ArrayList<>(), targetSum);
        return result;
    }

    private void getSumHelper(
            TreeNode node,
            List<List<Integer>> result,
            List<Integer> currentPath,
            int remainingSum) {

        // Base case: end of branch
        if (node == null)
            return;

        // Add current node to path
        currentPath.add(node.val);

        // Check if leaf and sum matches
        if (node.left == null && node.right == null && node.val == remainingSum) {
            result.add(new ArrayList<>(currentPath));
        } else {
            // Explore left
            getSumHelper(
                    node.left,
                    result,
                    currentPath,
                    remainingSum - node.val
            );

            // Explore right
            getSumHelper(
                    node.right,
                    result,
                    currentPath,
                    remainingSum - node.val
            );
        }

        // Backtrack: remove current node before returning
        currentPath.remove(currentPath.size() - 1);
    }
}
```

### Alternative: Early Termination Optimization

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPaths(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void findPaths(
            TreeNode node,
            int remaining,
            List<Integer> path,
            List<List<Integer>> result) {

        if (node == null)
            return;

        path.add(node.val);
        remaining -= node.val;

        if (node.left == null && node.right == null && remaining == 0)
            result.add(new ArrayList<>(path));
        else {
            findPaths(node.left, remaining, path, result);
            findPaths(node.right, remaining, path, result);
        }

        path.remove(path.size() - 1);
    }
}
```

---

## Visual Walkthrough

### Example: Tree with `targetSum = 22`

```text
       5 (remaining=22)
      / \
     4   8
    /   / \
   11  13  4
  /  \     / \
 7    2   5   1
```

### Path 1: `5 → 4 → 11 → 2`

```text
path = [], result = []

dfs(5, remaining=22):
    path = [5]
    NOT leaf, recurse left with remaining = 17

dfs(4, remaining=17):
    path = [5, 4]
    NOT leaf, recurse left with remaining = 13

dfs(11, remaining=13):
    path = [5, 4, 11]
    NOT leaf, recurse left with remaining = 2

dfs(7, remaining=2):
    path = [5, 4, 11, 7]
    LEAF? Yes (no children)
    7 != 2 → NOT valid
    Backtrack: path = [5, 4, 11]
    return

dfs(2, remaining=2):
    path = [5, 4, 11, 2]
    LEAF? Yes
    2 == 2 → VALID! Add [5,4,11,2] to result
    Backtrack: path = [5, 4, 11]
    return

Backtrack up to 4, then to 5
dfs from 5's right child (8):
```

### Path 2: `5 → 8 → 4 → 5`

```text
dfs(8, remaining=17):
    path = [5, 8]
    NOT leaf, recurse left with remaining = 9

dfs(13, remaining=9):
    path = [5, 8, 13]
    LEAF? Yes
    13 != 9 → NOT valid
    Backtrack: path = [5, 8]
    return

dfs(4, remaining=9):
    path = [5, 8, 4]
    NOT leaf, recurse left with remaining = 5

dfs(5, remaining=5):
    path = [5, 8, 4, 5]
    LEAF? Yes
    5 == 5 → VALID! Add [5,8,4,5] to result
    Backtrack: path = [5, 8, 4]
    return

dfs(1, remaining=5):
    path = [5, 8, 4, 1]
    LEAF? Yes
    1 != 5 → NOT valid
    Backtrack: path = [5, 8, 4]
    return

Final result: [[5,4,11,2],[5,8,4,5]]
```

---

## Complexity Analysis

### Time Complexity: `O(n²)` in the worst case

- **Visiting nodes:** `O(n)` — each node is visited once.
- **Copying paths:** `O(n × k)`, where `k` is the number of valid paths and each copied path can contain up to `O(n)` nodes.
- In a perfect binary tree, there can be about `n/2` leaves, each with path length `O(log n)`, giving `O(n log n)` for copying in that case.
- In a highly skewed tree, the path length can be `O(n)`, so the copying cost can reach `O(n²)`.

### Space Complexity

- **Recursion stack:** `O(h)`, where `h` is the height of the tree.
- **Current path list:** `O(h)`.
- **Result storage:** `O(n × k)` in the worst case, where `k` is the number of valid paths.
- **Auxiliary space excluding the result:** `O(h)`.

---

## Edge Cases

1. **Empty tree:** `root = null` → return `[]`.
2. **No valid paths:** No root-to-leaf sum equals the target → return `[]`.
3. **Root is a leaf:** For a single-node tree, check whether the target equals the node value.
4. **Negative values:** The algorithm works with negative numbers because it subtracts each node's value from the remaining sum.
5. **Multiple valid paths:** All matching root-to-leaf paths are captured.
6. **Large `targetSum`:** It can be larger than any possible path sum.

---

## Comparison: Path Sum vs Path Sum II

| Aspect | [[Path Sum]] (112) | Path Sum II (113) |
|---|---|---|
| Return type | boolean (exists?) | List of paths |
| Storage needed | None | List to track current path |
| Backtracking | Not needed | Required to remove nodes after exploration |
| Complexity | `O(n)` time, `O(h)` space | `O(n²)` worst-case time, `O(h)` auxiliary space |
| Edge case detection | Return true on first match | Must explore all branches |

---

## Pattern Recognition

This problem uses the **Backtracking on Trees** pattern:

1. **DFS traversal** — Preorder (root, left, right)
2. **Build path incrementally** — Add node when visiting
3. **Check condition at leaf** — Evaluate whether the path sum matches the target
4. **Backtrack** — Remove the node when leaving to undo its effects
5. **Store copy** — Create a new `ArrayList` copy of a valid path for the result

## Related Problems

- [[Path Sum]] (112) — Check existence
- [[Path Sum III]] (437) — Paths starting anywhere
- [[Binary Tree Paths]] (257) — All root-to-leaf strings
- [[Sum Root to Leaf Numbers]] (129) — Numbers formed by root-to-leaf paths

---

## Common Mistakes to Avoid

### ❌ Mistake 1: Not Creating a Copy of the Path

```java
// WRONG: Adds a reference to the mutable path that will be modified later
result.add(currentPath);  // Later backtracking removes nodes!

// CORRECT: Create a new copy
result.add(new ArrayList<>(currentPath));
```

### ❌ Mistake 2: Forgetting to Backtrack

```java
// WRONG: Path continues to grow indefinitely
private void dfs(TreeNode node, ...) {
    if (node == null)
        return;

    path.add(node.val);

    dfs(node.left, ...);
    dfs(node.right, ...);

    // Missing: path.removeLast() → path grows without bound!
}

// CORRECT:
path.add(node.val);
dfs(node.left, ...);
dfs(node.right, ...);
path.remove(path.size() - 1);
```

### ❌ Mistake 3: Wrong Leaf Detection

```java
// CORRECT: A leaf has no children
if (node.left == null && node.right == null) {
    // This is a leaf
}

// The leaf check can then be combined with the remaining-sum check:
if (node.left == null && node.right == null && node.val == remainingSum) {
    // Valid path
}
```

### ❌ Mistake 4: Modifying `remainingSum` Instead of Passing a New Value

```java
// WRONG: Modifying a shared variable
remainingSum -= node.val;  // Affects subsequent calls!
dfs(remainingSum);
dfs(remainingSum);
remainingSum += node.val;  // Manual revert

// CORRECT: Pass the expression directly
dfs(node.left, remainingSum - node.val);
dfs(node.right, remainingSum - node.val);
```

---

## Practice Tips

1. **Always add to the path before checking** — The current node must be included when evaluating the sum.
2. **Backtrack immediately after all children** — Remove the node just before returning up.
3. **Make a copy when adding to the result** — The path list will be mutated by backtracking.
4. **Use the `remainingSum` approach** — Subtract as you go down and check equality at the leaf.
5. **Trace a small example** on paper to understand the backtracking flow.

---

## Additional Examples

### Input: `root = [1,2,3]`, `targetSum = 5`

```text
    1
   / \
  2   3

Paths:
- 1 → 2 = 3 ≠ 5
- 1 → 3 = 4 ≠ 5

Output: [] (no valid paths)
```

### Input: `root = [1,2]`, `targetSum = 1`

```text
    1
   /
  2

Only leaf is 2, sum = 1 + 2 = 3 ≠ 1

Output: []
```

### Input: `root = [-2,null,-3]`, `targetSum = -5`

```text
   -2
     \
     -3

Path: -2 → -3 = -5 ✅

Output: [[-2,-3]]
```

---

#LeetCode #Medium #Tree #DFS #Backtracking #BinaryTree

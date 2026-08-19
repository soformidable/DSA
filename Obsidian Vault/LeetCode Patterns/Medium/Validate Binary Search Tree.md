# Validate Binary Search Tree

## Problem Statement
Given the `root` of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys **less than** the node's key.
- The right subtree of a node contains only nodes with keys **greater than** the node's key.
- Both the left and right subtrees must also be binary search trees.

## Example
```
Input: root = [2,1,3]
Output: true

Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
```

## Key Insight
A BST requires that **every node** in the left subtree is less than the root, and **every node** in the right subtree is greater than the root. This is not just about immediate children!

## Two Main Approaches

### Approach 1: Recursive Min/Max Bounds
- Pass down valid range (min, max) for each node
- Each node must be within its valid range

### Approach 2: Stack-Based Inorder Traversal
- Perform inorder traversal (Left → Root → Right)
- In a valid BST, inorder traversal produces sorted sequence
- Check if each element is greater than the previous

---

## Approach 1: Recursive with Min/Max Bounds

### Core Idea:
Each node has a valid range determined by its ancestors:
- Left child: range is (min, parent.val)
- Right child: range is (parent.val, max)

### Algorithm:
```
isValidBST(root):
    return helper(root, -∞, +∞)

helper(node, min, max):
    if node is null:
        return true
    
    if node.val <= min OR node.val >= max:
        return false
    
    // Left subtree: max becomes node.val
    // Right subtree: min becomes node.val
    return helper(node.left, min, node.val) AND 
           helper(node.right, node.val, max)
```

### Code Implementation
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }
    
    private boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        // Base case: null node is valid
        if(node == null) 
            return true;
        
        // Check if current node violates the range
        if((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;
        
        // Left subtree: update max to current node's value
        // Right subtree: update min to current node's value
        return isValidBSTHelper(node.left, min, node.val) && 
               isValidBSTHelper(node.right, node.val, max);
    }
}
```

### Visual Walkthrough

#### Example 1: Valid BST `[2,1,3]`
```
        2         Range: (-∞, +∞)
       / \
      1   3       Left: (-∞, 2), Right: (2, +∞)

Node 2: -∞ < 2 < +∞ ✓
Node 1: -∞ < 1 < 2 ✓
Node 3: 2 < 3 < +∞ ✓

Result: true
```

#### Example 2: Invalid BST `[5,1,4,null,null,3,6]`
```
            5         Range: (-∞, +∞)
           / \
          1   4       Left: (-∞, 5), Right: (5, +∞)
             / \
            3   6     Left: (5, 4) ← Invalid!

Node 5: -∞ < 5 < +∞ ✓
Node 1: -∞ < 1 < 5 ✓
Node 4: 5 < 4 < +∞? NO! 4 < 5 ✗

Result: false
```

---

## Approach 2: Stack-Based Inorder Traversal

### Core Idea:
In a valid BST, **inorder traversal** (Left → Root → Right) produces a **strictly increasing** sequence.

We can perform iterative inorder traversal using a stack and check if each element is greater than the previous one.

### Algorithm:
```
isValidBST(root):
    if root is null:
        return true
    
    stack = empty stack
    prev = null  // Previous node in inorder traversal
    curr = root
    
    while curr != null OR stack is not empty:
        // Go to leftmost node
        while curr != null:
            stack.push(curr)
            curr = curr.left
        
        // Process current node
        curr = stack.pop()
        
        // Check if current > previous
        if prev != null AND curr.val <= prev.val:
            return false
        
        prev = curr
        
        // Move to right subtree
        curr = curr.right
    
    return true
```

### Code Implementation
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;  // Previous node in inorder
        TreeNode curr = root;
        
        while(curr != null || !stack.isEmpty()) {
            // Go to leftmost node
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            
            // If previous value >= current, not a BST
            if(prev != null && curr.val <= prev.val)
                return false;
            
            prev = curr;
            curr = curr.right;
        }
        
        return true;
    }
}
```

### Visual Walkthrough

#### Example: Valid BST `[2,1,3]`
```
Inorder Traversal: 1 → 2 → 3 (sorted!)

Step-by-step:
1. Push 2, go left
2. Push 1, go left (null)
3. Pop 1: prev=null, set prev=1
4. Go right (null)
5. Pop 2: prev=1, 2 > 1 ✓, set prev=2
6. Go right to 3
7. Push 3, go left (null)
8. Pop 3: prev=2, 3 > 2 ✓, set prev=3
9. Stack empty, curr=null

Result: true
```

#### Example: Invalid BST `[5,1,4,null,null,3,6]`
```
Inorder Traversal: 1 → 5 → 3 → 4 → 6 (NOT sorted!)

Step-by-step:
1. Push 5, go left
2. Push 1, go left (null)
3. Pop 1: prev=null, set prev=1
4. Go right (null)
5. Pop 5: prev=1, 5 > 1 ✓, set prev=5
6. Go right to 4
7. Push 4, go left
8. Push 3, go left (null)
9. Pop 3: prev=5, 3 <= 5 ✗ RETURN FALSE

Result: false
```

---

## Comparison: Recursive vs Stack-Based

| Aspect | Recursive Min/Max | Stack-Based Inorder |
|--------|-------------------|---------------------|
| Time Complexity | O(n) | O(n) |
| Space Complexity | O(h) - recursion stack | O(h) - explicit stack |
| Approach | Divide and conquer with bounds | Inorder traversal check |
| Intuition | "Is this value in valid range?" | "Is the sequence sorted?" |
| Best For | Understanding BST properties | Iterative solution preference |
| Early Termination | Yes, when violation found | Yes, when violation found |

### When to Use Which?

**Use Recursive Min/Max when:**
- You want to understand BST properties clearly
- You prefer recursive solutions
- You want to pass down constraints naturally

**Use Stack-Based Inorder when:**
- You want to avoid recursion (stack overflow risk)
- You're comfortable with iterative tree traversal
- You want to leverage the sorted property of inorder traversal

---

## Complexity Analysis

### Time Complexity: O(n)
- Both approaches visit each node exactly once
- n = number of nodes in the tree

### Space Complexity: O(h)
- h = height of the tree
- Balanced tree: O(log n)
- Skewed tree: O(n)
- Recursive: uses call stack
- Stack-based: uses explicit stack

---

## Edge Cases

1. **Empty tree**: root = null → true
2. **Single node**: root only → true
3. **Left child equals parent**: `[2,2,3]` → false (must be strictly less)
4. **Right child equals parent**: `[2,1,2]` → false (must be strictly greater)
5. **Deep tree**: Test with skewed tree for stack overflow in recursive
6. **Integer overflow**: Use `Integer` instead of `int` for bounds

## Common Mistakes to Avoid

### ❌ Mistake 1: Only Checking Immediate Children
```java
// WRONG: Only checks left and right children
if(node.left != null && node.left.val >= node.val) return false;
if(node.right != null && node.right.val <= node.val) return false;

// CORRECT: Must check ALL descendants with proper bounds
```

### ❌ Mistake 2: Using <= or >= Incorrectly
```java
// WRONG: BST allows equal values? NO!
if(node.val <= min)  // Should be: node.val <= min (strictly less)

// Note: LeetCode 98 requires STRICT inequality
// Left: node.val < parent.val
// Right: node.val > parent.val
```

### ❌ Mistake 3: Not Using Integer for Bounds
```java
// WRONG: Using int won't handle edge cases
int min = Integer.MIN_VALUE;
int max = Integer.MAX_VALUE;

// CORRECT: Use Integer to allow null (representing -∞/+∞)
Integer min = null;  // Represents -∞
Integer max = null;  // Represents +∞
```

### ❌ Mistake 4: Stack-Based - Wrong Comparison
```java
// WRONG: Should be strictly increasing
if(prev != null && curr.val < prev.val)  // Misses equal values

// CORRECT: Must be strictly greater
if(prev != null && curr.val <= prev.val)
```

---

## Alternative: Morris Traversal (O(1) Space)

For advanced learners, Morris Traversal achieves O(1) space:

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        
        while(curr != null) {
            if(curr.left == null) {
                // Process curr
                if(prev != null && curr.val <= prev.val)
                    return false;
                prev = curr;
                curr = curr.right;
            } else {
                // Find inorder predecessor
                TreeNode pred = curr.left;
                while(pred.right != null && pred.right != curr)
                    pred = pred.right;
                
                if(pred.right == null) {
                    // Create thread
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // Remove thread, process curr
                    pred.right = null;
                    if(prev != null && curr.val <= prev.val)
                        return false;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        
        return true;
    }
}
```

---

## Pattern Recognition

This problem combines:
1. **Binary Tree Traversal** - Inorder, preorder, or custom DFS
2. **BST Properties** - Left < Root < Right for ALL nodes
3. **Range Validation** - Each node has valid bounds from ancestors
4. **Sorted Sequence Check** - Inorder of BST is sorted

## Related Problems
- [[Validate Binary Search Tree]] (98) - This problem
- [[Binary Tree Inorder Traversal]] (94)
- [[Kth Smallest Element in BST]] (230)
- [[Convert Sorted Array to BST]] (108)
- [[Recover Binary Search Tree]] (99)

## Practice Tips

1. **Draw the tree with ranges** for recursive approach
2. **Trace inorder traversal** for stack-based approach
3. **Remember**: BST requires STRICT inequality (no duplicates in standard BST)
4. **Test edge cases**: single node, equal values, skewed tree
5. **Understand why inorder works**: Left subtree < Root < Right subtree

#LeetCode #Medium #Tree #BinarySearchTree #DFS #Stack
</contents>
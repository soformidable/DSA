# Kth Smallest Element in a BST

**Problem Statement:**
Given the `root` of a binary search tree, and an integer `k`, return the `kth` smallest value (1-indexed) of all the values of the nodes in the tree.

---

## Problem:
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

## Key Insight

This problem leverages the **in-order traversal** property of a Binary Search Tree:

1. **BST Property:** In a BST, an in-order traversal visits nodes in **ascending order**. This means the kth node visited during an in-order traversal is the kth smallest element.

2. **Decrement Counter:** Instead of collecting all nodes, we can maintain a counter that decrements each time we visit a node. When the counter reaches `0`, the current node is our answer.

3. **Two Approaches:**
   - **Recursive (DFS):** Traverse left, process current, traverse right — naturally yields sorted order.
   - **Iterative (Stack):** Manually simulate in-order traversal using a stack, pushing left children and processing on pop.

4. **Early Termination:** Once the kth element is found, we can stop the traversal early — no need to visit the entire tree.

---

## Solution Approaches

### 1. Recursive DFS (In-Order Traversal)

**Algorithm:**
1. Set a class-level counter to `k`
2. Perform an in-order DFS (left → current → right)
3. At each node, decrement the counter
4. When the counter reaches `0`, record the node's value and return
5. The recorded value is the kth smallest

**Why this works:**
- In-order traversal of a BST visits nodes in sorted ascending order
- The kth node visited is naturally the kth smallest element
- Early return prevents unnecessary traversal of the remaining tree

---

## Code Implementation: Recursive DFS

```java
public static int kthSmallest(TreeNode root, int k) {
    current = k;
    DFS(root);
    return result;
}

private static void DFS(TreeNode node) {
    if (node.left != null)
        DFS(node.left);

    current--;
    if (current == 0) {
        result = node.val;
        return;
    }

    if (node.right != null)
        DFS(node.right);
}
```

---

## Solution 2: Iterative with Stack

**Algorithm:**
1. Push all left children of the root onto a stack
2. Pop nodes one at a time, decrementing `k` for each
3. When `k == 0`, the popped node is the answer
4. After popping, push all left children of the popped node's right subtree
5. Continue until `k` reaches 0

**Why this works:**
- Simulates in-order traversal iteratively
- The stack maintains the "leftmost unvisited" nodes
- Same time complexity as recursion but avoids stack overflow risks on skewed trees

---

## Code Implementation: Iterative with Stack

```java
public static int kthSmallestIterative(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();

    // Push all left children of root
    while (root != null) {
        stack.push(root);
        root = root.left;
    }

    while (k != 0) {
        TreeNode node = stack.pop();
        k--;

        if (k == 0)
            return node.val;

        // Push all left children of the right subtree
        TreeNode right = node.right;
        while (right != null) {
            stack.push(right);
            right = right.left;
        }
    }
    return -1;
}
```

---

## Step-by-Step Walkthrough

**Input:** 
```
        3
       / \
      1   4
       \
        2
```
k = 1

**Recursive DFS Approach:**

| Step | Node | Action | current | result |
|------|------|--------|---------|--------|
| 1 | 3 | Go left | 1 | -1 |
| 2 | 1 | Go left (null) | 1 | -1 |
| 3 | 1 | Process node, current-- → 0 | 0 | 1 |
| 4 | 1 | current == 0, record result = 1 | 0 | 1 |
| 5 | — | Return early | 0 | 1 |

**Final Result: 1**

---

**Input:**
```
        3
       / \
      1   4
       \
        2
```
k = 3

**Iterative Stack Approach:**

| Step | Stack (top → bottom) | k | Action |
|------|----------------------|---|--------|
| 1 | [1, 3] | 3 | Push left spine (3 → 1) |
| 2 | [3] | 2 | Pop 1, k-- → 2, push right subtree (2) |
| 3 | [2, 3] | 2 | Push left spine of 2's right (none) |
| 4 | [3] | 1 | Pop 2, k-- → 1, push right subtree (none) |
| 5 | [] | 0 | Pop 3, k-- → 0, return 3 |

**Final Result: 3**

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|-----------------|-------|
| Recursive DFS | O(H + k) | O(H) | H = tree height, stops early at kth node |
| Iterative with Stack | O(H + k) | O(H) | H = tree height, same early termination |

**Note:** In the worst case (skewed tree), H = n, making the worst-case time complexity O(n) and space O(n).

---

## Key Techniques

1. **In-Order Traversal:** The fundamental property that makes this problem trivial — sorted order
2. **Early Termination:** Stop traversal as soon as kth element is found
3. **Counter Decrement:** Track position without collecting all elements
4. **Stack Simulation:** Convert recursion to iteration to avoid stack overflow

---

## Edge Cases

- **k = 1:** Returns the smallest element (leftmost node)
- **k = n (tree size):** Returns the largest element (rightmost node)
- **Single-node tree:** Only possible answer regardless of k (k must be 1)
- **Skewed left tree:** Worst-case for recursion depth; consider iterative approach
- **Skewed right tree:** The first pushed node is immediately the answer for k = 1

---

## Visualization

For the tree:
```
        5
       / \
      3   6
     / \
    2   4
   /
  1
```

**In-order traversal (sorted):** 1, 2, 3, 4, 5, 6

| k | Result | Nodes Traversed |
|---|--------|-----------------|
| 1 | 1 | [1] |
| 2 | 2 | [1, 2] |
| 3 | 3 | [1, 2, 3] |
| 4 | 4 | [1, 2, 3, 4] |
| 5 | 5 | [1, 2, 3, 4, 5] |
| 6 | 6 | [1, 2, 3, 4, 5, 6] |

---

## Related Problems

- **Validate Binary Search Tree** - Uses in-order traversal to check BST validity
- **Binary Tree Inorder Traversal** - The base traversal technique
- **Kth Largest Element in an Array** - Similar kth selection but not sorted
- **Second Minimum Node In a Binary Tree** - Special case where k = 2

---

## Common Mistakes

1. **Not using in-order traversal:**
   - Using pre-order or post-order which don't yield sorted order

2. **Not decrementing the counter correctly:**
   - Decrementing before checking if kth was already found
   - Off-by-one errors in counter initialization

3. **Not handling null children properly:**
   - Dereferencing null pointers when traversing left/right

4. **Resetting global variables:**
   - Forgetting to reset `current` and `result` between multiple calls

5. **Recursion depth concerns:**
   - Not recognizing stack overflow risk on skewed trees (solution: iterative)

6. **Incorrect iterative stack logic:**
   - Forgetting to push left children of the right subtree after popping
   - Pushing right children before left in stack

---

## Tags

#leetcode #medium #binary-search-tree #tree #dfs #in-order-traversal #stack

---

## Additional Notes

### Pattern Recognition

This problem follows the **BST In-Order Traversal** pattern where:
- Binary Search Tree property guarantees sorted order via in-order
- The kth position problem reduces to stopping at the kth visited node
- Can be solved with recursion or explicit stack

### Similar Pattern Problems

1. **Binary Tree Inorder Traversal:** Just collect all nodes in order
2. **Validate BST:** Check if in-order traversal is strictly increasing
3. **Minimum Absolute Difference in BST:** Compare adjacent in-order values
4. **Two Sum IV:** Use in-order traversal to get sorted array, then two pointers

### Follow-up Questions

- **What if the BST is modified often (insert/delete)?** → Use a Fenwick tree or augmented BST with subtree size counts
- **What if k is frequently asked for different k values?** → Precompute ranks (augment each node with its rank)
- **Can we do it in O(log n)?** → Only if the tree is balanced and we track subtree sizes

### Augmented BST Approach (for follow-up)

Each node stores the size of its left subtree:

```
          5 (size=4)
         / \
  (2)  3   6 (0)
       / \
 (1) 2   4 (0)
     /
(0) 1
```

To find kth smallest:
1. Count nodes in left subtree = `leftSize`
2. If `k == leftSize + 1`, current node is the answer
3. If `k <= leftSize`, go left
4. Else, go right with `k = k - leftSize - 1`

This gives **O(log n)** time for balanced trees.

---

## Example Walkthroughs

**Example 1:**
```
        3
       / \
      1   4
       \
        2
```
- k = 1 → 1
- k = 2 → 2
- k = 3 → 3
- k = 4 → 4

**Example 2:**
```
        5
       / \
      3   6
     / \
    2   4
   /
  1
```
- k = 3 → 3
- In-order: [1, 2, 3, 4, 5, 6], 3rd element = 3

**Example 3:** Single node tree `[1]`
- k = 1 → 1

**Example 4:** Skewed right tree `1 → 2 → 3 → 4`
- k = 2 → 2
- In-order: [1, 2, 3, 4], 2nd element = 2

---

## Interview Tips

1. **Mention the key property first:** "In-order traversal of a BST gives sorted order"
2. **Start with recursive approach:** Simpler to explain and implement
3. **Discuss the counter approach:** Explain how we avoid collecting all values
4. **Mention early termination:** You don't need to traverse the whole tree
5. **Discuss iterative alternative:** Mention stack to handle recursion depth issues
6. **Mention follow-ups:** Be ready to discuss the augmented BST approach for O(log n)
7. **Complexity analysis:** Be clear that worst case is O(n) time and O(n) space

---

*Source: LCPatterns/Medium/KthSmallestInBST.java*
# Binary Tree Right Side View

**Problem Statement:**
Given the `root` of a binary tree, imagine yourself standing on the **right side** of it. Return the values of the nodes you can see ordered from top to bottom.

---

## Problem:
https://leetcode.com/problems/binary-tree-right-side-view/description/

## Key Insight

This problem can be solved using **tree traversal** techniques:

1. **BFS (Level Order Traversal):** Process nodes level by level and take the last node at each level.

2. **DFS (Pre-order Traversal):** Visit right subtree before left subtree, and track the first node seen at each depth.

3. **Level Tracking:** The key observation is that we need to track which nodes are visible from the right side - these are the rightmost nodes at each level.

---

## Solution Approaches

### 1. BFS (Level Order Traversal)

**Algorithm:**
1. Use a queue for level-order traversal
2. For each level, process all nodes at that level
3. The last node processed at each level is the rightmost node - add it to result
4. Continue until all levels are processed

**Why this works:**
- BFS naturally processes nodes level by level
- By tracking the last node at each level, we get the rightmost view
- Works for any binary tree shape

---

## Code Implementation: BFS

```java
public static List<Integer> rightSideView(TreeNode root) {
    if (root == null)
        return new ArrayList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    List<Integer> res = new ArrayList<>();
    
    while (!queue.isEmpty()) {
        int size = queue.size();
        
        for (int i = 0; i < size; i++) {
            TreeNode cur = queue.poll();
            
            // If this is the last node at this level
            if (i == size - 1) {
                res.add(cur.val);
            }
            
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
    }
    
    return res;
}
```

---

## Solution 2: DFS (Pre-order Traversal)

**Algorithm:**
1. Perform DFS traversal, visiting right subtree before left subtree
2. Track current depth and result list size
3. When we reach a new depth (depth == result.size()), add the node value
4. Since we visit right subtree first, the first node at each depth is the rightmost

**Why this works:**
- By visiting right subtree before left, we ensure rightmost nodes are encountered first
- The first time we reach a new depth, we add that node to result
- This gives us the right side view

---

## Code Implementation: DFS

```java
public List<Integer> rightSideViewDFS(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    rightView(root, result, 0);
    return result;
}

public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
    if (curr == null) {
        return;
    }
    
    // First time reaching this depth - add to result
    if (currDepth == result.size()) {
        result.add(curr.val);
    }
    
    // Visit right subtree first, then left
    rightView(curr.right, result, currDepth + 1);
    rightView(curr.left, result, currDepth + 1);
}
```

---

## Step-by-Step Walkthrough

**Input Tree:**
```
       1         <--
      / \
     2   3       <--
      \   \
       5   4     <--
```

**BFS Approach:**

| Level | Nodes in Queue | Processing | Rightmost Node | Result |
|-------|---------------|------------|----------------|--------|
| 0 | [1] | Process node 1 | 1 | [1] |
| 1 | [2, 3] | Process node 2, then 3 | 3 | [1, 3] |
| 2 | [5, 4] | Process node 5, then 4 | 4 | [1, 3, 4] |

**Result:** [1, 3, 4]

**DFS Approach (Right-first Pre-order):**

| Step | Current Node | Depth | Result Size | Action | Result |
|------|--------------|-------|-------------|--------|--------|
| 1 | 1 | 0 | 0 | Add 1 (new depth) | [1] |
| 2 | 3 (right) | 1 | 1 | Add 3 (new depth) | [1, 3] |
| 3 | 4 (right of 3) | 2 | 2 | Add 4 (new depth) | [1, 3, 4] |
| 4 | null (right of 4) | 3 | - | Return | [1, 3, 4] |
| 5 | null (left of 4) | 3 | - | Return | [1, 3, 4] |
| 6 | null (left of 3) | 2 | - | Return | [1, 3, 4] |
| 7 | 2 (left of 1) | 1 | 2 | Skip (depth already seen) | [1, 3, 4] |
| 8 | 5 (right of 2) | 2 | 2 | Skip (depth already seen) | [1, 3, 4] |
| 9 | null (right of 5) | 3 | - | Return | [1, 3, 4] |
| 10 | null (left of 5) | 3 | - | Return | [1, 3, 4] |
| 11 | null (left of 2) | 2 | - | Return | [1, 3, 4] |

**Result:** [1, 3, 4]

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| BFS | O(n) | O(w) | w = maximum width of tree |
| DFS | O(n) | O(h) | h = height of tree (recursion stack) |

**Where:**
- **n** = number of nodes in the tree
- **w** = maximum number of nodes at any level (can be up to n/2 for complete trees)
- **h** = height of tree (log n for balanced, n for skewed)

---

## Key Techniques

1. **Level Order Traversal (BFS):** Process nodes level by level using a queue
2. **Pre-order Traversal (DFS):** Visit nodes in specific order to prioritize right side
3. **Depth Tracking:** Track current depth to know when we encounter new levels
4. **Right-first Strategy:** Visit right subtree before left to ensure rightmost nodes are seen first

---

## Edge Cases

- **Empty tree (root = null):** Return empty list
- **Single node:** Return [root.val]
- **Left-skewed tree:** All nodes visible from right side
- **Right-skewed tree:** All nodes visible from right side
- **Complete binary tree:** Rightmost nodes at each level
- **Tree with only left children:** All left children are visible from right

---

## Visualization

**Example 1:**
```
       1
      / \
     2   3
      \   \
       5   4
```
Right Side View: [1, 3, 4]

**Example 2:**
```
       1
      /
     2
```
Right Side View: [1, 2]

**Example 3:**
```
       1
        \
         3
```
Right Side View: [1, 3]

**Example 4:**
```
       1
      / \
     2   3
    /
   4
```
Right Side View: [1, 3, 4]

**Example 5:**
```
       1
      / \
     2   3
    / \
   4   5
```
Right Side View: [1, 3, 5]

---

## Related Problems

- **Binary Tree Left Side View** - Same problem but from left side
- **Binary Tree Level Order Traversal** - BFS traversal pattern
- **Binary Tree Zigzag Level Order Traversal** - Level traversal with direction change
- **Binary Tree Level Order Traversal II** - Bottom-up level traversal
- **N-ary Tree Level Order Traversal** - Level traversal for N-ary trees

---

## Common Mistakes

1. **Not handling empty tree:**
   - Forgetting to check if root is null
   - Returning null instead of empty list

2. **Wrong node selection:**
   - Taking first node instead of last node at each level (BFS)
   - Visiting left subtree before right (DFS)

3. **Incorrect depth tracking:**
   - Not properly tracking when we reach a new depth
   - Using wrong condition for adding to result

4. **Queue/Stack issues:**
   - Not properly managing queue size for level processing
   - Incorrect order of adding children to queue

5. **Off-by-one errors:**
   - Wrong comparison for last node in level
   - Incorrect depth calculation

---

## Tags

#leetcode #binary-tree #bfs #dfs #tree-traversal #medium

---

## Additional Notes

### Pattern Recognition

This problem uses two common tree traversal patterns:

1. **BFS for Level Processing:**
   - Use queue to process nodes level by level
   - Track level boundaries using queue size
   - Useful for level-specific operations

2. **DFS with State Tracking:**
   - Track additional state (depth, result list) during traversal
   - Visit subtrees in specific order to prioritize certain nodes
   - Useful when order of traversal matters

### BFS Template for Level Order

```java
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

while (!queue.isEmpty()) {
    int size = queue.size();
    
    for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Process node
        
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```

### DFS Template with Depth Tracking

```java
void dfs(TreeNode node, List<Integer> result, int depth) {
    if (node == null) return;
    
    // First time at this depth
    if (depth == result.size()) {
        result.add(node.val);
    }
    
    // Visit right first for right view, left first for left view
    dfs(node.right, result, depth + 1);
    dfs(node.left, result, depth + 1);
}
```

### Why DFS Works for Right View

The key insight is that we visit the right subtree before the left subtree. This ensures:
- The first node we encounter at each depth is the rightmost node
- We add it to the result the first time we reach that depth
- Subsequent nodes at the same depth are ignored

### Why BFS Works for Right View

BFS processes nodes level by level:
- We process all nodes at a level before moving to the next
- The last node processed at each level is the rightmost node
- We add it to the result when we detect it's the last node in the level

### Space Complexity Comparison

- **BFS:** O(w) where w is the maximum width of the tree
  - For a complete binary tree, w = n/2 (last level)
  - Best for wide, shallow trees

- **DFS:** O(h) where h is the height of the tree
  - For a balanced tree, h = log n
  - Best for deep, narrow trees

### Converting to Left Side View

To get the left side view instead:

**BFS:** Take the first node at each level instead of the last:
```java
if (i == 0) {  // First node instead of last
    res.add(cur.val);
}
```

**DFS:** Visit left subtree before right:
```java
rightView(curr.left, result, currDepth + 1);   // Left first
rightView(curr.right, result, currDepth + 1);  // Then right
```

---

## Example Walkthroughs

**Example 1:** [1, 2, 3, null, 5, null, 4]
- Level 0: [1] → Rightmost: 1
- Level 1: [2, 3] → Rightmost: 3
- Level 2: [5, 4] → Rightmost: 4
- Result: [1, 3, 4]

**Example 2:** [1, 2]
- Level 0: [1] → Rightmost: 1
- Level 1: [2] → Rightmost: 2
- Result: [1, 2]

**Example 3:** [1, null, 3]
- Level 0: [1] → Rightmost: 1
- Level 1: [3] → Rightmost: 3
- Result: [1, 3]

**Example 4:** [1, 2, 3, 4]
- Level 0: [1] → Rightmost: 1
- Level 1: [2, 3] → Rightmost: 3
- Level 2: [4] → Rightmost: 4
- Result: [1, 3, 4]

**Example 5:** [1, 2, 3, 4, 5]
- Level 0: [1] → Rightmost: 1
- Level 1: [2, 3] → Rightmost: 3
- Level 2: [4, 5] → Rightmost: 5
- Result: [1, 3, 5]

---

## Interview Tips

1. **Clarify the problem:**
   - Are we looking from the right or left side?
   - What should we return for empty tree?

2. **Discuss both approaches:**
   - BFS is more intuitive for level-based problems
   - DFS is more space efficient for deep trees

3. **Explain the insight:**
   - BFS: Last node at each level
   - DFS: Right-first traversal + first node at each depth

4. **Analyze complexity:**
   - Time: O(n) for both - we visit each node once
   - Space: O(w) for BFS, O(h) for DFS

5. **Handle edge cases:**
   - Empty tree
   - Single node
   - Skewed trees (left or right)

---

*Source: LCPatterns/Medium/BTRightSideView.java*
"# Binary Tree Level Order Traversal

## Problem Statement
Given the `root` of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

## Link:
https://leetcode.com/problems/binary-tree-level-order-traversal/description/

## Example
```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

    3
   / \
  9  20
    /  \
   15   7
```

## Key Insight
Level order traversal visits nodes level by level, from left to right. This naturally lends itself to **Breadth-First Search (BFS)** using a queue. We process all nodes at the current depth before moving to the next depth.

## Two Main Approaches

### Approach 1: Iterative BFS with Queue
- Use a queue to track nodes level by level
- At each level, record the number of nodes (`levelSize`) before processing
- Process exactly `levelSize` nodes, adding their children to the queue

### Approach 2: Recursive DFS with Level Tracking
- Use DFS (preorder) while passing the current depth
- Add the node's value to the list corresponding to its depth
- Requires knowing the result size beforehand or dynamic addition

---

## Approach 1: Iterative BFS with Queue (Standard)

### Algorithm:
```
levelOrder(root):
    if root is null: return []
    
    result = []
    queue = new Queue
    queue.offer(root)
    
    while queue is not empty:
        levelSize = queue.size()
        currentLevel = []
        
        for i = 0 to levelSize - 1:
            node = queue.poll()
            currentLevel.add(node.val)
            
            if node.left != null:
                queue.offer(node.left)
            if node.right != null:
                queue.offer(node.right)
        
        result.add(currentLevel)
    
    return result
```

### Code Implementation (from BSTLevelOrderTraversal.java)
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null)
            return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            // Number of nodes to process in the current level
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for(int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
}
```

### Visual Walkthrough

#### Example: Tree [3,9,20,null,null,15,7]
```
Initial:
queue = [3]
result = []

Level 0 (root):
levelSize = 1
  - poll 3, add to currentLevel [3]
  - enqueue left(9), right(20)
queue = [9, 20]
result = [[3]]

Level 1:
levelSize = 2
  - poll 9, add to currentLevel [9]
  - no children (null)
  - poll 20, add to currentLevel [20]
  - enqueue left(15), right(7)
queue = [15, 7]
result = [[3], [9, 20]]

Level 2:
levelSize = 2
  - poll 15, add to currentLevel [15]
  - poll 7, add to currentLevel [7]
queue = []
result = [[3], [9, 20], [15, 7]]

Return [[3],[9,20],[15,7]]
```

---

## Approach 2: Recursive DFS with Level Tracking

### Core Idea:
Perform a preorder DFS traversal while keeping track of the current depth. When we visit a node at depth `d`:
- If `result` list doesn't have a sublist for depth `d`, create one
- Add node's value to `result.get(d)`

### Algorithm:
```
levelOrder(root):
    result = []
    dfs(root, result, 0)
    return result

dfs(node, result, depth):
    if node is null: return
    
    if depth == result.size():
        result.add(new ArrayList())
    
    result.get(depth).add(node.val)
    
    dfs(node.left, result, depth + 1)
    dfs(node.right, result, depth + 1)
```

### Code Implementation
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }
    
    private void dfs(TreeNode node, List<List<Integer>> result, int depth) {
        if(node == null)
            return;
        
        // If this is the first node at this depth, create a new list
        if(depth == result.size())
            result.add(new ArrayList<>());
        
        // Add current node's value to its depth level
        result.get(depth).add(node.val);
        
        // Recurse on children with increased depth
        dfs(node.left, result, depth + 1);
        dfs(node.right, result, depth + 1);
    }
}
```

### Visual Walkthrough

#### Example: Tree [3,9,20,null,null,15,7]
```
dfs(3, result, 0):
  depth=0, result.size()=0 → create list at index 0
  result[0] = [3]
  dfs(9, result, 1):
    depth=1, result.size()=1 → create list at index 1
    result[1] = [9]
    dfs(null, result, 2) → return
    dfs(null, result, 2) → return
  dfs(20, result, 1):
    depth=1, result.size()=2 → already exists
    result[1] = [9, 20]
    dfs(15, result, 2):
      depth=2, result.size()=2 → create list at index 2
      result[2] = [15]
      dfs(null, result, 3) → return
      dfs(null, result, 3) → return
    dfs(7, result, 2):
      result[2] = [15, 7]
      dfs(null, result, 3) → return
      dfs(null, result, 3) → return

Result: [[3], [9, 20], [15, 7]]
```

---

## Comparison: BFS (Iterative Queue) vs DFS (Recursive Level Tracking)

| Aspect | BFS Iterative (Queue) | DFS Recursive (Level) |
|--------|----------------------|----------------------|
| Time Complexity | O(n) | O(n) |
| Space Complexity | O(w) - max width | O(h) - recursion stack |
| Traversal Order | Level by level, left to right | Preorder (root, left, right) |
| Natural fit | BFS is inherently level order | Simpler code, but not intuitive |
| Early termination? | Yes, can stop any time | Yes, can stop any time |
| Best for | Wide trees (BFS uses memory proportional to width) | Deep trees (DFS uses memory proportional to height) |

---

## Complexity Analysis

### BFS (Queue)
- **Time**: O(n) - each node visited once
- **Space**: O(w) where w is maximum width of tree (at most n/2 for full binary tree)

### DFS (Recursive)
- **Time**: O(n) - each node visited once
- **Space**: O(h) where h is height of tree (worst-case O(n) for skewed tree)

---

## Edge Cases

1. **Empty tree**: root = null → return empty list []
2. **Single node**: root = [1] → return [[1]]
3. **Left-skewed tree**: [1,2,null,3,null,...] → levels with single node each
4. **Right-skewed tree**: [1,null,2,null,3,...] → levels with single node each
5. **Complete binary tree**: All levels full (except possibly last)

## Variations of Level Order Traversal

### 1. Bottom-Up Level Order (LeetCode 107)
Reverse the result list at the end, or use recursion to build from bottom.

### 2. Zigzag Level Order (LeetCode 103)
Alternate direction each level. Use a boolean flag and optionally a deque.

### 3. Level Averages (LeetCode 637)
Calculate average of each level while processing.

### 4. Right Side View (LeetCode 199)
Return the last node of each level (or the first if reversed).

### 5. Largest Value in Each Level (LeetCode 515)
Track maximum value per level.

---

## Code for Zigzag Level Order (Spiral)
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            
            for(int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if(leftToRight)
                    level.addLast(node.val);
                else
                    level.addFirst(node.val);
                
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            
            result.add(level);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
}
```

---

## Pattern Recognition

This problem uses the **BFS pattern** on trees:
1. **Process level by level** using a queue
2. **Record level size** before processing to maintain level separation
3. **Enqueue children** after processing parent

The **recursive DFS approach** follows the pattern of:
- **Track depth** as parameter in recursive calls
- **Create sublists** when encountering a new depth
- **Add node value** to appropriate depth list

## Related Problems
- [[Binary Tree Level Order Traversal II]] (107) - Bottom-up
- [[Binary Tree Zigzag Level Order Traversal]] (103)
- [[Average of Levels in Binary Tree]] (637)
- [[Binary Tree Right Side View]] (199)
- [[Find Largest Value in Each Tree Row]] (515)
- [[N-ary Tree Level Order Traversal]] (429)

## Common Mistakes to Avoid

### ❌ Mistake 1: Not Recording Level Size
```java
// WRONG: This mixes levels together
while(!queue.isEmpty()) {
    TreeNode node = queue.poll();
    // ... processing without level size
}

// CORRECT: Record size before processing level
while(!queue.isEmpty()) {
    int levelSize = queue.size();
    for(int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        // ...
    }
}
```

### ❌ Mistake 2: Forgetting Null Check
```java
// WRONG: Expects non-null tree
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);  // root might be null!

// CORRECT:
if(root == null) return result;
queue.offer(root);
```

### ❌ Mistake 3: Enqueuing Null Children
```java
// WRONG: Enqueues null children
queue.offer(node.left);  // may be null
queue.offer(node.right); // may be null

// CORRECT: Check for null
if(node.left != null) queue.offer(node.left);
if(node.right != null) queue.offer(node.right);
```

### ❌ Mistake 4: Recursive - Not Creating List for New Depth
```java
// WRONG: Assumes result already has list at depth
result.get(depth).add(node.val);  // IndexOutOfBounds if depth >= result.size()

// CORRECT:
if(depth == result.size())
    result.add(new ArrayList<>());
result.get(depth).add(node.val);
```

---

## Practice Tips

1. **Always draw the tree** before coding
2. **BFS (Queue) is the standard** for level order
3. **Understand the for-loop** structure: `levelSize = queue.size()` captured once
4. **For recursive DFS**, remember to check `depth == result.size()` first
5. **Practice variations**: zigzag, bottom-up, right side view

#LeetCode #Medium #Tree #BFS #LevelOrder #Queue #DFS
</contents>"
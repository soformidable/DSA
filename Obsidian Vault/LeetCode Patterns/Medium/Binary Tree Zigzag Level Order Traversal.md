# Binary Tree Zigzag Level Order Traversal

## Problem Statement
Given the `root` of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

## Example
```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

    3          Level 0: Left→Right: [3]
   / \         Level 1: Right→Left: [20,9]
  9  20        Level 2: Left→Right: [15,7]
    /  \
   15   7
```

## Key Insight
This is a variation of [[Binary Tree Level Order Traversal]] where we alternate the direction of traversal at each level:
- **Even levels (0, 2, 4...)**: Left to Right
- **Odd levels (1, 3, 5...)**: Right to Left

## Two Main Approaches

### Approach 1: BFS with Direction Flag
- Standard BFS using queue
- Reverse the level list on odd levels

### Approach 2: DFS with Depth-Based Insertion
- Use DFS with depth tracking
- On even levels: append to end
- On odd levels: insert at beginning (index 0)

---

## Approach 1: BFS with Direction Flag

### Algorithm:
```
zigzagLevelOrder(root):
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
            
            if node.left != null: queue.offer(node.left)
            if node.right != null: queue.offer(node.right)
        
        if result.size() is even:
            result.add(currentLevel)          // Left to Right
        else:
            result.add(reverse(currentLevel)) // Right to Left
    
    return result
```

### Code Implementation (from BTZigzagLevelOrder.java)
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null)
            return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
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
            
            // Even index: add as-is (L→R), Odd index: reverse (R→L)
            if(result.size() % 2 == 0)
                result.add(currentLevel);
            else
                result.add(currentLevel.reversed());
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

Level 0 (even - L→R):
levelSize = 1
  - poll 3, add to currentLevel [3]
  - enqueue 9, 20
queue = [9, 20]
result.size() = 0 (even) → add [3]
result = [[3]]

Level 1 (odd - R→L):
levelSize = 2
  - poll 9, add to currentLevel [9]
  - poll 20, add to currentLevel [9, 20]
  - enqueue 15, 7
queue = [15, 7]
result.size() = 1 (odd) → reverse [9, 20] → [20, 9]
result = [[3], [20, 9]]

Level 2 (even - L→R):
levelSize = 2
  - poll 15, add to currentLevel [15]
  - poll 7, add to currentLevel [15, 7]
queue = []
result.size() = 2 (even) → add [15, 7]
result = [[3], [20, 9], [15, 7]]

Return [[3],[20,9],[15,7]]
```

---

## Approach 2: DFS with Depth-Based Insertion

### Core Idea:
Use DFS traversal while tracking depth. Based on depth parity:
- **Even depth**: Append node value to end of list (normal order)
- **Odd depth**: Insert node value at beginning of list (reverse order)

### Algorithm:
```
zigzagLevelOrder(root):
    result = []
    dfs(root, result, 0)
    return result

dfs(node, result, depth):
    if node is null: return
    
    if depth >= result.size():
        result.add(new ArrayList())
    
    if depth is even:
        result.get(depth).add(node.val)      // Append to end
    else:
        result.get(depth).add(0, node.val)   // Insert at beginning
    
    dfs(node.left, result, depth + 1)
    dfs(node.right, result, depth + 1)
```

### Code Implementation (from BTZigzagLevelOrder.java)
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsZigZag(root, result, 0);
        return result;
    }
    
    private void dfsZigZag(TreeNode node, List<List<Integer>> result, int depth) {
        if(node == null)
            return;
        
        // Create new list for new depth
        if(depth >= result.size())
            result.add(new ArrayList<>());
        
        // Even depth: append to end, Odd depth: insert at beginning
        if(depth % 2 == 0)
            result.get(depth).add(node.val);
        else
            result.get(depth).add(0, node.val); // Insert at index 0, shifts others right
        
        // Recurse on children
        dfsZigZag(node.left, result, depth + 1);
        dfsZigZag(node.right, result, depth + 1);
    }
}
```

### Visual Walkthrough

#### Example: Tree [3,9,20,null,null,15,7]
```
dfs(3, result, 0):
  depth=0, result.size()=0 → create list
  depth=0 (even) → add to end: result[0] = [3]
  dfs(9, result, 1):
    depth=1, result.size()=1 → create list
    depth=1 (odd) → insert at 0: result[1] = [9]
    dfs(null, result, 2) → return
    dfs(null, result, 2) → return
  dfs(20, result, 1):
    depth=1, result.size()=2 → already exists
    depth=1 (odd) → insert at 0: result[1] = [20, 9]
    dfs(15, result, 2):
      depth=2, result.size()=2 → create list
      depth=2 (even) → add to end: result[2] = [15]
      dfs(null, result, 3) → return
      dfs(null, result, 3) → return
    dfs(7, result, 2):
      depth=2 (even) → add to end: result[2] = [15, 7]
      dfs(null, result, 3) → return
      dfs(null, result, 3) → return

Result: [[3], [20, 9], [15, 7]]
```

---

## Comparison: BFS vs DFS for Zigzag

| Aspect | BFS with Reverse | DFS with Insert-at-Beginning |
|--------|------------------|------------------------------|
| Time Complexity | O(n) | O(n) but O(n²) worst case |
| Space Complexity | O(w) - max width | O(h) - height |
| Reversal Cost | O(w) per level | O(1) per insertion but shifts elements |
| Natural fit | Standard BFS + post-processing | Clever but less intuitive |
| Best for | Most cases | Deep trees |

### ⚠️ Note on DFS Approach
The `add(0, node.val)` operation is O(n) due to shifting elements. For a level with k nodes, this makes the DFS approach O(k²) per level in the worst case. The BFS approach with reversal is generally preferred.

---

## Complexity Analysis

### BFS Approach
- **Time**: O(n) - each node visited once, reversal is O(w) per level
- **Space**: O(w) - maximum width of tree

### DFS Approach
- **Time**: O(n) average, but O(n²) worst case due to insert-at-beginning
- **Space**: O(h) - recursion stack depth

---

## Edge Cases

1. **Empty tree**: root = null → return []
2. **Single node**: [1] → [[1]]
3. **Two levels**: [1,2,3] → [[1],[3,2]]
4. **Skewed tree**: Each level has one node, zigzag doesn't matter much

## Alternative: BFS with Deque

Use a `Deque` (double-ended queue) to add from left or right based on direction:

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
                    level.addLast(node.val);   // Add to end
                else
                    level.addFirst(node.val);  // Add to beginning
                
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            
            result.add(level);
            leftToRight = !leftToRight;  // Toggle direction
        }
        
        return result;
    }
}
```

---

## Pattern Recognition

This problem extends the **Level Order Traversal** pattern with:
1. **Direction tracking** using a boolean flag or depth parity
2. **Conditional insertion** based on direction
3. **Post-processing** (reversal) or **pre-processing** (insert at beginning)

## Related Problems
- [[Binary Tree Level Order Traversal]] (102) - Standard level order
- [[Binary Tree Level Order Traversal II]] (107) - Bottom-up
- [[Binary Tree Right Side View]] (199)
- [[N-ary Tree Level Order Traversal]] (429)

## Common Mistakes to Avoid

### ❌ Mistake 1: Wrong Parity Check
```java
// WRONG: Reverses even levels instead of odd
if(result.size() % 2 == 1)  // Reverses level 1, 3, 5...
    result.add(currentLevel.reversed());

// CORRECT: Reverse odd-indexed levels (1, 3, 5...)
if(result.size() % 2 == 1)
    result.add(currentLevel.reversed());
```

### ❌ Mistake 2: Using Wrong Index for DFS Insert
```java
// WRONG: Adds to end on odd levels
result.get(depth).add(node.val);  // Always appends

// CORRECT: Insert at beginning for odd levels
if(depth % 2 == 0)
    result.get(depth).add(node.val);      // End
else
    result.get(depth).add(0, node.val);   // Beginning
```

### ❌ Mistake 3: Not Handling Empty Tree
```java
// WRONG: Assumes root exists
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);  // NPE if root is null

// CORRECT:
if(root == null) return result;
```

---

## Practice Tips

1. **Master [[Binary Tree Level Order Traversal]] first** - this is a direct extension
2. **BFS with reversal is simpler** - prefer this in interviews
3. **Understand the DFS trick** - `add(0, val)` inserts at beginning
4. **Draw the tree** and trace through level by level
5. **Test with odd/even number of levels** to verify direction toggling

#LeetCode #Medium #Tree #BFS #LevelOrder #Zigzag #DFS
# Binary Tree Level Order Traversal II (Bottom-Up)

## Problem Statement
Given the `root` of a binary tree, return the bottom-up level order traversal of its nodes' values.

(i.e., from left to right, level by level, from left to right, starting from the leaf node)

## Problem
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/

## Example
```
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

    3          Level 2: [3]
   / \
   9  20        Level 1: [9,20]
      /  \
     15   7    Level 0: [15,7] ← Bottom level first!
```

## Key Insight
This is a direct extension of [[Binary Tree Level Order Traversal]] where we simply **reverse the final result**. The traversal itself is identical - we process level by level from top to bottom, then reverse to get bottom-up order.

---

## Approach: Reverse Standard Level Order

### Algorithm:
```
levelOrderBottom(root):
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
        
        result.add(currentLevel)
    
    return result.reversed()  // Reverse to get bottom-up order
```

### Code Implementation (from BTLevelOrderBottomTop.java)
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
            
            result.add(currentLevel);
        }
        
        // Key: Reverse the result to get bottom-up order
        return result.reversed();
    }
}
```

### Visual Walkthrough

#### Example: Tree [3,9,20,null,null,15,7]
```
Initial:
queue = [3]
result = []

Level 0:
levelSize = 1
  - poll 3, add to currentLevel [3]
  - enqueue 9, 20
queue = [9, 20]
result = [[3]]

Level 1:
levelSize = 2
  - poll 9, add to currentLevel [9]
  - poll 20, add to currentLevel [9, 20]
  - enqueue 15, 7
queue = [15, 7]
result = [[3], [9, 20]]

Level 2:
levelSize = 2
  - poll 15, add to currentLevel [15]
  - poll 7, add to currentLevel [15, 7]
queue = []
result = [[3], [9, 20], [15, 7]]

Reverse:
result = [[15, 7], [9, 20], [3]]

Return [[15,7],[9,20],[3]] ✅
```

---

## Alternative Approaches

### Approach 1: Add Levels to Front
Instead of reversing at the end, prepend each level to the result:

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
            
            // Add to beginning instead of end
            result.add(0, currentLevel);
        }
        
        return result;
    }
}
```

**Drawback**: `add(0, list)` is O(n) operation, making overall complexity O(n²).

### Approach 2: DFS with Depth Tracking
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        
        // Reverse at the end
        Collections.reverse(result);
        return result;
    }
    
    private void dfs(TreeNode node, int depth, List<List<Integer>> result) {
        if(node == null)
            return;
        
        if(depth == result.size())
            result.add(new ArrayList<>());
        
        result.get(depth).add(node.val);
        
        dfs(node.left, depth + 1, result);
        dfs(node.right, depth + 1, result);
    }
}
```

---

## Complexity Analysis

### Time Complexity: O(n)
- Each node visited exactly once
- Reversing the list takes O(h) where h is number of levels
- Total: O(n)

### Space Complexity
- **Auxiliary Space**: O(w) where w is maximum width of tree (queue storage)
- **Result Storage**: O(n) for all node values
- **Total**: O(n)

---

## Edge Cases

1. **Empty tree**: root = null → return []
2. **Single node**: [1] → [[1]]
3. **Two levels**: [1,2,3] → [[2,3],[1]]
4. **Left-skewed**: [1,2,3] with only left children → [[3],[2],[1]]
5. **Right-skewed**: [1,2,3] with only right children → [[3],[2],[1]]

---

## Comparison: Top-Down vs Bottom-Up

| Aspect | Level Order (Top-Down) | Level Order (Bottom-Up) |
|--------|------------------------|-------------------------|
| Problem | [[Binary Tree Level Order Traversal]] | This problem |
| Result | [[3],[9,20],[15,7]] | [[15,7],[9,20],[3]] |
| Approach | Add to end of list | Add to end + reverse OR add to front |
| Time | O(n) | O(n) with reverse / O(n²) with prepend |
| Space | O(n) | O(n) |

---

## Pattern Recognition

This problem uses:
1. **Standard BFS pattern** - Level order traversal
2. **Post-processing** - Reverse the result
3. **Queue-based level separation** - Using `levelSize` to track nodes per level

## Related Problems
- [[Binary Tree Level Order Traversal]] (102) - Top-down version
- [[Binary Tree Zigzag Level Order Traversal]] (103) - Alternate directions
- [[Average of Levels in Binary Tree]] (637) - Average per level
- [[Binary Tree Right Side View]] (199) - Last element of each level
- [[N-ary Tree Level Order Traversal]] (429) - Similar for n-ary trees

---

## Common Mistakes to Avoid

### ❌ Mistake 1: Modifying Original BFS Algorithm
```java
// WRONG: Trying to change BFS direction
// BFS naturally processes top-down. Changing it is complex!

// CORRECT: Use standard BFS + reverse
Queue processes level 0 → 1 → 2... naturally
result.reversed() gives bottom-up order
```

### ❌ Mistake 2: Using add(0, currentLevel)
```java
// WRONG: O(n) operation, makes overall O(n²)
result.add(0, currentLevel);  // Inserting at beginning is expensive

// CORRECT: Add to end + reverse at the end
result.add(currentLevel);
return result.reversed();
```

### ❌ Mistake 3: Not Handling Empty Tree
```java
// WRONG: Missing null check
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);  // NPE if root is null!

// CORRECT:
if(root == null) return result;
queue.offer(root);
```

---

## Practice Tips

1. **Remember**: This is essentially [[Binary Tree Level Order Traversal]] + reversal
2. **Code Reuse**: You can often reuse the top-down code and just change the final return
3. **Java 21+**: `list.reversed()` returns a view, doesn't copy - O(1) space for reversal
4. **Earlier Java versions**: Use `Collections.reverse(list)` - O(n) time
5. **Alternative**: Use `LinkedList` and `addFirst()` for each level (but still O(n²) overall)

---

## Example with Different Tree

### Input: root = [1,2,3,4,5]
```
    1           Level 2: [4,5]
   / \
  2   3        Level 1: [2,3]
 / \
4   5            Level 0: [1] ← Wait, wrong structure!
```

Actually, [1,2,3,4,5] in level order means:
```
    1
   / \
  2   3
 / \
4   5
```

Levels:
- Level 0: [1]
- Level 1: [2,3]
- Level 2: [4,5]

Bottom-up: [[4,5],[2,3],[1]]

---

#LeetCode #Medium #Tree #BFS #LevelOrder #Queue #Reverse
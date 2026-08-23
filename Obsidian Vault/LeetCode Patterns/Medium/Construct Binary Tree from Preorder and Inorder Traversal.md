# Construct Binary Tree from Preorder and Inorder Traversal

## Problem Statement
Given two integer arrays `preorder` and `inorder` where `preorder` is the preorder traversal of a binary tree and `inorder` is the inorder traversal of the same tree, construct and return the binary tree.

## Link
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

## Example
```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
```

## Key Observations

### Preorder Traversal: [Root, Left, Right]
- First element is always the **root**
- After root, left subtree nodes come before right subtree nodes

### Inorder Traversal: [Left, Root, Right]
- Root divides the array into left and right subtrees
- Elements to the **left** of root → left subtree
- Elements to the **right** of root → right subtree

### Combined Insight:
1. **Preorder** tells us which node is the root (first element)
2. **Inorder** tells us which nodes belong to left vs right subtree
3. We can recursively apply this logic to build the entire tree

---

## Algorithm

### Core Idea:
1. Take the first element from preorder → this is the **root**
2. Find root's index in inorder → splits into left and right subtrees
3. Recursively build left subtree with elements before root in inorder
4. Recursively build right subtree with elements after root in inorder

### Step-by-Step:
```
buildTree(preorder, inorder):
    1. Create HashMap: value → index for inorder array
    2. Return buildHelper(preorder, 0, inorder.length - 1)

buildHelper(preorder, left, right):
    if left > right: return null  // Base case: empty subtree
    
    rootVal = preorder[preorderIndex++]  // Get next root from preorder
    root = new TreeNode(rootVal)
    
    mid = inorderMap.get(rootVal)  // Find root in inorder
    
    root.left = buildHelper(preorder, left, mid - 1)   // Build left subtree
    root.right = buildHelper(preorder, mid + 1, right)  // Build right subtree
    
    return root
```

---

## Code Implementation

### Solution from ConstructBTFromPreIn.java
```java
class Solution {
    private int preOrderIndex = 0;
    private Map<Integer, Integer> inOrderMap = new HashMap<>();
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrderIndex = 0;
        inOrderMap.clear();
        
        // Build hashmap for O(1) lookup of root in inorder
        for(int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int preorder[], int left, int right) {
        // Base case: no nodes to process
        if(left > right)
            return null;
        
        // Get root value from preorder (increment index)
        int rootVal = preorder[preOrderIndex++];
        TreeNode root = new TreeNode(rootVal);
        
        // Find root position in inorder
        int inOrderIndex = inOrderMap.get(rootVal);
        
        // ⚠️ IMPORTANT: Must assign return values to children!
        root.left = buildTreeHelper(preorder, left, inOrderIndex - 1);   // Left subtree
        root.right = buildTreeHelper(preorder, inOrderIndex + 1, right); // Right subtree
        
        return root;
    }
}
```

### ⚠️ Common Bug: Not Assigning Children
```java
// ❌ WRONG: Return values are discarded!
private TreeNode buildTreeHelper(int preorder[], int left, int right) {
    if(left > right)
        return null;
    
    int rootVal = preorder[preOrderIndex++];
    TreeNode root = new TreeNode(rootVal);
    
    int inOrderIndex = inOrderMap.get(rootVal);
    
    // BUG: These create child nodes but don't link them to root!
    buildTreeHelper(preorder, left, inOrderIndex - 1);   // ⚠️ Return value ignored
    buildTreeHelper(preorder, inOrderIndex + 1, right);  // ⚠️ Return value ignored
    
    return root;  // Returns root with NO children attached!
}
```

**Result**: Only prints root value when doing preorder traversal!

### ✅ Correct: Assign Return Values
```java
// CORRECT: Link children to parent
root.left = buildTreeHelper(preorder, left, inOrderIndex - 1);
root.right = buildTreeHelper(preorder, inOrderIndex + 1, right);
```

---

## Visual Walkthrough

### Example: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

**Step 0: Build Inorder Map**
```
{9→0, 3→1, 15→2, 20→3, 7→4}
```

**Step 1: preOrderIndex=0, range=[0,4]**
```
preorder[0] = 3 → root = 3
inorder: [9, 3, 15, 20, 7]
            ^
            3 at index 1
Left subtree: inorder[0..0] = [9]
Right subtree: inorder[2..4] = [15, 20, 7]

        3
       / \
      ?   ?
```

**Step 2: preOrderIndex=1, range=[0,0] (left subtree)**
```
preorder[1] = 9 → root = 9
inorder: [9]
         ^
         9 at index 0
Left subtree: inorder[0..-1] → null (base case)
Right subtree: inorder[1..0] → null (base case)

        3
       / \
      9   ?
```

**Step 3: preOrderIndex=2, range=[2,4] (right subtree)**
```
preorder[2] = 20 → root = 20
inorder: [15, 20, 7]
              ^
              20 at index 3
Left subtree: inorder[2..2] = [15]
Right subtree: inorder[4..4] = [7]

        3
       / \
      9  20
        /  \
       ?    ?
```

**Step 4: preOrderIndex=3, range=[2,2] (left child of 20)**
```
preorder[3] = 15 → root = 15
No children (leaf node)

        3
       / \
      9  20
        /  \
      15   ?
```

**Step 5: preOrderIndex=4, range=[4,4] (right child of 20)**
```
preorder[4] = 7 → root = 7
No children (leaf node)

        3
       / \
      9  20
        /  \
      15   7
```

**Final Tree**: `[3,9,20,null,null,15,7]` ✅

---

## Complexity Analysis

### Time Complexity: O(n)
- Building the HashMap: O(n)
- Each node is visited exactly once: O(n)
- HashMap lookup is O(1)
- Total: O(n)

### Space Complexity: O(n)
- HashMap stores n elements: O(n)
- Recursion stack depth: O(h) where h is tree height
  - Best case (balanced): O(log n)
  - Worst case (skewed): O(n)
- Total: O(n)

---

## Why HashMap is Essential

### Without HashMap: O(n²) time
```java
// Linear search for root in inorder - O(n) per node
int mid = 0;
for(int i = left; i <= right; i++) {
    if(inorder[i] == rootVal) {
        mid = i;
        break;
    }
}
```

### With HashMap: O(n) time
```java
// Direct lookup - O(1) per node
int mid = inOrderMap.get(rootVal);
```

---

## Edge Cases

1. **Empty tree**: Both arrays are empty → return null
2. **Single node**: [1], [1] → single TreeNode
3. **Left-skewed**: [1,2,3], [3,2,1] → all left children
4. **Right-skewed**: [1,2,3], [1,2,3] → all right children
5. **Large tree**: Performance test with 5000 nodes

## Pattern Recognition

This problem uses:
1. **Divide and Conquer**: Split problem into left and right subtrees
2. **Recursion**: Build subtrees recursively
3. **HashMap Optimization**: O(1) lookup instead of O(n) search
4. **Index Manipulation**: Track preorder index and inorder ranges

## Related Problems
- [[Construct Binary Tree from Inorder and Postorder Traversal]] (106)
- [[Serialize and Deserialize Binary Tree]] (297)
- [[Binary Tree from Preorder Traversal]] (1008)

## Common Mistakes

### ❌ Mistake 1: Not Assigning Children to Parent
```java
// WRONG: Children created but not linked
buildTreeHelper(preorder, left, mid - 1);
buildTreeHelper(preorder, mid + 1, right);

// CORRECT: Must assign to root.left and root.right
root.left = buildTreeHelper(preorder, left, mid - 1);
root.right = buildTreeHelper(preorder, mid + 1, right);
```

### ❌ Mistake 2: Wrong Preorder Index Increment
```java
// WRONG: Incrementing before use
int rootVal = preorder[++preOrderIndex];

// CORRECT: Increment after use (post-increment)
int rootVal = preorder[preOrderIndex++];
```

### ❌ Mistake 3: Off-by-One in Subtree Ranges
```java
// WRONG: Including root in subtree
buildTreeHelper(preorder, left, mid);      // Left includes root!
buildTreeHelper(preorder, mid, right);     // Right includes root!

// CORRECT: Exclude root (mid) from both subtrees
buildTreeHelper(preorder, left, mid - 1);  // Left: [left, mid-1]
buildTreeHelper(preorder, mid + 1, right); // Right: [mid+1, right]
```

## Practice Tips

1. **Draw the tree step by step** - visualization is key
2. **Remember the arrays' properties**:
   - Preorder: [Root, Left, Right]
   - Inorder: [Left, Root, Right]
3. **Always assign children** to parent node
4. **Use HashMap** for O(1) lookup in inorder array
5. **Test with examples** like [1,2,3] with different inorder arrangements

#LeetCode #Medium #Tree #BinaryTree #DivideAndConquer #Recursion #HashMap
</contents>
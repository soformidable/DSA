import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int val){this.val = val;}
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class ConstructBTFromPreIn{

    private int preOrderIndex = 0;
    private Map<Integer,Integer> inOrderMap = new HashMap<Integer,Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        preOrderIndex = 0;
        inOrderMap.clear();
        
        for(int i = 0 ; i < inorder.length ; i++){
            inOrderMap.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int preorder[],int left,int right){
        
        // Base Case (no left subtrees or right subtrees to process)
        if(left > right)
            return null;

        int rootVal = preorder[preOrderIndex++]; // move the preOrderIndex forward
        TreeNode root = new TreeNode(rootVal);

        int inOrderIndex = inOrderMap.get(rootVal);

        root.left = buildTreeHelper(preorder, left, inOrderIndex-1); // create the left subtree
        root.right = buildTreeHelper(preorder, inOrderIndex + 1, right); // create the right subtree

        return root;    
    }

    public static void preOrderTraversal(TreeNode node){
        if (node == null)
            return;
        System.out.print(node.val + "\t");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public static void main(String[] args) {

        ConstructBTFromPreIn obj = new ConstructBTFromPreIn();

        TreeNode root = obj.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});

        preOrderTraversal(root);
    }
}
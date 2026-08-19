import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){this.val = val;}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class CanFormBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null,null);
        //return isValidBSTHelperStack(root);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max){

        if(node == null) return true;

        if((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;

        return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }


    private static boolean isValidBSTHelperStack(TreeNode node){

        if(node == null)
            return true;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode pre = null;

        while(node != null){
            while(node != null || !stack.isEmpty()){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();

            if(pre != null && node.val >= pre.val)
                return false;

            pre = node;
            node = node.right;
        }

        return true;
    }
}

import java.util.*;
import java.util.LinkedList;

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

public class KthSmallestInBST {

    private static int current = 0;
    private static int result = -1;


    public static int kthSmallest(TreeNode root, int k) {
        current  = k;

        DFS(root);

        return result;
    }   

    private static void DFS(TreeNode node){

        if(node.left != null) 
            DFS(node.left);
        current--;
        if(current == 0){
            result = node.val;
            return;
        }

        if(node.right != null)
            DFS(node.right);
    }

    public static int kthSmallestIterative(TreeNode root, int k){

        Stack<TreeNode> stack = new Stack<>();
        
        while(root!=null){
            stack.push(root);
            root = root.left;
        }

        while(k != 0){
            TreeNode node = stack.pop();
            k--;

            if(k == 0)
                return node.val;

            TreeNode right = node.right;
            
            while(right != null){
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(kthSmallest(root,1));
    }

    
}


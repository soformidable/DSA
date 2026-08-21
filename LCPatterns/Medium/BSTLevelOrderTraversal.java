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


class BSTLevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null)
            return result;

        
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){

            // Number of nodes to process in the current level 
            int levelSize = queue.size();

            List<Integer> currentLevel = new ArrayList<>();

            for(int i = 0 ;  i < levelSize ; i++){

                TreeNode node = queue.poll();

                currentLevel.add(node.val);

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }

            }
            result.add(currentLevel);

        }
        return result;
    }



    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        TreeNode root_1 = new TreeNode(1);
        root_1.right = new TreeNode(3);

        // TreeNode root = new TreeNode();

        System.out.println(levelOrder(root_1));
    }
}

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

public class BTPathSum {


    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
     
        List<List<Integer>> result = new ArrayList<>();

        getSumHelper(root, result, new ArrayList<Integer>(), targetSum);

        return result;
    }

    private static void getSumHelper(TreeNode node, List<List<Integer>> result, List<Integer> currentPath, int remainingSum){

        // end of the branch
        if(node == null)
            return;

        currentPath.add(node.val);

        //remainingSum is decreasing sum
        if(node.left == null && node.right == null && node.val == remainingSum){
            result.add(new ArrayList<Integer>(currentPath));
        }
        else{
            //explore left 
            getSumHelper(node.left,result, currentPath, remainingSum - node.val);

            //explore right
            getSumHelper(node.right,result, currentPath, remainingSum - node.val);
        }

        // Backtrack to remove the current Node
        currentPath.remove(currentPath.size() - 1);

    }
    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13 );
        root.right.right = new TreeNode(4 );
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1 );

        System.out.println(pathSum(root, 22));
    }
}

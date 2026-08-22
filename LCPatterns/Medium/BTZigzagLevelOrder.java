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

public class BTZigzagLevelOrder {
    public static List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();

        DFSZigZag(root, result, 0);

        return result;
    }

    private static void DFSZigZag(TreeNode node, List<List<Integer>> result, int depth){
        if(node == null)
            return;

        if(depth >= result.size())
            result.add(new ArrayList<>());

        if(depth % 2 == 0)
            result.get(depth).add(node.val);
        else
            result.get(depth).add(0,node.val); // adds the node value to the left most index and shifts the already added values to the right
            

        DFSZigZag(node.left, result, depth + 1);
        DFSZigZag(node.right, result, depth + 1);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

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
            if(result.size()%2 == 0)
                result.add(currentLevel);
            else
                result.add(currentLevel.reversed());

        }
        return result;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(10);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        TreeNode root_1 = new TreeNode(1);
        root_1.left = new TreeNode(2);

        System.out.println(zigzagLevelOrder(root));
        System.out.println(zigzagLevelOrderDFS(root));
    }
}

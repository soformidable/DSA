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

public class BuildBinarySearchTree {


    public static TreeNode build(int[] preorder){
        int index[] = {0};
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, index);

    }

    private static TreeNode build(int preorder[], int min, int max, int[] index){

        if(index[0] >= preorder.length)
            return null;

        int val = preorder[index[0]];

        if(val < min || val > max)
            return null;

        index[0]++;

        TreeNode node= new TreeNode(val);

        node.left = build(preorder, min, val,index);
        node.right = build(preorder, val, max,index);

        return node;
    }

    public static void inorderTraversal(TreeNode root){
        if(root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    public static void preTraversal(TreeNode root){
        if(root == null) return;
        System.out.print(root.val + " ");
        preTraversal(root.left);
        preTraversal(root.right);
    }

    public static void postTraversal(TreeNode root){
        if(root == null) return;
        postTraversal(root.left);
        postTraversal(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        int preorder[] = new int[]{2, 1, 3};
        TreeNode root = build(preorder);

        System.out.println("\nIn Order");
        inorderTraversal(root);

        System.out.println("\nPre Order");
        preTraversal(root);

        System.out.println("\nPost Order");
        postTraversal(root);


        preorder = new int[] {5,1,4,3,6};

        root = build(preorder);
        System.out.println("\nIn Order");
        inorderTraversal(root);

        System.out.println("\nPre Order");
        preTraversal(root);

        System.out.println("\nPost Order");
        postTraversal(root);
    }

}

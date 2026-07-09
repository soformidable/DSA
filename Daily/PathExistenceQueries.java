import java.util.Arrays;

/*
Since nums is already sorted, we don't need to check every pair of nodes.

If the difference between two adjacent elements is greater than maxDiff, then no element on the left can connect to any element on the right. This is because all elements to the right are even larger, so their difference will also be greater than maxDiff.

Therefore, every such gap splits the array into a new connected component.

Once we know which component every index belongs to, answering a query becomes very easy:

If both indices are in the same component, there is a path.

Otherwise, there isn't.

If both the indices in queries are in the same component then they will have the same value and there is a path betwen them.

component[u] == component[v]
*/



public class PathExistenceQueries{
    public static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {



        int[] component = new int[n];

        int val = 0;
        component[0] = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                val++;
            }
            component[i] = val;
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = component[u] == component[v];
        }

        return ans;
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(pathExistenceQueries(4, new int[]{2,5,6,8}, 2, new int[][]{{0,1},{0,2},{1,3},{2,3}})));
    }
}
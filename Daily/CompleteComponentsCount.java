import java.util.*;

public class CompleteComponentsCount{

   public static int countCompleteComponents(int n, int[][] edges) {
        // Build adjacency list for all nodes
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        
        // Find each connected component using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);

                // Check if this component is complete:
                // In a complete component with k nodes, every node has degree k-1
                int size = component.size();
                boolean isComplete = true;
                for (int node : component) {
                    if (adj.get(node).size() != size - 1) {
                        isComplete = false;
                        break;
                    }
                }
                if (isComplete) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }

    public static void main(String args[]){
        System.out.println(countCompleteComponents(6, new int[][]{{0,1},{0,2},{1,2},{3,4},{3,5}}));
    }
}

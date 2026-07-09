# Graph Path Existence (Value Difference Constraint)

## Problem Statement
You are given:
- `n`: number of nodes (0-indexed)
- `nums[]`: an array of values for each node
- `edges[][]`: a list of undirected edges as pairs `[u, v]`
- `maxDiff`: maximum allowed absolute difference between values of nodes for an edge to be considered valid

Build an adjacency list representation of the graph consisting only of valid edges.  
A valid edge exists between `u` and `v` if `Math.abs(nums[u] - nums[v]) <= maxDiff`.

After building, you can use this adjacency list to answer queries like "does a path exist between two nodes?" using DFS/BFS.

## Example
```java
int n = 4;
int[] nums = {2, 5, 6, 8};
int[][] edges = {{0,1}, {0,2}, {1,3}, {2,3}};
int maxDiff = 2;
```

Edge validation:
- `0-1`: `|2-5|=3 > 2` → ❌
- `0-2`: `|2-6|=4 > 2` → ❌
- `1-3`: `|5-8|=3 > 2` → ❌
- `2-3`: `|6-8|=2 ≤ 2` → ✅

Resulting adjacency list:
- `0: []`
- `1: []`
- `2: [3]`
- `3: [2]`

## Java Implementation
```java
import java.util.*;

public class GraphPathExistence {
    public List<List<Integer>> buildGraph(int n, int[] nums, int[][] edges, int maxDiff) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (Math.abs(nums[u] - nums[v]) <= maxDiff) {
                adj.get(u).add(v);
                adj.get(v).add(u);   // undirected graph
            }
        }
        return adj;
    }

    // Optional: check if path exists using DFS
    public boolean hasPath(List<List<Integer>> adj, int source, int dest) {
        boolean[] visited = new boolean[adj.size()];
        return dfs(adj, source, dest, visited);
    }

    private boolean dfs(List<List<Integer>> adj, int current, int dest, boolean[] visited) {
        if (current == dest) return true;
        visited[current] = true;
        for (int neighbor : adj.get(current)) {
            if (!visited[neighbor] && dfs(adj, neighbor, dest, visited)) {
                return true;
            }
        }
        return false;
    }
}
```

## Complexity
- Time: `O(E)` to build the graph, where `E` is the number of edges.
- Space: `O(V + E)` for adjacency list.

## Related Problems
- LeetCode 1971 (Find if Path Exists in Graph)
- LeetCode 2578 (With condition on values)
- Any graph connectivity problem with custom edge conditions.
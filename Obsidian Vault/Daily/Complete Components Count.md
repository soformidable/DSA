# Complete Components Count

## Problem Statement
You are given an integer `n` representing the number of vertices in an undirected graph, and a 2D array `edges` where each `edges[i] = [u, v]` represents an undirected edge between vertex `u` and vertex `v`.

A **connected component** is a set of vertices where there exists a path between every pair of vertices in that set.

A connected component is said to be **complete** if there is an edge between every pair of vertices in that component. In other words, a complete component of size `k` must have exactly `k * (k - 1) / 2` edges (each vertex is directly connected to every other vertex).

Return the number of **complete connected components** in the graph.

## Example
```java
int n = 6;
int[][] edges = {{0,1}, {0,2}, {1,2}, {3,4}, {3,5}};
```

Visual representation:
```
Component 1:        Component 2:
    0                 3
   / \               / \
  1---2             4   5
```

Graph has 2 components:
- `{0, 1, 2}`: size = 3, each vertex has degree 2 (edges: `0-1`, `0-2`, `1-2`) → ✅ complete
- `{3, 4, 5}`: size = 3, vertex 4 and 5 have degree 1 (missing edge `4-5`) → ❌ not complete

**Output: 1**

## Intuition

The problem boils down to two steps:
1. **Find all connected components** – Use DFS (or BFS) to traverse the graph and group vertices into their respective connected components.
2. **Check if each component is complete** – For a component of size `k`, every vertex must be connected to the other `k - 1` vertices. This means each vertex in the component must have degree exactly `k - 1`.

### Why degree check works
In a complete graph of `k` vertices:
- Every vertex connects to `k - 1` others
- The degree of every vertex is `k - 1`
- Total edges = `k * (k - 1) / 2`

So if we find a component where every vertex has degree `k - 1` (where `k` is the component size), that component is guaranteed to be complete.

## Approach

1. **Build adjacency list** for all `n` vertices (even isolated ones).
2. **Initialize visited array** of size `n` to track which vertices have been explored.
3. **Iterate** through all vertices. For each unvisited vertex:
   - Start a DFS to collect all vertices in its connected component.
   - After DFS completes, check the completeness condition:
     - Let `size` = number of vertices in this component.
     - For each vertex in the component, verify `adj.get(vertex).size() == size - 1`.
     - If all satisfy, increment the count.
4. **Return** the count.

## Java Implementation
```java
import java.util.*;

public class CompleteComponentsCount {

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

    private static void dfs(int node, List<List<Integer>> adj,
                            boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(countCompleteComponents(6,
            new int[][]{{0,1}, {0,2}, {1,2}, {3,4}, {3,5}})); // Output: 1
    }
}
```

## Dry Run

Input: `n = 6`, `edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]`

**Adjacency list:**
```
0: [1, 2]
1: [0, 2]
2: [0, 1]
3: [4, 5]
4: [3]
5: [3]
```

**DFS Traversal:**
- Vertex 0 unvisited → DFS: collect `[0, 1, 2]`
  - size = 3
  - 0: degree 2 ✅, 1: degree 2 ✅, 2: degree 2 ✅
  - count = 1
- Vertex 3 unvisited → DFS: collect `[3, 4, 5]`
  - size = 3
  - 3: degree 2 ✅, 4: degree 1 ❌
  - Not complete

**Result: 1**

## Complexity
- **Time:** `O(V + E)` – Each vertex and edge is visited once during DFS. Checking completeness takes `O(V)` total across all components.
- **Space:** `O(V + E)` for the adjacency list, visited array, and recursion stack.

## Edge Cases
- **Isolated vertices:** A single vertex with no edges forms a component of size 1. A complete graph of size 1 has 0 edges → `degree = 0 = 1 - 1` → counts as complete.
- **Disconnected graph:** Each disconnected component is evaluated independently.
- **No edges:** Every vertex is isolated. A graph with `n` isolated vertices has `n` complete components (each of size 1).

## Related Problems
- Count Complete Components (LeetCode 2685)
- Number of Connected Components in an Undirected Graph (LeetCode 323)
- Find if Path Exists in Graph (LeetCode 1971)
- Clone Graph (LeetCode 133)
# DFS on Undirected Graph (Connected Components & Completeness Check)

## Overview
DFS (Depth-First Search) can be used on an undirected graph represented as an adjacency list to:
- Discover all connected components
- Traverse all vertices reachable from a starting vertex
- Collect vertices belonging to each component for further processing

## Building the Adjacency List

### What is an Adjacency List?
An adjacency list is a way to represent a graph where each vertex maps to a list of its neighboring vertices. It is memory-efficient for sparse graphs.

### Implementation in Java
There are two common ways to build an adjacency list:

#### Method 1: Using `ArrayList` of `ArrayList` (Preferred for indexed vertices 0 to n-1)
```java
// Initialize adjacency list for n vertices
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < n; i++) {
    adj.add(new ArrayList<>());
}

// Populate from edges
for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];
    adj.get(u).add(v);  // u → v
    adj.get(v).add(u);  // v → u (undirected)
}
```

#### Method 2: Using `HashMap` with `computeIfAbsent` (When vertex IDs are not sequential)
```java
Map<Integer, List<Integer>> graph = new HashMap<>();

for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];
    graph.computeIfAbsent(u, key -> new ArrayList<>()).add(v);
    graph.computeIfAbsent(v, key -> new ArrayList<>()).add(u);
}
```

`computeIfAbsent(key, mappingFunction)`:
- If the key exists → returns its current value
- If the key does NOT exist → computes a new value using the function, inserts it, and returns the new value

## DFS to Find Connected Components

### What is a Connected Component?
A connected component is a maximal set of vertices where there exists a path between every pair. In an undirected graph, the graph can be partitioned into one or more connected components.

### DFS Implementation for Component Discovery

```java
boolean[] visited = new boolean[n];

for (int i = 0; i < n; i++) {
    if (!visited[i]) {
        List<Integer> component = new ArrayList<>();
        dfs(i, adj, visited, component);
        
        // Process the component here
        System.out.println("Component: " + component);
    }
}
```

```java
private static void dfs(int node, List<List<Integer>> adj,
                        boolean[] visited, List<Integer> component) {
    visited[node] = true;            // Mark current node as visited
    component.add(node);             // Add to current component
    
    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            dfs(neighbor, adj, visited, component);  // Recurse deeper
        }
    }
}
```

### How DFS Traverses a Component (Step-by-Step)

Consider this graph:
```
    0
   / \
  1---2
```
Edges: `[[0,1], [0,2], [1,2]]`

Adjacency list:
```
0: [1, 2]
1: [0, 2]
2: [0, 1]
```

**DFS call stack starting from vertex 0:**

| Step | Call | Action | visited | component |
|------|------|--------|---------|-----------|
| 1 | `dfs(0)` | Mark 0 visited, add 0 | `[T,F,F]` | `[0]` |
| 2 | Loop neighbor 1: `dfs(1)` | Mark 1 visited, add 1 | `[T,T,F]` | `[0, 1]` |
| 3 | Loop neighbor 0: visited, skip → neighbor 2: `dfs(2)` | Mark 2 visited, add 2 | `[T,T,T]` | `[0, 1, 2]` |
| 4 | All neighbors of 2 visited → return | | | |
| 5 | All neighbors of 1 visited → return | | | |
| 6 | Loop neighbor 2: visited, skip → return | | | |

Final component: `[0, 1, 2]`

## Using Component Data to Check Completeness

Once we have all vertices in a component, we can verify if the component is **complete** (i.e., a clique).

### Condition
In a complete component with size `k`:
- Every vertex must be connected to `k - 1` other vertices
- Therefore `degree(v) == k - 1` for every vertex `v` in the component

### Code
```java
int size = component.size();
boolean isComplete = true;

for (int node : component) {
    if (adj.get(node).size() != size - 1) {  // degree != k-1
        isComplete = false;
        break;
    }
}

if (isComplete) {
    count++;
}
```

### Why Checking Degrees is Sufficient
- If a component has `k` vertices and every vertex has degree `k - 1`, it means every vertex is connected to every other vertex in that component.
- The total number of edges in the component would be `k * (k - 1) / 2`.
- There cannot be edges to vertices outside the component (otherwise the component would be larger).
- Therefore the component is complete.

## Complete Traversal Pattern

```java
public static int solve(int n, int[][] edges) {
    // Step 1: Build adjacency list
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
        int u = edge[0], v = edge[1];
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Step 2: Initialize visited array
    boolean[] visited = new boolean[n];
    int result = 0;

    // Step 3: Iterate all vertices, discover components via DFS
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            List<Integer> component = new ArrayList<>();
            dfs(i, adj, visited, component);

            // Step 4: Process component (custom logic here)
            // ...
        }
    }

    return result;
}
```

## Key Points to Remember

1. **Always initialize the adjacency list for ALL `n` vertices**, even if some have no edges. This ensures isolated vertices are not skipped.
2. **DFS uses recursion** – be mindful of stack overflow for very deep graphs. For extremely large graphs, use BFS with a `Queue` instead.
3. **Visited array prevents revisiting** – ensures each vertex is processed exactly once, giving `O(V + E)` time complexity.
4. **Undirected edges must be added both ways** (`u → v` and `v → u`).
5. **Component collection is optional** – if you only need to count components, you can skip collecting vertices and just use a counter.

## BFS Alternative (Iterative)

```java
private static void bfs(int start, List<List<Integer>> adj,
                        boolean[] visited, List<Integer> component) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited[start] = true;
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        component.add(node);
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

## Complexity
- **Time:** `O(V + E)` – Each vertex is visited once, each edge is examined twice (once from each endpoint).
- **Space:** `O(V + E)` for adjacency list + `O(V)` for visited array and recursion stack.

## Related Notes
- Adjacency List in ArrayList
- BFS using Queue
- Graph Path Existence (Value Difference Constraint)
- Complete Components Count
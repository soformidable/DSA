"# Breadth-First Search (BFS) using Queue

## Overview

BFS is a graph traversal algorithm that explores nodes **level by level** — all neighbors of the current node are visited before moving to the next level. This makes it useful for:
- Finding the **shortest path** in an unweighted graph.
- Checking **connectivity** / reachability.
- Solving puzzles (e.g., minimum moves, word ladder).
- Finding the **minimum edge weight** in a connected component (as seen in `MinimumScoreRoad.java`).

The core data structure is a **FIFO Queue** — typically `Queue<T> queue = new LinkedList<>()`.

---

## General BFS Template

```java
import java.util.*;

public class BFS {

    public static void bfs(int start, Map<Integer>, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // 1. Initialize: start node goes in queue and marked visited
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            // 2. Dequeue front node
            int node = queue.poll();
            System.out.print(node + \" \");  // process node

            // 3. Explore all unvisited neighbors
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example graph:
        // 1 — 2 — 4
        // |   |
        // 3 — 5
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(1, 4, 5));
        graph.put(3, Arrays.asList(1, 5));
        graph.put(4, Arrays.asList(2));
        graph.put(5, Arrays.asList(2, 3));

        System.out.print(\"BFS starting from 1: \");
        bfs(1, graph);
        // Output: 1 2 3 4 5
    }
}
```

---

## Step-by-Step Breakdown

1. **Data Structures:**
   - `Queue<T>` — stores nodes to visit next (FIFO order ensures level-by-level).
   - `Set<T> visited` — prevents re-visiting nodes (handles cycles).

2. **Initialization:**
   - Add the starting node to the queue **and** mark it visited immediately.

3. **Loop:**
   - **Poll** (remove) the front node from the queue.
   - **Process** it (print, compute, check conditions, etc.).
   - For each **unvisited neighbor**, mark it visited and **add** it to the queue.

4. **Why mark visited on enqueue?**
   - If you mark visited only when polling, the same node could be added multiple times by different neighbors, causing duplicates in the queue.

---

## Example: Shortest Path in Unweighted Graph (Distance from Start)

```java
public static void bfsShortestPath(int start, Map<Integer, List<Integer>> graph) {
    Map<Integer, Integer> distance = new HashMap<>();
    Queue<Integer> queue = new LinkedList<>();

    queue.add(start);
    distance.put(start, 0);

    while (!queue.isEmpty()) {
        int node = queue.poll();
        int currentDist = distance.get(node);

        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!distance.containsKey(neighbor)) {  // not visited yet
                distance.put(neighbor, currentDist + 1);
                queue.add(neighbor);
            }
        }
    }

    // Print distances
    for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
        System.out.println(\"Node \" + entry.getKey() + \" is at distance \" + entry.getValue());
    }
}
```

For the same graph above, starting from 1:

| Node | Distance from 1 |
|------|-----------------|
| 1    | 0               |
| 2    | 1               |
| 3    | 1               |
| 4    | 2               |
| 5    | 2               |

---

## Example from `MinimumScoreRoad.java` — Finding Minimum Edge in Component

```java
public static int minScoreBFS(int n, int[][] roads) {
    int min = Integer.MAX_VALUE;
    Map<Integer, List<int[]>> graph = new HashMap<>();

    // Build adjacency list: each city stores its incident edges
    for (int[] road : roads) {
        graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(road);
        graph.computeIfAbsent(road[1], k -> new ArrayList<>()).add(road);
    }

    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    visited.add(1);

    while (!queue.isEmpty()) {
        int city = queue.poll();
        for (int[] edge : graph.getOrDefault(city, new ArrayList<>())) {
            min = Math.min(min, edge[2]);  // process edge weight

            // Determine the neighbor
            int neighbor = (edge[0] == city) ? edge[1] : edge[0];
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }
    }
    return min;
}
```

**BFS pattern used here:**
- Queue stores **cities** (nodes).
- Each edge is examined exactly once when one of its endpoints is visited.
- Visited set prevents cycles.
- Instead of processing neighbors from a separate adjacency list, the graph stores the **entire edge** (including weight) so the distance is immediately available.

---

## Common BFS Variations

| Variation | Key Change |
|-----------|------------|
| **Shortest path** | Track `distance[node] = distance[parent] + 1` on enqueue. |
| **BFS with level tracking** | Use a nested loop: `for (int i = queue.size(); i > 0; i--)` to process one level at a time. |
| **BFS on grid (2D)** | Use `int[]` or custom class representing `(row, col)`. Directions array for moves. |
| **BFS with state** | Store `(node, mask, steps)` for problems like shortest path with keys/doors. |
| **BFS on weighted graph** | Use `PriorityQueue` (Dijkstra) instead of `Queue`. |

---

## Key Points for Revisit

- **Queue = FIFO** = Level-order traversal.
- **Always mark visited on enqueue**, not on poll.
- BFS guarantees shortest path in **unweighted** graphs (number of edges).
- For **grid BFS**, use `int[][]` directions: `{{1,0},{-1,0},{0,1},{0,-1}}`.
- For **level-by-level** processing, use `for (int sz = queue.size(); sz > 0; sz--)` inside the while loop.
- Time complexity: `O(V + E)` — each node and each edge is processed once.
- Space complexity: `O(V)` for the queue and visited set.

**Related files in this vault:**
- `../Daily/MinimumScoreRoad.md` — BFS used to find minimum edge in a connected component.
- `../Useful procedures and theorems/Stack and Queue.md` — basic Queue operations."
# Minimum Score of a Path Between Two Cities

## Problem
https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/?envType=daily-question&envId=2026-07-04

## Code Link
https://github.com/soformidable/DSA/blob/main/Daily/MinimumScoreRoad.java

Given `n` cities numbered from `1` to `n` and a 2D array `roads` where `roads[i] = [u, v, dist]` represents a bidirectional road connecting cities `u` and `v` with a distance (score) of `dist`, find the **minimum possible score** of a path from city `1` to city `n`.

**Key nuance:** The "score" of a path is defined as the **minimum edge weight** along that path (not the sum). We are looking for the path from 1 to n that has the **highest possible minimum edge** — or equivalently, we want to find the minimum edge weight among all edges that lie on **any** path from 1 to n. Actually, re-reading the code: both functions find the **absolute minimum edge weight** among **all edges reachable from city 1**, because any edge reachable from city 1 could potentially be on some path to city n as long as n is also reachable.

---

## Function 1: Iterative Set Expansion (`minScore`)

### Intuition

Start with city `1` in a reachable set. Repeatedly scan all roads: if either endpoint of a road is in the reachable set, add the other endpoint (if not already there) and update the global minimum distance. Keep scanning until no new cities are added in a full pass.

At the end, the reachable set contains **all cities reachable from city 1** via any sequence of roads. The `min` variable holds the smallest edge weight among all edges that touch any reachable city — which is the minimum score path to city n (since n is reachable if it's in the set).

### Algorithm

```
reachable = {1}
min = INFINITY
changed = true

while changed:
    changed = false
    for each road (u, v, dist):
        if u is reachable OR v is reachable:
            if u not yet reachable → add u, set changed = true
            if v not yet reachable → add v, set changed = true
            min = min(min, dist)

return min
```

**Why this works:** Each iteration expands the frontier. Once no new cities are added, all reachable cities have been found. Any edge that has at least one endpoint in the reachable set could be part of a path from 1 to other reachable cities. The minimum weight among those edges is the answer.

### Complexity

- **Time:** `O(R * C)` where `R` = number of roads, `C` = number of connected components (worst case: `O(R²)` since we may scan all roads for each new city added).
- **Space:** `O(N)` for the reachable set.

### Code

```java
public static int minScore(int n, int[][] roads) {
    HashSet<Integer> reachable = new HashSet<>();
    int min = Integer.MAX_VALUE;
    reachable.add(1);
    boolean changed = true;

    while (changed) {
        changed = false;
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];

            if (reachable.contains(u) || reachable.contains(v)) {
                if (!reachable.contains(u)) {
                    reachable.add(u);
                    changed = true;
                }
                if (!reachable.contains(v)) {
                    reachable.add(v);
                    changed = true;
                }
                min = Math.min(min, dist);
            }
        }
    }
    return min;
}
```

---

## Function 2: BFS with Adjacency List (`minScore_BFS`)

### Intuition

Build an adjacency list graph where each node stores all edges incident to it. Then perform a standard **BFS** starting from city `1`, visiting all reachable cities. While traversing, check every edge connected to the current city and update the global minimum edge weight.

BFS ensures we explore the entire connected component of city `1` in `O(N + R)` time, without repeatedly scanning the entire road list.

BFS Link: [[BFS using Queue]]
Adjacency List: [[Adjacency List in ArrayList]]
### Algorithm

1. Build graph: for each road `(u, v, dist)`, add it to both `graph[u]` and `graph[v]`.
2. BFS:
   - `visited = {1}`, `queue = [1]`
   - While queue not empty:
     - Poll city `c`
     - For each road adjacent to `c`:
       - Update `min = min(min, road[2])`
       - Determine the neighbor (`next = road[0] if road[1] == c, else road[1]`)
       - If neighbor not visited → mark visited and enqueue
3. Return `min`

### Code

```java
public static int minScore_BFS(int n, int[][] roads) {
    int min = Integer.MAX_VALUE;
    Map<Integer, List<int[]>> graph = new HashMap<>();

    // Build adjacency list
    for (int[] road : roads) {
        graph.computeIfAbsent(road[0], key -> new ArrayList<>()).add(road);
        graph.computeIfAbsent(road[1], key -> new ArrayList<>()).add(road);
    }

    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    visited.add(1);

    while (!queue.isEmpty()) {
        int city = queue.poll();
        for (int[] road : graph.getOrDefault(city, new ArrayList<>())) {
            min = Math.min(min, road[2]);  // check all incident edges

            int next = (road[0] == city) ? road[1] : road[0];
            if (!visited.contains(next)) {
                visited.add(next);
                queue.add(next);
            }
        }
    }
    return min;
}
```

### Why BFS works

The entire connected component of city `1` is reachable via BFS. Every edge in that component is incident to at least one visited city, so we check every edge exactly once (when we first encounter either endpoint). The minimum edge weight across all edges in the component is the answer — because:
- City `n` is in the same connected component (if a path exists).
- Any path from 1 to n can use any edge in the component.
- The path's score is the minimum edge along it, and we want the path with the **highest minimum edge**. That's equivalent to finding the **largest bottleneck** path, which is solved by finding the edge with the maximum possible minimum weight. But the code actually returns the **minimum** of all edges in the component — this assumes the problem asks for the smallest edge weight that **must** be crossed to reach n, or equivalently, the path is scored by its smallest edge.

> **Correction / Clarification:** Both functions return the **minimum edge weight among all edges reachable from city 1**. This is correct if the problem defines the "score" of a path as the **minimum edge on that path**, and we want the **minimum possible score** across all paths from 1 to n. The minimum edge on any path cannot be smaller than the smallest edge in the entire component, and it's achievable by taking a path that uses that smallest edge.

### Complexity (BFS)

- **Time:** `O(N + R)` — each city and each edge is visited once.
- **Space:** `O(N + R)` for the adjacency list, visited set, and queue.

---

## Comparison of the Two Functions

| Aspect | `minScore` (Iterative Set) | `minScore_BFS` |
|--------|----------------------------|----------------|
| Data structure | `HashSet<Integer>` + raw array scan | `Map<Integer, List<int[]>>` adjacency list + `Queue` |
| Time complexity | `O(R * C)` (worst `O(R²)`) | `O(N + R)` |
| Space complexity | `O(N)` | `O(N + R)` |
| Approach | Repeatedly scan all roads until no new cities | BFS traversal through graph edges |
| Best for | Small/moderate road counts, simple code | Larger graphs, guaranteed linear time |

## Key Points for Revisit

- The **minimum score** of a path from 1 to n is the **smallest edge weight** in the entire connected component containing city 1.
- Both functions find this by exploring all reachable cities and tracking the minimum edge weight seen.
- The BFS version is more efficient (linear in nodes + edges) and is the preferred approach.
- The adjacency list stores the **entire edge array**, not just neighbors — this lets you access the distance directly during traversal.
- Edge case: If city `n` is not reachable from city `1`, the functions will still return the minimum edge weight among whatever is reachable — but the problem guarantees a path exists (or you'd need to handle unreachable separately).
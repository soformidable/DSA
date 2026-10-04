# Course Schedule

**Problem Statement:**
There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

Return `true` if you can finish all courses. Otherwise, return `false`.

---

## Problem:
https://leetcode.com/problems/course-schedule/description/

## Key Insight

This is a classic **cycle detection in a directed graph** problem:

1. **Graph Modeling:** Each course is a node. A prerequisite pair `[a, b]` becomes a directed edge `b → a` ("b must come before a").

2. **Core Question:** You can finish all courses **if and only if** the prerequisite graph has **no directed cycle**. A cycle means a circular dependency (e.g., "take A before B, and B before A"), making it impossible to schedule anything.

3. **DFS 3-Color States:** Track each node with a state:
   - `0` = **WHITE** (unvisited)
   - `1` = **GRAY** (in the current recursion path) → encountering it again = **cycle**
   - `2` = **BLACK** (fully processed / safe) → prune, no need to revisit

4. **Back-edge Detection:** During DFS, if we reach a GRAY node, we've found a back-edge — a cycle exists.

---

## Solution Approaches

### 1. DFS with 3-Color State Tracking (List-based adjacency)

**Algorithm:**
1. Build an adjacency list where `adj[b]` holds all courses that depend on `b`
2. Maintain a `state[]` array initialized to `0` (unvisited)
3. Run DFS from every unvisited node
4. If DFS ever revisits a **GRAY (1)** node → cycle detected → return `false`
5. After exploring all neighbors, mark the node **BLACK (2)** (it's safe)
6. If no cycles are found after checking all nodes → return `true`

**Why this works:**
- Marking nodes GRAY before recursion and BLACK after ensures we only detect true back-edges (cycles), not cross-edges between independent branches
- BLACK nodes are fully explored and provably safe, so we skip them (memoization effect)

---

## Code Implementation: DFS (List-based)

```java
public static boolean canFinish(int numCourses, int[][] prerequisites) {

    // Build adjacency list: index = course, value = list of courses that depend on it
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    // Edge direction: prerequisite -> course  (b must be taken before a)
    for (int[] courses : prerequisites) {
        int course = courses[0];
        int prerequesite = courses[1];
        adj.get(prerequesite).add(course);
    }

    // state[]: 0 = unvisited (WHITE), 1 = in-progress (GRAY), 2 = safe (BLACK)
    int state[] = new int[numCourses];

    // Check every node, since the graph may be disconnected
    for (int i = 0; i < numCourses; i++) {
        if (hasCycle(adj, state, i))
            return false;   // cycle found -> impossible to finish all courses
    }

    return true;   // no cycles -> all courses can be completed
}

// 1 == GRAY --> Cycle ; 2 == BLACK --> SAFE
private static boolean hasCycle(List<List<Integer>> adj, int[] state, int node) {

    if (state[node] == 2) return false;  // already proven safe (BLACK) -> prune
    if (state[node] == 1) return true;   // revisiting in-progress node (GRAY) -> cycle!

    state[node] = 1;  // mark as GRAY (currently in recursion stack)

    for (int neighbour : adj.get(node)) {
        if (hasCycle(adj, state, neighbour)) {
            return true;  // cycle found somewhere downstream
        }
    }

    state[node] = 2;  // all descendants safe -> mark BLACK
    return false;
}
```

---

### 2. DFS with HashMap-based adjacency

Same algorithm, but uses `HashMap` + `computeIfAbsent` — convenient when node IDs are sparse or unknown upfront.

**Algorithm:**
1. Build a `Map<Integer, List<Integer>>` adjacency map using `computeIfAbsent`
2. Track states in a `Map<Integer, Integer>` with `getOrDefault(node, 0)`
3. Handle missing neighbors gracefully with `getOrDefault(node, Collections.emptyList())`
4. Rest of the cycle-detection logic is identical

---

## Code Implementation: DFS (HashMap-based)

```java
public static boolean canFinishMap(int numCourses, int[][] prerequisites) {

    // Adjacency map: prerequisite -> list of dependent courses
    Map<Integer, List<Integer>> adj = new HashMap<>();
    for (int[] courses : prerequisites) {
        // create the bucket only when a node first appears
        adj.computeIfAbsent(courses[1], k -> new ArrayList<>()).add(courses[0]);
    }

    // State map: default 0 (WHITE) for nodes never seen
    Map<Integer, Integer> state = new HashMap<>();

    for (int i = 0; i < numCourses; i++) {
        if (hasCycleHM(adj, state, i))
            return false;
    }
    return true;
}

private static boolean hasCycleHM(Map<Integer, List<Integer>> adj, Map<Integer, Integer> state, int node) {

    if (state.getOrDefault(node, 0) == 2) return false;  // BLACK -> safe
    if (state.getOrDefault(node, 0) == 1) return true;   // GRAY  -> back-edge = cycle

    state.put(node, 1);  // mark GRAY

    // getOrDefault handles nodes with no outgoing edges (isolated courses)
    for (int neighbour : adj.getOrDefault(node, Collections.emptyList())) {
        if (hasCycleHM(adj, state, neighbour))
            return true;
    }

    state.put(node, 2);  // mark BLACK (fully processed)
    return false;
}
```

---

## Step-by-Step Walkthrough

**Input:** `numCourses = 2`, `prerequisites = [[1,0]]`
(Course 1 requires Course 0)

**Graph:**
```
0 → 1
```

**DFS Traversal:**

| Step | Node | State Before | Action | State After | Cycle? |
|------|------|--------------|--------|-------------|--------|
| 1 | 0 | WHITE | Mark GRAY, explore neighbor 1 | GRAY | No |
| 2 | 1 | WHITE | Mark GRAY, no neighbors | GRAY | No |
| 3 | 1 | GRAY | All neighbors done, mark BLACK | BLACK | No |
| 4 | 0 | GRAY | All neighbors done, mark BLACK | BLACK | No |

**Final Result:** `true` — both courses can be finished (take 0, then 1)

---

**Counter-example:** `numCourses = 2`, `prerequisites = [[1,0],[0,1]]`

**Graph:**
```
0 ⇄ 1
```

**DFS Traversal:**

| Step | Node | State Before | Action | Cycle? |
|------|------|--------------|--------|--------|
| 1 | 0 | WHITE | Mark GRAY, explore neighbor 1 | No |
| 2 | 1 | WHITE | Mark GRAY, explore neighbor 0 | No |
| 3 | 0 | **GRAY** | Revisited while in-progress → **back-edge!** | **Yes** |

**Final Result:** `false` — circular dependency, cannot finish

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| DFS (List-based) | O(V + E) | O(V + E) | V = courses, E = prerequisite pairs |
| DFS (HashMap-based) | O(V + E) | O(V + E) | Same asymptotics, higher constant factor |

**Where:**
- **V** = `numCourses` (number of nodes)
- **E** = `prerequisites.length` (number of edges)

**Time Complexity Explanation:**
- Each node is fully processed once (marked BLACK and skipped afterward)
- Each edge is traversed exactly once in the adjacency list
- Total: O(V + E)

**Space Complexity Explanation:**
- Adjacency list/map stores all edges: O(E)
- State array/map: O(V)
- Recursion stack in the worst case (a long chain of courses): O(V)

---

## Key Techniques

1. **Graph Modeling:** Convert prerequisite pairs into directed edges `b → a`
2. **3-Color DFS (WHITE/GRAY/BLACK):** Distinguish "unvisited", "in progress", and "proven safe"
3. **Back-edge Detection:** A GRAY node encountered again signals a directed cycle
4. **Memoized DFS:** BLACK nodes are pruned, avoiding redundant exploration
5. **Full Graph Scan:** Loop over all nodes to handle disconnected components

---

## Edge Cases

- **No prerequisites (empty array):** Return `true` — any order works
- **Single course:** Return `true`
- **Self-loop `[a, a]`:** Cycle → return `false`
- **Disconnected components:** Must check every node, not just the first
- **Long chain `0 → 1 → 2 → ... → n`:** Valid ordering exists → `true`, but watch recursion depth
- **Multiple independent cycles:** Any single cycle makes the answer `false`
- **Duplicate prerequisite pairs:** Harmless — the edge is just added twice

---

## Visualization

**Example 1:**
```
numCourses = 2, prerequisites = [[1,0]]
0 → 1         (take 0, then 1)        → true
```

**Example 2:**
```
numCourses = 2, prerequisites = [[1,0],[0,1]]
0 ⇄ 1         (circular)              → false
```

**Example 3:**
```
numCourses = 5, prerequisites = [[1,0],[2,1],[3,2],[4,3]]
0 → 1 → 2 → 3 → 4   (valid chain)    → true
```

**Example 4:**
```
numCourses = 4, prerequisites = [[1,0],[2,1],[0,2]]
0 → 1 → 2 ↺ 0       (3-node cycle)   → false
```

---

## Related Problems

- **Course Schedule II** - Return a valid ordering of courses (topological sort)
- **Redundant Connection** - Find the edge that creates a cycle
- **Detect Cycle in a Directed Graph** - Same core algorithm
- **Alien Dictionary** - Topological sort from character ordering rules
- **Longest Increasing Path in a Matrix** - DFS with memoization on a DAG

---

## Common Mistakes

1. **Reversing the edge direction:**
   - Using `a → b` instead of `b → a`
   - Cycle detection still works either way, but topological ordering would be wrong

2. **Only checking node 0:**
   - The graph can be disconnected; every node must be visited
   - Loop from `0` to `numCourses - 1`

3. **Using a simple visited boolean:**
   - Cannot distinguish "currently exploring" from "already finished"
   - Must use 3 states (or track the recursion stack explicitly)

4. **Forgetting to mark BLACK after recursion:**
   - Nodes get re-explored repeatedly → TLE on large inputs

5. **Not handling nodes with no edges in the HashMap version:**
   - `adj.get(node)` returns `null` → NullPointerException
   - Use `getOrDefault(node, Collections.emptyList())`

---

## Tags

#leetcode #graph #dfs #cycle-detection #topological-sort #medium

---

## Additional Notes

### Pattern Recognition

This problem follows the **Detect Cycle in Directed Graph** pattern:
- The problem's constraints encode a directed graph
- Feasibility ⇔ graph is a DAG (Directed Acyclic Graph)
- DFS with 3-color states (or BFS/Kahn's algorithm) detects cycles

### Alternative: BFS - Kahn's Algorithm (Topological Sort)

Count in-degrees and peel off nodes with in-degree 0, like "removing layers":

```java
public static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();
    int[] indegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

    // b -> a, so 'a' gains one incoming edge
    for (int[] p : prerequisites) {
        adj.get(p[1]).add(p[0]);
        indegree[p[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    // start with courses that have no prerequisites
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) queue.offer(i);
    }

    int taken = 0;
    while (!queue.isEmpty()) {
        int course = queue.poll();
        taken++;
        for (int next : adj.get(course)) {
            if (--indegree[next] == 0) queue.offer(next);
        }
    }

    // if we couldn't 'take' every course, a cycle blocked the rest
    return taken == numCourses;
}
```

### DFS vs Kahn's Algorithm

| Aspect | DFS (3-Color) | BFS (Kahn's) |
|--------|---------------|--------------|
| Core idea | Detect back-edge | Count in-degrees |
| Result | `boolean` | `boolean` + topological order |
| Space | O(V + E) + recursion stack | O(V + E) |
| Stack overflow risk | Yes, on deep chains | No |

### Visualizing the DFS Call Stack

For `prerequisites = [[1,0],[2,1]]` (chain 0 → 1 → 2):

```
hasCycle(0)
└── state[0] = GRAY
    └── hasCycle(1)
        └── state[1] = GRAY
            └── hasCycle(2)
                └── state[2] = GRAY
                    └── no neighbors → state[2] = BLACK, return false
                → state[1] = BLACK, return false
            → state[0] = BLACK, return false
→ no cycle → true
```

For `prerequisites = [[1,0],[0,1]]` (cycle 0 ⇄ 1):

```
hasCycle(0)
└── state[0] = GRAY
    └── hasCycle(1)
        └── state[1] = GRAY
            └── hasCycle(0)  ← state[0] == GRAY → return true (CYCLE!)
        → return true
    → return true
→ return false from canFinish
```

---

## Interview Tips

1. **Clarify the problem:**
   - Are duplicate prerequisite pairs possible? (Yes, handle gracefully)
   - Can a course require itself? (Treat `[a, a]` as a cycle)
   - Should we return an ordering, or just `true`/`false`?

2. **Start with the intuition:**
   - Restate: "finish all courses" ⇔ "no circular dependencies" ⇔ "graph is a DAG"

3. **Explain the 3-color insight:**
   - A plain `visited[]` boolean can't tell in-progress from finished
   - GRAY = on the current path; hitting GRAY again means a back-edge (cycle)
   - BLACK = memoized safe state

4. **Walk through an example:**
   - Draw the graph for `[[1,0],[2,1]]` (true) vs `[[1,0],[0,1]]` (false)

5. **Analyze complexity:**
   - Time: O(V + E) — each node and edge processed once
   - Space: O(V + E) — adjacency list + state array + recursion stack

6. **Discuss alternatives:**
   - Kahn's BFS algorithm gives a topological order for free (leads into Course Schedule II)
   - Iterative DFS avoids stack overflow on very deep chains

---

*Source: LCPatterns/Medium/CourseSchedule.java*
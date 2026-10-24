# Course Schedule II

**Problem Statement:**
There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

Return **any** ordering of courses such that all prerequisites are satisfied. If there are many valid answers, return **any** of them. If it is impossible to finish all courses, return **an empty array**.

---

## Problem:
https://leetcode.com/problems/course-schedule-ii/description/

## Key Insight

This problem builds on **Course Schedule** by asking for a **topological ordering** instead of just a feasibility check:

1. **Graph Modeling:** Each course is a node. A prerequisite pair `[a, b]` becomes a directed edge `b → a` ("b must come before a").

2. **Topological Sort:** A valid ordering is a linear ordering of vertices such that for every directed edge `u → v`, `u` comes **before** `v` in the ordering.

3. **Cycle ⇔ Impossible:** If the graph contains a directed cycle, no valid ordering exists → return `[]`.

4. **Two Classic Approaches:**
   - **DFS-based:** Post-order traversal reversed gives a valid topological order.
   - **BFS-based (Kahn's Algorithm):** Repeatedly remove nodes with in-degree 0.

---

## Solution Approaches

### 1. DFS with 3-Color Cycle Detection + Post-Order Stack

**Algorithm:**
1. Build an adjacency list where `adj[b]` holds courses that depend on `b`.
2. Run DFS from every unvisited node with 3-color state tracking (`0` = WHITE, `1` = GRAY, `2` = BLACK).
3. After finishing all neighbors of a node, **push it onto a stack** (post-order).
4. If a cycle is detected (encountering a GRAY node) → return empty array.
5. **Pop the stack** into the result array — this gives the correct topological order.

**Why reverse post-order works:**
- In DFS, a node is pushed onto the stack only after all its descendants are processed.
- So a prerequisite (descendant in the graph) is always pushed **before** the courses that depend on it.
- Popping the stack reverses this, placing prerequisites first.

---

## Code Implementation: DFS (Post-Order Stack)

```java
import java.util.*;
import java.util.LinkedList;

public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        // Build adjacency list: index = course, value = list of courses that depend on it
        List<List<Integer>> adjacency = new ArrayList<>();

        // Use deque instead of stack (more optimized for iteration)
        Deque<Integer> stack = new ArrayDeque<>();

        // Initialize adjacency list for all courses
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        // Edge direction: prerequisite -> course (b must be taken before a)
        for (int courses[] : prerequisites) {
            int pre = courses[1];      // prerequisite
            int course = courses[0];   // dependent course
            adjacency.get(pre).add(course);
        }

        // state[]: 0 = unvisited (WHITE), 1 = in-progress (GRAY), 2 = safe (BLACK)
        int state[] = new int[numCourses];

        // Check every node, since the graph may be disconnected
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(state, adjacency, stack, i))
                    return new int[]{};  // cycle detected → impossible to order
            }
        }

        // Pop stack into result array → reverse post-order = topological sort
        int result[] = new int[numCourses];
        for (int i = 0; i < numCourses; i++)
            result[i] = stack.pop();

        return result;
    }

    // Returns true if a cycle is detected, false otherwise
    private static boolean hasCycle(int[] state, List<List<Integer>> adjacency, Deque<Integer> stack, int node) {
        if (state[node] == 2)   // BLACK → already proven safe
            return false;
        if (state[node] == 1)   // GRAY → back-edge found = cycle!
            return true;

        state[node] = 1;  // Mark as GRAY (currently in recursion stack)

        // Explore all dependent courses
        for (int neighbour : adjacency.get(node)) {
            if (hasCycle(state, adjacency, stack, neighbour))
                return true;  // cycle found somewhere downstream
        }

        state[node] = 2;    // Mark as BLACK (all descendants safe)
        stack.push(node);   // Post-order push: push after children are done
        return false;
    }
}
```

---

### 2. BFS - Kahn's Algorithm (Topological Sort)

**Algorithm:**
1. Build an adjacency list and compute the **in-degree** (number of incoming edges) for each course.
2. Start BFS with all courses that have **in-degree 0** (no prerequisites).
3. When a course is dequeued, add it to the order.
4. For each neighbor, **decrement its in-degree**. If in-degree becomes 0, enqueue it.
5. If the result array is not full (not all courses were processed) → cycle exists → return `[]`.

**Why Kahn's works:**
- Courses with in-degree 0 have no unmet prerequisites → safe to take first.
- Removing them effectively "unlocks" their dependents.
- If a cycle exists, some nodes will never reach in-degree 0 → result will be incomplete.

---

## Code Implementation: BFS (Kahn's Algorithm)

```java
public static int[] findOrderKahn(int numCourses, int[][] prerequisites) {

    // Build adjacency list and in-degree count for each course
    List<List<Integer>> adjacency = new ArrayList<>();
    int inDegree[] = new int[numCourses];

    // Initialize adjacency list for all courses
    for (int i = 0; i < numCourses; i++)
        adjacency.add(new ArrayList<>());

    // Edge direction: prerequisite -> course; increment in-degree of dependent
    for (int courses[] : prerequisites) {
        adjacency.get(courses[1]).add(courses[0]);
        inDegree[courses[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    // Add all nodes with in-degree == 0 (no prerequisites → start here)
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0)
            queue.offer(i);
    }

    int order[] = new int[numCourses];
    int index = 0;

    // Process nodes level by level (BFS)
    while (!queue.isEmpty()) {
        int course = queue.poll();

        order[index++] = course;  // Place course in topological order

        for (int next : adjacency.get(course)) {
            // One prerequisite satisfied → decrement in-degree
            if (--inDegree[next] == 0)
                queue.offer(next);  // All prerequisites met → ready to take
        }
    }

    // If all courses were processed → valid order; otherwise, cycle exists
    return index == numCourses ? order : new int[]{};
}
```

---

## Step-by-Step Walkthrough

**Input:** `numCourses = 4`, `prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

**Graph:**
```
  0
 / \
1   2
 \ /
  3
```

**DFS Approach (Post-Order):**

| Step | Node | Action | Stack |
|------|------|--------|-------|
| 1 | 0 | Mark GRAY, visit 1 | [] |
| 2 | 1 | Mark GRAY, visit 3 | [] |
| 3 | 3 | Mark GRAY, visit 3's neighbors (none besides 3→none from 3) | [] |
| 4 | 3 | Mark BLACK, push 3 | [3] |
| 5 | 1 | Mark BLACK, push 1 | [3,1] |
| 6 | 0 | Visit 2 | [3,1] |
| 7 | 2 | Mark GRAY, visit 3 (BLACK → skip) | [3,1] |
| 8 | 2 | Mark BLACK, push 2 | [3,1,2] |
| 9 | 0 | Mark BLACK, push 0 | [3,1,2,0] |
| 10 | Pop stack | → `[0,2,1,3]` | |

**Kahn's BFS Approach:**

| Step | Queue | Dequeued | In-degree changes | Order |
|------|-------|----------|-------------------|-------|
| 1 | [0] | 0 | 1→0, 2→0 | [0] |
| 2 | [1,2] | 1 | 3→1 | [0,1] |
| 3 | [2] | 2 | 3→0 | [0,1,2] |
| 4 | [3] | 3 | — | [0,1,2,3] |

**Final Result:** `[0, 1, 2, 3]` (or `[0, 2, 1, 3]` for DFS)

---

**Counter-example:** `numCourses = 2`, `prerequisites = [[1,0],[0,1]]`

**Graph:**
```
0 ⇄ 1
```

- DFS: encounters GRAY node → cycle → return `[]`
- Kahn's: no node has in-degree 0 → queue empty immediately → `index != numCourses` → return `[]`

---

## Complexity Analysis

| Approach | Time Complexity | Space Complexity | Notes |
|----------|-----------------|---------------|-------|
| DFS (Post-Order Stack) | O(V + E) | O(V + E) | V = courses, E = prerequisite pairs |
| BFS (Kahn's Algorithm) | O(V + E) | O(V + E) | Same asymptotics |

**Where:**
- **V** = `numCourses` (number of nodes)
- **E** = `prerequisites.length` (number of edges)

**Time Complexity Explanation:**
- Each node is visited and processed once.
- Each edge is traversed once in the adjacency list.
- Total: O(V + E)

**Space Complexity Explanation:**
- Adjacency list: O(V + E)
- State array / in-degree array: O(V)
- Stack / Queue: O(V) in the worst case

---

## Key Techniques

1. **Topological Sort:** Linear ordering respecting all directed edges.
2. **DFS Post-Order Reversal:** Push nodes after children; pop stack → topological order.
3. **Kahn's BFS:** Peel off in-degree-0 nodes layer by layer.
4. **3-Color Cycle Detection (DFS):** WHITE → GRAY → BLACK distinguishes unvisited / in-progress / finished.
5. **Full Graph Scan:** Loop over all nodes to handle disconnected components.

---

## Edge Cases

- **No prerequisites (empty array):** Return any permutation of `[0, 1, ..., numCourses-1]`.
- **Single course:** Return `[0]`.
- **Self-loop `[a, a]`:** Cycle → return `[]`.
- **Disconnected components:** Each component is ordered independently; the overall order is still valid.
- **Long chain `0 → 1 → 2 → ... → n`:** Result is `[0, 1, 2, ..., n]`.
- **Multiple valid orders:** Return any one (e.g., parallel branches can be interleaved).
- **Impossible (cycle):** Return `[]`.
- **Duplicate prerequisite pairs:** Harmless — the edge is just added twice (in-degree increases).

---

## Visualization

**Example 1:**
```
numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
  0
 / \
1   2
 \ /
  3
→ [0, 1, 2, 3] (or [0, 2, 1, 3])
```

**Example 2:**
```
numCourses = 1, prerequisites = []
→ [0]
```

**Example 3:**
```
numCourses = 2, prerequisites = [[1,0]]
0 → 1
→ [0, 1]
```

---

## Related Problems

- **Course Schedule** - Check if all courses can be finished (cycle detection)
- **Alien Dictionary** - Build topological order from character precedence rules
- **Redundant Connection** - Find the edge that creates a cycle
- **Sequence Reconstruction** - Determine if a sequence uniquely reconstructs the topological order
- **Longest Increasing Path in a Matrix** - DFS with memoization on a DAG

---

## Common Mistakes

1. **Returning the result in DFS pre-order:**
   - Pushing the node **before** recursing gives wrong order
   - Must push **after** all neighbors are processed (post-order)

2. **Forgetting to reverse the stack:**
   - The stack pops in reverse post-order = correct topological order
   - If using a list, remember to reverse it

3. **Not checking for cycles in DFS:**
   - Returning an order without cycle detection produces wrong results for cyclic graphs

4. **Not checking `index == numCourses` in Kahn's:**
   - If not all nodes were processed, a cycle exists → must return `[]`

5. **Confusing edge direction:**
   - `[a, b]` means `b → a` (b must come **before** a)
   - Reversing this gives incorrect topological order

6. **Using a `Stack` instead of `Deque`:**
   - `Stack` extends `Vector` and is synchronized (slower)
   - `ArrayDeque` is the preferred stack implementation in Java

---

## Tags

#leetcode #graph #dfs #bfs #topological-sort #cycle-detection #medium

---

## Additional Notes

### Pattern Recognition

This problem follows the **Topological Sort** pattern:
- Problem constraints encode a directed graph (dependencies).
- A valid ordering exists iff the graph is a **DAG** (Directed Acyclic Graph).
- DFS reverse post-order and Kahn's BFS are the two standard techniques.

### DFS vs Kahn's Algorithm

| Aspect | DFS (Post-Order) | BFS (Kahn's) |
|--------|-----------------|--------------|
| Core idea | Reverse post-order | In-degree peeling |
| Cycle detection | Back-edge (GRAY node) | Unprocessed nodes remain |
| Order produced | Any valid topological order | Layer-by-layer order |
| Space | O(V + E) + recursion stack | O(V + E) |
| Stack overflow risk | Yes, on deep chains | No |

### Visualizing the DFS Call Stack

For `prerequisites = [[1,0],[2,1]]` (chain 0 → 1 → 2):

```
findOrder(0)
├── state[0] = GRAY
│   └── findOrder(1)
│       ├── state[1] = GRAY
│       │   └── findOrder(2)
│       │       ├── state[2] = GRAY
│       │       ├── no neighbors → state[2] = BLACK, push 2
│       │       └── return false
│       │   → state[1] = BLACK, push 1
│       └── return false
│   → state[0] = BLACK, push 0
└── return false

Stack (bottom → top): [2, 1, 0]
Pop into result: [0, 1, 2] ✓
```

---

## Interview Tips

1. **Clarify the problem:**
   - "Return any valid ordering" — not necessarily the only one.
   - What to return on cycle? (Empty array)
   - Are duplicate edges possible? (Yes)

2. **Start with the intuition:**
   - "I need a topological sort of the dependency graph."
   - "If a cycle exists, no valid order exists."

3. **Choose an approach and explain:**
   - DFS: elegant with 3-color cycle detection and post-order stack.
   - Kahn's: intuitive — take courses with no unmet prerequisites first.

4. **Walk through an example:**
   - Draw the graph for `[[1,0],[2,0],[3,1],[3,2]]` → two valid orders.
   - Show a cyclic example → empty array.

5. **Analyze complexity:**
   - Time: O(V + E), Space: O(V + E).

6. **Discuss trade-offs:**
   - DFS has recursion depth risk; Kahn's avoids it.
   - Both produce valid topological orders; Kahn's gives layer structure.

---

*Source: LCPatterns/Medium/CourseScheduleII.java*
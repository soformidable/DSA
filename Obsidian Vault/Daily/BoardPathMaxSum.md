"# Board Path Maximum Sum and Number of Ways

## Problem
https://leetcode.com/problems/number-of-paths-with-max-score/?envType=daily-question&envId=2026-07-05

Given a square board of size `n × n` represented as a list of strings, find the **maximum sum** along a path from the top-left corner **'E'** (start) to the bottom-right corner **'S'** (end), and the **number of distinct paths** that achieve that maximum sum.

## Code link:
https://github.com/soformidable/DSA/blob/main/Daily/BoardPathMaxSum.java

**Movement rules:**
- From cell `(i, j)`, you can move **right** `→ (i, j+1)`, **down** `↓ (i+1, j)`, or **diagonal down-right** `↘ (i+1, j+1)`.
- Cells with **'X'** are blocked and cannot be entered.
- Cells with **'E'** (top-left) and **'S'** (bottom-right) contribute **0** to the sum.
- Other cells contain a single **digit '0'–'9'** representing the score collected when stepping on that cell.

Return `[maxSum, count]`, where `count` is modulo `1_000_000_007`.

---

## Approach 1: Recursion (Brute-Force DFS)

### Intuition

Explore all possible paths recursively from the starting cell `(0, 0)`. For each path that reaches `'S'`, record the accumulated sum in a `HashMap<Long, Long>` mapping `sum → number of ways to achieve that sum`. After exploring all paths, find the maximum sum from the map and return it along with its count.

### Algorithm (`pathsWithmaxscore` / `move`)

```
res = HashMap<sum, count>
move(board, res, sum=0, i=0, j=0)

function move(board, res, sum, i, j):
    if out of bounds OR cell is 'X':
        return
    if cell is 'S':
        res[sum] += 1
        return
    
    // Add digit value to sum (except 'E')
    if cell is not 'E':
        sum += digit
    
    // Recurse in three directions
    move(board, res, sum, i+1, j)     // down
    move(board, res, sum, i+1, j+1)   // diagonal
    move(board, res, sum, i, j+1)     // right
```

After recursion, find the maximum key in the map (largest sum) and return `[maxKey, count]`.

### Complexity

- **Time:** `O(3^(n²))` — each cell branches into 3 directions, leading to exponential explosion.
- **Space:** `O(n²)` recursion stack depth (actually up to `2n-1` moves to reach bottom-right, but worst-case number of paths stored is huge).

### Code

```java
public static int[] pathsWithmaxscore(List<String> board) {
    Map<Long, Long> res = new HashMap<>();
    move(board, res, 0, 0, 0);

    long key = 0;
    int count = 0;

    if (!res.isEmpty()) {
        key = Collections.max(res.entrySet(), Map.Entry.comparingByKey()).getKey();
        count = (int) ((res.get(key)) % ((Math.pow(10, 9)) + 7));
    }

    return new int[]{(int) key, count};
}

public static void move(List<String> board, Map<Long, Long> res, long sum, int i, int j) {
    if (i >= board.size() || j >= board.get(i).length() || board.get(i).charAt(j) == 'X') {
        return;
    } else if (board.get(i).charAt(j) == 'S') {
        res.put(sum, 1L + res.getOrDefault(sum, 0L));
        return;
    } else {
        if (board.get(i).charAt(j) != 'E')
            sum += Integer.parseInt(String.valueOf(board.get(i).charAt(j)));

        move(board, res, sum, i + 1, j);
        move(board, res, sum, i + 1, j + 1);
        move(board, res, sum, i, j + 1);
    }
}
```

### Limitations

- Exponential time — impractical for `n > 5`.
- Only counts ways per sum but does not prune suboptimal paths.
- The return type uses `long` sums but the final answer is cast to `int` (fine for small boards).

---

## Approach 2: Dynamic Programming (Bottom-Up)

### Intuition

Process the board **from bottom-right to top-left**. For each cell, compute two values:
- `score[i][j]`: the **maximum sum** achievable from `(i, j)` to `'S'`.
- `ways[i][j]`: the **number of paths** from `(i, j)` to `'S'` that achieve that maximum sum.

A cell can only be reached from its **three predecessors** (up, left, up-left). By filling the table from the bottom-right corner backwards, we can build optimal substructure.

### Algorithm (`pathsWithMaxScore_OP`)

1. Initialize `score[n][n]` with `-1` (unreachable) and `ways[n][n]` with `0`.
2. Set `score[n-1][n-1] = 0` (the 'S' cell), `ways[n-1][n-1] = 1`.
3. Iterate `i` from `n-1` down to `0`, `j` from `n-1` down to `0`:
   - Skip if cell is `'X'` or `'S'` (already set).
   - Look at the three possible **next** cells: `(i+1, j)`, `(i, j+1)`, `(i+1, j+1)`.
   - Among those reachable (`score != -1`), find the **maximum** score.
     - If a next cell has a new best score: update `best` and reset `count` to the ways of that cell.
     - If a next cell ties the current best: add its ways to `count`.
   - If no reachable next cell (`best == -1`), leave as unreachable.
   - Otherwise, set:
     - `score[i][j] = best + value_of_current_cell` (digit or 0 for 'E')
     - `ways[i][j] = count % MOD`
4. After the loop:
   - If `ways[0][0] == 0` → no path exists → return `[0, 0]`.
   - Else return `[score[0][0], ways[0][0]]`.

### Complexity

- **Time:** `O(n²)` — each cell is processed exactly once, checking at most 3 neighbors.
- **Space:** `O(n²)` — two `n × n` tables.

### Code

```java
public static int[] pathsWithMaxScore_OP(List<String> board) {
    int n = board.size();
    int MOD = 1_000_000_007;

    int[][] score = new int[n][n];
    int[][] ways = new int[n][n];

    for (int i = 0; i < n; i++)
        Arrays.fill(score[i], -1);   // -1 = unreachable

    score[n - 1][n - 1] = 0;
    ways[n - 1][n - 1] = 1;

    for (int i = n - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            char c = board.get(i).charAt(j);

            if (c == 'X' || c == 'S')
                continue;

            int best = -1;
            int count = 0;

            // Three possible next cells: down, right, diagonal
            int[][] next = {
                {i + 1, j},
                {i, j + 1},
                {i + 1, j + 1}
            };

            for (int[] p : next) {
                int r = p[0], col = p[1];
                if (r >= n || col >= n)
                    continue;
                if (score[r][col] == -1)
                    continue;

                if (score[r][col] > best) {
                    best = score[r][col];
                    count = ways[r][col];
                } else if (score[r][col] == best) {
                    count = (count + ways[r][col]) % MOD;
                }
            }

            if (best == -1)
                continue;  // no valid path from here

            int value = (c == 'E') ? 0 : c - '0';
            score[i][j] = best + value;
            ways[i][j] = count;
        }
    }

    if (ways[0][0] == 0)
        return new int[]{0, 0};

    return new int[]{score[0][0], ways[0][0]};
}
```

### How the DP Table is Filled (Bottom-Right to Top-Left)

Consider board:
```
E 1 2
1 X 1
2 1 S
```

Starting from `S` (index `[2][2]`):
- `score[2][2] = 0`, `ways[2][2] = 1`
- `[2][1]` = '1': looks at `[2][2]` (right) → best=0, count=1 → `score=0+1=1`, `ways=1`
- `[2][0]` = '2': looks at `[2][1]` (right) → best=1, count=1 → `score=1+2=3`, `ways=1`
- `[1][2]` = '1': looks at `[2][2]` (down) → best=0, count=1 → `score=0+1=1`, `ways=1`
- `[1][1]` = 'X': skipped.
- `[1][0]` = '1': looks at `[2][0]` (down, score=3, ways=1) and `[2][1]` (diag, score=1, ways=1) → best=3, count=1 → `score=3+1=4`, `ways=1`
- `[0][2]` = '2': looks at `[1][2]` (down, score=1, ways=1) → `score=1+2=3`, `ways=1`
- `[0][1]` = '1': looks at `[0][2]` (right, score=3, ways=1) and `[1][2]` (diag, score=1, ways=1) → best=3, count=1 → `score=3+1=4`, `ways=1`
- `[0][0]` = 'E': looks at `[0][1]` (right, score=4, ways=1) and `[1][0]` (down, score=4, ways=1) and `[1][1]` (diag, X → skipped) → best=4, tie → count=1+1=2 → `score=4+0=4`, `ways=2`

Result: `[4, 2]` — maximum sum is 4, achievable by 2 paths.

---

## Comparison

| Aspect | Recursion (DFS) | Dynamic Programming |
|--------|-----------------|---------------------|
| Time | `O(3^(n²))` — exponential | `O(n²)` — quadratic |
| Space | `O(HashMap + recursion stack)` | `O(n²)` for two tables |
| Handles large `n` | No | Yes |
| Implementation complexity | Simple recursive backtracking | Iterative bottom-up DP |
| Correctness | Brute force — always correct | DP — correct due to optimal substructure |
| Use case | Small boards, quick exploration | Large boards, optimal solution |

## Key Points for Revisit

- **Direction matters:** We move **bottom-right to top-left** because the recurrence depends on future cells (`i+1`, `j+1`). Filling from bottom to top ensures those values are already computed.
- **Unreachable cells:** Marked with `score = -1` to distinguish from `score = 0`.
- **Counting paths:** When multiple next cells have the same best score, **add** their ways together.
- **Modulo:** Apply `% MOD` when adding ways to prevent overflow.
- **Grid BFS/DFS pattern:** The recursion version is essentially a DFS on a DAG (no cycles because we only move down/right), so recursion works without visited tracking.
- **Related problems:** Minimum path sum, unique paths, knight probability, dungeon game."
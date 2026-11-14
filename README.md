# DSA Practice Workspace (LeetCode Priority Roadmap)

A structured Java 21 workspace arranged sequentially by standard DSA topic progression (NeetCode/Blind 75 roadmap).

---

## 🎯 Topic Progression (Folders Ordered by Priority)

The folders under `src/main/java/com/dsapractice/leetcode/` are numbered (`c01` through `c18`) according to the recommended learning path:

| Stage | Package | Topic | Why Solve in This Order? |
|:---:|:---|:---|:---|
| **01** | `c01_arrays` | Arrays & Hashing | Fundamental lookups, hash tables, and frequency counts. |
| **02** | `c02_twopointers` | Two Pointers | Linear traversals, inward sweeps, sorted arrays. |
| **03** | `c03_slidingwindow` | Sliding Window | Substring and contiguous subarray problems. |
| **04** | `c04_stack` | Stack | LIFO processing, parentheses, monotonic stacks. |
| **05** | `c05_binarysearch` | Binary Search | $O(\log n)$ search space reduction and condition tests. |
| **06** | `c06_linkedlist` | Linked List | Pointer manipulation, cycles, reversals. |
| **07** | `c07_trees` | Trees | Recursive DFS, BFS level-order, and BST properties. |
| **08** | `c08_tries` | Tries | Prefix trees for fast dictionary and word lookups. |
| **09** | `c09_heaps` | Heap / Priority Queue | Streaming top-k, medians, and greedy selections. |
| **10** | `c10_backtracking` | Backtracking | Exhaustive search, permutations, subsets, board states. |
| **11** | `c11_graphs` | Graphs | Grid searches, BFS shortest paths, DFS, cycle detection. |
| **12** | `c12_advancedgraphs` | Advanced Graphs | Topological sort, Dijkstra, minimum spanning tree. |
| **13** | `c13_1d_dp` | 1-D Dynamic Programming | Subproblems, memoization, bottom-up tabulations. |
| **14** | `c14_2d_dp` | 2-D Dynamic Programming | Grids, sequence comparisons (LCS, Knapsack). |
| **15** | `c15_greedy` | Greedy | Locally optimal choices for global solutions. |
| **16** | `c16_intervals` | Intervals | Sorting intervals, checking overlaps, scheduling. |
| **17** | `c17_math_geometry` | Math & Geometry | Matrix rotations, coordinate spirals, number theory. |
| **18** | `c18_bit_manipulation` | Bit Manipulation | XOR tricks, masking, bit counting. |

---

## 🚀 Working on Problems

1. Check your next problem in [TRACKER.md](TRACKER.md).
2. Open the solution file (e.g., `P15_ThreeSum.java` in `c02_twopointers`).
3. Write your implementation inside `public static class Solution`.
4. Open the matching test in `src/test/java/com/dsapractice/leetcode/c02_twopointers/P15_ThreeSumTest.java`, remove `@Disabled`, and test:
   ```bash
   mvn test -Dtest=P15_ThreeSumTest
   ```

---

## ⚡ Add a New Problem with Sequential Tagging
```bash
./new-problem.sh <problem_id> <CamelCaseName> <category> [Difficulty]
```
**Example:**
```bash
./new-problem.sh 15 ThreeSum c02_twopointers Medium
```

# DSA Practice Workspace (LeetCode Priority Roadmap)

A structured Java 21 workspace arranged sequentially by standard DSA topic progression (NeetCode/Blind 75 roadmap).

---

## 🎯 Topic Progression (Folders Ordered by Priority)

The folders under `src/main/java/com/dsapractice/leetcode/` are numbered (`c01` through `c18`) according to the recommended learning path:

| Stage | Package | Topic | Why Solve in This Order? |
|:---:|:---|:---|:---|
| **01** | [`c01_arrays`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c01_arrays) | Arrays & Hashing | Fundamental lookups, hash tables, and frequency counts. |
| **02** | [`c02_twopointers`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c02_twopointers) | Two Pointers | Linear traversals, inward sweeps, sorted arrays. |
| **03** | [`c03_slidingwindow`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c03_slidingwindow) | Sliding Window | Substring and contiguous subarray problems. |
| **04** | [`c04_stack`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c04_stack) | Stack | LIFO processing, parentheses, monotonic stacks. |
| **05** | [`c05_binarysearch`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c05_binarysearch) | Binary Search | $O(\log n)$ search space reduction and condition tests. |
| **06** | [`c06_linkedlist`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c06_linkedlist) | Linked List | Pointer manipulation, cycles, reversals. |
| **07** | [`c07_trees`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c07_trees) | Trees | Recursive DFS, BFS level-order, and BST properties. |
| **08** | [`c08_tries`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c08_tries) | Tries | Prefix trees for fast dictionary and word lookups. |
| **09** | [`c09_heaps`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c09_heaps) | Heap / Priority Queue | Streaming top-k, medians, and greedy selections. |
| **10** | [`c10_backtracking`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c10_backtracking) | Backtracking | Exhaustive search, permutations, subsets, board states. |
| **11** | [`c11_graphs`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c11_graphs) | Graphs | Grid searches, BFS shortest paths, DFS, cycle detection. |
| **12** | [`c12_advancedgraphs`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c12_advancedgraphs) | Advanced Graphs | Topological sort, Dijkstra, minimum spanning tree. |
| **13** | [`c13_1d_dp`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c13_1d_dp) | 1-D Dynamic Programming | Subproblems, memoization, bottom-up tabulations. |
| **14** | [`c14_2d_dp`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c14_2d_dp) | 2-D Dynamic Programming | Grids, sequence comparisons (LCS, Knapsack). |
| **15** | [`c15_greedy`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c15_greedy) | Greedy | Locally optimal choices for global solutions. |
| **16** | [`c16_intervals`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c16_intervals) | Intervals | Sorting intervals, checking overlaps, scheduling. |
| **17** | [`c17_math_geometry`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c17_math_geometry) | Math & Geometry | Matrix rotations, coordinate spirals, number theory. |
| **18** | [`c18_bit_manipulation`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/c18_bit_manipulation) | Bit Manipulation | XOR tricks, masking, bit counting. |

---

## 🛠️ Data Structure Helpers

Common LeetCode data structure utilities are located in [`com.dsapractice.leetcode.common`](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/common):

- **[ListNode.java](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/common/ListNode.java)**:
  ```java
  ListNode head = ListNode.of(1, 2, 3, 4, 5);
  int[] array = head.toArray(); // [1, 2, 3, 4, 5]
  ```
- **[TreeNode.java](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/src/main/java/com/dsapractice/leetcode/common/TreeNode.java)**:
  ```java
  // Level-order construction supporting nulls
  TreeNode root = TreeNode.of(1, null, 2, 3);
  ```

---

## 🚀 Working on Problems

1. Pick your next target in [TRACKER.md](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/TRACKER.md).
2. Open the solution file (e.g., `P15_ThreeSum.java` in `c02_twopointers`).
3. Write your implementation inside `public static class Solution`.
4. Open the matching test in `src/test/java/com/dsapractice/leetcode/c02_twopointers/P15_ThreeSumTest.java`, remove `@Disabled`, and test:
   ```bash
   mvn test -Dtest=P15_ThreeSumTest
   ```
5. Run all unit tests:
   ```bash
   mvn test
   ```
6. When a problem is solved, change `Status: To Do` to `Status: Done` in its header comment. [TRACKER.md](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/TRACKER.md) refreshes automatically the next time you run any problem (`main()` calls `TrackerAutoSync.sync(...)`), so there is nothing to run manually.

---

## ⚡ Add a New Problem with Sequential Tagging

To scaffold any problem beyond the initial Blind 75 set:
```bash
./new-problem.sh <problem_id> <CamelCaseName> <category> [Difficulty]
```
**Example:**
```bash
./new-problem.sh 567 PermutationInString c03_slidingwindow Medium
```
This automatically creates the solution template, test template, and appends a row to [TRACKER.md](file:///Users/dinesh/git/My%20Coding%20Workspace/dsa-practice/TRACKER.md).

---

## 🌿 Recommended Git Workflow

- **`main`**: Verified, production-ready solutions and milestone tags.
- **`dev`**: Daily integration branch.
- **`feat/<topic>-<problem>`**: Feature branch for an individual problem:
  ```bash
  git checkout dev
  git checkout -b feat/c02-p15-threesum
  # implement and run: mvn test -Dtest=P15_ThreeSumTest
  git commit -m "feat(c02_twopointers): solve 15 ThreeSum with O(n^2) sort and two pointers"
  git checkout dev
  git merge feat/c02-p15-threesum
  ```

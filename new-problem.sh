#!/usr/bin/env bash
# Usage: ./new-problem.sh <id> <CamelCaseName> <category> [Difficulty]
# Example: ./new-problem.sh 15 ThreeSum c02_twopointers Medium

set -e

if [ "$#" -lt 3 ]; then
    echo "Usage: ./new-problem.sh <id> <CamelCaseName> <category> [Difficulty]"
    echo "Example: ./new-problem.sh 15 ThreeSum c02_twopointers Medium"
    echo ""
    echo "Roadmap Categories (ordered by priority):"
    echo "  c01_arrays           - Arrays & Hashing"
    echo "  c02_twopointers      - Two Pointers"
    echo "  c03_slidingwindow    - Sliding Window"
    echo "  c04_stack            - Stack"
    echo "  c05_binarysearch     - Binary Search"
    echo "  c06_linkedlist       - Linked List"
    echo "  c07_trees            - Trees"
    echo "  c08_tries            - Tries"
    echo "  c09_heaps            - Heap & Priority Queue"
    echo "  c10_backtracking     - Backtracking"
    echo "  c11_graphs           - Graphs"
    echo "  c12_advancedgraphs   - Advanced Graphs"
    echo "  c13_1d_dp            - 1-D Dynamic Programming"
    echo "  c14_2d_dp            - 2-D Dynamic Programming"
    echo "  c15_greedy           - Greedy"
    echo "  c16_intervals        - Intervals"
    echo "  c17_math_geometry    - Math & Geometry"
    echo "  c18_bit_manipulation - Bit Manipulation"
    exit 1
fi

PID="$1"
NAME="$2"
CATEGORY="$3"
DIFFICULTY="${4:-Medium}"

BASE_SRC="src/main/java/com/dsapractice/leetcode/$CATEGORY"
BASE_TEST="src/test/java/com/dsapractice/leetcode/$CATEGORY"

mkdir -p "$BASE_SRC" "$BASE_TEST"

CLASS_NAME="P${PID}_${NAME}"
SRC_FILE="$BASE_SRC/${CLASS_NAME}.java"
TEST_FILE="$BASE_TEST/${CLASS_NAME}Test.java"

if [ -f "$SRC_FILE" ]; then
    echo "File $SRC_FILE already exists!"
    exit 1
fi

cat <<EOF > "$SRC_FILE"
package com.dsapractice.leetcode.$CATEGORY;

/**
 * [$PID] $NAME
 * Difficulty: $DIFFICULTY
 * Source: https://leetcode.com/problems/
 */
public class $CLASS_NAME {

    public static class Solution {
        // TODO: Paste or write LeetCode method signature here
    }
}
EOF

cat <<EOF > "$TEST_FILE"
package com.dsapractice.leetcode.$CATEGORY;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ${CLASS_NAME}Test {

    private final ${CLASS_NAME}.Solution solution = new ${CLASS_NAME}.Solution();

    @Test
    @Disabled("Enable when solution is implemented")
    @DisplayName("Example 1")
    void testExample1() {
        // Arrange & Act
        // Assert
    }
}
EOF

echo "| $PID | [$NAME](https://leetcode.com/problems/) | $DIFFICULTY | $CATEGORY | [Code]($SRC_FILE) | [Test]($TEST_FILE) | ⏳ Pending |" >> TRACKER.md

echo "Created:"
echo "  $SRC_FILE"
echo "  $TEST_FILE"
echo "  Appended to TRACKER.md"

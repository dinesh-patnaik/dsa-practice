#!/usr/bin/env bash
# Quick Runner for any LeetCode problem by name or class path
# Progress dashboard: ./run.sh TrackerDashboard
# Usage: ./run.sh P69_SqrtX
#    or: ./run.sh com.dsapractice.leetcode.c05_binarysearch.P69_SqrtX

set -e

# Detect JAVA_HOME if not set
if [ -z "$JAVA_HOME" ]; then
    if [ -d "/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home" ]; then
        export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home"
    elif command -v /usr/libexec/java_home &> /dev/null; then
        export JAVA_HOME=$(/usr/libexec/java_home)
    fi
fi

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TARGET_CLASSES="$PROJECT_DIR/target/classes"

if [ -z "$1" ]; then
    echo "Usage: ./run.sh <ProblemClassName>"
    echo "Examples:"
    echo "  ./run.sh P69_SqrtX"
    echo "  ./run.sh P1_TwoSum"
    echo "  ./run.sh P206_ReverseLinkedList"
    exit 1
fi

INPUT="$1"

# If full class name is not provided, locate it
if [[ "$INPUT" == *"."* ]]; then
    FULL_CLASS="$INPUT"
else
    # Find matching file under src/main/java
    MATCH=$(find "$PROJECT_DIR/src/main/java" -name "${INPUT}.java" | head -n 1)
    if [ -z "$MATCH" ]; then
        echo "Error: Could not find class file ${INPUT}.java"
        exit 1
    fi
    # Convert path to fully qualified class name
    REL_PATH="${MATCH#$PROJECT_DIR/src/main/java/}"
    FULL_CLASS="${REL_PATH%.java}"
    FULL_CLASS="${FULL_CLASS//\//.}"
fi

echo "Compiling and running $FULL_CLASS..."
mvn -f "$PROJECT_DIR/pom.xml" compile -q
"$JAVA_HOME/bin/java" -cp "$TARGET_CLASSES" "$FULL_CLASS" "${@:2}"

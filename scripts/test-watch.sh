#!/usr/bin/env bash
set -e

# Watch-mode test runner that re-runs on file changes (nodemon-like)
# Requires: fswatch (macOS: brew install fswatch) or inotifywait on Linux.

ROOT_DIR="$(cd "$(dirname "$0")"/.. && pwd)"
cd "$ROOT_DIR"

PATTERN="$1"

run_tests() {
  clear
  NOW=$(date '+%H:%M:%S')
  if [ -n "$PATTERN" ]; then
    echo "[watch $NOW] Running filtered tests: $PATTERN"
    ./scripts/test-run.sh "$PATTERN"
  else
    echo "[watch $NOW] Running all tests..."
    ./scripts/test-run.sh
  fi
}

run_tests

if command -v fswatch >/dev/null 2>&1; then
  fswatch -o src | while read -r _; do run_tests; done
elif command -v inotifywait >/dev/null 2>&1; then
  while inotifywait -r -e modify,create,delete src; do run_tests; done
else
  echo "watch: install fswatch (macOS: brew install fswatch) or inotify-tools (Linux)"
  exit 1
fi



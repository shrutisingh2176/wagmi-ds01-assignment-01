## 🧑‍🎓 Student Guide: Personal Finance DSA Project (Make All Tests Pass)

### 🚦 Start Here: Your Goal

- ✅ Primary goal: Make every test in `src/test/java/**` pass.
- ▶️ Best way to run tests with friendly output:
  - `./scripts/test-run.sh` (one-shot summary)
  - `./scripts/test-watch.sh` (auto re-run on changes)
  - Filter to a class or a single method: `./scripts/test-run.sh test.java.com.wagmi.finance.alg.SortersTest#mergeSortByDate_sorts_by_date_ascending`

Keep this README open and follow sections in order: Setup → Run tests → Implement → Re-run → Repeat until green.

This repository contains a scaffolded Java/Maven project with unit tests already written for you. Your job is to implement the data structures and algorithms so that all tests pass.

Focus: setup quickly, run tests often, implement incrementally.

### ✅ What You’ll Build

A console-based Personal Finance system that demonstrates:
- Linked lists (transactions), stacks (undo), queues (pending processing)
- Sorting and searching (arrays, quick/merge sort, binary search)
- Hash-based budget management
- Recursion and string utilities

The implementation lives in `src/main/java/...`. Tests that verify your work are in `src/test/java/...`.

### 🧰 Prerequisites

- Java 17+ (JDK)
- Maven 3.8+
- An IDE (IntelliJ IDEA recommended)

Verify your setup:
```bash
java -version
mvn -version
```

### 🚀 Getting Started

1) Clone and open the project
```bash
git clone <your-fork-or-starter-url>
cd wagmi-ds01-assignment-01
```

2) First build (runs all tests)
```bash
mvn -q -DskipTests=false clean test
```
Expect failures at the beginning. Your goal is to turn the build green by implementing code in `src/main/java`.

### 🧪 Running Tests

- Run everything:
```bash
mvn test
```

- Run a single test class (example: sorting):
```bash
mvn -Dtest=com.wagmi.finance.alg.SortersTest test
```

- Run a single test method:
```bash
mvn -Dtest=com.wagmi.finance.alg.SortersTest#shouldSortByAmountAscending test
```

- Show surefire reports (after tests): see `target/surefire-reports/`.

IntelliJ users:
- Right-click any test class or method in `src/test/java` and select “Run”.

### ⚡ Nodemon-style runners (easier output)

- One-shot summary:
```bash
./scripts/test-run.sh            # run all tests with a clean summary
./scripts/test-run.sh <Filter>   # e.g., test.java.com.wagmi.finance.alg.SortersTest
./scripts/test-run.sh <Class#Method>
```

- Watch mode (auto re-run on changes):
```bash
brew install fswatch              # macOS only (one-time)
./scripts/test-watch.sh           # all tests
./scripts/test-watch.sh <Filter>  # run specific test(s) on change
```

### 🧪 Unit Testing 101 (If you're new to tests)

- What is a test? A small program that calls your code and checks the result automatically.
- What does “pass/fail” mean?
  - Pass: your method returned what the test expected.
  - Fail: your method returned something else, or threw an unexpected error.
- Where do I see results?
  - In the terminal after `mvn test`.
  - Detailed per-class reports in `target/surefire-reports/*.txt` (open the file that matches the test class name).
- How do I read a failure?
  - Look for the first “AssertionFailedError” or stack trace. It shows the expected vs actual values and the file:line of the failing test.
  - Open the mentioned test file to see what the test expects, then open the corresponding class in `src/main/java/**` and implement the logic to meet that expectation.
- Common assertions you’ll see:
  - `assertEquals(expected, actual)`: your code should produce `expected`.
  - `assertTrue(condition)`, `assertFalse(condition)`: your code should make the condition true/false.
  - `assertThrows(Exception.class, () -> call())`: your code should throw the specified exception.
- Productive loop:
  1) Run a specific test (e.g., one method) to see the first failure.
  2) Implement the minimal code to satisfy that failure.
  3) Re-run the same test until it passes.
  4) Move to the next failing test.

### 📁 Project Map (Where to Code)

- `src/main/java/com/wagmi/finance/alg/Sorters.java`: implement sorting algorithms.
- `src/main/java/com/wagmi/finance/alg/Search.java`: implement binary search and helpers.
- `src/main/java/com/wagmi/finance/ds/DoublyLinkedTransactions.java`: implement a doubly linked list.
- `src/main/java/com/wagmi/finance/history/OperationStack.java`: implement an array-based stack.
- `src/main/java/com/wagmi/finance/processing/PendingQueue.java`: implement a circular-array queue.
- `src/main/java/com/wagmi/finance/recursion/RecursionUtils.java`: implement required recursive functions.
- `src/main/java/com/wagmi/finance/strings/StringUtils.java`: implement string utilities and validation.
- `src/main/java/com/wagmi/finance/budget/BudgetManager.java`: implement category budgets with hash-based structures.
- `src/main/java/com/wagmi/finance/app/FinanceApp.java`: simple console runner (keep minimal; tests focus on logic).

Tests to watch:
- `src/test/java/com/wagmi/finance/**`: each package has targeted tests + an integration test.

### 🧭 Your Goal (Pass All Tests)

Implement methods in `src/main/java/**` so that all tests under `src/test/java/**` pass. Start with the simplest structures first and run tests after every small change.

Suggested order:
1. `DoublyLinkedTransactions`
2. `OperationStack` and `PendingQueue`
3. `Sorters` and `Search`
4. `StringUtils` and `RecursionUtils`
5. `BudgetManager`
6. `FinanceApp` (light, if needed by tests)

Progress check:
- Run `./scripts/test-run.sh` after each small change.
- All green? You’re done. If red, open the mentioned test file and implement what it expects.

### 📋 TODO Checklist (Follow Along)

- [ ] Strings: make `sanitizeDescription` and `matchesDatePattern` pass all tests
- [ ] DS: implement `DoublyLinkedTransactions` add/delete/find/reverse/toArray + size tracking
- [ ] Stack: implement `OperationStack` push/pop/peek with correct overflow/underflow
- [ ] Queue: implement `PendingQueue` enqueue/dequeue with circular behavior and size
- [ ] Sorting: implement `quickSortByAmount`, `mergeSortByDate`, `insertionSortByCategory`
- [ ] Search: implement `binarySearchById`
- [ ] Recursion: implement `isValidDateRecursive`, `categoryTotalRecursive`, `generateBudgetReportRecursive`
- [ ] Budget: implement categories, limits, spending, approaching/over limit
- [ ] Integration: run the full suite and verify everything is green

### 🧱 Rules and Expectations

- Use only Java Standard Library (no external DS/Algo libs).
- Implement algorithms yourself where required (e.g., quick sort, merge sort, binary search).
- Write clean, readable code with clear names. Handle edge cases and invalid inputs.

### 🧭 Common Maven Commands

```bash
# clean build and run all tests
mvn clean test

# run tests without re-compiling unchanged classes
mvn -DskipTests=false test

# quickly re-run a specific test class
mvn -Dtest=com.wagmi.finance.recursion.RecursionUtilsTest test
```

### 🧩 Troubleshooting

- Java/Maven not found: ensure JDK and Maven are installed and on PATH.
- Failing tests: read the assertion message and open the referenced class; implement just enough to make that test pass.
- Stuck on algorithms: start with the simpler version, then optimize.
- CI-like check locally: run `mvn -q clean test` to emulate a quiet build.

### 📈 Grading (How You’re Evaluated)

- Functionality: the tests pass (unit + integration)
- Algorithm/Data Structure correctness and efficiency
- Code quality and organization
- Edge case handling

### 📝 Tips for Success

- Run tests constantly; commit frequently.
- Implement minimum code to pass one test at a time (TDD mindset).
- Prefer small, well-named methods over long ones.
- Use the `target/surefire-reports/*.txt` files for detailed failures.

### 🧪 Provided Test Suite (High-Level)

- `alg`: `SearchTest`, `SortersTest`
- `ds`: `DoublyLinkedTransactionsTest`
- `history`: `OperationStackTest`
- `processing`: `PendingQueueTest`
- `recursion`: `RecursionUtilsTest`
- `strings`: `StringUtilsTest`
- `budget`: `BudgetManagerTest`
- `app`: `FinanceAppSmokeTest`
- `integration`: `FinanceSystemIntegrationTest`

When all of these are green, you’re done.

---

You’ve got this—ship small, test often, and make the build green. ✅


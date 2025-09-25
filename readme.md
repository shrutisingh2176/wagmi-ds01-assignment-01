# 🏦 DSA Capstone Project: Personal Finance Management System

## 📖 Project Overview

You are tasked with building a comprehensive **Personal Finance Management System** that integrates **ALL** concepts learned in your 6-week Data Structures and Algorithms course. This is not just a collection of separate programs—it's a single, cohesive application that demonstrates mastery of every topic covered.

## 🎯 Learning Objectives

By completing this capstone project, you will demonstrate proficiency in:
- Java fundamentals and object-oriented programming
- Array operations and sorting algorithms
- Search algorithms and optimization
- Hash tables and collision handling
- Linked list implementations and operations
- Stack and queue data structures
- Recursion and recursive problem solving
- String manipulation and validation
- Algorithm analysis and complexity understanding

## 🛠️ Project Requirements

### Core System Architecture

Your system must implement the following components:

#### 1. **Transaction Management (Week 4 - Linked Lists)**
- Create a `Transaction` class with fields:
  - Unique ID, description, amount, category, date, income/expense flag
- Implement a **doubly linked list** to store transaction history
- Support operations: add, delete, search, reverse order
- Maintain size counter and proper pointer management

#### 2. **Data Processing (Week 2-3 - Arrays & Sorting)**
- Convert linked list to arrays for sorting operations
- Implement **at least 2 different sorting algorithms**:
  - Quick Sort (sort by amount)
  - Merge Sort (sort by date)
  - Insertion/Selection Sort (your choice for a third criterion)
- Implement **Binary Search** for efficient transaction lookup by ID
- Handle edge cases: empty arrays, single elements, duplicate values

#### 3. **Category and Budget Management (Week 4 - Hashing)**
- Use **HashMap** to store budget limits per category
- Use **HashMap** to track current spending per category
- Use **HashSet** to maintain valid category list
- Implement collision handling and demonstrate hash table efficiency
- Detect and prevent duplicate entries

#### 4. **Operation History (Week 5 - Stacks)**
- Implement a **Stack** to track user operations for undo functionality
- Support operations: push, pop, peek, isEmpty
- Store operation strings (e.g., "ADD:123", "DELETE:456")
- Handle stack overflow and underflow gracefully

#### 5. **Transaction Processing (Week 5 - Queues)**
- Implement a **Queue** for pending transaction processing
- Use circular array implementation
- Support batch processing of queued transactions
- Handle queue full/empty states

#### 6. **Advanced Analysis (Week 6 - Recursion)**
Implement **at least 3 recursive functions**:
- **Recursive validation**: Validate date formats (YYYY-MM-DD)
- **Recursive calculation**: Calculate category totals from linked list
- **Recursive analysis**: Generate budget reports with category breakdown
- **Bonus**: Palindrome detection in transaction descriptions

#### 7. **String Processing (Week 6 - Strings)**
- Input validation and sanitization
- Pattern matching for date formats
- String parsing for transaction data
- Format output strings for reports

#### 8. **User Interface (Week 1 - Java Basics)**
- Menu-driven console application
- Input validation with proper error handling
- Loops for continuous operation
- Conditional logic for different user choices
- Exception handling for all user inputs

## 🏗️ Required Features

### Must-Have Functionality:

1. **Add Transaction**
   - Validate all inputs (amount > 0, valid category, proper date format)
   - Use recursion for date validation
   - Add to pending queue, then process to main list

2. **View Transactions**
   - Display all transactions from linked list
   - Show total count and basic statistics

3. **Delete Transaction**
   - Remove by ID from linked list
   - Update category spending accordingly
   - Add operation to undo stack

4. **Search Transaction**
   - Sort array by ID first, then use binary search
   - Display search results with performance metrics

5. **Sort Transactions**
   - Offer multiple sorting criteria (amount, date, category)
   - Use different algorithms for different criteria
   - Display sorted results

6. **Budget Management**
   - Set budget limits per category
   - Track spending against budgets
   - Warn when approaching/exceeding limits

7. **Generate Reports**
   - Use recursion to calculate category totals
   - Show income vs. expenses
   - Budget analysis with percentages
   - Top spending categories

8. **Undo Operations**
   - Use stack to reverse last operations
   - Support multiple levels of undo

9. **Process Pending Transactions**
   - Process all transactions in queue
   - Update spending totals
   - Check budget violations

## 🧪 Technical Specifications

### Data Structure Requirements:
- **Doubly Linked List**: Manual implementation (no ArrayList)
- **Stack**: Array-based implementation with fixed capacity
- **Queue**: Circular array implementation
- **HashMap**: Use Java's built-in for budget/category management
- **HashSet**: Use Java's built-in for category validation

### Algorithm Requirements:
- **Sorting**: Implement Quick Sort and Merge Sort from scratch
- **Searching**: Implement Binary Search from scratch
- **Recursion**: Minimum 3 recursive functions with different purposes

### Code Quality Requirements:
- Proper error handling for all user inputs
- Clear variable naming and code organization
- Comments explaining complex algorithms
- Modular design with separate methods for each feature

## 📊 Complexity Analysis

For each major operation, document:
- **Time Complexity**: Best, average, and worst case
- **Space Complexity**: Additional memory usage
- **Trade-offs**: Why you chose specific algorithms

Example operations to analyze:
- Adding a transaction: O(?)
- Sorting by amount: O(?)
- Binary search: O(?)
- Recursive category calculation: O(?)

## 🎮 Testing Requirements

### Test Cases You Must Handle:
1. **Empty System**: All operations on empty data structures
2. **Single Element**: Operations with only one transaction
3. **Large Dataset**: Performance with 100+ transactions
4. **Invalid Inputs**: Negative amounts, invalid dates, wrong categories
5. **Edge Cases**: Maximum capacity, duplicate IDs, null values
6. **Algorithm Verification**: Verify sorting produces correct order

### Suggested Test Data:
Create transactions with:
- Different amounts (including very small and large values)
- Various categories and dates
- Both income and expense transactions
- Duplicate descriptions and amounts
- Edge case dates (year boundaries, invalid formats)

## 🏆 Advanced Challenges (Bonus Points)

If you want to push your skills further:

1. **Performance Optimization**
   - Implement custom hash function
   - Add caching for frequently accessed data
   - Optimize recursive functions to avoid stack overflow

2. **Additional Features**
   - Transaction categories with subcategories (tree structure)
   - Monthly/yearly report generation
   - Import/export functionality with file I/O
   - Search by multiple criteria

3. **Algorithm Variations**
   - Implement Heap Sort for another sorting option
   - Add interpolation search for uniformly distributed data
   - Implement recursive Quick Sort vs iterative version

## 📋 Deliverables

Submit the following:

1. **Source Code**: Complete Java implementation
2. **README**: Setup and usage instructions
3. **Test Report**: Document your test cases and results
4. **Complexity Analysis**: Big-O analysis for all major operations
5. **Demo Data**: Sample transactions for testing
6. **Reflection**: 1-page reflection on challenges faced and lessons learned

## 🕒 Timeline Suggestions

- **Week 1**: Plan architecture, implement basic classes
- **Week 2**: Implement linked list and basic operations
- **Week 3**: Add sorting and searching algorithms
- **Week 4**: Implement stack/queue and hash table features
- **Week 5**: Add recursive functions and advanced features
- **Week 6**: Testing, optimization, and documentation

## 📈 Grading Criteria

- **Functionality** (40%): All required features work correctly
- **Algorithm Implementation** (25%): Proper implementation of data structures and algorithms
- **Code Quality** (20%): Clean, readable, well-organized code
- **Testing** (10%): Comprehensive test cases and edge case handling
- **Documentation** (5%): Clear comments and complexity analysis

## 🚨 Important Notes

- **No external libraries** except Java standard library
- **Manual implementation** required for core data structures
- **Original work only** - this tests YOUR understanding
- **Handle all edge cases** - robust error handling expected
- **Document your complexity analysis** - show your algorithmic thinking

## 💡 Success Tips

1. **Start with the data structures** - they're the foundation
2. **Test incrementally** - don't build everything before testing
3. **Handle edge cases early** - they're often forgotten until the end
4. **Use meaningful variable names** - your code should be self-documenting
5. **Draw diagrams** - visualize your linked lists and tree structures
6. **Time your algorithms** - see the performance differences in practice

---

**Remember**: This capstone project is designed to be challenging. It integrates everything you've learned into one comprehensive system. Take your time, plan carefully, and don't hesitate to revisit earlier concepts when needed. The goal is to demonstrate mastery of DSA concepts in a practical, real-world application.

**Good luck, and happy coding! 🚀**

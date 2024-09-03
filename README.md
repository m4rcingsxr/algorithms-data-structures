# :boom: data-structures & algorithms :boom:
Data Structures are fundamental concepts in computer science used to organize and store data efficiently. They define a systematic way to manage and manipulate data, which enhances the performance of algorithms and applications. By choosing the appropriate data structure, you can improve both the speed and resource usage of your programs.

Algorithms are well-defined procedures or sets of instructions designed to perform specific tasks or solve problems. They provide a step-by-step approach to processing data and achieving desired results. Algorithms are essential for tasks ranging from sorting and searching to complex operations in machine learning and data analysis.

# Building and Running the Project
This project is built using [Apache Maven](https://maven.apache.org/), a build automation tool used primarily for Java projects. It handles project dependencies, builds the code, and runs tests.

## Java Version
The project uses Java 17. Ensure you have Java 17 installed on your machine. If not, you can download and install it from [here](https://openjdk.org/).

# Running a Single Java Class
To run a specific Java class that contains a main method, you can use the Maven Exec Plugin. This can be done with either a locally installed Maven version or the Maven Wrapper.

### Using the Maven Wrapper
1. Navigate to the Project Root Directory:

   Open a terminal and navigate to the root directory of your project.
2. Execute the Main Class:

   Run the following command, replacing com.example.MainClass with the fully qualified name of your Java class:
    ```shell
      ./mvnw exec:java -Dexec.mainClass="com.example.MainClass"
    ```    
   <b>For windows</b>
      ```shell
      mvnw.cmd exec:java -Dexec.mainClass="com.example.MainClass"
    ``` 
### Using Installed Maven
1. Navigate to the Project Root Directory:

   Open a terminal and navigate to the root directory of your project.
2. Execute the Main Class:

   Run the following command, replacing com.example.MainClass with the fully qualified name of your Java class:
   ```shell
    mvn exec:java -Dexec.mainClass="com.example.MainClass"
    ```

# Running Specific Tests
To run a specific test class or method using Maven, you can use the Maven Surefire Plugin.

### Using the Maven Wrapper
1. Navigate to the Project Root Directory:

    Open a terminal and navigate to the root directory of your project.

2. Run a Specific Test Class:

    Replace com.example.MyTestClass with the fully qualified name of your test class:

    ```shell
      ./mvnw -Dtest=com.example.MyTestClass test
    ```
   
    <b>For Windows</b>
    ```shell
      mvnw.cmd -Dtest=com.example.MyTestClass test
    ```

3. Run a Specific Test Method:

   Replace com.example.MyTestClass#myTestMethod with the fully qualified name of your test method:
    ```shell
    ./mvnw -Dtest=com.example.MyTestClass#myTestMethod test
    ```

   <b>For Windows</b>
    ```shell
      mvnw.cmd -Dtest=com.example.MyTestClass#myTestMethod test
    ```

### Using Installed Maven
1. Navigate to the Project Root Directory:

    Open a terminal and navigate to the root directory of your project.
2. Run a Specific Test Class:

    Replace com.example.MyTestClass with the fully qualified name of your test class:
    ```shell
      mvn -Dtest=com.example.MyTestClass test
    ```
3. Run a Specific Test Method:

   Replace com.example.MyTestClass#myTestMethod with the fully qualified name of your test method:
    ```shell
    mvn -Dtest=com.example.MyTestClass#myTestMethod test
    ```
# Data Structures

- :link: [LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist)
    - [DOUBLY LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/DoublyLinkedList.java)
    - [SINGLY LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/SinglyLinkedList.java)
    - [SINGLY CIRCULAR LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/CircularSinglyLinkedList.java)
    - [DOUBLY CIRCULAR LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/CircularDoublyLinkedList.java)
- :books: [STACK](src/main/java/com/marcinseweryn/algorithms/datastructures/stack)
    - [ARRAY](src/main/java/com/marcinseweryn/algorithms/datastructures/stack/StackArray.java)
    - [LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/stack/StackLinkedList.java)
- :walking:[QUEUE](src/main/java/com/marcinseweryn/algorithms/datastructures/queue)
    - [CIRCULAR ARRAY](src/main/java/com/marcinseweryn/algorithms/datastructures/queue/CircularArrayQueue.java)
    - [LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/queue/LinkedListQueue.java)
- :evergreen_tree: [TREE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree)
    - [BINARY SEARCH TREE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/BST.java)
    - [AVL TREE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/AVL.java)
    - [TRIE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/Trie.java)
- :key: [HASHING - COLLISION RESOLUTION](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing)
    - [SEPARATE CHAINING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/SeparateChaining.java)
    - [LINEAR PROBING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/LinearProbing.java)
    - [QUADRATIC PROBING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/QuadraticProbing.java)
    - [DOUBLE HASHING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/DoubleHashing.java)
- [PRIORITY QUEUE](src/main/java/com/marcinseweryn/algorithms/datastructures/priorityqueue/README.md#priority-queue)
    - [BINARY HEAP FAST REMOVE](src/main/java/com/marcinseweryn/algorithms/datastructures/priorityqueue/PriorityQueue.java)
    - [BINARY HEAP](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/BinaryHeap.java)
    - [BINARY HEAP RECURSIVE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/BinaryHeap.java)
# Algorithms
- [SORTING](src/main/java/com/marcinseweryn/algorithms/sorting)
    - [BUBBLE SORT](src/main/java/com/marcinseweryn/algorithms/sorting/BubbleSort.java)
    - [SELECTION SORT](src/main/java/com/marcinseweryn/algorithms/sorting/SelectionSort.java)
    - [INSERTION SORT](src/main/java/com/marcinseweryn/algorithms/sorting/InsertionSort.java)
    - [BUCKET SORT](src/main/java/com/marcinseweryn/algorithms/sorting/BucketSort.java)
    - [MERGE SORT](src/main/java/com/marcinseweryn/algorithms/sorting/MergeSort.java)
    - [QUICK SORT](src/main/java/com/marcinseweryn/algorithms/sorting/QuickSort.java)
    - [HEAP SORT](src/main/java/com/marcinseweryn/algorithms/sorting/HeapSort.java)

- **SEARCHING**
    - [BINARY SEARCH](src/main/java/com/marcinseweryn/algorithms/searching/BinarySearch.java)

- [GRAPHS](src/main/java/com/marcinseweryn/algorithms/graphs)
    - [ADJACENCY MATRIX](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/GraphMatrix.java)
        - BREADTH-FIRST SEARCH [QUEUE](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/BreadthFirstSearch.java)
        - DEPTH-FIRST SEARCH [STACK](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/DepthFirstSearchStack.java) / [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/DepthFirstSearchRecursive.java)
        - TOPOLOGICAL SORT [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/TopologicalSortRecursive.java) /[STACK](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/TopologicalSortStack.java)
        - SINGLE SOURCE SHORTEST PATH PROBLEM
            - [BST](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/SingleSourceShortestPathBST.java)
            - [BELLMAN FORD](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/BellmanFord.java)
        - ALL PAIRS SHORTEST PATH [FLOYD WARSHALL](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/FloydWarshall.java)
        - MINIMUM SPANNING TREE
            - [LAZY PRIM'S ALGORITHM](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/LazyPrim.java)
    - [ADJACENCY LIST](src/main/java/com/marcinseweryn/algorithms/graphs/list/GraphList.java)
        - BREADTH-FIRST SEARCH [BFS](src/main/java/com/marcinseweryn/algorithms/graphs/list/BreadthFirstSearch.java)
        - DEPTH-FIRST SEARCH [STACK](src/main/java/com/marcinseweryn/algorithms/graphs/list/DepthFirstSearchStack.java) / [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/list/DepthFirstSearchRecursive.java)
        - TOPOLOGICAL SORT [STACK](src/main/java/com/marcinseweryn/algorithms/graphs/list/TopologicalSortStack.java) / [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/list/TopologicalSort.java)
        - SINGLE SOURCE SHORTEST PATH PROBLEM
            - [BFS](src/main/java/com/marcinseweryn/algorithms/graphs/list/SingleSourceShortestPathBST.java)
            - [LAZY DIJKSTRA](src/main/java/com/marcinseweryn/algorithms/graphs/list/LazyDijkstra.java)
            - [BELLMAN FORD(ADJACENCY LIST)](src/main/java/com/marcinseweryn/algorithms/graphs/list/BellmanFordAdjacencyList.java) / [BELLMAN FORD(EDGE LIST)](src/main/java/com/marcinseweryn/algorithms/graphs/list/BellmanFordEdgeList.java)
        - MINIMUM SPANNING TREE
            - [LAZY PRIM'S ALGORITHM](src/main/java/com/marcinseweryn/algorithms/graphs/list/LazyPrims.java) 


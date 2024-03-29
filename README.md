# :boom: data-structures & algorithms :boom:
:star:Data Structures:star: are the programmatic way of storing data so that data can be used efficiently. :star:Algorithm:star: :star:is a step-by-step procedure, which defines a set of instructions to be executed in a certain order to get the desired output.
# Contributing
This repository is open for contributing. You can add new or improve already existing algorithm. I would appreciate if you check out [CONTRIBUTING.md](docs/CONTRIBUTING.md) file before creating pull request. Thanks for Your time. :blush:
# Running algorithm
This project is customized to build with [Gradle Build Tool](https://gradle.org/). Gradle is an open-source build automation tool flexible enough to build almost any type of software :green_heart:
## Build with Gradle
The recommended way to execute any Gradle build is with the help of the Gradle Wrapper. The Wrapper is a script that invokes a declared version of Gradle, downloading it beforehand if necessary. All you need to do is to run commands below:

Wrapper script:
- ./gradlew -> linux/mac OS
- gradlew.bat Windows OS

To build whole project use
```
./gradlew build
```
build include:
```
:build
+--- :assemble
|    \--- :jar
|         \--- :classes
|              +--- :compileJava
|              \--- :processResources
\--- :check
     \--- :test
          +--- :classes
          |    +--- :compileJava
          |    \--- :processResources
          \--- :testClasses
               +--- :compileTestJava
               |    \--- :classes
               |         +--- :compileJava
               |         \--- :processResources
               \--- :processTestResources
```
Run a single algorithm:
```
./gradlew -PmainClass=<fully-qualified-class-name> runAlgorithm
```
e.g.
```
./gradlew -PmainClass=com.marcinseweryn.algorithms.sorting.MergeSort runAlgorithm
```
Run a single test
```
./gradlew test --tests com.marcinseweryn.algorithms.sorting.MergeSortTest 
```
e.g.
```
./gradlew test --tests <fully-qualified-class-name>  
```
Get the test result report in html
```
./gradlew testReport
```

## Compile and run with JDK
#### Setup Java
- Download and install [Java](https://www.oracle.com/pl/java/technologies/downloads/#java17)
#### Setup folder structure
1. Place your class files inside a directory—for example, /home/user/classdir.
Note that this directory is the base directory for the package tree. If you
add the class com.horstmann.corejava.Employee, then the Employee.class file must
be located in the subdirectory /home/user/classdir/com/horstmann/corejava.
2. Place any JAR files inside a directory—for example, /home/user/archives.
3. Set the class path. The class path is the collection of all locations that can
contain class files.
#### Compile
```
javac -classpath/jar MyProg.java
```
#### Run
```
java -classpath classes/jar MyProg
```
e.g.
```
java -classpath /home/user/classdir:.:/home/user/archives/archive.jar MyProg
```
or
```
java -classpath c:\classdir;.;c:\archives\archive.jar MyProg
```

# Data Structures

- :link: [LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist)
    - [DOUBLY LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/LinkedList.java)]
    - [SINGLY LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/SinglyLinkedList.java)]
    - [SINGLY CIRCULAR LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/CircularSinglyLinkedList.java)]
    - [DOUBLY CIRCULAR LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/linkedlist/CircularDoublyLinkedList.java)]
- :books: [STACK](src/main/java/com/marcinseweryn/algorithms/datastructures/stack)
    - [ARRAY](src/main/java/com/marcinseweryn/algorithms/datastructures/stack/StackArray.java)
    - [LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/stack/StackLinkedList.java)
- :walking:[QUEUE](src/main/java/com/marcinseweryn/algorithms/datastructures/queue)
    - [CIRCULAR ARRAY](src/main/java/com/marcinseweryn/algorithms/datastructures/queue/CircularArrayQueue.java)
    - [LINKED LIST](src/main/java/com/marcinseweryn/algorithms/datastructures/queue/LinkedListQueue.java)
- :evergreen_tree: [TREE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree)
    - [BINARY SEARCH TREE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/BST.java)
    - [AVL TREE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/AVL.java)
    - [BINARY HEAP](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/binary/BinaryHeap.java)
    - [TRIE](src/main/java/com/marcinseweryn/algorithms/datastructures/tree/Trie.java)
- :key: [HASHING - COLLISION RESOLUTION](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing)
    - [SEPARATE CHAINING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/SeparateChaining.java)
    - [LINEAR PROBING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/LinearProbing.java)
    - [QUADRATIC PROBING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/QuadraticProbing.java)
    - [DOUBLE HASHING](src/main/java/com/marcinseweryn/algorithms/datastructures/hashing/DoubleHashing.java)

# Algorithms
- [SORTING](src/main/java/com/marcinseweryn/algorithms/sorting)
    - [BUBBLE SORT](src/main/java/com/marcinseweryn/algorithms/sorting/BubbleSort.java)
    - [SELECTION SORT](src/main/java/com/marcinseweryn/algorithms/sorting/BubbleSort.java)
    - [INSERTION SORT](src/main/java/com/marcinseweryn/algorithms/sorting/InsertionSort.java)
    - [BUCKET SORT](src/main/java/com/marcinseweryn/algorithms/sorting/BucketSort.java)
    - [MERGE SORT](src/main/java/com/marcinseweryn/algorithms/sorting/MergeSort.java)
    - [QUICK SORT](src/main/java/com/marcinseweryn/algorithms/sorting/QuickSort.java)
    - [HEAP SORT](src/main/java/com/marcinseweryn/algorithms/sorting/HeapSort.java)

- **SEARCHES**
    - [BINARY SEARCHING](src/main/java/com/marcinseweryn/algorithms/searching/BinarySearch.java)

- [GRAPHS](src/main/java/com/marcinseweryn/algorithms/graphs)
    - [ADJACENCY MATRIX](src/main/java/com/marcinseweryn/algorithms/graphs/list/GraphList.java)
      - BREADTH-FIRST SEARCH [QUEUE](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/BreadthFirstSearch.java)
      - DEPTH-FIRST SEARCH [STACK](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/DepthFirstSearch.java) / [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/DepthFirstSearch.java)
      - TOPOLOGICAL SORT [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/TopologicalSortRecursiveArray.java)
      - SINGLE SOURCE SHORTEST PATH PROBLEM [BST](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/SingleSourceShortestPathBST.java)
    - [ADJACENCY LIST](src/main/java/com/marcinseweryn/algorithms/graphs/matrix/GraphMatrix.java) 
        - BREADTH-FIRST SEARCH [QUEUE](src/main/java/com/marcinseweryn/algorithms/graphs/list/BreadthFirstSearch.java)
        - DEPTH-FIRST SEARCH [STACK](src/main/java/com/marcinseweryn/algorithms/graphs/list/DepthFirstSearchStack.java) / [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/list/DepthFirstSearchRecursive.java)
        - TOPOLOGICAL SORT [STACK](src/main/java/com/marcinseweryn/algorithms/graphs/list/TopologicalSortStack.java) / [RECURSIVE](src/main/java/com/marcinseweryn/algorithms/graphs/list/TopologicalSort.java)
        - SINGLE SOURCE SHORTEST PATH PROBLEM [BST](src/main/java/com/marcinseweryn/algorithms/graphs/list/SingleSourceShortestPathBST.java) / [BST/BETWEEN TWO VERTICES](src/main/java/com/marcinseweryn/algorithms/graphs/list/SingleSourceShortestPathBST.java)

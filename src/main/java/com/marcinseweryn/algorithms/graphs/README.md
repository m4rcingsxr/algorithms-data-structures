# <center>Graph</center>

> Graph consist of a finite set of Vertices(nodes) and a set of Edges which connect a pair of nodes
![img/img.png](img/img.png)

## Graph Terminology

![img/img_1.png](img/img_1.png)

![img/img_3.png](img/img_3.png)

## Graph Representation

- [**Adjacency Matrix**](matrix/GraphMatrix.java)  : an adjacency matrix is a square matrix. And the elements of the
  matrix indicate whether pairs of vertices are adjacent or not in the graph
    - Use when a graph is complete or almost complete(all nodes has edge to each other), else it's not space efficient!
- [**Adjacency List**](list/GraphList.java) : an adjacency list is a collection of unordered list used to
  represent a graph. Each list describes the set of neighbors of a vertex in the graph
    - If the number of edges are few then we should use Adjacency List(Matrix is not optimal, because most of the cells
      will be empty)
      ![img/img_4.png](img/img_4.png)


## Breadth-First Search [list](list/BreadthFirstSearch.java) [matrix](matrix/BreadthFirstSearch.java)
- The **Breadth-First Search** is algorithm used to explore nodes and edges of a graph.
  It run with time complexity of O(V+E)(vertices + Edges) / space complexity O(V+E) and is often used as building block
  in other algorithms
- Undirected/Directed unweighted graphs
- The BFS algorithm is particular useful for one thing: finding the shortest path on unweighted graphs
- First visit the closest vertices
  ![img/img.png](img/img_5.png)

        BFS uses a queue data structure to track which node is visited next.

## Depth-First Search [list/stack](list/DepthFirstSearchStack.java) [list/recursive](list/DepthFirstSearchRecursive.java) [matrix/stack](matrix/DepthFirstSearch.java) 

- The **Depth First Search** is the most fundamental search algorithm used to explore nodes and edges of a graph.
- It runs with a time complexity O(V+E) and is often used as a building block in other algorithms
- Directed or undirected edges

As the name suggests, a DFS plunges depth first into a graph without regard for which edge it takes next until it
cannot go any further at which point it backtracks and continues.

![gif](https://www3.cs.stonybrook.edu/~skiena/combinatorica/animations/anim/dfs.gif)

## BFS vs DFS

|                             | BFS                                                       | DFS                                                           |
|-----------------------------|-----------------------------------------------------------|---------------------------------------------------------------|
| Internally                  | It goes in breath first                                   | it goes in depth first                                        |
| what ds does use internally | Queue                                                     | Stack  /  Recursive                                           |
| Time complexity             | O(V+E)                                                    | O(V+E)                                                        |y
| Space complexity            | O(V+E)                                                    | O(V+E)                                                        |
| When to use                 | If we know that the target is close to the starting point | If we already know that the target vertex is buried very deep |

## Topological Sort

> - ([TS/MATRIX](adjacency/matrix/Graph.java))  ([TS/LIST](adjacency/list/Graph.java))

- Directed acyclic graphs
    - graph which contain a cycle cannot have topological ordering
    - valid topological ordering only for Directed Acyclic Graph
- Sorts given actions in such a way that if there is a dependency of one action on another, then the dependent action
  always comes later than its parent action (identify order of task) (arrows indicated dependencies)
  ![img/img_6.png](img/img_6.png)
  ![img/img_8.png](img/img_8.png)
- Topological ordering is an ordering of the nodes in a directed graph where for each directed edge from
  node A to node B, node A appears before node B in the ordering.
- Topological sort algorithm can find a topological ordering in O(V+E) time!
- All root trees have a topological ordering since they do not contain any cycle

> We dont traverse through all edges for each vertex's, only for unvisited,
> We are looping through all vertex's to make sure that each one was visited

| Time Complexity | Space Complexity |
|-----------------|------------------|
| O(V+E)          | O(V+E)           |

## Single Source Shortest Path Problem

> Finding a path between a given vertex(called source) to all other vertices in a graph
> such that the total distance between them (source and destination) is minimum

The Problem:

- Five destination in different cities.
- Travel cost between cities are known
- Find the cheapest way from prime destination to desired destination
## BFS
- Only for unweighted graphs
- ![img/img_11.png](img/img_11.png)
- **Time: O(E)(E=VERTEX*NEIGHBORS) / Space:O(V)**
- ![img/img_12.png](img/img_12.png)- Do not work with weighted graphs (BFS - might ignore some edges!)


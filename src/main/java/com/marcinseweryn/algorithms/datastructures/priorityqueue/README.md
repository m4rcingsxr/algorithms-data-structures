# Priority Queue
> A Priority queue is a an Abstract Data Type that operates similar to a normal queue
> except that each element has a certain priority. The priority of the elements in the
> priority queue determine the order in which elements are removed from the PQ

### Heap 
> One way of implement Priority Queue is with use of Heap. Heap is a tree based Data structure
> that satisfies the heap invariant : If A is a parent no of B then A is ordered with respect 
> to B for all node A,B in the heap
>-  [Heap](../tree/README.md#binary-heap) [src](../tree/binary/BinaryHeap.java)

### Why PQ?
- Used in certain implementations of Dijkstra's shortest path algorithm
- Anytime you need the dynamically fetch the 'next best' or 'next word' element.
- Used by Minimum Spanning Tree (MST) algorithm
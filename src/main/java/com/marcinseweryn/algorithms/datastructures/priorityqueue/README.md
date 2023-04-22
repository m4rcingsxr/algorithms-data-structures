# Priority Queue

> A Priority queue is a an Abstract Data Type that operates similar to a normal queue
> except that each element has a certain priority. The priority of the elements in the
> priority queue determine the order in which elements are removed from the PQ

### Heap

> One way of implement Priority Queue is with use of Heap. Heap is a tree based Data structure
> that satisfies the heap invariant : If A is a parent no of B then A is ordered with respect
> to B for all node A,B in the heap
>- [Heap](../tree/README.md#binary-heap) [src](../tree/binary/BinaryHeap.java)

### Why PQ?

- Used in certain implementations of Dijkstra's shortest path algorithm
- Anytime you need the dynamically fetch the 'next best' or 'next word' element.
- Used by Minimum Spanning Tree (MST) algorithm

# Indexed Priority Queue

> An Indexed Priority Queue is a traditional priority queue variant which on top of the regular PQ
> operations supports quick updates and deletion of key-value pairs.

### Why Indexed PQ?

> We are able to dynamically update the priority(value) of certain keys

> The indexed Priority Queue data structure lets us do this efficiently.
> The first step to using an IPQ is to assign index values to all the keys
> forming a bidirectional mapping between your N keys and the domain [0, N)
> using a bidirectional hashtable

> If 'k' is the key we want to update first get the key's index: ki = map[k],
> then use 'ki' with the IPQ
> - delete(ki)
> - valueOf(ki)
> - ...

| Operation              | Indexed Binary Heap PQ |
|------------------------|------------------------|
| delete(ki)             | O(log(N))              |
| valueOf(ki)            | O(1)                   |
| contains(ki)           | O(1)                   |
| peekMinKeyIndex()      | O(1)                   |
| pollMinKeyIndex()      | O(log(N))              |
| peekMinValue()         | O(1)                   |
| pollMinValue()         | O(log(N))              |
| insert(ki, value)      | O(log(N))              |
| decreaseKey(ki, value) | O(log(N))              |
| increaseKey(ki, value) | O(log(N))              |

### PseudoCode (MIN INDEXED BINARY HEAP PQ)

```
# Inserts a value into the min indexed binary 
# heap. The key index must not already be in
# the heap and the value msut not be null.

# pm - Position map maintain to tell us the index of the node in the heap for a given key index
# im - (inverse map) If we know the position of the node we are able to get Key index
# values - To access the value for any given key k, find its key index (ki) and do a lookup
           in the vals array maintained by the IPQ
function insert(ki, value):
    values[ki] = value
    # sz is the current size of the heap
    pm[ki] = sz
    im[sz] = ki
    heapifyFromBottomToTop(sz)
    sz = sz + 1

# heapify from bottom to top for node i (zero based) until heap invariant is satisfied.
function heapifyFromBottomToTop(i):
    for (p = (i - 1)/2; i>0 and less(i,p)):
        swap(i,p)
        i = p
        p = (i - 1)/2

function swap(i,j):
    pm[im[j]] = i
    pm[im[i]] = j
    tmp = im[i]
    im[i] = im[j]
    im[j] = tmp

function less(i, j):
    return values[im[i]] < values[im[j]]

# Deletes the node with the key index ki in the heap. The key index ki must exist
# and be present in the heap.
function remove(ki):
    i = pm[ki]
    swap(i, sz)
    sz = sz - 1
    heapifyFromBottomToTop(i)
    heapifyFromTopToBottom(i)
    values[ki] = null
    pm[ki] = -1
    im[sz] = -1

# heapify from top to bottom node at index i by swapping
# itself with the smalles of the left or the right child node
function heapifyFromTopToBottom(i):
    while true:
        left = 2*i + 1
        right = 2*i +2
        smallest = left
        if right < sz and less(right, left):
            smallest = right
        if left >= sz or less(i, smallest):
            break
        
        swap(smallest, i)
        i = smallest        

     
```
### Decrease and Increase Key
In many apps(eager Dijkstra and Prims algorithm) it is ofter useful to only update a given key
to make its value either always smaller (or larger). In the event that a worse value is given the 
value in the IPQ should not be updated.

In such a case it's useful to define a more restrictive form of update operation we call
increaseKey(ki, v) and decreaseKey(ki, v)

```
function increaseKey(ki, v):
    if less(value, values[ki]):
        values[ki] = value
        heapifyBottomToTop(pm[ki])
        
function decreaseKey(ki, v):
    if less(values[ki], value):
        values[ki] = value
        heapifyTopToBottom(pm[ki])
```
---
resource:

- https://www.youtube.com/watch?v=jND_WJ8r7FE&t=126s
- https://www.youtube.com/watch?v=GLIRnUhknP0&t=55s
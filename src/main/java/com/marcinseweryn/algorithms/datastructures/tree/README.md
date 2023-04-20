# <center>TREE</center>

        In computer science, a tree is a widely used abstract data type that represents a hierarchical tree structure
        with a set of connected nodes. Each node in the tree can be connected to many children (depending on the type of tree),
        but must be connected to exactly one parent, except for the root node, which has no parent. 
        
        - Quicker and easier access to a data than linear(O(N))
        - Store hierarchical data like folder structure
        - Many different types of data structures which performs better in various situations

## Tree types

- [BINARY TREE](#binary-tree) [[(ARRAY) src](binary/BinaryTreeArray.java)] [[(Linked List) src](binary/BinaryTreeLinkedList.java)]
    - [BINARY SEARCH TREE](#center-binary-search-tree-center) [[src](binary/bst/BST.java)]
    - [AVL TREE](#center-avl-tree-center) [[src](binary/avl/AVL.java)]
    - [RED BLACK TREE](rbt) [[src](binary/bst/BST.java)]
    - [BINARY HEAP](#center-binary-heap-center) [[src](binary/binaryheap/BinaryHeap.java)]
- [TRIE](#center-trie-center) [[src](trie/Trie.java)]

## Tree terminology

<p align="center">
  <img src="img/img.png" alt="img"/>
</p>
[Tree example](TreeNode.java)

# Binary Tree

    - Each node at most have 2 children
    - Family of data structure -> BinaryHeap, Binary Search Tree, AVL tree
    - Many problem can be solved using Binary Tree

## Types of Binary Tree

![img/img_2.png](img/img_2.png)

## Traverse

![img/img_5.png](img/img_5.png)

| ARRAY                           | LINKED LIST                     |
|---------------------------------|---------------------------------|
| ![img/img_3.png](img/img_3.png) | ![img/img_4.png](img/img_4.png) |

<table>
<tr>
</tr>
<tr>
<td>
<pre>

| ARRAY                 | Time Complexity[] | Space Complexity[] | 
|-----------------------|-------------------|--------------------|
| Create BT             | O(1)              | O(N)               | 
| Insert node to BT     | O(1)              | O(1)               | 
| Delete a node from BT | O(N)              | O(1)               | 
| Search for node in BT | O(N)              | O(1)               | 
| Traverse BT           | O(N)              | O(1)               | 
| Delete entire BT      | O(1)              | O(1)               | 
| Space Efficient?      | NO                | NO                 |

</pre>
</td>
<td>
<pre>

| LINKED LIST           | Time Complexity[] | Space Complexity[] | 
|-----------------------|-------------------|--------------------|
| Create BT             | O(1)              | O(1)               | 
| Insert node to BT     | O(N)              | O(N)               | 
| Delete a node from BT | O(N)              | O(N)               | 
| Search for node in BT | O(N)              | O(N)               | 
| Traverse BT           | O(N)              | O(N)               | 
| Delete entire BT      | O(1)              | O(1)               | 
| Space Efficient?      | NO                | NO                 |

</pre>

</td>
</tr>
</table>

# <center>Binary Search Tree</center>

     - Value of the left subtree root node is less than or equal to it's parent node's value
     - In the right subtree the value of a root node is greater than it's parent node's value
     - BST implement with Linked List

    - Performs faster and more efficient than BinaryTree when inserting, searching and deleting node (better time & space)

![img/img6.png](img/img6.png)
![img/img7.png](img/img7.png)
![img/img_8.png](img/img_8.png)
![img/img_9.png](img/img_9.png)
![img/img_10.png](img/img_10.png)
![img/img_14.png](img/img_14.png)

## Limitation

- BST might lead to O(N) time complexity for add/delete/search methods
  ![img/img_11.png](img/img_11.png)

# <center>AVL TREE</center>

      AVL TREE - self-balancing BST where the difference between height of left and right subtrees cannot be more than one
      for all nodes

![img/img_13.png](img/img_13.png)

    If any time difference between height of left and right subtree differ by ore than one, then rebalancing is done to
    restore AVL property(balanced), by rotation
    With balancing algorithms we keep O(logN) time complexity for insert/delete/search 

#### Insertion

- Rotation is not required
  ![img/img_15.png](img/img_15.png)
- Rotation is required

| LL CONDITION                                                                                                                                                                                                                                                            | RR CONDITION                                                                                                                                                                                                                                                        |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![img_1.png](img/img_34.png)                                                                                                                                                                                                                                            | ![img/img_18.png](img/img_18.png)                                                                                                                                                                                                                                   |
| rotateRight(disbalancedNode) <br/>{<br/>   newRoot = disbalancedNode.leftChild<br/>disbalancedNode.leftChild = disbalancedNode.leftChild.rightChild<br/>newRoot.rightChild = disbalancedNode<br/>update height of disbalancedNode and newRoot<br/>return newRoot <br/>} | rotateLeft(disbalancedNode) <br/>{<br/>newRoot = disbalancedNode.rightChild<br/>disbalancedNode.rightChild = disbalancedNode.rightChild.leftChild<br/>newRoot.leftChild = disbalancedNode<br/>update height of disbalancedNode and newRoot<br/>return newRoot<br/>} |

| LR CONDITION                    | RL CONDITION                    |
|---------------------------------|---------------------------------|
| ![img_20.png](img%2Fimg_20.png) | ![img_22.png](img%2Fimg_22.png) |
|                                 |                                 |       

#### Deletion

- Delete a node
    - Case 1: Rotation is not required
        - ![img_23.png](img%2Fimg_23.png)
        - ![img_24.png](img%2Fimg_24.png)
        - ![img_26.png](img%2Fimg_26.png)

    - Case 2: Rotation is required
        - ![img_27.png](img%2Fimg_27.png)
        - ![img_28.png](img%2Fimg_28.png)
        - ![img_29.png](img%2Fimg_29.png)

- Delete the entire AVL tree
    - ![img_25.png](img%2Fimg_25.png)

#### Search

![img/img_14.png](img/img_14.png)

# <center> Trie </center>

        a tree data structure used for locating specific keys from within a set. These keys are most often strings,
        with links between nodes defined not by the entire key, but by individual characters.

> - Any node in trie can store non-repetitive multiple characters
    >
- Every node stores link of the next character of the string
>   - Every node keeps track of end of string
>   - **Each node has HashMap with pairs of character and link to the next character**

#### Insertion

![img_30.png](img%2Fimg_30.png)

#### Deletion

![img_31.png](img%2Fimg_31.png)

#### Use case

![img_32.png](img%2Fimg_32.png)

# <center>Binary Heap</center>

    Binary Heap is a complete tree (all levels are completly filled except last level, and
    the last level has all keys as left as possible)
    
    BinaryHeap is eathier Min Heap or Max Heap. Key root must be minimum(min heap) or 
    maximum (max heap) among all keys present in tree.

> **Binary tree** with following proporties:
>  - It's either min heap(root must be minimum among all keys) or max heap
>  - It's complete tree(all level complete perfect except last level - **filled from left**)

## Why BH?

- Find the minimum or maximum number among set of numbers in logN time
- Make sure that inserting additional numbers does not take more than O(logN)

## Practical use

- Prim's Algorithm
- Heap Sort
- Priority Queue

## Types of Binary Heap

- **Min heap** - the value of each node is less than or equal to the value of both its children.
- **Max heap** - it is exactly the opposite of min heap that is the value of each node is more than or equal to the
  value of both its children.

![resources/img/img](https://algorithmtutor.com/images/max-and-min-heap.png)

## Array Implementation

![resources/img/img.png](img/33.png)

## Common operations on Binary Heap

![resources/img/img_1.png](img/34.png)
![resources/img/img_2.png](img/35.png)
![resources/img/img_4.png](img/36.png)

## Pseudocode

    arr[]
    size = 0;
    Create(capacity) {
        arr = new arr[capacity + 1] -- dont include size 0
    }

  

    isEmpty {
        return size == 0
    }
    Add(val) {
        if isFull()
            resize
        
        arr[++size] = val
        heapify(size)
    }
    
    heapifyBottomToTop(index) {
        parent = index / 2
        if index <= 1 
            return
        if arr[parent] > arr[index]  -- min heap
            swap(arr[parent], arr[index])
    }

    heapifyTopToBottom(index) {
        left = index * 2
        right = index * 2 + 1
        if left > size -- Base case
            return
        if size == left -- 1 child, right - OutOfBoundException
            minIndex = left
        else
            minIndex = arr[left] < arr[right] ? left : right
        if arr[index] > arr[minIndex]
            swap arr[index] arr[minIndex]
        heapifyTopToBottom(minIndex)
    }
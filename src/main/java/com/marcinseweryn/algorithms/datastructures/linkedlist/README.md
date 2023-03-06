# <center>Linked List</center>

        Linked list is a linear collection of data elements whose each element points to the next 
        It is a data structure consisting of a collection of nodes which together represent a
        sequence. In its most basic form, each node contains: data, and a reference to the next 
        node in the sequence. This structure allows for efficient insertion or removal of elements 
        from any position in the sequence during iteration.

        The first node in the list is called the head, 
        and the last node in the list is called the tail.



<table>
<tr>
<th>ARRAY VS LINKED LIST</th>
<th>Linked List Types</th>
</tr>
<tr>
<td>
<pre>

| Operation                     | Static Array | Linked List |
|-------------------------------|--------------|-------------|
| Creation                      | O(1)         | O(1)        |
| Insertion 1st position        | O(1)         | O(1)        |
| Insertion last position       | O(1)         | O(1)        |
| Insertion nth position        | O(1)         | O(N)        |
| Searching in unsorted data    | O(N)         | O(N)        |
| Searching in sorted data      | O(logN)      | O(N)        |
| Traversing                    | O(N)         | O(N)        |
| Deletion of 1st position      | O(1)         | O(1)        |
| Deletion of last position     | O(1)         | O(1)        |
| Deletion of nth position      | O(1)         | O(N)        |
| Deletion of array/linked list | O(1)         | O(N)/O(1)   |
| Access nth element            | O(1)         | O(N)        |

</pre>
</td>
<td>
<pre>
<ul>
  <font size="+1">
<li>Each element of linked list is independent object </li>
<li>Size of linked list is not predefined (Array fixed size)</li>
<li>Accessing elements in arrays is very efficient (Random access)</li>
<li>Linked list: removing and inserting elements does not require<br/> shifting elements in the array</li>
<li>Linked List:  models FIFO & LIFO in O(1) time complexity</li>
</ul>
</pre>

</td>
</tr>
</table>

## Types
|          Presentation           |                            Source                            |
|:-------------------------------:|:------------------------------------------------------------:|
|    ![img.png](img%2Fimg.png)    |            [DOUBLY LINKED LIST](LinkedList.java)             |
|    ![img.png](img%2Fimg.png)    |         [SINGLY LINKED LIST](SinglyLinkedList.java)          |
| ![img_12.png](img%2Fimg_12.png) | [CIRCULAR DOUBLY LINKED LIST](CircularDoublyLinkedList.java) |
| ![img_16.png](img%2Fimg_16.png) | [CIRCULAR SINGLY LINKED LIST](CircularSinglyLinkedList.java) |

## Operation details

|      Doubly Linked List       |      Singly Linked List       |
|:-----------------------------:|:-----------------------------:|
| ![img_1.png](img%2Fimg_1.png) | ![img_4.png](img%2Fimg_4.png) |
| ![img_2.png](img%2Fimg_2.png) | ![img_6.png](img%2Fimg_6.png) |
| ![img_8.png](img%2Fimg_8.png) | ![img_7.png](img%2Fimg_7.png) |

![img_9.png](img%2Fimg_9.png) 


# <center>Stack</center>

<p align="center">
  <img src="https://i.stechies.com/userfiles/images/Stack-Programmin-in-C.jpg" alt="img"/>
</p>

    In computer science, a stack is an abstract data type that serves as a collection of elements, with two main operations:

    - Push, which adds an element to the collection, and
    - Pop, which removes the most recently added element that was not yet removed.

    The order in which an element added to or removed from a stack is described as last in, first out, referred to by the acronym LIFO

|         Presentation          |                  Source                   |
|:-----------------------------:|:-----------------------------------------:|
|   ![img.png](img%2Fimg.png)   |      [STACK ARRAY](StackArray.java)       |
| ![img_1.png](img%2Fimg_1.png) | [STACK LINKED LIST](StackLinkedList.java) |

<table style="width: 100%">
<tr>
<th>Array</th>
<th>Linked List</th>
</tr>
<tr>
<td>

|                     | Time complexity | Space complexity |
|---------------------|-----------------|------------------|
| Create Stack        | O(1)            | O(N)             |
| Push                | O(1)            | O(1)             |
| Pop                 | O(1)            | O(1)             |
| Peek                | O(1)            | O(1)             |
| isEmpty             | O(1)            | O(1)             |
| Delete Entire Stack | O(1)            | O(1)             |

    int[] arr;
    int cap = arr.length, top = 0;

    isEmpty() {
        return top == 0;
    }
    
    Pop() {
        if isEmpty 
            return
        else
            return arr[--top]
    }

    Push(element) {
        if isFull
            cap = cap * 2;
            arr = Arrays.copyOf(arr, arr.length, size)
        else
            arr[top++] = element
    }

    Peek() {
        if isEmpty
            return
        else
            return arr[top - 1]
    }

</td>
<td>

|                     | Time complexity | Space complexity |
|---------------------|-----------------|------------------|
| Create Stack        | O(1)            | O(1)             |
| Push                | O(1)            | O(1)             |
| Pop                 | O(1)            | O(1)             |
| Peek                | O(1)            | O(1)             |
| isEmpty             | O(1)            | O(1)             |
| Delete Entire Stack | O(1)            | O(1)             |

     LinkedList list;
    
    Size() {
        return list.size()
    }
    
    isEmpty() {
        return size() == 0
    }
    
    Pop() {
        if isEmpty 
            return
        else
            return list.removeLast
    }

    Push(element) {
        list.add(element)
    }

    Peek() {
        if isEmpty
            return
        else
            return list.peekLast
    }

</td>
</tr>
</table>


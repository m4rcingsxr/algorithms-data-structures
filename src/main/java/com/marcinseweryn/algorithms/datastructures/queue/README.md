# <center>Queue</center>

## Implementations

- [CIRCULAR ARRAY QUEUE](CircularArrayQueue.java)
- [LINKED LIST QUEUE](LinkedListQueue.java)

<table>

<tr>
</tr>
<tr>
<td>
  <img src="https://www.shutterstock.com/image-vector/big-queue-many-multitude-people-260nw-2142305183.jpg"/>
</td>

<td>
<pre>
<p>
<ul>
<li>In computer science, a queue is a collection of entities that are 
maintained in a sequence and can be modified by the addition of 
entities at one end of the sequence and the removal of entities 
from the beginning of the sequence.By convention, the end of the
sequence at which elements are added is called tail, or rear of 
the queue,and the beginning at which elements are removed is 
called the head of the queue
</li>
<li> Queue stores elements in a FIFO(first-in-first-out) manner </li>
</ul>
</pre>

</td>
</tr>
</table>



  <table style="width: 100%">
<tr>
<th>Circular Array</th>
<th>Linked List</th>
</tr>
<tr>
<td>

| ______________________ | Time complexity | Space Complexity |
|------------------------|-----------------|------------------|
| Create Queue           | O(1)            | O(N)             |
| enQueue                | O(1)            | O(1)             |
| deQueue                | O(1)            | O(1)             |
| Peek                   | O(1)            | O(1)             |
| isEmpty                | O(1)            | O(1)             |
| isFull                 | O(1)            | O(1)             |
| Delete Queue           | O(1)            | O(1)             |

    int[] arr; 
    int top, beginning, N = arr.length;
    
    isEmpty() { 
      if beginning == -1 && top == -1
        return true
      else
        return false
    }

    Enqueue(e) { 
        if (top + 1) % N == beginning 
            return
        else if isEmpty() { 
            beginning = top = 0
        } else { 
            top = (top + 1) % N
        }
        arr[top] = x;
    }  

    Dequeue() {
        if isEmpty()
            return
        else if beginning == top 
            beginning = -1;
            top = -1;
        else 
            beginning = (beginning + 1) % N
    }

</td>
<td>

| _____________________=__ | Time complexity | Space Complexity |
|--------------------------|-----------------|------------------|
| Create Queue             | O(1)            | O(1)             |
| enQueue                  | O(1)            | O(1)             |
| deQueue                  | O(1)            | O(1)             |
| Peek                     | O(1)            | O(1)             |
| isEmpty                  | O(1)            | O(1)             |
| isFull                   | O(1)            | O(1)             |
| Delete Queue             | O(1)            | O(1)             |

    LinkedList list;

    Size() {
        return list.size()
    }

    isEmpty() {
        return size() == 0
    }

    Peek() {
        if isEmpty()
        return
        else
        return list.peekFirst()
    }

    Enqueue(element) {
        list.addLast(element)
    }

    Dequeue() {
        if isEmpty()
        return
        else
        return list.removeFirst()
    }
    
    ![img_3.png](img_3.png)

</td>
</tr>
</table>

|      Circular Array       |          Linked List          |
|:-------------------------:|:-----------------------------:|
| ![img.png](img%2Fimg.png) | ![img_3.png](img%2Fimg_3.png) |







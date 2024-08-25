package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of the Heapsort algorithm using a {@link Comparator}.
 *
 * <p>
 * Heapsort is a comparison-based sorting algorithm that utilizes a binary heap data structure.
 * The algorithm consists of two main phases:
 * </p>
 * <ol>
 *     <li><b>Heap Construction:</b> Convert the input array into a heap using the provided {@link Comparator}.
 *         This process ensures that the largest (or smallest) element is at the root of the heap.</li>
 *     <li><b>Sorting Phase:</b> Repeatedly extract the root element (maximum or minimum) from the heap and place
 *         it at the end of the array. After each extraction, reduce the heap size by one and restore the heap property
 *         for the remaining elements.</li>
 * </ol>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(n log n) - The time complexity for building the heap and performing the sorting phase is
 *         O(n log n), regardless of the initial order of elements.</li>
 *     <li><b>Average Case:</b> O(n log n) - On average, the time complexity remains O(n log n) due to the heap operations
 *         (insertions and deletions).</li>
 *     <li><b>Worst Case:</b> O(n log n) - The worst-case time complexity is O(n log n) as the heap operations are consistent
 *         across all cases.</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b> O(1) - Heapsort is an in-place sorting algorithm and does not require additional storage beyond
 * the input array.</p>
 *
 * <p><b>Stability:</b> Heapsort is <b>not stable</b>. The relative order of equal elements may not be preserved after sorting,
 * as the algorithm involves swapping elements in the heap.</p>
 *
 * <p><b>Heap Construction:</b></p>
 * <p>
 * The array is initially transformed into a heap structure by calling {@link #heapifyTopToBottom(int[], int, int, Comparator)}
 * for non-leaf nodes in a bottom-up manner. This step ensures that each subtree of the array satisfies the heap property.
 * </p>
 *
 * <p><b>Sorting Phase:</b></p>
 * <p>
 * After heap construction, the algorithm repeatedly extracts the root (maximum or minimum) and places it at the end of
 * the current heap. The heap size is decreased by one, and the heap property is restored by re-heapifying the root.
 * This process continues until the entire array is sorted.
 * </p>
 *
 */
public class HeapSort {

    /**
     * Sorts the input array in the order defined by the provided Comparator using the Heapsort algorithm.
     *
     * @param values The array to be sorted.
     * @param comparator The Comparator that defines the order of the elements.
     */
    public void sort(int[] values, Comparator<Integer> comparator) {
        heapsort(values, comparator);
    }

    /**
     * Main method to perform heapsort on the array using a Comparator.
     *
     * This method first converts the array into a heap based on the comparator provided,
     * then repeatedly extracts the maximum (or minimum) element (the root of the heap) and places it at the end of the array.
     * The heap size is reduced by one each time an element is extracted, and the heap is
     * re-heapified to maintain the heap property.
     *
     * @param ar The array to be sorted.
     * @param comparator The Comparator that defines the order of the elements.
     */
    private static void heapsort(int[] ar, Comparator<Integer> comparator) {
        if (ar == null || comparator == null) return;
        int n = ar.length;

        // Heapify: Convert the array into a heap using the comparator. Process only non leaf nodes
        for (int i = Math.max(0, (n / 2) - 1); i >= 0; i--) {
            heapifyTopToBottom(ar, n, i, comparator);
        }

        // Sorting phase: Extract the root element of the heap and place it at the end of the array.
        // Then reduce the heap size by one and re-heapify the root.
        for (int i = n - 1; i >= 0; i--) {
            swap(ar, 0, i);    // Move the root element to its final position
            heapifyTopToBottom(ar, i, 0, comparator);    // Re-heapify the reduced heap
        }
    }

    /**
     * Maintains the heap property for the subtree rooted at index i using a Comparator.
     *
     * The heapifyTopToBottom method ensures that the subtree rooted at index i is a heap
     * based on the order defined by the comparator. This is done by comparing the value at index i with its children
     * and swapping it with the larger (or smaller, depending on the comparator) child if necessary.
     * This process continues down the tree until the heap property is restored.
     *
     * @param ar The array representing the heap.
     * @param n The size of the heap.
     * @param i The index of the root of the subtree to heapify.
     * @param comparator The Comparator that defines the order of the elements.
     */
    private static void heapifyTopToBottom(int[] ar, int n, int i, Comparator<Integer> comparator) {
        while (true) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            int largest = leftChild;

            // Determine the smallest child to compare with the current node
            if (rightChild < n && comparator.compare(ar[rightChild], ar[leftChild]) > 0) {
                largest = rightChild;
            }

            // If the smallest child is out of bounds or the current node is smaller, break the loop
            if (leftChild >= n || comparator.compare(ar[i], ar[largest]) > 0) break;

            swap(ar, i, largest); // Swap with the smaller child
            i = largest; // Move down the tree
        }
    }

    /**
     * Swaps two elements in an array.
     *
     * @param ar The array containing the elements to swap.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void main(String[] args) {
        // Create an instance of HeapSort
        HeapSort heapSort = new HeapSort();

        // Array to be sorted
        int[] array = {5, 3, 8, 1, 2, 7};

        // Print the array before sorting
        System.out.println("Before sorting: " + Arrays.toString(array));

        // Sort the array using HeapSort with a comparator for natural order
        heapSort.sort(array, Comparator.naturalOrder());

        // Print the array after sorting
        System.out.println("After sorting: " + Arrays.toString(array));

        // Example usage with reverse order comparator
        int[] reverseArray = {5, 3, 8, 1, 2, 7};

        // Print the array before sorting
        System.out.println("Before reverse sorting: " + Arrays.toString(reverseArray));

        // Sort the array using HeapSort with a comparator for reverse order
        heapSort.sort(reverseArray, Comparator.reverseOrder());

        // Print the array after sorting
        System.out.println("After reverse sorting: " + Arrays.toString(reverseArray));
    }

}

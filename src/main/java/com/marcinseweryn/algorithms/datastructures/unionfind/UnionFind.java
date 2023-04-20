package com.marcinseweryn.algorithms.datastructures.unionfind;

/**
 * The Union-Find data structure is used to represent a collection
 * of disjoint sets and supports two operations: union and find.
 * <p>
 * The union operation is used to merge two sets, and the find
 * operation is used to determine which set an element belongs to.
 * <p>
 * The data structure is commonly referred to as "disjoint set"
 * because the sets represented by the data structure are disjoint,
 * meaning they have no elements in common.
 */
public class UnionFind {

    // The number of elements in this union-find
    private int numElements;

    // The size of each of the components
    private int[] componentSizes;

    // A mapping from an element to its parent element.
    // If an element is a root node, its parent is itself.
    private int[] parentElements;

    // The number of components in this union-find
    private int numComponents;

    /**
     * Constructs a new UnionFind data structure with a specified number of elements.
     *
     * @param numElements The number of elements in the UnionFind data structure.
     * @throws IllegalArgumentException if {@code numElements <= 0}.
     */
    public UnionFind(int numElements) {
        if (numElements <= 0) {
            throw new IllegalArgumentException("Number of elements must be positive.");
        }

        this.numElements = numComponents = numElements;
        componentSizes = new int[numElements];
        parentElements = new int[numElements];

        for (int i = 0; i < numElements; i++) {
            parentElements[i] = i; // Link to itself (self root)
            componentSizes[i] = 1; // Each component is originally of size one
        }
    }

    /**
     * Finds the root element of the component that {@code element} belongs to.
     *
     * @param element The element to find the root of.
     * @return The root element of the component that {@code element} belongs to.
     */
    public int find(int element) {
        // Find the root of the component
        int root = element;
        while (root != parentElements[root]) {
            root = parentElements[root];
        }

        // Compress the path leading back to the root.
        // Doing this operation is called "path compression"
        // and is what gives us amortized constant time complexity.
        while (element != root) {
            int next = parentElements[element];
            parentElements[element] = root;
            element = next;
        }

        return root;
    }

    /**
     * Returns whether or not the two elements belong to the same component.
     *
     * @param firstElement  The first element.
     * @param secondElement The second element.
     * @return {@code true} if the two elements belong to the same component, {@code false} otherwise.
     */
    public boolean areConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    /**
     * Returns the size of the component that {@code element} belongs to.
     *
     * @param element The element.
     * @return The size of the component that {@code element} belongs to.
     */
    public int getComponentSize(int element) {
        return componentSizes[find(element)];
    }

    /**
     * Returns the number of elements in this UnionFind data structure.
     *
     * @return The number of elements in this UnionFind data structure.
     */
    public int getNumElements() {
        return numElements;
    }

    // Unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        // These elements are already in the same group!
        if (root1 == root2) {
            return;
        }

        // Merge two components/sets together
        // Merge smaller component/set into the large one
        if (componentSizes[root1] < componentSizes[root2]) {
            componentSizes[root2] += componentSizes[root1];
            parentElements[root1] = root2;
        } else {
            componentSizes[root1] += componentSizes[root2];
            parentElements[root2] = root1;
        }

        // Since the roots found are different we know that the
        // number of components/sets has decreased by one
        numComponents--;
    }
}

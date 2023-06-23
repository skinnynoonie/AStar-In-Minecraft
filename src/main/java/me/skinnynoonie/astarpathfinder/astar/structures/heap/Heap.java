package me.skinnynoonie.astarpathfinder.astar.structures.heap;

import java.lang.reflect.Array;

public class Heap<T extends HeapElement<T>> {

    private final T[] heap;
    private int elementCount = 0;

    public Heap(Class<?> clazz, int capacity) {
        this.heap = (T[]) Array.newInstance(clazz, capacity);
    }

    public void add(T element) {
        element.setHeapIndex(elementCount);
        heap[elementCount] = element;
        sortUpwards(element);
        elementCount++;
    }

    public T getAndRemoveFirst() {
        T firstElement = heap[0];
        elementCount--;
        heap[0] = heap[elementCount];
        heap[elementCount].setHeapIndex(0);
        sortDownwards(heap[elementCount]);
        return firstElement;
    }

    public boolean contains(T element) {
        return heap[element.getHeapIndex()].equals(element);
    }

    public void updateElement(T element) {
        sortUpwards(element);
        //sortDownwards(element); In AStar we will not update an item unless it has a better priority than before.
    }

    public int getSize() {
        return elementCount;
    }

    private void sortDownwards(T element) {
        while (true) {

            int childOneIndex = element.getHeapIndex() * 2 + 1;

            if(childOneIndex < elementCount) {
                int betterChildIndex = childOneIndex;

                int childTwoIndex = element.getHeapIndex() * 2 + 2;
                if(childTwoIndex < elementCount) {
                    if(heap[childTwoIndex].compareTo(heap[childOneIndex]) > 0) {
                        betterChildIndex = childTwoIndex;
                    }
                }
                if(heap[betterChildIndex].compareTo(element) > 0) {
                    swapElements(element, heap[betterChildIndex]);
                    continue;
                }
                return;
            }
            return;
        }
    }

    private void sortUpwards(T element) {
        while(true) {
            int parentIndex = (element.getHeapIndex() - 1) / 2;
            if(element.compareTo(heap[parentIndex]) > 0) {
                swapElements(element, heap[parentIndex]);
                continue;
            }
            return;
        }
    }

    private void swapElements(T a, T b) {
        heap[a.getHeapIndex()] = b;
        heap[b.getHeapIndex()] = a;
        int indexA = a.getHeapIndex();
        a.setHeapIndex(b.getHeapIndex());
        b.setHeapIndex(indexA);
    }

}

package me.skinnynoonie.astarpathfinder.astar.structures.heap;

public interface HeapElement<T> extends Comparable<T> {

    int getHeapIndex();
    void setHeapIndex(int index);

}

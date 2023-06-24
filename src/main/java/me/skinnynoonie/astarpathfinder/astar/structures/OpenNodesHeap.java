package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;
import me.skinnynoonie.astarpathfinder.astar.structures.heap.Heap;

public class OpenNodesHeap implements OpenNodesStructure {

    private final Heap<Node> nodeHeap;

    public OpenNodesHeap(int capacity) {
        nodeHeap = new Heap<>(Node.class, capacity);
    }

    @Override
    public void update(Node node) {
        nodeHeap.updateElement(node);
    }

    @Override
    public Node getAndRemoveFirst() {
        return nodeHeap.getAndRemoveFirst();
    }

    @Override
    public void add(Node node) {
        nodeHeap.add(node);
    }

    @Override
    public void clear() {
        nodeHeap.clear();
    }

    @Override
    public boolean contains(Node node) {
        return nodeHeap.contains(node);
    }

    @Override
    public boolean isEmpty() {
        return nodeHeap.getSize() == 0;
    }
}

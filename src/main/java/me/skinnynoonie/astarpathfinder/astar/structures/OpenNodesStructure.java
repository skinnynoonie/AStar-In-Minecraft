package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;

public interface OpenNodesStructure {

    Node getAndRemoveFirst();
    void add(Node node);
    void clear();
    boolean contains(Node node);
    boolean isEmpty();
    default void update(Node node) {
    }

}

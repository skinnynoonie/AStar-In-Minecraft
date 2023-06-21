package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;

public interface ClosedNodesStructure {

    void add(Node node);
    void clear();
    boolean contains(Node node);

}

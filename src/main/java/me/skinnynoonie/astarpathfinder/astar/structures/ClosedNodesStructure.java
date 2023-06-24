package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;

//WAS PURELY FOR AESTHETIC, IT IS WAY, WAY FASTER TO STORE THE CLOSED STATUS IN THE NODE AS A BOOLEAN.
public interface ClosedNodesStructure {

    void add(Node node);
    void clear();
    boolean contains(Node node);

}

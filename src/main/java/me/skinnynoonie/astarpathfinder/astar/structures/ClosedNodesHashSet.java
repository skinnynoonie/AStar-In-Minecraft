package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;

import java.util.HashSet;

public class ClosedNodesHashSet implements ClosedNodesStructure{

    private final HashSet<Node> closedNodes = new HashSet<>();

    @Override
    public void add(Node node) {
        closedNodes.add(node);
    }

    @Override
    public void clear() {
        closedNodes.clear();
    }

    @Override
    public boolean contains(Node node) {
        return closedNodes.contains(node);
    }

}

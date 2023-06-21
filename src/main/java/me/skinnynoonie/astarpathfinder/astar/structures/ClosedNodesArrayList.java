package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;

import java.util.ArrayList;

public class ClosedNodesArrayList implements ClosedNodesStructure {

    private final ArrayList<Node> closedNodes = new ArrayList<>();

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

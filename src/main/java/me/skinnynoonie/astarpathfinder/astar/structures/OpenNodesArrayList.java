package me.skinnynoonie.astarpathfinder.astar.structures;

import me.skinnynoonie.astarpathfinder.astar.Node;

import java.util.ArrayList;

public class OpenNodesArrayList implements OpenNodesStructure {

    private final ArrayList<Node> nodes = new ArrayList<>();

    @Override
    public Node getAndRemoveFirst() {
        Node firstNode = nodes.get(0);
        for(int i = 1; i < nodes.size(); i++) {
            Node contestantNode = nodes.get(i);
            if(contestantNode.compareTo(firstNode) > 0) {
                firstNode = contestantNode;
            }
        }
        nodes.remove(firstNode);
        return firstNode;
    }

    @Override
    public void add(Node node) {
        nodes.add(node);
    }

    @Override
    public void clear() {
        nodes.clear();
    }

    @Override
    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    @Override
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

}

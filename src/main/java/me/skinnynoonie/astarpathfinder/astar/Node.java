package me.skinnynoonie.astarpathfinder.astar;

import org.bukkit.Location;

public class Node {

    private final Location location;
    private Node parent;
    private boolean isClosed = false;
    private int gCost;
    private int hCost;

    public Node(Location location) {
        this.location = location;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public Location getLocation() {
        return location;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

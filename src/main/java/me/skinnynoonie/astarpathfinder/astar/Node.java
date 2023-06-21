package me.skinnynoonie.astarpathfinder.astar;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Node {

    private final ImmutableVector location;
    private Node parent;
    private boolean isClosed = false;
    private double gCost;
    private double hCost;

    public Node(ImmutableVector immutableVector) {
        this.location = immutableVector;
    }

    public Node(Location location) {
        this(new ImmutableVector(location.getX(), location.getY(), location.getZ()));
    }

    public Node(double x, double y, double z) {
        this(new ImmutableVector(x, y, z));
    }

    public ImmutableVector asImmutableVector() {
        return location;
    }

    public Block getBlockAt(World world) {
        return world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ()));
    }

    public double getX() {
        return location.getX();
    }

    public double getY() {
        return location.getY();
    }

    public double getZ() {
        return location.getZ();
    }

    public boolean isClosed() {
        return isClosed;
    }

    public double getFCost() {
        return gCost + hCost;
    }

    public double getGCost() {
        return gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

}

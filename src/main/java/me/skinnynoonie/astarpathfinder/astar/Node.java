package me.skinnynoonie.astarpathfinder.astar;

import me.skinnynoonie.astarpathfinder.astar.structures.heap.HeapElement;
import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Node implements HeapElement<Node> {

    private final ImmutableVector location;
    private Node parent;
    private double gCost;
    private double hCost;
    private int heapIndex;

    public Node(ImmutableVector immutableVector) {
        this.location = immutableVector;
    }

    @Override
    public int compareTo(Node o) {
        int isPriority = Double.compare(this.getFCost(), o.getFCost());
        if(isPriority == 0) {
            isPriority = Double.compare(this.getHCost(), o.getHCost());
        }
        return -isPriority;
    }

    public ImmutableVector asImmutableVector() {
        return location;
    }

    public Location asLocation(World world) {
        return new Location(world, location.getX(), location.getY(), location.getZ());
    }

    public Block getBlockAt(World world) {
        return world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ()));
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

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    @Override
    public int getHeapIndex() {
        return this.heapIndex;
    }

    @Override
    public void setHeapIndex(int index) {
        this.heapIndex = index;
    }
}

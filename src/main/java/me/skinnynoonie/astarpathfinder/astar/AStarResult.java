package me.skinnynoonie.astarpathfinder.astar;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AStarResult {

    private World world;
    private ImmutableVector start;
    private ImmutableVector end;
    private HashMap<ImmutableVector, Node> nodeCache;
    private int iterations = 0;
    private boolean success = false;

    public List<Location> getPathTaken() {
        if(!success) return null;
        List<Location> backTrack = new ArrayList<>();
        Node currentNode = nodeCache.get(end);
        while (currentNode != nodeCache.get(start)) {
            backTrack.add(currentNode.asLocation(world));
            currentNode = currentNode.getParent();
        }
        backTrack.add(nodeCache.get(start).asLocation(world));
        Collections.reverse(backTrack);
        return backTrack;
    }

    public double getPathDistance() {
        double distance = 0;
        List<Location> path = getPathTaken();
        if(path == null) return distance;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += path.get(i).distance(path.get(i+1));
        }
        return distance;
    }

    public HashMap<ImmutableVector, Node> getNodeCache() {
        return nodeCache;
    }

    public ImmutableVector getStart() {
        return start;
    }

    public ImmutableVector getEnd() {
        return end;
    }

    public int getIterations() {
        return iterations;
    }

    public boolean isSuccessful() {
        return success;
    }

    public AStarResult setWorld(World world) {
        this.world = world;
        return this;
    }

    public AStarResult setStart(ImmutableVector start) {
        this.start = start;
        return this;
    }

    public AStarResult setEnd(ImmutableVector end) {
        this.end = end;
        return this;
    }

    public AStarResult setNodeCache(HashMap<ImmutableVector, Node> nodeCache) {
        this.nodeCache = nodeCache;
        return this;
    }

    public AStarResult setIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public AStarResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

}

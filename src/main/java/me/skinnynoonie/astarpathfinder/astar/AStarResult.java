package me.skinnynoonie.astarpathfinder.astar;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AStarResult {

    private final World world;
    private final ImmutableVector start;
    private final ImmutableVector end;
    private final HashMap<ImmutableVector, Node> nodeCache;
    private final int iterations;
    private final boolean success;

    public AStarResult(World world, ImmutableVector start, ImmutableVector end, HashMap<ImmutableVector, Node> nodeCache, int iterations, boolean success) {
        this.world = world;
        this.start = start;
        this.end = end;
        this.nodeCache = nodeCache;
        this.iterations = iterations;
        this.success = success;
    }

    public List<Location> getPathTaken() {
        List<Location> backTrack = new ArrayList<>();
        Node currentNode = nodeCache.get(end);
        while (currentNode != nodeCache.get(start)) {
            backTrack.add(currentNode.asLocation(world));
            currentNode = currentNode.getParent();
            if(currentNode == null) {
                Collections.reverse(backTrack);
                return backTrack;
            }
        }
        backTrack.add(nodeCache.get(start).asLocation(world));
        Collections.reverse(backTrack);
        return backTrack;
    }

    public double getPathDistance() {
        double distance = 0;
        List<Location> path = getPathTaken();
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

}

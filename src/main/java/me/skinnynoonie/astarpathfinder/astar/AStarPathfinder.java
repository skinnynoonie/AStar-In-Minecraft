package me.skinnynoonie.astarpathfinder.astar;

import me.skinnynoonie.astarpathfinder.astar.distances.DistanceCalculator;
import me.skinnynoonie.astarpathfinder.astar.distances.EuclideanDistanceCalculator;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarPathfinder {

    /**
     * Modules are here, this is what I like to call making the engine.
     */
    private DistanceCalculator distanceCalculator = new EuclideanDistanceCalculator();

    private final ImmutableVector[] neighbourChecks = new ImmutableVector[] {
            new ImmutableVector(1,0,0),
            new ImmutableVector(-1,0,0),
            new ImmutableVector(0,0,1),
            new ImmutableVector(0,0,-1),
            new ImmutableVector(0,1,0),
            new ImmutableVector(0,-1,0),
    };
    private final HashMap<ImmutableVector, Node> nodeCache = new HashMap<>();
    private final ArrayList<Node> openList = new ArrayList<>();
    private final int maxIterations;

    public AStarPathfinder(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    @Nullable
    public List<Location> findPathTo(Location from, Location to) {
        if(from.getWorld() != to.getWorld()) return null;
        return findPathTo(from.getWorld(), new ImmutableVector(from), new ImmutableVector(to));
    }

    @Nullable
    public List<Location> findPathTo(World world, ImmutableVector initialLocation, ImmutableVector endLocation) {
        nodeCache.clear();
        openList.clear();
        nodeCache.put(initialLocation, new Node(initialLocation.getX(), initialLocation.getY(), initialLocation.getZ()));
        openList.add(nodeCache.get(initialLocation));

        int iterations = 0;
        while (!openList.isEmpty() && iterations < maxIterations) {
            iterations++;

            Node currentNode = openList.get(0);
            for(int i = 1; i < openList.size(); i++) {
                Node contestantNode = openList.get(i);
                if(contestantNode.getFCost() < currentNode.getFCost() || (contestantNode.getFCost() == currentNode.getFCost() && contestantNode.getHCost() < currentNode.getHCost())) {
                    currentNode = contestantNode;
                }
            }

            openList.remove(currentNode);
            currentNode.setClosed(true);

            if(currentNode.asImmutableVector().equals(endLocation)) {
                System.out.println("Location found!");
                //Will make an AStarResult Object to return instead of just a list of locations
                return null;
            }

            for(Node neighbourNode : getNeighbours(currentNode)) {
                if(neighbourNode.isClosed()) continue;
                if(neighbourNode.getBlockAt(world).getType() != Material.AIR) {
                    continue;
                }

                double newNeighbourGCost = currentNode.getGCost() + distanceCalculator.calculateDistance(currentNode.asImmutableVector(), neighbourNode.asImmutableVector());
                if(newNeighbourGCost < neighbourNode.getGCost() || !openList.contains(neighbourNode)) {
                    neighbourNode.setGCost(newNeighbourGCost);
                    neighbourNode.setHCost(distanceCalculator.calculateDistance(neighbourNode.asImmutableVector(), endLocation));
                    neighbourNode.setParent(currentNode);

                    if(!openList.contains(neighbourNode)) openList.add(neighbourNode);
                }
            }
        }
        return null;
    }

    private Node[] getNeighbours(Node node) {
        Node[] neighbourNodes = new Node[neighbourChecks.length];
        for(int i = 0; i < neighbourChecks.length; i++) {
            ImmutableVector neighbourLocation = node.asImmutableVector().add(neighbourChecks[i]);
            if(!nodeCache.containsKey(neighbourLocation)) nodeCache.put(neighbourLocation, new Node(neighbourLocation));
            neighbourNodes[i] = nodeCache.get(neighbourLocation);
        }
        return neighbourNodes;
    }

    public DistanceCalculator getDistanceCalculator() {
        return distanceCalculator;
    }

    public AStarPathfinder setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
        return this;
    }

}

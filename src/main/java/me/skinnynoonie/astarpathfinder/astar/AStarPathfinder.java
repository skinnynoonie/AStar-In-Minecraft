package me.skinnynoonie.astarpathfinder.astar;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.*;

public class AStarPathfinder {

    private final Vector[] neighbourChecks = new Vector[] {
            new Vector(1,0,0),
            new Vector(-1,0,0),
            new Vector(0,0,1),
            new Vector(0,0,-1),
    };
    private final HashMap<Location, Node> nodeCache = new HashMap<>();
    private final Node startingNode;
    private final int maxIterations;

    public AStarPathfinder(Location initialLocation, int maxIterations) {
        this.startingNode = new Node(initialLocation);
        this.maxIterations = maxIterations;
    }

    @Nullable
    public List<Location> findPathTo(Location endLocation) {
        if(endLocation.getWorld() != startingNode.getLocation().getWorld()) return null;
        nodeCache.clear();
        nodeCache.put(startingNode.getLocation(), startingNode);
        ArrayList<Node> openList = new ArrayList<>();
        openList.add(startingNode);
        startingNode.setGCost(0);
        startingNode.setHCost(getDistance(startingNode.getLocation(), endLocation));

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

            if(currentNode.getLocation().equals(endLocation)) {
                return getTrace(currentNode);
            }

            for(Node neighbourNode : getNeighbours(currentNode)) {
                if(neighbourNode.getLocation().getBlock().getType() != Material.AIR) continue;
                if(neighbourNode.isClosed()) continue;

                int newNeighbourGCost = currentNode.getGCost() + 10; //Hardcoded for now
                if(newNeighbourGCost < neighbourNode.getGCost() || !openList.contains(neighbourNode)) {
                    neighbourNode.setGCost(newNeighbourGCost);
                    neighbourNode.setHCost(getDistance(neighbourNode.getLocation(), endLocation));
                    neighbourNode.setParent(currentNode);

                    if(!openList.contains(neighbourNode)) openList.add(neighbourNode);
                }
            }
        }
        return null;
    }

    private List<Location> getTrace(Node endNode) {
        List<Location> trace = new ArrayList<>();
        Node currentNode = endNode;
        while (currentNode != startingNode) {
            trace.add(currentNode.getLocation());
            currentNode = currentNode.getParent();
        }
        trace.add(startingNode.getLocation());
        Collections.reverse(trace);
        return trace;
    }

    private Node[] getNeighbours(Node node) {
        Node[] neighbourNodes = new Node[neighbourChecks.length];
        for(int i = 0; i < neighbourChecks.length; i++) {
            Location neighbourLocation = node.getLocation().clone().add(neighbourChecks[i]);
            if(!nodeCache.containsKey(neighbourLocation)) nodeCache.put(neighbourLocation, new Node(neighbourLocation));
            neighbourNodes[i] = nodeCache.get(neighbourLocation);
        }
        return neighbourNodes;
    }

    private int getDistance(Location from, Location to) {
        int distanceX = (int) Math.abs(to.getX() - from.getX());
        int distanceZ = (int) Math.abs(to.getZ() - from.getZ());
        if(distanceX > distanceZ) {
            return (14*distanceZ + 10*(distanceX - distanceZ));
        }
        return (14*distanceX + 10*(distanceZ - distanceX));
    }

}

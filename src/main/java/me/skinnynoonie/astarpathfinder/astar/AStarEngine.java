package me.skinnynoonie.astarpathfinder.astar;

import me.skinnynoonie.astarpathfinder.astar.distances.DistanceCalculator;
import me.skinnynoonie.astarpathfinder.astar.distances.EuclideanDistanceCalculator;
import me.skinnynoonie.astarpathfinder.astar.movements.BasicStraightMovementController;
import me.skinnynoonie.astarpathfinder.astar.movements.MovementController;
import me.skinnynoonie.astarpathfinder.astar.structures.ClosedNodesArrayList;
import me.skinnynoonie.astarpathfinder.astar.structures.ClosedNodesStructure;
import me.skinnynoonie.astarpathfinder.astar.structures.OpenNodesArrayList;
import me.skinnynoonie.astarpathfinder.astar.structures.OpenNodesStructure;
import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import org.bukkit.World;

import java.util.HashMap;

public class AStarEngine {

    private DistanceCalculator distanceCalculator = new EuclideanDistanceCalculator();
    private OpenNodesStructure openNodesStructure = new OpenNodesArrayList();
    private ClosedNodesStructure closedNodesStructure = new ClosedNodesArrayList();
    private MovementController movementController = new BasicStraightMovementController();
    private int maxIterations = 100000;

    private final HashMap<ImmutableVector, Node> nodeCache = new HashMap<>();

    public AStarResult findPathTo(World world, ImmutableVector initialLocation, ImmutableVector endLocation) {
        nodeCache.clear();
        openNodesStructure.clear();
        closedNodesStructure.clear();

        nodeCache.put(initialLocation, new Node(initialLocation, world));
        openNodesStructure.add(nodeCache.get(initialLocation));

        int iterations = 0;
        while (!openNodesStructure.isEmpty() && iterations < maxIterations) {
            iterations++;

            Node currentNode = openNodesStructure.getAndRemoveFirst();
            currentNode.setClosed(true);

            if(currentNode.asImmutableVector().equals(endLocation)) {
                return new AStarResult()
                        .setStart(initialLocation)
                        .setEnd(endLocation)
                        .setIterations(iterations)
                        .setWorld(world)
                        .setNodeCache(nodeCache)
                        .setSuccess(true);
            }

            for(Node neighbourNode : getNeighbours(currentNode, world)) {
                if(neighbourNode.isClosed()) continue;
                if(!movementController.isTraversable(neighbourNode)) continue;

                double newNeighbourGCost = currentNode.getGCost() + distanceCalculator.calculateDistance(currentNode.asImmutableVector(), neighbourNode.asImmutableVector()) + movementController.getBiasGCost(neighbourNode);
                if(newNeighbourGCost < neighbourNode.getGCost() || !openNodesStructure.contains(neighbourNode)) {
                    neighbourNode.setGCost(newNeighbourGCost);
                    neighbourNode.setHCost(distanceCalculator.calculateDistance(neighbourNode.asImmutableVector(), endLocation) * movementController.getBiasHCost(neighbourNode));
                    neighbourNode.setParent(currentNode);

                    if(!openNodesStructure.contains(neighbourNode)) openNodesStructure.add(neighbourNode);
                    else openNodesStructure.update(neighbourNode);
                }
            }
        }
        return new AStarResult()
                .setStart(initialLocation)
                .setEnd(endLocation)
                .setIterations(iterations)
                .setWorld(world)
                .setNodeCache(nodeCache)
                .setSuccess(false);
    }

    private Node[] getNeighbours(Node node, World world) {
        ImmutableVector[] movements = movementController.getMovableOptions(node);
        Node[] neighbourNodes = new Node[movements.length];
        for(int i = 0; i < movements.length; i++) {
            ImmutableVector neighbourLocation = node.asImmutableVector().add(movements[i]);
            if(!nodeCache.containsKey(neighbourLocation)) nodeCache.put(neighbourLocation, new Node(neighbourLocation, world));
            neighbourNodes[i] = nodeCache.get(neighbourLocation);
        }
        return neighbourNodes;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public void setOpenNodesStructure(OpenNodesStructure openNodesStructure) {
        this.openNodesStructure = openNodesStructure;
    }

    public void setClosedNodesStructure(ClosedNodesStructure closedNodesStructure) {
        this.closedNodesStructure = closedNodesStructure;
    }

    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

}

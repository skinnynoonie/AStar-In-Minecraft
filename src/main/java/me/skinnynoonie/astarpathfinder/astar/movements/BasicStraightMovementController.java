package me.skinnynoonie.astarpathfinder.astar.movements;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import me.skinnynoonie.astarpathfinder.astar.Node;
import org.bukkit.Material;

public class BasicStraightMovementController implements MovementController{

    private final ImmutableVector[] moves = {
            new ImmutableVector(1,0,0),
            new ImmutableVector(-1,0,0),
            new ImmutableVector(0,0,1),
            new ImmutableVector(0,0,-1),
            new ImmutableVector(0,1,0),
            new ImmutableVector(0,-1,0),
    };

    @Override
    public boolean isTraversable(Node node) {
        return node.getBlockType() == Material.AIR;
    }

    @Override
    public ImmutableVector[] getMovableOptions(Node node) {
        return moves;
    }

    @Override
    public double getBiasGCost(Node node) {
        return 0;
    }

    @Override
    public double getBiasHCost(Node node) {
        return 0;
    }

}

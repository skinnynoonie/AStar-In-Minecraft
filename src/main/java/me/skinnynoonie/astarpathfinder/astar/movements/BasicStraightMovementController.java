package me.skinnynoonie.astarpathfinder.astar.movements;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import me.skinnynoonie.astarpathfinder.astar.Node;
import org.bukkit.Material;
import org.bukkit.block.Block;

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
    public boolean isTraversable(Block block) {
        return block.getType() == Material.AIR;
    }

    @Override
    public ImmutableVector[] getMovableOptions(Node node) {
        return moves;
    }

}

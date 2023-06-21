package me.skinnynoonie.astarpathfinder.astar.movements;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import me.skinnynoonie.astarpathfinder.astar.Node;
import org.bukkit.block.Block;

public interface MovementController {

    boolean isTraversable(Block block);
    ImmutableVector[] getMovableOptions(Node node);
    

}

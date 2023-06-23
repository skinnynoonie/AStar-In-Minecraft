package me.skinnynoonie.astarpathfinder.astar.movements;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import me.skinnynoonie.astarpathfinder.astar.Node;

public interface MovementController {

    boolean isTraversable(Node node);
    ImmutableVector[] getMovableOptions(Node node);

    /**
     * @return Bias that will be used in addition.
     */
    double getBiasGCost(Node node);

    /**
     * @return Bias that will be used in multiplication
     */
    double getBiasHCost(Node node);


}

package me.skinnynoonie.astarpathfinder.astarwand;

import me.skinnynoonie.astarpathfinder.astar.AStarEngine;
import me.skinnynoonie.astarpathfinder.astar.AStarResult;
import me.skinnynoonie.astarpathfinder.astar.distances.EuclideanDistanceCalculator;
import me.skinnynoonie.astarpathfinder.astar.distances.ManhattanDistanceCalculator;
import me.skinnynoonie.astarpathfinder.astar.movements.BasicStraightMovementController;
import me.skinnynoonie.astarpathfinder.astar.structures.ClosedNodesArrayList;
import me.skinnynoonie.astarpathfinder.astar.structures.ClosedNodesHashSet;
import me.skinnynoonie.astarpathfinder.astar.structures.OpenNodesArrayList;
import me.skinnynoonie.astarpathfinder.astar.structures.OpenNodesHeap;
import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;
import org.bukkit.Location;
import org.bukkit.Material;

public class AStarLocationPathfinder extends AStarEngine {

    public AStarResult findPathTo(Location from, Location to) {
        if(from.getWorld() != to.getWorld()) return new AStarResult();
        if(to.getBlock().getType() != Material.AIR) return new AStarResult();

        super.setMovementController(new BasicStraightMovementController());
        super.setMaxIterations(500000);
        super.setDistanceCalculator(new ManhattanDistanceCalculator());
        super.setOpenNodesStructure(new OpenNodesHeap(500000));

        return super.findPathTo(from.getWorld(), new ImmutableVector(from), new ImmutableVector(to));
    }

}

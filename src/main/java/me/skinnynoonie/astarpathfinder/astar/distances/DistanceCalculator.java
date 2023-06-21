package me.skinnynoonie.astarpathfinder.astar.distances;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;

public interface DistanceCalculator {

    double calculateDistance(ImmutableVector from, ImmutableVector to);

}

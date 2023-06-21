package me.skinnynoonie.astarpathfinder.astar.distances;

import me.skinnynoonie.astarpathfinder.astar.util.ImmutableVector;

public class EuclideanDistanceCalculator implements DistanceCalculator{
    @Override
    public double calculateDistance(ImmutableVector from, ImmutableVector to) {
        return Math.sqrt(
                (to.getX() - from.getX()) * (to.getX() - from.getX()) +
                (to.getY() - from.getY()) * (to.getY() - from.getY()) +
                (to.getZ() - from.getZ()) * (to.getZ() - from.getZ())
        );
    }
}

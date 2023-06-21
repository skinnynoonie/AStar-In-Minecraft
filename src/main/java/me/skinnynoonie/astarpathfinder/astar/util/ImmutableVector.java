package me.skinnynoonie.astarpathfinder.astar.util;

import org.bukkit.Location;

import java.util.Objects;

public class ImmutableVector {

    private final double x;
    private final double y;
    private final double z;

    public ImmutableVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ImmutableVector(Location location) {
        this(location.getX(), location.getY(), location.getZ());
    }

    public ImmutableVector add(ImmutableVector vector) {
        return new ImmutableVector(x + vector.x, y + vector.y, z + vector.z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableVector that = (ImmutableVector) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}

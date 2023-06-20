package me.skinnynoonie.astarpathfinder.astarwand;

import org.bukkit.Location;

public class TwoPoints {

    private Location pointOne;
    private Location pointTwo;

    public TwoPoints(Location pointOne, Location pointTwo) {
        this.pointOne = pointOne;
        this.pointTwo = pointTwo;
    }

    public double getDistance() {
        return pointOne.distance(pointTwo);
    }

    public boolean isSameWorld() {
        if(pointOne == null || pointTwo == null) return false;
        return pointOne.getWorld().getUID().equals(pointTwo.getWorld().getUID());
    }

    public boolean bothPointsSet() {
        return pointOne != null && pointTwo != null;
    }

    public Location getPointOne() {
        return pointOne;
    }

    public void setPointOne(Location pointOne) {
        this.pointOne = pointOne;
    }

    public Location getPointTwo() {
        return pointTwo;
    }

    public void setPointTwo(Location pointTwo) {
        this.pointTwo = pointTwo;
    }

}

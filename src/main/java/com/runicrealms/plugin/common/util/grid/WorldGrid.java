package com.runicrealms.plugin.common.util.grid;

import org.bukkit.Location;

import java.util.Set;

public class WorldGrid<E> extends Grid<E> {

    private final short blocksPerBox;

    public WorldGrid(GridBounds bounds, short blocksPerBox) {
        super(bounds);
        this.blocksPerBox = blocksPerBox;
    }

    public boolean containsElementInGrid(Location location, E element) {
        return this.containsElementInGrid(this.getGridLocationFromLocation(location), element);
    }

    public short getBlocksPerBox() {
        return this.blocksPerBox;
    }

    public Set<E> getElements(Location location) {
        return this.getElements(this.getGridLocationFromLocation(location));
    }

    public GridLocation getGridLocationFromLocation(Location location) {
        return new GridLocation((short) (location.getBlockX() / this.blocksPerBox), (short) (location.getBlockZ() / this.blocksPerBox));
    }

    public Set<E> getSurroundingElements(Location location, short distance) {
        return this.getSurroundingElements(this.getGridLocationFromLocation(location), distance);
    }

    public Set<E> getSurroundingElements(Location location) {
        return this.getSurroundingElements(this.getGridLocationFromLocation(location));
    }

    public void insertElement(Location location, E element) {
        this.insertElement(this.getGridLocationFromLocation(location), element);
    }

    public void removeElement(Location location, E element) {
        this.removeElement(this.getGridLocationFromLocation(location), element);
    }

}

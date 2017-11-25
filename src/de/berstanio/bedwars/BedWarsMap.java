package de.berstanio.bedwars;

import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.ArrayList;

public class BedWarsMap {

    private static BedWarsMap currentBedWarsMap;
    private Location spawnLocation;
    private Location bedLocation;
    private String size;
    private ArrayList<DyeColor> colors = new ArrayList<>();

    public BedWarsMap(Location spawnLocation, Location bedLocation, String size, ArrayList<DyeColor> colors) {
        setSpawnLocation(spawnLocation);
        setBedLocation(bedLocation);
        setSize(size);
        setColors(colors);
    }

    public static BedWarsMap getCurrentBedWarsMap() {
        return currentBedWarsMap;
    }

    public static void setCurrentBedWarsMap(BedWarsMap currentBedWarsMap) {
        BedWarsMap.currentBedWarsMap = currentBedWarsMap;
    }

    public ArrayList<DyeColor> getColors() {
        return colors;
    }

    public void setColors(ArrayList<DyeColor> colors) {
        this.colors = colors;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public void setBedLocation(Location bedLocation) {
        this.bedLocation = bedLocation;
    }
}

package de.berstanio.bedwars;

import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.ArrayList;

public class BedWarsTeam {

    private ArrayList<BedWarsPlayer> players = new ArrayList<>();
    private DyeColor color;
    private String name;
    private Location spawnLocation;
    private BedWarsBed bedWarsBed;
    private int size;

    public BedWarsTeam(DyeColor color, String name, Location spawnLocation, Location bedLocation, int size) {
        setColor(color);
        setName(name);
        setSpawnLocation(spawnLocation);
        setSize(size);
        setBedWarsBed(new BedWarsBed(bedLocation, this));
    }
    public boolean isEmpty(){
        return getPlayers().size() == 0;
    }

    public boolean isFull() {
        return getPlayers().size() == getSize();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<BedWarsPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<BedWarsPlayer> players) {
        this.players = players;
    }

    public DyeColor getColor() {
        return color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public BedWarsBed getBedWarsBed() {
        return bedWarsBed;
    }

    public void setBedWarsBed(BedWarsBed bedWarsBed) {
        this.bedWarsBed = bedWarsBed;
    }
}

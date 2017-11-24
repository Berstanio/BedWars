package de.berstanio.bedwars;

import org.bukkit.Color;
import org.bukkit.DyeColor;

import java.util.ArrayList;

public class BedWarsTeam {

    private ArrayList<BedWarsPlayer> players = new ArrayList<>();
    private DyeColor color;
    private String name;

    public BedWarsTeam(DyeColor color, String name) {
        setColor(color);
        setName(name);
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
}

package de.berstanio.bedwars;

import org.bukkit.DyeColor;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveableMap implements Serializable{

    private String worldName;
    private double bedX;
    private double bedY;
    private double bedZ;
    private double spawnX;
    private double spawnY;
    private double spawnZ;
    private float spawnPitch;
    private float spawnYaw;
    private String size;
    private ArrayList<DyeColor> colors = new ArrayList<>();

    public SaveableMap(String worldName, double bedX, double bedY, double bedZ, double spawnX, double spawnY, double spawnZ, float spawnPitch, float spawnYaw, String size, ArrayList<DyeColor> colors) {
        setWorldName(worldName);
        setBedX(bedX);
        setBedY(bedY);
        setBedZ(bedZ);
        setSpawnX(spawnX);
        setSpawnY(spawnY);
        setSpawnZ(spawnZ);
        setSpawnPitch(spawnPitch);
        setSpawnYaw(spawnYaw);
        setSize(size);
        setColors(colors);
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public double getBedX() {
        return bedX;
    }

    public void setBedX(double bedX) {
        this.bedX = bedX;
    }

    public double getBedY() {
        return bedY;
    }

    public void setBedY(double bedY) {
        this.bedY = bedY;
    }

    public double getBedZ() {
        return bedZ;
    }

    public void setBedZ(double bedZ) {
        this.bedZ = bedZ;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(double spawnX) {
        this.spawnX = spawnX;
    }

    public double getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(double spawnY) {
        this.spawnY = spawnY;
    }

    public double getSpawnZ() {
        return spawnZ;
    }

    public void setSpawnZ(double spawnZ) {
        this.spawnZ = spawnZ;
    }

    public float getSpawnPitch() {
        return spawnPitch;
    }

    public void setSpawnPitch(float spawnPitch) {
        this.spawnPitch = spawnPitch;
    }

    public float getSpawnYaw() {
        return spawnYaw;
    }

    public void setSpawnYaw(float spawnYaw) {
        this.spawnYaw = spawnYaw;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ArrayList<DyeColor> getColors() {
        return colors;
    }

    public void setColors(ArrayList<DyeColor> colors) {
        this.colors = colors;
    }
}

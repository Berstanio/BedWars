package de.berstanio.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;


public class Configuration {

    public void loadConfiguration() {
        Main.getInstance().getConfig().options().copyDefaults(true);
        Main.getInstance().saveConfig();
        ArrayList<String> names = (ArrayList<String>) Main.getInstance().getConfig().getList("Config.names");
        for (String name : names) {
            /*String worldName = Main.getInstance().getConfig().getString("Config." + name + ".worldName");
            int spawnX = Main.getInstance().getConfig().getInt("Config." + name + ".spawn.x");
            int spawnY = Main.getInstance().getConfig().getInt("Config." + name + ".spawn.y");
            int spawnZ = Main.getInstance().getConfig().getInt("Config." + name + ".spawn.z");
            float spawnYaw = (float) Main.getInstance().getConfig().getDouble("Config." + name + ".spawn.yaw");
            float spawnPitch = (float) Main.getInstance().getConfig().getDouble("Config." + name + ".spawn.pitch");
            Location spawn = new Location(Bukkit.getWorld(worldName), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);
            int bedX = Main.getInstance().getConfig().getInt("Config." + name + ".bed.x");
            int bedY = Main.getInstance().getConfig().getInt("Config." + name + ".bed.y");
            int bedZ = Main.getInstance().getConfig().getInt("Config." + name + ".bed.z");
            float bedYaw = (float) Main.getInstance().getConfig().getDouble("Config." + name + ".bed.yaw");
            float bedPitch = (float) Main.getInstance().getConfig().getDouble("Config." + name + ".bed.pitch");
            Location bed = new Location(Bukkit.getWorld(worldName), bedX, bedY, bedZ, bedYaw, bedPitch);
            String size = Main.getInstance().getConfig().getString("Config." + name + ".size");
            ArrayList<String> colors = (ArrayList<String>) Main.getInstance().getConfig().getList("Config." + name + ".colors");
            ArrayList<DyeColor> dyeColors = new ArrayList<>();
            for (String color : colors) {
                dyeColors.add(DyeColor.valueOf(color));
            }*/
            String saveableMapString = Main.getInstance().getConfig().getString("Config." + name);
            try {
                SaveableMap saveableMap = (SaveableMap) deSerialize(saveableMapString);
                if (Bukkit.getWorld(saveableMap.getWorldName()) != null) {
                    Location spawn = new Location(Bukkit.getWorld(saveableMap.getWorldName()), saveableMap.getSpawnX(), saveableMap.getSpawnY(), saveableMap.getSpawnZ(), saveableMap.getSpawnYaw(), saveableMap.getSpawnPitch());
                    Location bed = new Location(Bukkit.getWorld(saveableMap.getWorldName()), saveableMap.getBedX(), saveableMap.getBedY(), saveableMap.getBedZ());
                    BedWarsMap bedWarsMap = new BedWarsMap(spawn, bed, saveableMap.getSize(), saveableMap.getColors());
                    BedWarsMap.setCurrentBedWarsMap(bedWarsMap);
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private Object deSerialize(String s) throws IOException, ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    private String serialize(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}

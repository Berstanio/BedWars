package de.berstanio.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static String size;
    public static ArrayList<BedWarsTeam> bedWarsTeams = new ArrayList<>();
    public static boolean started;

    @Override
    public void onEnable() {
        System.out.println("Bedwars aktiviert!");
    }

    public static boolean canStart(){
        switch (getSize()){
            case "2x4":
                if (Bukkit.getOnlinePlayers().size() == 6) {
                    // TODO: 24.11.17 Timer starten!
                    return true;
                }
        }
        return false;
    }

    public void startGame(){
        // TODO: 24.11.17 Hier machen!
    }

    public static String getSize() {
        return size;
    }

    public static void setSize(String size) {
        Main.size = size;
    }

    public static ArrayList<BedWarsTeam> getBedWarsTeams() {
        return bedWarsTeams;
    }

    public static void setBedWarsTeams(ArrayList<BedWarsTeam> bedWarsTeams) {
        Main.bedWarsTeams = bedWarsTeams;
    }

    public static boolean isStarted() {
        return started;
    }

    public static void setStarted(boolean started) {
        Main.started = started;
    }
}

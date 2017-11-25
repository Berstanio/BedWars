package de.berstanio.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    private static String size;
    private static ArrayList<BedWarsTeam> bedWarsTeams = new ArrayList<>();
    private static boolean started;
    private static Main instance;
    private static ArrayList<BedWarsMap> bedWarsMaps = new ArrayList<>();

    @Override
    public void onEnable() {
        setInstance(this);
        getServer().getPluginManager().registerEvents(new Events(), this);
        new Configuration().loadConfiguration();
        setSize(BedWarsMap.getCurrentBedWarsMap().getSize());
        System.out.println("Bedwars aktiviert!");
        String[] sizeSplit = getSize().split("x");
        int teams = Integer.parseInt(sizeSplit[0]);
        int teamSize = Integer.parseInt(sizeSplit[1]);
        for (int i = 0; i < teams; i++) {
            getBedWarsTeams().add(new BedWarsTeam(BedWarsMap.getCurrentBedWarsMap().getColors().get(i), BedWarsMap.getCurrentBedWarsMap().getColors().get(i).name(), BedWarsMap.getCurrentBedWarsMap().getSpawnLocation(), BedWarsMap.getCurrentBedWarsMap().getBedLocation(), teamSize));
        }
    }

    public static void canStart(){
        switch (getSize()){
            case "2x4":
                if (Bukkit.getOnlinePlayers().size() == 6) {
                    try {
                        new CountDownTimer(60, "Das Spiel startet in ***zeit*** sekunden!", Main.class.getMethod("startGame"), Main.getInstance());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public void startGame(){
        int notEmpty = 0;
        BedWarsTeam randomEmptyBwTeam = null;
        for (BedWarsTeam bedWarsTeam : getBedWarsTeams()) {
            if (!bedWarsTeam.isEmpty()){
                notEmpty++;
            }else {
                randomEmptyBwTeam = bedWarsTeam;
            }
        }
        if (notEmpty == 1){
            for (BedWarsTeam bedWarsTeam : getBedWarsTeams()) {
                if (!bedWarsTeam.isEmpty()){
                    for (int i = 0; i < bedWarsTeam.getPlayers().size() / 2; i++) {
                        assert randomEmptyBwTeam != null;
                        BedWarsPlayer bedWarsPlayer = bedWarsTeam.getPlayers().remove(i);
                        bedWarsPlayer.setBedWarsTeam(randomEmptyBwTeam);
                        randomEmptyBwTeam.getPlayers().add(bedWarsPlayer);
                    }
                }
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!BedWarsPlayer.hasBedwarsPlayer(player)){
                BedWarsTeam bestBedWarsTeam = null;
                for (BedWarsTeam bedWarsTeam : getBedWarsTeams()) {
                    if (!bedWarsTeam.isFull()){
                        if (bestBedWarsTeam == null){
                            bestBedWarsTeam = bedWarsTeam;
                        }else {
                            if (bestBedWarsTeam.getPlayers().size() > bedWarsTeam.getPlayers().size()){
                                bestBedWarsTeam = bedWarsTeam;
                            }
                        }
                    }
                }
                assert bestBedWarsTeam != null;
                bestBedWarsTeam.getPlayers().add(new BedWarsPlayer(player, bestBedWarsTeam));
            }
        }
        Bukkit.broadcastMessage("Spiel ist gestartet!");
        for (BedWarsTeam bedWarsTeam : getBedWarsTeams()) {
            for (BedWarsPlayer bedWarsPlayer : bedWarsTeam.getPlayers()) {
                bedWarsPlayer.getPlayer().setBedSpawnLocation(bedWarsTeam.getSpawnLocation());
                bedWarsPlayer.getPlayer().teleport(bedWarsTeam.getSpawnLocation());
            }
        }
        try {
            new CountDownTimer(3600, null, Main.class.getMethod("finish"), this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finish(){
        Bukkit.broadcastMessage("Fertig");
    }

    public static ArrayList<BedWarsMap> getBedWarsMaps() {
        return bedWarsMaps;
    }

    public static void setBedWarsMaps(ArrayList<BedWarsMap> bedWarsMaps) {
        Main.bedWarsMaps = bedWarsMaps;
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
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

package de.berstanio.bedwars;

import org.bukkit.entity.Player;

public class BedWarsPlayer  {

    private Player player;
    private BedWarsTeam bedWarsTeam;
    private boolean dead;

    public BedWarsPlayer(Player player, BedWarsTeam bedWarsTeam) {
        setPlayer(player);
        setBedWarsTeam(bedWarsTeam);
    }
    
    public static BedWarsPlayer getBedWarsPlayerForName(Player player){
        for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
            for (BedWarsPlayer bedWarsPlayer : bedWarsTeam.getPlayers()) {
                if (bedWarsPlayer.getPlayer().equals(player)){
                    return bedWarsPlayer;
                }
            }
        }
        return null;
    }

    public static boolean hasBedwarsPlayer(Player player){
        for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
            for (BedWarsPlayer bedWarsPlayer : bedWarsTeam.getPlayers()) {
                if (bedWarsPlayer.getPlayer().equals(player)){
                    return true;
                }
            }
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BedWarsTeam getBedWarsTeam() {
        return bedWarsTeam;
    }

    public void setBedWarsTeam(BedWarsTeam bedWarsTeam) {
        this.bedWarsTeam = bedWarsTeam;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}

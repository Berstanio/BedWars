package de.berstanio.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (Main.isStarted()){
                for (Player player : Bukkit.getOnlinePlayers()){
                    player.hidePlayer(e.getPlayer());
                }
            return;
        }
        e.setJoinMessage(ChatColor.BLUE + "[" + ChatColor.DARK_GRAY + "Bedwars" + ChatColor.BLUE + "] " + e.getPlayer().getName() + ChatColor.GRAY + " hat das Spiel betreten!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.BLUE + "[" + ChatColor.DARK_GRAY + "Bedwars" + ChatColor.BLUE + "] " + e.getPlayer().getName() + ChatColor.GRAY + " hat das Spiel verlassen!");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        BedWarsPlayer bedWarsPlayer = BedWarsPlayer.getBedWarsPlayerForName(e.getEntity());
        assert bedWarsPlayer != null;
        bedWarsPlayer.setDead(true);
        for (Player player : Bukkit.getOnlinePlayers()){
            player.hidePlayer(e.getEntity());
        }
        bedWarsPlayer.getBedWarsTeam().getPlayers().remove(bedWarsPlayer);
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e){
        if (e.getClickedInventory().getTitle().equalsIgnoreCase("Team-Auswahl")){
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS){
                for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
                    if (e.getCurrentItem().getData().getData() == bedWarsTeam.getColor().getData()) {
                        if (BedWarsPlayer.hasBedwarsPlayer(Bukkit.getPlayer(e.getWhoClicked().getName()))){
                            BedWarsPlayer bedWarsPlayer = BedWarsPlayer.getBedWarsPlayerForName(Bukkit.getPlayer(e.getWhoClicked().getName()));
                            assert bedWarsPlayer != null;
                            bedWarsPlayer.getBedWarsTeam().getPlayers().remove(bedWarsPlayer);
                            bedWarsTeam.getPlayers().add(bedWarsPlayer);
                            bedWarsPlayer.setBedWarsTeam(bedWarsTeam);
                        }else {
                            bedWarsTeam.getPlayers().add(new BedWarsPlayer(Bukkit.getPlayer(e.getWhoClicked().getName()), bedWarsTeam));
                        }
                        break;
                    }
                }
            }
        }
    }
}

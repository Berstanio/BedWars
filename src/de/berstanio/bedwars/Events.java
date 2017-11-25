package de.berstanio.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (Main.isStarted()){
            //e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE, 2,false ,false))
                for (Player player : Bukkit.getOnlinePlayers()){
                    player.hidePlayer(e.getPlayer());
                }
            return;
        }
        Main.canStart();
        e.setJoinMessage(ChatColor.BLUE + "[" + ChatColor.DARK_GRAY + "Bedwars" + ChatColor.BLUE + "] " + e.getPlayer().getName() + ChatColor.GRAY + " hat das Spiel betreten!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.BLUE + "[" + ChatColor.DARK_GRAY + "Bedwars" + ChatColor.BLUE + "] " + e.getPlayer().getName() + ChatColor.GRAY + " hat das Spiel verlassen!");
        if (Main.isStarted()){
            if (BedWarsPlayer.hasBedwarsPlayer(e.getPlayer())) {
                BedWarsPlayer.getBedWarsPlayerForName(e.getPlayer()).getBedWarsTeam().getPlayers().remove(BedWarsPlayer.getBedWarsPlayerForName(e.getPlayer()));
            }
        }
        int teams = Main.getBedWarsTeams().size();
        for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
            if (bedWarsTeam.isEmpty()){
                teams--;
            }
        }
        if (teams == 1){
            if (Main.isStarted()) {
                Main.getInstance().finish();
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        BedWarsPlayer bedWarsPlayer = BedWarsPlayer.getBedWarsPlayerForName(e.getEntity());
        assert bedWarsPlayer != null;
        if (!bedWarsPlayer.getBedWarsTeam().getBedWarsBed().isDestroyed()){
            e.setDeathMessage(e.getEntity().getName() + " ist gestorben");
            return;
        }
        bedWarsPlayer.setDead(true);
        for (Player player : Bukkit.getOnlinePlayers()){
            player.hidePlayer(e.getEntity());
        }
        bedWarsPlayer.getBedWarsTeam().getPlayers().remove(bedWarsPlayer);
        int teams = Main.getBedWarsTeams().size();
        for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
            if (bedWarsTeam.isEmpty()){
                teams--;
            }
        }
        if (teams == 1){
            if (Main.isStarted()) {
                Main.getInstance().finish();
            }
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e){
        if (e.getClickedInventory().getTitle().equalsIgnoreCase("Team-Auswahl")){
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS){
                for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
                    if (e.getCurrentItem().getData().getData() == bedWarsTeam.getColor().getData()) {
                        if (bedWarsTeam.isFull()){
                            e.getWhoClicked().sendMessage("Dieses Team ist voll!");
                            return;
                        }
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

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if (e.getPlayer().getItemInHand().getType() == Material.BOOK) {
            if (e.getAction().name().contains("RIGHT")) {
                System.out.println("Right");
                Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, "Team-Auswahl");
                for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
                    inventory.addItem(new ItemStack(Material.STAINED_GLASS, 1, (short) bedWarsTeam.getColor().getData()));
                }
                e.getPlayer().openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onBedDestroy(BlockDamageEvent e){
        // TODO: 25.11.17 Prozentanzeige
        if (e.getBlock().getType() == Material.BED){
            for (BedWarsTeam bedWarsTeam : Main.getBedWarsTeams()) {
                if (bedWarsTeam.getBedWarsBed().getLocation().equals(e.getBlock().getLocation())){
                    if (BedWarsPlayer.hasBedwarsPlayer(e.getPlayer())) {
                        if (!BedWarsPlayer.getBedWarsPlayerForName(e.getPlayer()).getBedWarsTeam().equals(bedWarsTeam)) {
                            bedWarsTeam.getBedWarsBed().destroy();
                            Bukkit.broadcastMessage(e.getPlayer().getName() + " hat das Bett von Team " + bedWarsTeam.getColor().name() + " zerstört!");
                        } else {
                            e.getPlayer().sendMessage("Du kannst dein eigenes Bett nicht zerstören!");
                        }
                    }
                }
            }
        }
    }
}

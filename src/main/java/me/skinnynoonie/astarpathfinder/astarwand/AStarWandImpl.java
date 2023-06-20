package me.skinnynoonie.astarpathfinder.astarwand;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class AStarWandImpl implements Listener {

    private final HashMap<UUID, TwoPoints> twoPoints = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(!event.getItem().getItemMeta().hasDisplayName()) return;
        if(!event.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED+"A"+ChatColor.YELLOW+"* "+ChatColor.RED+"Wand")) return;
        UUID uuid = event.getPlayer().getUniqueId();
        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
            twoPoints.putIfAbsent(uuid, new TwoPoints(event.getClickedBlock().getLocation(), null));
            twoPoints.get(uuid).setPointOne(event.getClickedBlock().getLocation());
            event.getPlayer().sendMessage(ChatColor.GREEN + "Point one has been set to: " + readableLocation(event.getClickedBlock().getLocation()));
        }
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            twoPoints.putIfAbsent(uuid, new TwoPoints(null, event.getClickedBlock().getLocation()));
            twoPoints.get(uuid).setPointTwo(event.getClickedBlock().getLocation());
            event.getPlayer().sendMessage(ChatColor.GREEN + "Point two has been set to: " + readableLocation(event.getClickedBlock().getLocation()));
        }
        if(event.getAction() == Action.LEFT_CLICK_AIR && event.getPlayer().isSneaking()) {
            twoPoints.remove(uuid);
            event.getPlayer().sendMessage(ChatColor.GREEN + "Successfully cleared your clipboard!");
        }
        if(twoPoints.getOrDefault(uuid, new TwoPoints(null, null)).bothPointsSet() && twoPoints.get(uuid).isSameWorld()) {
            event.getPlayer().sendMessage(ChatColor.GOLD + "Attempting the A* search...");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        twoPoints.remove(event.getPlayer().getUniqueId());
    }

    private String readableLocation(Location location) {
        return location.getX() + ", " + location.getY() + ", " + location.getZ();
    }

}

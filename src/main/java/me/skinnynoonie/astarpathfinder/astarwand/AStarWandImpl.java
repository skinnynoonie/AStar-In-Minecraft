package me.skinnynoonie.astarpathfinder.astarwand;

import me.skinnynoonie.astarpathfinder.astar.AStarPathfinder;
import me.skinnynoonie.astarpathfinder.astar.distances.ManhattanDistanceCalculator;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AStarWandImpl implements Listener {

    private final HashMap<UUID, TwoPoints> twoPoints = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(!event.getItem().getItemMeta().hasDisplayName()) return;
        if(!event.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED+"A"+ChatColor.YELLOW+"* "+ChatColor.RED+"Wand")) return;
        event.setCancelled(true);
        UUID uuid = event.getPlayer().getUniqueId();

        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
            pointOne(event, uuid);
        }
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            pointTwo(event, uuid);
        }
        if(event.getAction() == Action.LEFT_CLICK_AIR && event.getPlayer().isSneaking()) {
            twoPoints.remove(uuid);
            event.getPlayer().sendMessage("Successfully cleared your clipboard!");
        }

        if(twoPoints.getOrDefault(uuid, new TwoPoints(null, null)).bothPointsSet() && twoPoints.get(uuid).isSameWorld()) {
            event.getPlayer().sendMessage("Attempting the A* search...");
            TwoPoints selectTwoPoints = twoPoints.get(uuid);

            long now = System.currentTimeMillis();
            List<Location> path = new AStarPathfinder(500000)
                    .setDistanceCalculator(new ManhattanDistanceCalculator())
                    .findPathTo(selectTwoPoints.getPointOne(), selectTwoPoints.getPointTwo());
            long time = System.currentTimeMillis() - now;

            if(path == null) {
                event.getPlayer().sendMessage(ChatColor.RED+"Failed! Took (ms): "+time);
                return;
            }
            event.getPlayer().sendMessage(ChatColor.GREEN+"Success! Took (ms): "+time);
            path.forEach(
                    location -> location.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 1, 0, 0, 0, 1, 5, 64)
            );
        }
    }

    private void pointOne(PlayerInteractEvent event, UUID uuid) {
        Location location = event.getClickedBlock().getLocation().add(0, 1, 0);
        twoPoints.putIfAbsent(uuid, new TwoPoints(location, null));
        twoPoints.get(uuid).setPointOne(location);
        location.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 1, 0, 0, 0, 1, 50, 64);
        event.getPlayer().sendMessage("Point one has been set to: " + readableLocation(location));
    }

    private void pointTwo(PlayerInteractEvent event, UUID uuid) {
        Location location = event.getClickedBlock().getLocation().add(0, 1, 0);
        twoPoints.putIfAbsent(uuid, new TwoPoints(null, location));
        twoPoints.get(uuid).setPointTwo(location);
        location.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 1, 0, 0, 0, 1, 50, 64);
        event.getPlayer().sendMessage("Point two has been set to: " + readableLocation(location));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        twoPoints.remove(event.getPlayer().getUniqueId());
    }

    private String readableLocation(Location location) {
        return location.getX() + ", " + location.getY() + ", " + location.getZ();
    }

}

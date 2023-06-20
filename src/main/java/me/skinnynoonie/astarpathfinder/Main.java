package me.skinnynoonie.astarpathfinder;

import me.skinnynoonie.astarpathfinder.astarwand.AStarWandCommand;
import me.skinnynoonie.astarpathfinder.astarwand.AStarWandImpl;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.getServer().getPluginManager().registerEvents(new AStarWandImpl(), this);
        super.getCommand("giveastarwand").setExecutor(new AStarWandCommand());
    }

    @Override
    public void onDisable() {
    }

}

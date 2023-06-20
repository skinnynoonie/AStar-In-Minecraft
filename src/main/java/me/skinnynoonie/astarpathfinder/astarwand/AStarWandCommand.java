package me.skinnynoonie.astarpathfinder.astarwand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AStarWandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Only players can use this command!");
            return true;
        }
        if(!sender.isOp()) {
            sender.sendMessage(ChatColor.RED+"You do not have permission to use this command!");
            return true;
        }
        Player player = (Player) sender;
        player.getInventory().addItem(getAStarWand());
        sender.sendMessage(ChatColor.GREEN+"You have received an "+ChatColor.RED+"A"+ChatColor.YELLOW+"* "+ChatColor.RED+"Wand"+ChatColor.GREEN+".");
        return true;
    }

    private ItemStack getAStarWand() {
        ItemStack aStarWand = new ItemStack(Material.STICK);
        ItemMeta itemMeta = aStarWand.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED+"A"+ChatColor.YELLOW+"* "+ChatColor.RED+"Wand");
        itemMeta.setLore(Arrays.asList(
                String.valueOf(ChatColor.RED),
                ChatColor.GRAY+"Left click to set point one.",
                ChatColor.GRAY+"Right click to set point two.",
                ChatColor.GRAY+"Left click AIR + crouch to wipe clipboard."
        ));
        itemMeta.addEnchant(Enchantment.LURE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        aStarWand.setItemMeta(itemMeta);
        return aStarWand;
    }

}

package com.ksor.cordleacker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CordLeacker extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CordLeacker успешно запущен!");
        
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CordLeackerExpansion(this).register();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cord")) {
            if (!sender.hasPermission("cordleacker.use")) {
                sender.sendMessage("§cУ вас нет прав на использование этой команды!");
                return true;
            }
            
            if (args.length < 1) {
                sender.sendMessage("§cИспользование: /cord <ник>");
                return true;
            }
            
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cИгрок " + args[0] + " не найден или не в сети!");
                return true;
            }
            
            sendLocationInfo(sender, target);
            return true;
        }
        return false;
    }

    public void sendLocationInfo(CommandSender sender, Player target) {
        Location loc = target.getLocation();
        String worldName = getFormattedWorldName(loc.getWorld());
        
        sender.sendMessage("§fИнформация об игроке §a" + target.getName());
        sender.sendMessage("§7Мир: §f" + worldName);
        sender.sendMessage("§7Координаты: §aX§f " + (int)loc.getX() + 
                         " §aY§f " + (int)loc.getY() + 
                         " §aZ§f " + (int)loc.getZ());
    }

    public String getFormattedWorldName(World world) {
        String name = world.getName();
        switch (name.toLowerCase()) {
            case "world": return "Основной мир";
            case "world_nether": return "§4Незер";
            case "world_the_end": return "§5Энд";
            default: return name;
        }
    }
}
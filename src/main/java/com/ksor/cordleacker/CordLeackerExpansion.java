package com.ksor.cordleacker;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CordLeackerExpansion extends PlaceholderExpansion {
    private final CordLeacker plugin;

    public CordLeackerExpansion(CordLeacker plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "cordleacker";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ksor";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return "";
        
        switch (params.toLowerCase()) {
            case "x": return String.valueOf((int) player.getLocation().getX());
            case "y": return String.valueOf((int) player.getLocation().getY());
            case "z": return String.valueOf((int) player.getLocation().getZ());
            case "world": return player.getWorld().getName();
            case "world_formatted": return plugin.getFormattedWorldName(player.getWorld());
            case "full_info": return plugin.getFormattedWorldName(player.getWorld()) + " | " + 
                   (int) player.getLocation().getX() + " " + 
                   (int) player.getLocation().getY() + " " + 
                   (int) player.getLocation().getZ();
            default: return null;
        }
    }
}
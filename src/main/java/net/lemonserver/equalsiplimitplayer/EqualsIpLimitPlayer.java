package net.lemonserver.equalsiplimitplayer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EqualsIpLimitPlayer extends JavaPlugin {
    public static FileConfiguration config;
    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        config = getConfig();
        plugin=this;
        getServer().getPluginManager().registerEvents(new eventcatch(), this);
    }

    @Override
    public void onDisable() {
    }
}

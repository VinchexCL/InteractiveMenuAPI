package dev.vinchex.interactivemenuapi;

import dev.vinchex.interactivemenuapi.listeners.MenuListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import dev.vinchex.interactivemenuapi.utils.InteractiveMenu;

public class Main extends JavaPlugin {

    private InteractiveMenu menu;


    @Override
    public void onEnable() {
        getLogger().info("InteractiveMenuAPI has been enabled!");
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("InteractiveMenuAPI has been disabled!");
    }
}

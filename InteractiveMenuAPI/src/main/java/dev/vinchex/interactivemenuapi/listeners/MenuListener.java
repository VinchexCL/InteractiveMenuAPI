package dev.vinchex.interactivemenuapi.listeners;

import dev.vinchex.interactivemenuapi.utils.InteractiveMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.HashMap;
import java.util.Map;

public class MenuListener implements Listener {

    private static final Map<String, InteractiveMenu> menus = new HashMap<>();

    public static void registerMenu(String id, InteractiveMenu menu) {
        menus.put(id, menu);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        menus.values().forEach(menu -> menu.handleClick(event));
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        menus.values().forEach(menu -> menu.handleDrag(event));
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        menus.values().forEach(menu -> menu.handleDrop(event));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        menus.values().forEach(menu -> menu.handleClose(event));
    }
}
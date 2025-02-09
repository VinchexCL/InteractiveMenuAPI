package dev.vinchex.interactivemenuapi.listeners;

import dev.vinchex.interactivemenuapi.utils.InteractiveMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MenuListener implements Listener {

    private static final Map<String, InteractiveMenu> menus = new ConcurrentHashMap<>();

    public static void registerMenu(String id, InteractiveMenu menu) {
        menus.put(id, menu);
    }

    public static void unregisterMenu(String id) {
        menus.remove(id);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        for (InteractiveMenu menu : menus.values()) {
            menu.handleClick(event);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        for (InteractiveMenu menu : menus.values()) {
            menu.handleDrag(event);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        for (InteractiveMenu menu : menus.values()) {
            menu.handleDrop(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        for (InteractiveMenu menu : menus.values()) {
            menu.handleClose(event);
        }
    }
}

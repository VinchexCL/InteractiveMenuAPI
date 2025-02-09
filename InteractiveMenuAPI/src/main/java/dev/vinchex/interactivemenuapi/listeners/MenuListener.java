package dev.vinchex.interactivemenuapi.listeners;

import dev.vinchex.interactivemenuapi.utils.InteractiveMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MenuListener implements Listener {

    private static final Map<String, InteractiveMenu> menus = Collections.synchronizedMap(new HashMap<>());

    public static void registerMenu(String id, InteractiveMenu menu) {
        menus.put(id, menu);
    }

    public static void unregisterMenu(String id) {
        menus.remove(id);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        synchronized (menus) {
            Iterator<InteractiveMenu> iterator = menus.values().iterator();
            while (iterator.hasNext()) {
                InteractiveMenu menu = iterator.next();
                menu.handleClick(event);
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        synchronized (menus) {
            Iterator<InteractiveMenu> iterator = menus.values().iterator();
            while (iterator.hasNext()) {
                InteractiveMenu menu = iterator.next();
                menu.handleDrag(event);
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        synchronized (menus) {
            Iterator<InteractiveMenu> iterator = menus.values().iterator();
            while (iterator.hasNext()) {
                InteractiveMenu menu = iterator.next();
                menu.handleDrop(event);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        synchronized (menus) {
            Iterator<InteractiveMenu> iterator = menus.values().iterator();
            while (iterator.hasNext()) {
                InteractiveMenu menu = iterator.next();
                menu.handleClose(event);
            }
        }
    }
}

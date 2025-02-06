# InteractiveMenuAPI

La `InteractiveMenuAPI` es una biblioteca para crear menús interactivos en plugins de Minecraft. Esta API permite a los desarrolladores crear menús personalizados con ítems interactivos que no pueden ser movidos, agarrados ni botados por los jugadores.

## Características

- Crear menús personalizados con títulos y tamaños definidos.
- Agregar ítems interactivos con acciones personalizadas.
- Evitar que los jugadores muevan, agarren o boten objetos del menú.
- Manejo de eventos de clic, arrastre y cierre de inventario.

## Requisitos

- Spigot 1.16.5 o superior.
- Java 8 o superior.
- Maven.

## Instalación

1. Clona o descarga el repositorio de `InteractiveMenuAPI`.
2. Incluye la dependencia en tu archivo `pom.xml`.

```xml

    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/VinchexCL/InteractiveMenuAPI</url>
    </repository>

<dependency>
    <groupId>dev.vinchex</groupId>
    <artifactId>interactive-menu-api</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

```
## Codigo de Ejemplo

```java

import dev.vinchexcl.interactivemenuapi.utils.InteractiveMenu;
import dev.vinchexcl.interactivemenuapi.listeners.MenuListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MenuManager {

    private final InteractiveMenu menu;

    public MenuManager(Plugin plugin) {
        // Crea un menú con 3 objetos
        menu = new InteractiveMenu("My Menu", 9);

        // Agrega el primer objeto
        menu.setItem(0, InteractiveMenu.createMenuItem(Material.DIAMOND, "Diamond Item", "This is a diamond item"), event -> {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("You clicked the diamond item!");
        });

        // Agrega el segundo objeto
        menu.setItem(1, InteractiveMenu.createMenuItem(Material.GOLD_INGOT, "Gold Item", "This is a gold item"), event -> {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("You clicked the gold item!");
        });

        // Agrega el tercer objeto
        menu.setItem(2, InteractiveMenu.createMenuItem(Material.IRON_INGOT, "Iron Item", "This is an iron item"), event -> {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("You clicked the iron item!");
        });

        // Registra el menú
        MenuListener.registerMenu("myMenu", menu);
    }

    public void openMenu(Player player) {
        menu.open(player);
    }
}

```

```java

import dev.vinchexcl.interactivemenuapi.utils.InteractiveMenu;
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

```

```java

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InteractiveMenu {

    private final Inventory inventory;
    private final Map<Integer, Consumer<InventoryClickEvent>> actions;

    public InteractiveMenu(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
        this.actions = new HashMap<>();
    }

    public void setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> action) {
        inventory.setItem(slot, item);
        actions.put(slot, action);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public void handleClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
            event.setCancelled(true);
            Consumer<InventoryClickEvent> action = actions.get(event.getSlot());
            if (action != null) {
                action.accept(event);
            }
        }
    }

    public void handleDrag(InventoryDragEvent event) {
        if (event.getInventory().equals(inventory)) {
            event.setCancelled(true);
        }
    }

    public void handleDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getOpenInventory().getTopInventory().equals(inventory)) {
            event.setCancelled(true);
        }
    }

    public void handleClose(InventoryCloseEvent event) {
        // Opcional: Si quieres realizar alguna acción cuando se cierra el inventario
    }

    public static ItemStack createMenuItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
        }
        return item;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

```

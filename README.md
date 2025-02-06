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

package dev.vinchexcl.example.others;

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

package me.kansio.abilities.items.damage;


import me.kansio.abilities.AbilityPlugin;
import me.kansio.abilities.manager.Ability;
import me.kansio.abilities.utils.Cooldowns;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Combo extends Ability implements Listener {

    public Map<UUID, Integer> combos = new ConcurrentHashMap<>();


    public Combo() {
        Bukkit.getPluginManager().registerEvents(this, AbilityPlugin.getInstance());
    }

    @Override
    public int coolDown() {
        return 5;
    }

    @Override
    public String displayName() {
        return "§c§lRocket";
    }

    @Override
    public String name() {
        return "rocket";
    }

    @Override
    public Material material() {
        return Material.FIREWORK;
    }

    @Override
    public String lore() {
        return "§c§lRIGHT CLICK §fstart combo mode. .";
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) {
            return;
        }
        if (!event.getAction().name().contains("RIGHT")) {
            return;
        }
        if (!item().isSimilar(event.getItem())) {
            return;
        }

        if (Cooldowns.isOnCooldown("partner", player)) {
            player.sendMessage("§cYou're still on item cooldown.");
            return;
        }

        if (Cooldowns.isOnCooldown(name(), player)) {
            player.sendMessage("§cYou're still on " + displayName() + " cooldown.");
            return;
        }

        putOnCooldown(player);
        player.sendMessage("§aYou've used " + displayName() + "§a! You're now on cooldown for " + coolDown() + " seconds.");
        player.setVelocity(new Vector(0, 1.1, 0));
        takeItem(player, item(), true);
        event.setCancelled(true);
    }
}
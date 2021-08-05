package me.kansio.abilities.items.interactables;

import me.kansio.abilities.AbilityPlugin;
import me.kansio.abilities.manager.Ability;
import me.kansio.abilities.utils.Cooldowns;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Strength extends Ability implements Listener {

    public Strength() {
        Bukkit.getPluginManager().registerEvents(this, AbilityPlugin.getInstance());
    }

    @Override
    public int coolDown() {
        return 60;
    }

    @Override
    public String displayName() {
        return "§4§lStrength";
    }

    @Override
    public String name() {
        return "strength";
    }

    @Override
    public Material material() {
        return Material.BLAZE_POWDER;
    }

    @Override
    public String lore() {
        return "§c§lRIGHT CLICK §fto give yourself strength 2";
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 8 * 20, 1));
        takeItem(player, item(), true);
        event.setCancelled(true);
    }
}

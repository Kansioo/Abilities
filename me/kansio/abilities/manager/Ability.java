package me.kansio.abilities.manager;

import me.kansio.abilities.utils.Cooldowns;
import me.kansio.abilities.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Ability implements Listener {

    public abstract int coolDown();
    public abstract String displayName();
    public abstract String name();
    public abstract Material material();
    public abstract String lore();

    public void takeItem(Player player, ItemStack item, boolean shouldTake) {
        if (shouldTake) {
            if (player.getItemInHand().getAmount() > 1) {
                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            } else {
                player.setItemInHand(null);
            }
        }
    }

    public void putOnCooldown(Player player) {
        Cooldowns.addCooldown("partner", player, 10);
        Cooldowns.addCooldown(name(), player, coolDown());
    }

    public ItemStack item() {
        ItemStack stack = new ItemBuilder(material())
                .displayName(displayName())
                .lore(lore())
                .build();

        return stack;
    }
}

package me.kansio.abilities.commands;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import me.kansio.abilities.AbilityPlugin;
import me.kansio.abilities.manager.Ability;
import org.bukkit.entity.Player;

import java.util.Locale;

public class AbilityCommand {

    @Command(
            names = "ability give",
            permission = "rank.headadmin"
    )
    public static void abilityGive(Player player, @Parameter(name = "player") Player target, @Parameter(name = "ability") String name, @Parameter(name = "amount", defaultValue = "1") int amount) {
        if (amount >= 30) {
            player.sendMessage("§cYou cannot give that many items...");
            return;
        }

        for (Ability abilities: AbilityPlugin.getInstance().getAbilityManager().getAbilities()) {
            if (abilities.name().equalsIgnoreCase(name)) {
                for (int i = 0; i < amount; i++) {
                    target.getInventory().addItem(abilities.item());
                }
                player.sendMessage("§aYou gave §f" + amount + "§a of " + abilities.displayName() + " §ato §f" + target.getName() + "§a.");
                target.sendMessage("§aYou received " + amount + "x of " + abilities.displayName() + "§a.");
                return;
            } else {
                player.sendMessage(abilities.name() + " - " + name);
            }
        }
        player.sendMessage("§cThat item doesn't exist.");
    }

    @Command(
            names = "ability list",
            permission = "rank.headadmin"
    )
    public static void abilityList(Player player) {
        for (Ability abilities: AbilityPlugin.getInstance().getAbilityManager().getAbilities()) {
            player.sendMessage(abilities.displayName() + " §7- §f" + abilities.name());
        }
    }

}

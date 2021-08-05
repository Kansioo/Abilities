package me.kansio.abilities;

import cc.fyre.proton.Proton;
import me.kansio.abilities.manager.Ability;
import me.kansio.abilities.manager.AbilityManager;
import me.kansio.abilities.utils.Cooldowns;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class AbilityPlugin extends JavaPlugin {

    private static AbilityPlugin instance;
    private AbilityManager abilityManager;

    public static AbilityPlugin getInstance() {
        return instance;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    @Override
    public void onEnable() {
        Cooldowns.createCooldown("partner");
        instance = this;
        abilityManager = new AbilityManager();
        Proton.getInstance().getCommandHandler().registerAll(this);
        //automatically create the cooldowns :)
        for (Ability ability : getAbilityManager().getAbilities()) {
            Cooldowns.createCooldown(ability.name());
        }
    }
}

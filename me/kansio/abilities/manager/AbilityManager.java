package me.kansio.abilities.manager;

import me.kansio.abilities.items.interactables.Resistance;
import me.kansio.abilities.items.interactables.Rocket;
import me.kansio.abilities.items.interactables.Strength;

import java.util.ArrayList;

public class AbilityManager {

    private ArrayList<Ability> abilities = new ArrayList<>();

    public AbilityManager() {
        System.out.println("Registering abilities.");
        abilities.add(new Rocket());
        abilities.add(new Resistance());
        abilities.add(new Strength());
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }


}

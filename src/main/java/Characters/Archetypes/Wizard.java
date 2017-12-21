package Characters.Archetypes;

import Characters.Armour;
import Characters.OffHand;
import Characters.Weapon;

public class Wizard extends Character {

    Integer manaMax;
    Integer manaLevel;
    Integer manaRegen;


    public Wizard(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);
        this.strength = 20;
        this.agility = 30;
        this.intellect = 80;
        this.stamina = 25;
        this.baseThreat = 40;
        this.threat = 0;
        this.critChance = intellect/100;
        this.critDamage = intellect/25;
        this.stunned = false;
        this.manaLevel = 50;
        this.manaRegen = 5;
        this.manaMax = 100;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }

//  Attack Mechanics:
    @Override
    public String spell(Character target) {
        double damage;
        damage = 1000.00 * randomDamageModifier();
        target.healthBar = healthBar - damage;
        return "Fireball! : Did " + damage + "damage.";}
}

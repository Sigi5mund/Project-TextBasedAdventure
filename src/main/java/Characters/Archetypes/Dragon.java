package Characters.Archetypes;

import Characters.Armour;
import Characters.Interfaces.IThreatAttack;
import Characters.OffHand;
import Characters.Weapon;

public class Dragon extends Character implements IThreatAttack {

    double maxHealth;

    public Dragon(String name, int gold) {
        super(name, gold, Weapon.CLAWS, Armour.LEATHER, OffHand.CLAW);
        this.maxHealth = stamina * 8;
        this.healthBar = maxHealth;
        this.strength = 100;
        this.agility = 20;
        this.intellect = 50;
        this.stamina = 100;
        this.baseThreat = 100;
        this.threat = 0;
        this.critChance = agility / 100;
        this.stunned = false;
    }

}

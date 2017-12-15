package Characters;

public class Priest extends Character implements ISpell {

    double maxHealth;

    public Priest(String name, int gold, Weapon weapon, Armour armour) {
        super(name, gold, weapon, armour);
        this.maxHealth = 350;
        this.healthBar = maxHealth;
    }


    public double getMaxHealth() {
        return maxHealth;
    }

    @Override
    public String Spell(Character target) {
        double heal;
        heal = 300 * randomDamageModifier();
        target.healthBar = healthBar + heal;
        return "HealthBomb: did " + heal + " healing";
    }

}


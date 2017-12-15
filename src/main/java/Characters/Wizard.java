package Characters;

public class Wizard extends Character implements ISpell{

    double maxHealth;

    public Wizard(String name, int gold, Weapon weapon, Armour armour) {
        super(name, gold, weapon, armour);
        this.maxHealth = 500;
        this.healthBar = maxHealth;
    }


    public double getMaxHealth() {
        return maxHealth;
    }

    @Override
    public String Spell(Character target) {
        double damage;
        damage = 1000.00 * randomDamageModifier();
        target.healthBar = healthBar - damage;
        return "Fireball! : Did " + damage + "damage.";}
}

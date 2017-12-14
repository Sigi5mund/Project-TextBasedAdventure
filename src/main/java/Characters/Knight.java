package Characters;

public class Knight extends Character {

        double maxHealth;

    public Knight(String name, int gold, Weapon weapon, Armour armour) {
        super(name, gold, weapon, armour);
        this.maxHealth = 1000.00;
        this.healthBar = maxHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }
}

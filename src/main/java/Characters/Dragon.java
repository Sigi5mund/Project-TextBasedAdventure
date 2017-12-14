package Characters;

public class Dragon extends Character {

    double maxHealth;

    public Dragon(String name, int gold){
        super(name, gold, Weapon.CLAWS, Armour.LEATHER);
        this.maxHealth = 2000;
        this.healthBar = maxHealth;


    }

    public double getMaxHealth() {
        return maxHealth;
    }
}

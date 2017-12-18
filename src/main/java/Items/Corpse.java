package Items;

import Characters.Armour;
import Characters.Archetypes.Character;
import Characters.OffHand;
import Characters.Weapon;

import java.util.ArrayList;

public class Corpse extends Item{
    Armour armour;
    Weapon weapon;
    OffHand offHand;


    public Corpse(String name, double gold, ArrayList<Item> items) {
        super(name, gold, items);
        this.armour = Armour.DEFAULT;
        this.weapon = Weapon.DEFAULT;
        this.offHand = OffHand.DEFAULT;
    }


    public Armour getArmour() {
        return armour;
    }
    public void setArmour(Armour armour) {
        this.armour = armour;
    }
    public void lootArmour(Character looter){
        looter.setArmour(armour);
    }
    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void lootWeapon(Character looter) {
        looter.setWeapon(weapon);
    }

    public OffHand getOffHand() {
        return offHand;
    }

    public void setOffHand(OffHand offHand) {
        this.offHand = offHand;
    }
}

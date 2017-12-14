package Items;

import Characters.Armour;
import Characters.Character;
import Characters.Weapon;

import java.util.ArrayList;

public class Corpse extends Item{
    Armour armour;
    Weapon weapon;


    public Corpse(String name, double gold, ArrayList<Item> items) {
        super(name, gold, items);
        this.armour = Armour.DEFAULT;
        this.weapon = Weapon.DEFAULT;
    }

    public void corpseCreation(Character character){
        Corpse playerCorpse;
        playerCorpse= new Corpse(character.getName(), character.getGold(), character.getItems());
        playerCorpse.setArmour(character.getArmour());
        playerCorpse.setWeapon(character.getWeapon());
        String name = character.getName() + "'s corpse";
        
    }


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

}

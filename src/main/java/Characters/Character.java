package Characters;

import Items.Corpse;
import Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Character{
    String name;
    double gold;
    Weapon weapon;
    Double healthBar;
    ArrayList<Item> items;
    boolean superWeapon;
    ArrayList<Double> damageModifier;
    Armour armour;
    boolean alive;



    public Character(String name, double gold, Weapon weapon, Armour armour) {
        this.name = name;
        this.gold = gold;
        this.weapon = weapon;
        this.healthBar = healthBar;
        this.items = new ArrayList<>();
        this.superWeapon = false;
        this.armour = armour;
        this.alive = true;
        this.damageModifier = new ArrayList<>(Arrays.asList(0.5, 0.75, 1.0, 1.0, 1.0, 1.0, 1.5, 2.0));
    }

    public ArrayList<Item> getItems() {
        return items;
    }


    public void setSuperWeapon(Boolean change){
        this.superWeapon = change;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armour getArmour() {
        return armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public void attack(Character target) {
        double damage;
        damage = weapon.getWeaponDamage() * randomDamageModifier();
        if (superWeapon == true) {
            damage = damage * 3;
        }
        this.superWeapon = false;
        target.takeDamage(damage);
        target.checkAlive();
    }

    public void takeDamage(double damage) {
        if (damage < 0) {
            this.healthBar = healthBar - damage;
        } else {
            damage = damage * armour.getValue();
            this.healthBar = healthBar - damage;
        }
    }

    public Double randomDamageModifier() {
        Collections.shuffle(this.damageModifier);
        return damageModifier.get(0);
    }

    public Double getHealthBar() {
        return this.healthBar;
    }

    public void changeArmour(Armour armour) {
        this.armour = armour;
    }



    public String checkAlive() {
        if (this.healthBar <= 0){
            this.alive = false;
            return "AHHHHhhh!";
        }
        else
            return "I am still alive";
    }

    public void setHealthBar(Double healthBar) {
        this.healthBar = healthBar;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public void addGold(double gold){
        this.gold= this.gold + gold;
    }

    public void payGold(double gold){
        this.gold = this.gold - gold;
    }

    public void addHealth(double increase){
          this.healthBar = healthBar + increase;
    }

    public void decreaseHealth (double decrease){
        this.healthBar = healthBar - decrease;
    }

    public void increaseHealth50Percent(){
        this.healthBar = healthBar * 1.5;
    }

    public String examineCorpse(Corpse corpse){
        return
    }
}

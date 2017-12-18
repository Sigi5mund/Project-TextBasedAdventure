package Characters.Archetypes;

import Characters.Armour;
import Characters.Interfaces.IAttack;
import Characters.Interfaces.ISpell;
import Characters.Interfaces.ITakeDamage;
import Characters.OffHand;
import Characters.Weapon;
import Items.Corpse;
import Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Character implements ISpell, IAttack, ITakeDamage {

    protected String name;
    protected double gold;
    protected Weapon weapon;
    protected OffHand offHand;
    protected  Double healthBar;
    protected  ArrayList<Item> items;
    protected boolean superWeapon;
    protected ArrayList<Double> damageModifier;
    protected Armour armour;
    protected  boolean alive;
    protected  Integer strength;
    protected Integer agility;
    protected  Integer intellect;
    protected  Integer stamina;
    protected Integer baseThreat;
    protected  Integer threat;
    protected  Integer critChance;
    protected  Boolean stunned;
    protected Integer dodgeChance;
    protected  Integer blockChance;
    protected  Integer magicDefense;
    protected  Integer stunnedChance;
    protected  String attackExclamation;
    protected  String defenseExclamation;
    protected String healedExclamation;
    protected String critExclamation;
    protected double maxHealth;


    public Character(String name, double gold, Weapon weapon, Armour armour, OffHand offHand) {
        this.name = name;
        this.gold = gold;
        this.weapon = weapon;
        this.offHand = offHand;
        this.items = new ArrayList<>();
        this.superWeapon = false;
        this.armour = armour;
        this.alive = true;
        this.damageModifier = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0));
        this.strength = 100;
        this.agility = 100;
        this.intellect = 100;
        this.stamina = 100;
        this.baseThreat = 50;
        this.threat = 0;
        this.critChance = 50;
        this.stunned = false;
        this.dodgeChance = agility/100;
        this.blockChance = strength/100;
        this.magicDefense = intellect/100;
        this.stunnedChance = stamina/100;
        this.attackExclamation = "";
        this.defenseExclamation= "";
        this.healedExclamation = "";
        this.critExclamation = "";
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }

//  Attack Mechanics:

    public void attack(Character target) {
        double damage;
        damage = weapon.getWeaponDamage() * randomDamageModifier();
        if (superWeapon) {
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
            this.healthBar = this.healthBar - damage;
        }
    }

    public Double randomDamageModifier() {
        Collections.shuffle(this.damageModifier);
        return damageModifier.get(0);
    }

    @Override
    public String spell(Character target) {
        return null;
    }




//    Weapons and Armours:

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armour getArmour() {
        return this.armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public void changeArmour(Armour armour) {
        this.armour = armour;
    }

    public OffHand getOffHand() {
        return this.offHand;
    }




//    Health Getters and Setters:

    public Double getHealthBar() {
        return this.healthBar;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public void setHealthBar(Double healthBar) {
        this.healthBar = healthBar;
    }

    public void increaseHealth(double increase){
        this.healthBar = healthBar + increase;
    }

    public void decreaseHealth (double decrease){
        this.healthBar = healthBar - decrease;
    }

    public void increaseHealth50Percent(){
        this.healthBar = healthBar * 1.5;
    }

    public void maxHealthExceededCheck(){
        if (this.getHealthBar() > this.getMaxHealth()){
            this.setHealthBar(this.getMaxHealth());
        }
    }

    public boolean checkAlive() {
        if (this.healthBar <= 0){
            this.alive = false;
            return alive;
        }
        else {
            this.alive = true;
            return alive;
        }
    }

    public boolean isAlive() {
        return alive;
    }




//  Constructor Getters and Setters:

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public boolean isSuperWeapon() {
        return superWeapon;
    }

    public void setSuperWeapon(Boolean change){
        this.superWeapon = change;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }




//    Gold and Loot Mechanics:

    public double getGold() {
        return this.gold;
    }

    public void takeGold(Corpse corpse){
        addGold(corpse.getGold());
        corpse.setGold(corpse.getGold());
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

    public String examineCorpse(Corpse corpse){
        return corpse.getName() + " has " + corpse.getGold() + " gold, " + corpse.getArmour() + " armour and a " + corpse.getWeapon() + " weapon. What will you take?";
    }




//    Stats and Status Getters:

    public Integer getStrength() {
        return this.strength;
    }

    public Integer getAgility() {
        return this.agility;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public Integer getStamina() {
        return stamina;
    }

    public Integer getBaseThreat() {
        return this.baseThreat;
    }

    public Integer getThreat() {
        return this.threat;
    }

    public Integer getCritChance() {
        return this.critChance;
    }

    public Integer getDodgeChance() {
        return this.dodgeChance;
    }

    public Integer getBlockChance() {
        return this.blockChance;
    }

    public Integer getMagicDefense() {
        return this.magicDefense;
    }

    public Boolean getStunned() {
        return this.stunned;
    }

    public Integer getStunnedChance() {
        return this.stunnedChance;
    }

    public String getAttackExclamation() {
        return this.attackExclamation;
    }

    public String getDefenseExclamation() {
        return this.defenseExclamation;
    }

    public String getHealedExclamation() {
        return this.healedExclamation;
    }

    public String getCritExclamation() {
        return this.critExclamation;
    }



//    Stats and Status Setters:

    public void setStunned(boolean stun){
        this.stunned = stun;
    }

}

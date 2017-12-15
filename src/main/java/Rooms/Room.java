package Rooms;

import Characters.Character;
import Items.Corpse;
import Items.Item;

import java.util.ArrayList;


public abstract class Room {

    double rewardGold;
    public ArrayList<Character> goodies;
    public ArrayList<Character> baddies;
    public ArrayList<Corpse> floor;
    public ArrayList<Item> shelves;


    public Room(ArrayList goodies, ArrayList baddies, double rewardGold) {
        this.rewardGold = rewardGold;
        this.goodies = goodies;
        this.baddies = baddies;
        this.floor = new ArrayList<>();
        this.shelves = new ArrayList<>();

    }

    public ArrayList<Character> getGoodies() {
        return goodies;
    }


    public void setGoodies(ArrayList<Character> goodies) {
        this.goodies = goodies;
    }

    public void addGoodies(Character character){
        this.goodies.add(character);
    }

    public void removeGoodies(Character character){
        this.goodies.remove(character);
    }


    public ArrayList<Character> getBaddies() {
        return baddies;
    }

    public void setBaddies(ArrayList<Character> baddies) {
        this.baddies = baddies;
    }

    public void addBaddies(Character character){
        this.baddies.add(character);
    }

    public void removeBaddies(Character character){
        this.baddies.remove(character);
    }

    public void collectReward(Character character){
        character.setGold(character.getGold() + this.rewardGold);
    }

    public double getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(double rewardGold) {
        this.rewardGold = rewardGold;
    }

    public void addToFloor(Corpse corpse) {
        this.floor.add(corpse);
    }

    public void checkForCorpses(){
        for (Character character: goodies){
            if (character.checkAlive() == false){
                addToFloor(corpseCreation(character));
            }
        }
        for (Character character: baddies){
            if (character.checkAlive() == false){
                addToFloor(corpseCreation(character));
            }
        }
    }

    public void removeDead() {
        baddies.removeIf(next -> !next.checkAlive());
        goodies.removeIf(next -> !next.checkAlive());

    }

//    public void checkArrayForDeadCharacters(){
//
//        ArrayList<Character> toDelete = new ArrayList<>();
//
//        for (Character character: goodies){
//            if (character.checkAlive() == false){
//                toDelete.add(character);
//            }
//        }
//        goodies.removeAll(toDelete);
//        toDelete.clear();
//
//        for (Character character: baddies){
//            if (character.checkAlive() == false){
//                toDelete.add(character);
//            }
//        }
//        baddies.removeAll(toDelete);
//    }

    public Corpse corpseCreation(Character character){
        Corpse playerCorpse;
        playerCorpse= new Corpse(character.getName()+ "'s corpse", character.getGold(), character.getItems());
        playerCorpse.setArmour(character.getArmour());
        playerCorpse.setWeapon(character.getWeapon());
        return playerCorpse;
    }


    public void endOfCombatChecks(){
        checkForCorpses();
        removeDead();
    }

//        public String goToTheNextRoom(ArrayList<Character> adventurers){
//        if (baddies.size() == 0){
//            Room nextRoom = new Dungeon(adventurers, baddies, 10000)
//
//        }
//
//
//        return ""
//    }




}

package Rooms;

import Characters.Character;
import Items.Item;

import java.util.ArrayList;

public abstract class Room {

    double rewardGold;
    ArrayList<Character> goodies;
    ArrayList<Character> baddies;
    ArrayList<Item> floor;


    public Room(ArrayList goodies, ArrayList baddies, double rewardGold) {
        this.rewardGold = rewardGold;
        this.goodies = goodies;
        this.baddies = baddies;
        this.floor = new ArrayList<>();

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

}

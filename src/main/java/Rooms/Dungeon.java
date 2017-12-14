package Rooms;

import Characters.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dungeon extends Room {

    ArrayList<Integer> box1;

    public Dungeon(ArrayList goodies, ArrayList baddies, double rewardGold) {
        super(goodies, baddies, rewardGold);
        this.box1 = new ArrayList<>(Arrays.asList(1, 1, 1,2,3));

    }

    public String openBox(Character target) {
        Integer item;
        Collections.shuffle(this.box1);
        item = box1.get(0);
        Character person = target;
        switch (item) {
            case 1:
                target.addHealth(250);
                return target.getName() + " opens the box in the corner and finds a health potion. It raises " + target.getName() + "'s health by 250 points!";
            case 2:
                target.increaseHealth50Percent();
                return target.getName() + " opens the box in the corner and finds a health potion. The health potion raises " + target.getName() + "'s health by 50%!";
            case 3:
                target.setSuperWeapon(true);
                return target.getName() + " opens the box. A magical dust hangs in the air for a moment before infusing "+ target.getName() + "'s weapon with magical energy";
                default:
                    return "Error"; }
    }

    public String goToTheNextRoom(ArrayList<Character> adventurers){
        if (baddies.)


        return ""
    }

}
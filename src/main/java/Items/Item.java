package Items;

import Characters.Character;

import java.util.ArrayList;

public class Item {
    String name;
    String description;
    double gold;
    ArrayList<Item> items;

    public Item(String name, double gold, ArrayList<Item> items) {
        this.name = name;
        this.description = description;
        this.gold = gold;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public void takeGold(Character looter){
        looter.addGold(gold);
    }
}

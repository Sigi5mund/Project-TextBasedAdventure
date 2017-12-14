import Characters.*;
import Characters.Character;
import Rooms.Dungeon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class DungeonTest {

    Dungeon dungeon;
    ArrayList<Character> heroes;
    ArrayList<Character> villians;
    Dragon dragon;
    Knight knight;
    Priest priest;
    Wizard wizard;

    @Before
    public void before() {
        priest = new Priest("Julian", 5, Weapon.WAND, Armour.CLOTHE);
        wizard = new Wizard("Gandalf", 10, Weapon.STAFF, Armour.CLOTHE);
        knight = new Knight("Athina", 20, Weapon.SWORD, Armour.GOLD);
        heroes = new ArrayList<>();
        villians = new ArrayList<>();
        heroes.add(priest);
        heroes.add(wizard);
        heroes.add(knight);
        dragon = new Dragon("Smaug", 1000);
        villians.add(dragon);
        dungeon = new Dungeon(heroes, villians, 10000);


    }


    @Test
    public void attackFromArrays(){
        Character dragon1;
        Character knight1;
        Character priest1;
        Character wizard1;
        dragon1 = villians.get(0);
        priest1 = heroes.get(0);
        wizard1 = heroes.get(1);
        knight1= heroes.get(2);
        dragon1.attack(knight1);
        assertNotEquals(1000, knight1.getHealthBar());
        priest1.attack(wizard1);
        assertNotEquals(500, wizard1.getHealthBar());
    }

    @Test
    public void openBoxWorks(){
        Character dragon1;
        Character knight1;
        Character priest1;
        Character wizard1;
        dragon1 = villians.get(0);
        priest1 = heroes.get(0);
        wizard1 = heroes.get(1);
        knight1= heroes.get(2);
        assertNotEquals("", dungeon.openBox(priest1));
        assertNotEquals(350, priest1.getHealthBar());
    }

}

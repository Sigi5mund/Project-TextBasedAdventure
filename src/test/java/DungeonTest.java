import Characters.*;
import Characters.Character;
import Items.Item;
import Rooms.Dungeon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DungeonTest {

    Dungeon dungeon;
    ArrayList<Character> heroes;
    ArrayList<Character> villains;
    Item item;

    @Before
    public void before() {
        heroes = new ArrayList<>();
        Priest priest;
        heroes.add(new Priest("Julian", 5, Weapon.WAND, Armour.CLOTHE));
        heroes.add(new Wizard("Gandalf", 10, Weapon.STAFF, Armour.CLOTHE));
        heroes.add(new Knight("Athina", 20, Weapon.SWORD, Armour.GOLD));
        villains = new ArrayList<>();
        villains.add(new Dragon("Smaug", 1000));
        dungeon = new Dungeon(heroes, villains, 10000);
        item = new Item("Suspicious box", 1, new ArrayList<>());
        dungeon.shelves.add(item);

    }

    @Test
    public void attackFromArrays() {
        Character dragon = villains.get(0);
        Character priest = heroes.get(0);
        Character wizard = heroes.get(1);
        Character knight = heroes.get(2);
        dragon.attack(knight);
        assertNotEquals(1000, knight.getHealthBar());
        priest.attack(wizard);
        assertNotEquals(500, wizard.getHealthBar());
    }

    @Test
    public void openBoxWorks() {
        Character priest = heroes.get(0);
        item = dungeon.shelves.get(0);
        assertNotEquals("", item.openBox(priest));
        assertNotEquals(350, priest.getHealthBar());
    }

    @Test
    public void checkCorpseCreationAndLootability() {
        Character dragon = villains.get(0);
        Character priest = heroes.get(0);
        Character knight = heroes.get(2);
        dragon.attack(priest);
        dragon.attack(priest);
        dungeon.endOfCombatChecks();
        assertEquals(1, dungeon.floor.size());
        assertEquals(2, dungeon.goodies.size());
        assertEquals("Julian's corpse has 5.0 gold, CLOTHE armour and a WAND weapon. What will you take?",
                knight.examineCorpse(dungeon.floor.get(0)));
    }

    @Test
    public void checkdeletecorpsesafterspawn() {
        Character dragon = villains.get(0);
        Character priest = heroes.get(0);
        dragon.attack(priest);
        dragon.attack(priest);
        dungeon.removeDead();
        assertEquals(2, dungeon.goodies.size());
    }

    @Test
    public void checkGoldLootableAndCorpseGoldEmptyAfter() {
        Character dragon = villains.get(0);
        Character priest = heroes.get(0);
        Character knight = heroes.get(2);
        dragon.attack(priest);
        dragon.attack(priest);
        dungeon.endOfCombatChecks();
        double gold1 = knight.getGold();
        knight.takeGold(dungeon.floor.get(0));
        double gold2 = knight.getGold();
        assertNotEquals(gold1, gold2);
    }

//    @Test
//    public void spellWorksInDungeons(){
//        Character dragon;
//        dragon = villains.get(0);
//        Priest priest;
//        priest = heroes.get(0);
//        Character wizard = heroes.get(1);
//        Character knight = heroes.get(2);
//        priest.Spell(knight);
//        wizard.Spell(dragon);
//    }
}
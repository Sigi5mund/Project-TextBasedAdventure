import Characters.Archetypes.Character;
import Characters.Archetypes.*;
import Characters.Armour;
import Characters.OffHand;
import Characters.Weapon;
import Items.Item;
import Magic.DamageOverTime;
import Magic.HealOverTime;
import Rooms.Dungeon;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DungeonTest {

    private Dungeon dungeon;
    private ArrayList<Character> heroes;
    private ArrayList<Character> villains;
    private Item item;
    private HealOverTime renew;
    private DamageOverTime scorch;


    @Before
    public void before() {
        heroes = new ArrayList<>();
        heroes.add(new Priest("Cadfael", 5, Weapon.BLESSED_SCEPTER, Armour.CLOTHE, OffHand.HEALWAND));
        heroes.add(new Wizard("Gandalf", 10, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND));
        heroes.add(new Knight("Athina", 20, Weapon.SWORD, Armour.PLATE, OffHand.SHIELD));
        villains = new ArrayList<>();
        villains.add(new Dragon("Smaug", 1000));
        villains.add(new OrcCaptain("Badrag", 100, Weapon.SWORD, Armour.PLATE, OffHand.KNIFE));
        dungeon = new Dungeon(heroes, villains, 10000);
        item = new Item("Suspicious box", 1, new ArrayList<>());
        dungeon.shelves.add(item);


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
        Character dragon;
        dragon = villains.get(0);
        Character priest;
        priest = heroes.get(0);
        Character knight;
        knight = heroes.get(2);
        priest.takeDamage(2000);
        dungeon.endOfCombatChecks();
        assertEquals(1, dungeon.floor.size());
        assertEquals(2, dungeon.goodies.size());
        assertEquals("Cadfael's corpse has 5.0 gold, CLOTHE armour and a BLESSED_SCEPTER weapon. What will you take?", knight.examineCorpse(dungeon.floor.get(0)));
    }

    @Test
    public void checkdeletecorpsesafterspawn() {
        Character priest = heroes.get(0);
        priest.takeDamage(2000);
        dungeon.removeDead();
        assertEquals(2, dungeon.goodies.size());
    }

    @Test
    public void checkGoldLootableAndCorpseGoldEmptyAfter() {
        Character priest = heroes.get(0);
        Character knight = heroes.get(2);
        priest.takeDamage(2000);
        dungeon.endOfCombatChecks();
        double gold1 = knight.getGold();
        knight.takeGold(dungeon.floor.get(0));
        double gold2 = knight.getGold();
        assertNotEquals(gold1, gold2);
    }

    @Test
    public void spellWorksInDungeons() {
        Character dragon;
        dragon = villains.get(0);
        Character priest;
        priest = heroes.get(0);
        Character wizard = heroes.get(1);
        Character knight = heroes.get(2);
        priest.spell(knight);
        assertEquals(2100, knight.getHealthBar(), 1);
        double dragonHP = dragon.getHealthBar();
        wizard.spell(dragon);
        assertNotEquals(dragonHP, dragon.getHealthBar());
    }

    @Test
    public void overHealCorrectedByEndTurn() {
        Character priest;
        priest = heroes.get(0);
        Character knight;
        knight = heroes.get(2);
        priest.spell(knight);
        assertEquals(2100, knight.getHealthBar(), 1);
        dungeon.endOfCombatChecks();
        assertEquals(1800, knight.getHealthBar(), 1);
    }


    //    HOTs and Dots:
    @Test
    public void hotInteractsWithTarget() {
        Character knight;
        knight = heroes.get(2);
        renew = new HealOverTime(knight, 50, 3);
        renew.tick();
        assertEquals(1850, knight.getHealthBar(), 10);
    }

    @Test
    public void hotHasDuration() {
        Character knight;
        knight = heroes.get(2);
        renew = new HealOverTime(knight, 50, 3);
        assertEquals(1800, knight.getHealthBar(), 10);
        assertEquals(3, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1850, knight.getHealthBar(), 10);
        assertEquals(2, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1900, knight.getHealthBar(), 10);
        assertEquals(1, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1950, knight.getHealthBar(), 10);
        assertEquals(0, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1950, knight.getHealthBar(), 10);
        assertEquals(0, renew.getDuration(), 0.1);

    }

    @Test
    public void ITickLoopMechanismWorksWithHot() {
        Character knight;
        knight = heroes.get(2);
        renew = new HealOverTime(knight, 50, 3);
        dungeon.hotsAndDots.add(renew);
        assertEquals(1, dungeon.hotsAndDots.size());
        dungeon.triggerITickMechanism();
        assertEquals(1850, knight.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1900, knight.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1950, knight.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1950, knight.getHealthBar(), 10);
    }

    @Test
    public void endOfTurnTriggersHots() {
        Character knight;
        knight = heroes.get(2);
        Character orcCaptain;
        knight.takeDamage(500);
        renew = new HealOverTime(knight, 50, 3);
        dungeon.hotsAndDots.add(renew);
        assertEquals(1, dungeon.hotsAndDots.size());
        assertEquals(1550, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1600, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1650, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, knight.getHealthBar(), 10);
    }

    @Test
    public void dotInteractsWithTarget() {
        Character knight;
        knight = heroes.get(2);
        scorch = new DamageOverTime(knight, 50, 3);
        scorch.tick();
        assertEquals(1750, knight.getHealthBar(), 10);
    }


    @Test
    public void dotHasDuration() {
        Character knight;
        knight = heroes.get(2);
        scorch = new DamageOverTime(knight, 50, 3);
        assertEquals(1800, knight.getHealthBar(), 10);
        assertEquals(3, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1750, knight.getHealthBar(), 10);
        assertEquals(2, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1700, knight.getHealthBar(), 10);
        assertEquals(1, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1650, knight.getHealthBar(), 10);
        assertEquals(0, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1650, knight.getHealthBar(), 10);
        assertEquals(0, scorch.getDuration(), 0.1);
    }

    @Test
    public void ITickLoopMechanismWorksWithDot() {
        Character knight;
        knight = heroes.get(2);
        scorch = new DamageOverTime(knight, 50, 3);
        dungeon.hotsAndDots.add(scorch);
        assertEquals(1, dungeon.hotsAndDots.size());
        dungeon.triggerITickMechanism();
        assertEquals(1750, knight.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1700, knight.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1650, knight.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1650, knight.getHealthBar(), 10);
    }

    @Test
    public void endOfTurnTriggersDots() {
        Character knight;
        knight = heroes.get(2);
        scorch = new DamageOverTime(knight, 50, 3);
        dungeon.hotsAndDots.add(scorch);
        assertEquals(1, dungeon.hotsAndDots.size());
        assertEquals(1800, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1750, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1650, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1650, knight.getHealthBar(), 10);
    }

    @Test
    public void endOfTurnTriggersAllHotsAndDotsInRoomArray(){
        Character knight;
        knight = heroes.get(2);
        scorch = new DamageOverTime(knight, 100, 3);
        dungeon.hotsAndDots.add(scorch);
        renew = new HealOverTime(knight, 50, 2);
        dungeon.hotsAndDots.add(renew);
        assertEquals(1800, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1750, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1600, knight.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1600, knight.getHealthBar(), 10);

    }

}
import Characters.*;
import Characters.Archetypes.*;
import Characters.Archetypes.Character;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CharacterTest {

    Character tank;
    Character dps;
    Character dragon;
    Character healer;
    Character orcCaptain;

    @Before
    public void before(){

        tank = new Knight("Athina",0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        dps = new Wizard( "Gandalf",5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        dragon = new Dragon("Smaug",10000000);
        healer = new Priest("Cadfael", 100,Weapon.BLESSED_SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        orcCaptain = new OrcCaptain("Badrag", 100, Weapon.SWORD, Armour.PLATE, OffHand.KNIFE);

    }


    @Test
    public void attackIsModifiedByArmour(){
        tank.changeArmour(Armour.MAGIC);
        tank.takePhysicalDamage(200000000);
        assertEquals(1800, tank.getHealthBar(),1);
        tank.changeArmour(Armour.CLOTHE);
        tank.takePhysicalDamage(500);
        assertEquals(1300, tank.getHealthBar(), 1);

    }

    @Test
    public void knightTakesDamage(){
        tank.takePhysicalDamage(500);
        assertEquals(1675, tank.getHealthBar(), 0.1);
    }

    @Test
    public void canDie(){
        dps.takePhysicalDamage(2000);
        assertEquals(false, dps.checkAlive());
    }

    @Test
    public void increaseHealthPossible(){
        healer.attack(tank);
        assertEquals(2000, tank.getHealthBar(),100);
    }

    @Test
    public void castSpellPriest(){
        double health;
        double health2;
        health = tank.getHealthBar();
        healer.spell(tank);
        health2 = tank.getHealthBar();
        assertNotEquals(health, health2);
    }

    @Test
    public void castSpellWizard(){
        double health;
        double health2;
        health = dragon.getHealthBar();
        dps.spell(dragon);
        health2 = dragon.getHealthBar();
        assertNotEquals(health, health2);
    }

    @Test
    public void newAttackMethod(){
        orcCaptain.weaponattack1(tank);
        assertEquals(1780, tank.getHealthBar(), 1);
    }
}

import Characters.*;
import Characters.Archetypes.*;
import Characters.Archetypes.Character;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CharacterTest {

    Character knight;
    Character wizard;
    Character dragon;
    Character priest;
    Character orcCaptain;

    @Before
    public void before(){

        knight = new Knight("Athina",0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        wizard = new Wizard( "Gandalf",5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        dragon = new Dragon("Smaug",10000000);
        priest = new Priest("Cadfael", 100,Weapon.BLESSED_SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        orcCaptain = new OrcCaptain("Badrag", 100, Weapon.SWORD, Armour.PLATE, OffHand.KNIFE);

    }


    @Test
    public void attackIsModifiedByArmour(){
        knight.changeArmour(Armour.MAGIC);
        knight.takeDamage(200000000);
        assertEquals(1800, knight.getHealthBar(),1);
        knight.changeArmour(Armour.CLOTHE);
        knight.takeDamage(500);
        assertEquals(1300, knight.getHealthBar(), 1);

    }

    @Test
    public void knightTakesDamage(){
        knight.takeDamage(500);
        assertEquals(1675, knight.getHealthBar(), 0.1);
    }

    @Test
    public void canDie(){
        wizard.takeDamage(2000);
        assertEquals(false, wizard.checkAlive());
    }

    @Test
    public void increaseHealthPossible(){
        priest.attack(knight);
        assertEquals(2000, knight.getHealthBar(),100);
    }

    @Test
    public void castSpellPriest(){
        double health;
        double health2;
        health = knight.getHealthBar();
        priest.spell(knight);
        health2 = knight.getHealthBar();
        assertNotEquals(health, health2);
    }

    @Test
    public void castSpellWizard(){
        double health;
        double health2;
        health = dragon.getHealthBar();
        wizard.spell(dragon);
        health2 = dragon.getHealthBar();
        assertNotEquals(health, health2);
    }

    @Test
    public void newAttackMethod(){
        orcCaptain.weaponattack1(knight);
        assertEquals(1780, knight.getHealthBar(), 1);
    }
}

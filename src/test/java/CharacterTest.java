import Characters.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CharacterTest {

    Knight knight;
    Wizard wizard;
    Dragon dragon;
    Priest priest;

    @Before
    public void before(){

        knight = new Knight("Xena",0, Weapon.SWORD, Armour.GOLD);
        wizard = new Wizard( "Gandalf",5, Weapon.STAFF, Armour.CLOTHE);
        dragon = new Dragon("Smaug",10000000);
        priest = new Priest("Pope", 100,Weapon.WAND, Armour.LEATHER);
    }

    @Test
    public void knightAttacksDragon(){
        double health;
        double health2;
        health = dragon.getHealthBar();
        knight.attack(dragon);
        health2 = dragon.getHealthBar();
        assertNotEquals(health, health2);
    }

    @Test
    public void attackIsModifiedByArmour(){
        knight.changeArmour(Armour.MAGIC);
        dragon.attack(knight);
        assertEquals(1000, knight.getHealthBar(),1);
        knight.changeArmour(Armour.CLOTHE);
        dragon.attack(knight);
        assertNotEquals(1000, knight.getHealthBar(), 1);

    }

    @Test
    public void doesarmourwork(){
        knight.changeArmour(Armour.MAGIC);
    }
    @Test
    public void wizardTakesDamage(){
        wizard.takeDamage(250);
        assertEquals(250, wizard.getHealthBar(), 0.1);

    }

    @Test
    public void canDie(){
        dragon.attack(wizard);
        assertEquals("AHHHHhhh!", wizard.checkAlive());
    }

    @Test
    public void increaseHealthPossible(){
        priest.attack(knight);
        assertEquals(1100, knight.getHealthBar(),1);
    }

    @Test
    public void castSpellPriest(){
        double health;
        double health2;
        health = knight.getHealthBar();
        priest.castSpell(knight);
        health2 = knight.getHealthBar();
        assertNotEquals(health, health2);
    }

    @Test
    public void castSpellWizard(){
        double health;
        double health2;
        health = dragon.getHealthBar();
        wizard.castSpell(dragon);
        health2 = dragon.getHealthBar();
        assertNotEquals(health, health2);
    }

}

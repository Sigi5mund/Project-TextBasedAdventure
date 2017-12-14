package Characters;

public enum Weapon {

    SWORD(100),
    STAFF(10),
    WAND(-100),
    CLAWS(500);

    int weaponDamage;

    Weapon(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }


}

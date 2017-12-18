package Characters;

public enum OffHand {

    KNIFE(100, true, 10, false),
    SHIELD(10, true, 5, true),
    HEALWAND(-100, false, 20, false),
    DPSWAND(100, true, 50, false),
    CLAW (500, false, 100, true),
    DEFAULT(0, false, 0, false);

    int weaponDamage;
    boolean parryable;
    Integer threatIncrease;
    boolean canBlock;


    OffHand(int weaponDamage, boolean parryable, Integer threatIncrease, boolean canBlock) {
        this.weaponDamage = weaponDamage;
        this.parryable = parryable;
        this.threatIncrease = threatIncrease;
        this.canBlock = canBlock;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public boolean isParryable() {
        return parryable;
    }

    public Integer getThreatIncrease() {
        return threatIncrease;
    }

    public boolean isCanBlock() {
        return canBlock;
    }
}


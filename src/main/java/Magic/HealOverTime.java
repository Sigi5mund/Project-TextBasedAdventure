package Magic;

import Characters.Archetypes.Character;

public class HealOverTime implements ITick{

    Character target;
    Integer healingPerTick;
    Integer duration;

    public HealOverTime(Character target, Integer damagePerTick, Integer duration) {
        this.target = target;
        this.healingPerTick = damagePerTick;
        this.duration = duration;
    }

    public Character getTarget() {
        return this.target;
    }

    public Integer getHealingPerTick() {
        return this.healingPerTick;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public void tick(){
        if (duration <=0) {
            return;
        }
        else {
            target.increaseHealth(healingPerTick);
            setDuration(duration -1);
        }
    }


}

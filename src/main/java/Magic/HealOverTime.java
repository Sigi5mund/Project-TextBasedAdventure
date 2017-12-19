package Magic;

import Characters.Archetypes.Character;

public class HealOverTime implements ITick{

    Character target;
    Integer healingPerTick;
    Integer duration;

    public HealOverTime(Character target, Integer healingPerTick, Integer duration) {
        this.target = target;
        this.healingPerTick = healingPerTick;
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
            setDuration(this.duration -1);
        }
    }


}

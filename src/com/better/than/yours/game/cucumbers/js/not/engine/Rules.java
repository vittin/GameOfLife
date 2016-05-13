package com.better.than.yours.game.cucumbers.js.not.engine;

/**
 * Created by mati on 2016-05-10.
 */
public class Rules {
    public final int overPopulationLimit;
    public final int tooAloneLimit;
    public final int bornTime;
    public Rules(int aloneDie, int tooMuchDie , int born){
        this.overPopulationLimit = tooMuchDie;
        this.tooAloneLimit = aloneDie;
        this.bornTime = born;
    }
}

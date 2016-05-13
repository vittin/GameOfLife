package com.better.than.yours.game.cucumbers.js.not.models;

/**
 * Created by Pszemek on 2016-05-09.
 */
public interface Cells {
    //return Cell status;
    boolean isAlive();
    //return number of Living neighbours;
    int getLivingNeighbours();
    //kill Cell
    void kill();
    //revive Cell;
    void revive();
}


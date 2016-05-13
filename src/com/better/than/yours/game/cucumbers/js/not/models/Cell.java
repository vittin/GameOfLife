package com.better.than.yours.game.cucumbers.js.not.models;

import com.better.than.yours.game.cucumbers.js.not.exceptions.WrongPositionException;


/**
 * Created by Pszemek on 2016-05-09.
 * Refactor by Mati on 2016-05-10.
 */
public class Cell implements Cells {

    //Each Cell has own Position, id, and number of Neighbours, also is linked to BoardObserver (1 observer per Board);
    private boolean livingStatus;
    public final Position position;
    private int id;
    private int neighbors;
    private BoardObserver observer;

    //Constructor
    public Cell(Position position){

        this.livingStatus = false;

        if (!position.isValid()){

            throw new WrongPositionException("X or Y coordinate is outside the map");
        }

        this.position = position;
    }


    public void setId(int id){

        this.id = id;
    }
    public int getId(){

        return this.id;
    }

    //method which is responsible for linking Cell with Observer, always should be called;
    public void setObserver(BoardObserver observer){

        this.observer = observer;
    }

    //inform BoardObserver when Cell is born (pass true), or is dies(false);
    public void notifyObserver(boolean isReviveEvent){

        this.observer.update(id, isReviveEvent);
    }

    @Override
    public boolean isAlive() {

        return livingStatus;
    }

    //because rules of the game, the revieve / kill event must be delegated to the end of tour;
    public void setLivingStatus(boolean livingStatus){

        this.livingStatus = livingStatus;
    }

    @Override
    public void kill(){

        //this.livingStatus = false;
        notifyObserver(false);
    }

    @Override
    public void revive(){
        //this.livingStatus = true;
        notifyObserver(true);
    }

    @Override
    public int getLivingNeighbours() {

        return neighbors;
    }

    //boardObserver call this method;
    void updateNumberOfNeighbors(int neighbors){
        this.neighbors = neighbors;
        oracle();
    }

    //delete unused cells (unused => if they hasn't got any living neighbors)
    void oracle(){

        if (this.neighbors < 1){

            if (isAlive()){
                notifyObserver(false);
            }

            observer.deleteCell(id);
        }
    }

}

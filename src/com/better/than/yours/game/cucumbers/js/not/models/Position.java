package com.better.than.yours.game.cucumbers.js.not.models;

/**
 * Created by mati on 2016-05-10.
 */
public class Position {
    //Cell position is related with Board, isn't?
    private Board board;
    private int x, y;
    private boolean isNew = false;

    //Create new Cell Position in Board;
    public Position(int x, int y, Board board){
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public boolean isNew(){
        return isNew;
    }

    public void setIsNew(boolean isNew){
        this.isNew = isNew;
    }
    //We can't put Cell outside the Board;
    public boolean isValid(){

        return x >= 0 && x < board.width && y >= 0 && y < board.height;
    }
}

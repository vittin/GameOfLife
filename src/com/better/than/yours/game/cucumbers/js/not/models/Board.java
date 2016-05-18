package com.better.than.yours.game.cucumbers.js.not.models;

import com.better.than.yours.game.cucumbers.js.not.exceptions.WrongPositionException;
import com.better.than.yours.game.cucumbers.js.not.factories.CellFactory;

import java.util.HashMap;

/**
 * Created by Pszemek on 2016-05-10.
 */
public class Board {
    private final CellFactory cellFactory;
    HashMap<Integer, Cell> cellsHashMap = new HashMap<>();
    int width;
    int height;
    public Board(int width, int height){
        if (width < 1 || height < 1){
            throw new WrongPositionException("Map size must be at least 1x1!");
        }
        this.width = width;
        this.height = height;
        this.cellFactory = new CellFactory();
    }
    public void passObserver(BoardObserver observer){

        cellFactory.passObserver(observer);
    }
    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }

    public int getSize(){

        return width * height;
    }

    public Cell getCell(int id){

        return cellsHashMap.get(id);
    }

    public HashMap<Integer, Cell> getCells(){

        return cellsHashMap;
    }

    void addCell(Integer id, Cell cell) {
        cellsHashMap.put(id, cell);
    }
    public Cell createCell(Position position){
        Cell cell;
        int id = generateCellId(position);
        if (cellsHashMap.containsKey(id)){
            cell = null;
        } else{
            cell = cellFactory.makeCell(position);
            cell.setId(id);
            addCell(id, cell);
        }
        return cell;
    }

    public void removeCell(Integer id){

        cellsHashMap.remove(id);
    }

    public int generateCellId(Position position){

        return getWidth() * position.getY() + position.getX();
    }

    void setCellId(Cell cell, int id) {

        cell.setId(id);
    }
    Position getCellPosition(int CellId){
        return cellsHashMap.get(CellId).position;

    }

    public Cell getNextNeighbor(Position position, int whichNeighbor){
        int[] xCoords = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] yCoords = {-1, -1, -1, 0, 0, 1, 1, 1};
        int thatX = position.getX();
        int thatY = position.getY();
        int newX = thatX + xCoords[whichNeighbor];
        int newY = thatY + yCoords[whichNeighbor];
        Position newPosition = new Position(newX, newY, this);
        if(newPosition.isValid()){
            int id = generateCellId(newPosition);
            Cell cell = getCell(id);
            if (cell == null){
                cell = createCell(newPosition);
            }
            return cell;
        }
        else {
            return null;
        }
    }
}

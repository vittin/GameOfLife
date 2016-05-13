package com.better.than.yours.game.cucumbers.js.not.factories;

import com.better.than.yours.game.cucumbers.js.not.models.BoardObserver;
import com.better.than.yours.game.cucumbers.js.not.models.Cell;
import com.better.than.yours.game.cucumbers.js.not.models.Position;

import java.util.Observer;

public class CellFactory {

    private BoardObserver observer;
    public Cell makeCell(Position position){
        Cell cell = new Cell(position);
        cell.setObserver(this.observer);
        return cell;
    }
    public void passObserver(BoardObserver observer){

        this.observer = observer;
    }
}

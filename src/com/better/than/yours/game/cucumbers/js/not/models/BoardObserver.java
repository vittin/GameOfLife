package com.better.than.yours.game.cucumbers.js.not.models;

import com.better.than.yours.game.cucumbers.js.not.models.Board;
import com.better.than.yours.game.cucumbers.js.not.models.Cell;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pszemek on 2016-05-09.
 */
public class BoardObserver {
    HashMap<Integer,Boolean> changeStatusMap = new HashMap<>();
    Board board;
    public BoardObserver(Board board){
        this.board = board;
    }
    void update(Integer id, boolean reviveEvent){
        changeStatusMap.put(id, reviveEvent);
    }

    public void push(){
        changeStatusMap.forEach((k,v) -> {
            Cell cell = board.getCell(k);
            cell.setLivingStatus(v);
        });
    }

    void deleteCell(int id){
        board.removeCell(id);
    }

}

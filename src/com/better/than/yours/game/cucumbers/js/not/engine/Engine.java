package com.better.than.yours.game.cucumbers.js.not.engine;
import com.better.than.yours.game.cucumbers.js.not.controllers.GameViewController;
import com.better.than.yours.game.cucumbers.js.not.models.Board;
import com.better.than.yours.game.cucumbers.js.not.models.BoardObserver;
import com.better.than.yours.game.cucumbers.js.not.models.Cell;
import com.better.than.yours.game.cucumbers.js.not.models.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by mati on 2016-05-10.
 */
public class Engine {
    Board board;
    BoardObserver observer;
    Rules rules;
    Thread gameThread;
    GameViewController view;
    private int livePopulation;
    boolean isRunning = false;
    public Engine(Board board, Rules rules){
        this.board = board;
        this.rules = rules;
        this.observer = new BoardObserver(board);
        this.gameThread = new Thread(new GameThread(this), this.toString());
        System.out.println(this.toString());
        board.passObserver(observer);
    }

    public void passView(GameViewController gameViewController) {
        this.view = gameViewController;
    }

    public void startGame(int startPopulation){
        for (int i = 0; i < startPopulation; i++){
            int x = (int) (Math.random() * board.getWidth());
            int y = (int) (Math.random() * board.getHeight());
            Position position = new Position(x, y, board);
            Cell cell = board.createCell(position);
            if (cell == null){
                int id = board.generateCellId(position);
                board.removeCell(id);
                cell = board.createCell(position);
            }
            cell.revive();
        }
        isRunning = true;
        gameThread.start();
    }

    public void startGame(Position[] positions){

        for (Position position : positions) {
            Cell cell = board.createCell(position);
            if (cell == null){
                int id = board.generateCellId(position);
                board.removeCell(id);
                cell = board.createCell(position);
            }
            cell.revive();
        }
        isRunning = true;
        gameThread.start();
    }

    public void endGame() {
        System.out.println("call");
        isRunning = false;
    }

    void checkEach(){
        HashMap<Integer, Cell> cells = new HashMap<>(board.getCells());
        cells.forEach((k, v) -> oracle(v, countNeighbours(v)));
    }

    int countNeighbours(Cell cell){

        int numberOfNeighs = 0;
        for (int i = 0; i < 8; i++){
            Cell neighbor = board.getNextNeighbor(cell.position, i);
            if (neighbor != null && neighbor.isAlive()){
                numberOfNeighs += 1;
            }
        }
        cell.setLivingNeighbours(numberOfNeighs);
        return numberOfNeighs;
    }

    void oracle(Cell cell, int neighbors){
        if (cell.isAlive()){
            if (neighbors <= rules.tooAloneLimit){
                cell.kill();
            } else if (neighbors >= rules.overPopulationLimit) {
                cell.kill();
            }
            if (view != null){
                view.add(cell.position, cell.getId());
            }
        } else {
            if (neighbors == rules.bornTime){
                cell.revive();
            }
        }
    }

    int getPopulation(){
        return livePopulation;
    }
}

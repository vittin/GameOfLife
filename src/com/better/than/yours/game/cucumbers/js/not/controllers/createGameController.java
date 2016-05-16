package com.better.than.yours.game.cucumbers.js.not.controllers;

import com.better.than.yours.game.cucumbers.js.not.display.Display;
import com.better.than.yours.game.cucumbers.js.not.engine.Engine;
import com.better.than.yours.game.cucumbers.js.not.engine.Rules;
import com.better.than.yours.game.cucumbers.js.not.models.Board;
import com.better.than.yours.game.cucumbers.js.not.models.Position;

/**
 * Created by mati on 2016-05-14.
 */
public class createGameController {

    static private Rules rules;
    static private Board board;
    static private int startPopulation = 0;


    public static void startGame(Display display){
        checkVariables();
        GameViewController gameController = new GameViewController(display);
        Engine engine = new Engine(board, rules);
        engine.passView(gameController);
        engine.startGame(startPopulation);
    }

    public static void startGame(Display display, String name){
        checkVariables();
        GameViewController gameController = new GameViewController(display);

        //inicialize;
        Engine engine = new Engine(board, rules);
        engine.passView(gameController);
        Position[] positions;

        //choose one;
        if (name.equals("glider")){
            positions = new Position[5];
            positions[0] = new Position(22,21, board);
            positions[1] = new Position(22,23, board);
            positions[2] = new Position(23,22, board);
            positions[3] = new Position(23,23, board);
            positions[4] = new Position(21,23, board);

        } else if (name.equals("spaceship")){
            positions = new Position[12];
            positions[0] = new Position(5,30, board);
            positions[1] = new Position(5,31, board);
            positions[2] = new Position(6,30, board);
            positions[3] = new Position(6,31, board);
            positions[4] = new Position(6,32, board);
            positions[5] = new Position(7,31, board);
            positions[6] = new Position(7,32, board);
            positions[7] = new Position(8,31, board);
            positions[8] = new Position(8,30, board);
            positions[9] = new Position(8,29, board);
            positions[10] = new Position(7,29, board);
            positions[11] = new Position(9,30, board);

        } else if(name.equals("all")) {
            positions = new Position[30];
            //glider
            positions[0] = new Position(35,21, board);
            positions[1] = new Position(35,23, board);
            positions[2] = new Position(36,22, board);
            positions[3] = new Position(36,23, board);
            positions[4] = new Position(34,23, board);

            //squere
            positions[5] = new Position(11,11, board);
            positions[6] = new Position(11,12, board);
            positions[7] = new Position(10,11, board);
            positions[8] = new Position(10,12, board);

            //line
            positions[9] = new Position(17,15, board);
            positions[10] = new Position(18,15, board);
            positions[11] = new Position(19,15, board);

            //oscilator 2
            positions[12] = new Position(5,6, board);
            positions[13] = new Position(6,6, board);
            positions[14] = new Position(7,6, board);
            positions[15] = new Position(4,7, board);
            positions[16] = new Position(5,7, board);
            positions[17] = new Position(6,7, board);

            //light spaceship
            positions[18] = new Position(5,30, board);
            positions[19] = new Position(5,31, board);
            positions[20] = new Position(6,30, board);
            positions[21] = new Position(6,31, board);
            positions[22] = new Position(6,32, board);
            positions[23] = new Position(7,31, board);
            positions[24] = new Position(7,32, board);
            positions[25] = new Position(8,31, board);
            positions[26] = new Position(8,30, board);
            positions[27] = new Position(8,29, board);
            positions[28] = new Position(7,29, board);
            positions[29] = new Position(9,30, board);

            //final test, acorn
//            positions[30] = new Position(30,5, board);
//            positions[31] = new Position(30,7, board);
//            positions[32] = new Position(29,7, board);
//            positions[33] = new Position(32,6, board);
//            positions[34] = new Position(33,7, board);
//            positions[35] = new Position(34,7, board);
//            positions[36] = new Position(35,7, board);

        } else {
            startGame(display);
            return;
        }

        engine.startGame(positions);
    }
    static public void setRules(int tooLess, int tooMuch, int toBorn){
        rules = new Rules(tooLess, tooMuch, toBorn);
    }

    static public void setBoard(int width, int height){
        board = new Board(width, height);
    }

    static public void setStartPopulation(int population){
        startPopulation = population;
    }

    static public void setStartPopulation(double populationMultiplier){
        if (populationMultiplier > 0 && populationMultiplier <= 1){
            startPopulation = (int) (board.getSize() * populationMultiplier);
        }
    }
    static public void checkVariables(){
        if (rules == null){
            System.out.print("Set 0");
            rules = new Rules(1,4,3);
        }

        if (board == null){
            board = new Board(100, 100);
        }

        if (startPopulation < 1) {
            startPopulation = (int) (board.getSize() * 0.4);
        }
    }
}

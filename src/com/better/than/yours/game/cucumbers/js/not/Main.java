package com.better.than.yours.game.cucumbers.js.not;

import com.better.than.yours.game.cucumbers.js.not.engine.Engine;
import com.better.than.yours.game.cucumbers.js.not.engine.Rules;
import com.better.than.yours.game.cucumbers.js.not.models.Board;

public class Main {
    /*
        RULES: (a, b, c);
            a = cell is killing when it has equals or less neighbours;
            b = cell is killing when it has equals or more neighbours;
            c = cell is born when it has as many neighbours as c value;

        BOARD: (map width, map height);

        ENGINE: startGame(int startPopulation) OR ENGINE:  startGame(Positions[] arrayWithStartPositions)
            first method create Cells with random positions,
            second create Cells with given positions;
            Both start game Thread;
     */
    public static void main(String[] args) {
        Rules rules = new Rules(1, 4, 3);
        Board board = new Board(10, 10);
        Engine engine = new Engine(board, rules);
        engine.startGame(40);
    }
}

package com.better.than.yours.game.cucumbers.js.not.tests;

import com.better.than.yours.game.cucumbers.js.not.engine.Rules;
import com.better.than.yours.game.cucumbers.js.not.exceptions.CellNotFoundException;
import com.better.than.yours.game.cucumbers.js.not.factories.CellFactory;
import com.better.than.yours.game.cucumbers.js.not.engine.Engine;
import com.better.than.yours.game.cucumbers.js.not.exceptions.WrongPositionException;
import com.better.than.yours.game.cucumbers.js.not.models.Board;
import com.better.than.yours.game.cucumbers.js.not.models.Cell;
import com.better.than.yours.game.cucumbers.js.not.models.Position;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by Pszemek on 2016-05-09.
 * Developed by Mati on 2016-05-10.
 */
public class CellTest {
    CellFactory factory = new CellFactory();
    Board board = new Board(10,10);

    @org.junit.Test
    public void getPositionX()  {
        Position position = new Position(2,1, board);
        Cell cell=factory.makeCell(position);
        assertEquals(2,cell.position.getX());
    }
    @org.junit.Test
    public void getPositionY()  {
        Position position = new Position(2,1, board);
        Cell cell=factory.makeCell(position);
        assertEquals(1,cell.position.getY());
    }

    @org.junit.Test(expected = WrongPositionException.class)
    public void getWrongPosition()  {
        CellFactory factory = new CellFactory();
        Position position = new Position(-2,1, board);
        Cell cell = factory.makeCell(position);
    }
    //TODO: moreee tests!!!
}
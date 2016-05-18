package com.better.than.yours.game.cucumbers.js.not.models;

import com.better.than.yours.game.cucumbers.js.not.exceptions.WrongPositionException;
import com.better.than.yours.game.cucumbers.js.not.factories.CellFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by mati on 2016-05-10.
 */

public class BoardTest {

    //create new Board, inject dependencies;
    Board board = new Board(10, 15);
    BoardObserver observer = new BoardObserver(board);
    CellFactory cellFactory = new CellFactory();


    @Test
    public void getWidth() throws Exception {
        assertEquals(10, board.getWidth());
    }

    @Test
    public void getHeight() throws Exception {
        assertEquals(15, board.getHeight());
    }

    //map must be at least 1x1;
    @Test (expected = WrongPositionException.class)
    public void createTooSmallBoard(){
        board = new Board(2, 0);
    }

    //test: put single Cell into Board;
    @Test
    public void addCell() throws Exception {
        board.passObserver(observer);
        Position position = new Position(2,1, board);
        Cell cell = board.createCell(position);
        assertEquals(9, board.getCells().size());
        assertEquals(1, cell.position.getY());
    }

    //generateId method test;
    @Test
    public void generateCellIdTest() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(9,6, board);
        int id = board.generateCellId(position1);
        assertEquals(6*10 + 9, id);
    }

    //isAlive method test;
    @Test
    public void isAlive()  {
        board.passObserver(observer);
        Position position = new Position(2,1, board);
        Cell cell = board.createCell(position);
        observer.push();
        assertTrue(cell.isAlive());
    }
    @Test
    public void isNotAlive() {
        Position position = new Position(2,1, board);
        Cell cell = board.createCell(position);
        assertFalse(cell.isAlive());
    }


    //test setId method;
    @Test
    public void setCellId() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Cell cell1 = cellFactory.makeCell(position1);
        board.setCellId(cell1, 17);
        assertEquals(17, cell1.getId());
    }

    //getCell method test;
    @Test
    public void getCell() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Position position2 = new Position(9,6, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        assertEquals(cell1, board.getCell(cell1.getId()));
        assertEquals(cell2, board.getCell(cell2.getId()));
    }

    //test: no relations between two cells;
    @Test
    public void addTwoCellsFar() throws Exception {
        board.passObserver(observer);
        Position position = new Position(2,1, board);
        Position position2 = new Position(5,7, board);
        Cell cell2 = board.createCell(position2);
        Cell cell = board.createCell(position);
        assertEquals(18, board.getCells().size());
    }

    //getCellsMethod should return all cells in the Board;
    @Test
    public void getCells() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(3,4, board);
        Position position2 = new Position(8,6, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        assertEquals(18, board.getCells().size());
    }

    //test: are cells creating own dead neighbours only if they didn't exist yet?
    @Test
    public void addTwoCellsNear() throws Exception {
        board.passObserver(observer);
        Position position = new Position(2,1, board);
        Position position2 = new Position(2,2, board);
        Cell cell2 = board.createCell(position2);
        Cell cell = board.createCell(position);
        assertEquals(12, board.getCells().size());
    }

    //i think HashMap is solution;
    @Test
    public void createCellTestTheSamePosition() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Position position2 = new Position(2,1, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        assertEquals(9, board.getCells().size());
    }

    //create Cell out of the Board;
    @Test (expected = WrongPositionException.class)
    public void createCellBadCoordinates() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Position position2 = new Position(2,-1, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
    }

    //add two cells, kill one of them. Did cell (which is killing) remove its dead neighbors?
    @Test
    public void getCellsWithRemoval() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Position position2 = new Position(8,6, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        cell1.kill();
        observer.push();
        board.removeCell(cell1.getId());
        assertEquals(9, board.getCells().size());
    }

    //core test;
    @Test
    public void getLivingNeighbours() {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Position position2 = new Position(2,2, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        observer.push();
        assertEquals(1, cell1.getLivingNeighbours());
        assertEquals(1, cell2.getLivingNeighbours());
    }

    //create square, add and remove 1 cell next to this square;
    @Test
    public void getLivingNeighboursAfterRemoval() {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Position position2 = new Position(2,2, board);
        Position position3 = new Position(3,1, board);
        Position position4 = new Position(3,2, board);
        Position position5 = new Position(2,3, board);

        //cells which contain squere;
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        Cell cell3 = board.createCell(position3);
        Cell cell4 = board.createCell(position4);

        //neighbour for cell 2 and 4;
        Cell cell5 = board.createCell(position5);
        observer.push();

        //now 2 and 4 should have 4 neighbours;
        assertEquals(4, cell2.getLivingNeighbours());
        assertEquals(4, cell4.getLivingNeighbours());

        //but after kill again 3;
        cell5.kill();
        observer.push();
        assertEquals(3, cell2.getLivingNeighbours());
        assertEquals(3, cell4.getLivingNeighbours());
    }

    @Test //it should be in engine tests.
    public void removeCell() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Cell cell1 = board.createCell(position1);
        cell1.kill();
        observer.push();
        //cell1.oracle();
        assertEquals(0, board.getCells().size());
    }
    //BAD PLACE - CANNOT FINISH WITHOUT ENGINE
//    //test: is cell killing when it is alone?;
//    @Test
//    public void lonelyDeath() throws Exception {
//        board.passObserver(observer);
//        Position position1 = new Position(3,2, board);
//        Position position2 = new Position(2,2, board);
//        Cell cell1 = board.createCell(position1, true);
//        Cell cell2 = board.createCell(position2, true);
//        cell1.kill();
//        observer.push();
//        assertEquals(0, board.getCells().size());
//    }

    //position assignment test;
    @Test
    public void getCellPosition() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(2,1, board);
        Cell cell1 = board.createCell(position1);
        assertEquals(cell1.position, position1);
    }

    //get last neighbor;
    @Test
    public void getNextNeighborRightDown() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(3,4, board);
        Position position2 = new Position(4,5, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        Cell cell3 = board.getNextNeighbor(position1, 7);
        assertEquals(cell2, cell3);
    }

    //get first neighbor;
    @Test
    public void getNextNeighborLeftUp() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(3,4, board);
        Position position2 = new Position(2,3, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        Cell cell3 = board.getNextNeighbor(position1, 0);
        assertEquals(cell2, cell3);
    }

    //get dead neighbor;
    @Test
    public void getNextNeighborIsDead() throws Exception {
        board.passObserver(observer);
        Position position1 = new Position(3,4, board);
        Position position2 = new Position(3,3, board);
        Cell cell1 = board.createCell(position1);
        Cell cell2 = board.createCell(position2);
        Cell cell3 = board.getNextNeighbor(position1, 0);
        assertNotEquals(cell2, cell3);
    }

}
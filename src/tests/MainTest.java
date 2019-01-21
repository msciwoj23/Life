package tests;

import genericPackage.Logic;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    private Logic logic = new Logic();
    
    @Before
    public void initObjects() {
        logic.getFirstGneration();
    }

    @Test
    public void incrementOrNot() {

        int livingNeighboursCount = 0;

        assertEquals(0, logic.incrementOrNot(1,1,0,0,
                livingNeighboursCount));
        assertEquals(0, logic.incrementOrNot(1,1,1,1,
                livingNeighboursCount));
        assertEquals(0, logic.incrementOrNot(1,1,1,2,
                livingNeighboursCount));
        assertEquals(0, logic.incrementOrNot(1,1,2,2,
                livingNeighboursCount));

        assertEquals(1, logic.incrementOrNot(1,1,0,1,
                livingNeighboursCount));
        assertEquals(1, logic.incrementOrNot(1,1,2,1,
                livingNeighboursCount));
    }

    @Test
    public void getLivingNeighbours() {

        assertEquals(2, logic.getLivingNeighbours(1, 1));
        assertEquals(3, logic.getLivingNeighbours(1, 2));
        assertEquals(1, logic.getLivingNeighbours(3, 2));
        assertEquals(0, logic.getLivingNeighbours(4, 0));
    }

    @Test
    public void decideLife() {

        assertFalse(logic.decideLife(true,0));
        assertFalse(logic.decideLife(true,1));
        assertTrue(logic.decideLife(true, 2));
        assertTrue(logic.decideLife(true, 3));
        assertFalse(logic.decideLife(true,4));

        assertFalse(logic.decideLife(false,0));
        assertFalse(logic.decideLife(false,1));
        assertFalse(logic.decideLife(false,2));
        assertTrue(logic.decideLife(false, 3));
        assertFalse(logic.decideLife(false,4));
    }

    @Test
    public void getNextGeneration() {

        boolean[][] board = logic.getNextGeneration();

        assertFalse(board[0][0]);
        assertFalse(board[0][1]);
        assertFalse(board[0][2]);

        assertTrue(board[1][0]);
        assertTrue(board[1][1]);
        assertTrue(board[1][2]);
    }
}

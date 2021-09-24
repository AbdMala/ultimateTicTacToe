package uttt.tests;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

import static org.junit.Assert.*;

public class testSimulator {


    BoardInterface first_board;
    BoardInterface second_board;
    BoardInterface third_board;
    BoardInterface zero_board;

    SimulatorInterface simulator;
    SimulatorInterface simulator_win;
    SimulatorInterface simulator_empty;
    SimulatorInterface simulator_tie;
    SimulatorInterface simulator_co;


    @Before
    public void setUp() throws Exception {
        simulator = UTTTFactory.createSimulator();

        first_board = UTTTFactory.createBoard();
        MarkInterface markone = UTTTFactory.createMark(Symbol.CROSS, 0); //  X win
        MarkInterface marktwo = UTTTFactory.createMark(Symbol.EMPTY, 1);
        MarkInterface markthree = UTTTFactory.createMark(Symbol.CIRCLE, 2);
        MarkInterface markfour = UTTTFactory.createMark(Symbol.EMPTY, 3);
        MarkInterface markfive = UTTTFactory.createMark(Symbol.CROSS, 4);
        MarkInterface marksix = UTTTFactory.createMark(Symbol.CIRCLE, 5);
        MarkInterface markseven = UTTTFactory.createMark(Symbol.EMPTY, 6);
        MarkInterface markeight = UTTTFactory.createMark(Symbol.EMPTY, 7);
        MarkInterface marknine = UTTTFactory.createMark(Symbol.CROSS, 8);
        first_board.setMarks(new MarkInterface[]{markone, marktwo, markthree, markfour, markfive,
                marksix, markseven, markeight, marknine});

        second_board = UTTTFactory.createBoard();
        MarkInterface markonet = UTTTFactory.createMark(Symbol.CIRCLE, 0); // tie
        MarkInterface marktwot = UTTTFactory.createMark(Symbol.CROSS, 1);
        MarkInterface markthreet = UTTTFactory.createMark(Symbol.CIRCLE, 2);
        MarkInterface markfourt = UTTTFactory.createMark(Symbol.CROSS, 3);
        MarkInterface markfivet = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        MarkInterface marksixt = UTTTFactory.createMark(Symbol.CIRCLE, 5);
        MarkInterface marksevent = UTTTFactory.createMark(Symbol.CROSS, 6);
        MarkInterface markeightt = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        MarkInterface markninet = UTTTFactory.createMark(Symbol.CROSS, 8);
        second_board.setMarks(new MarkInterface[]{markonet, marktwot, markthreet, markfourt, markfivet,
                marksixt, marksevent, markeightt, markninet});

        third_board = UTTTFactory.createBoard();
        MarkInterface markonen = UTTTFactory.createMark(Symbol.CIRCLE, 0); // no win no tie
        MarkInterface marktwon = UTTTFactory.createMark(Symbol.CROSS, 1);
        MarkInterface markthreen = UTTTFactory.createMark(Symbol.CROSS, 2);
        MarkInterface markfourn = UTTTFactory.createMark(Symbol.EMPTY, 3);
        MarkInterface markfiven = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        MarkInterface marksixn = UTTTFactory.createMark(Symbol.EMPTY, 5);
        MarkInterface marksevenn = UTTTFactory.createMark(Symbol.CROSS, 6);
        MarkInterface markeightn = UTTTFactory.createMark(Symbol.CROSS, 7);
        MarkInterface markninen = UTTTFactory.createMark(Symbol.EMPTY, 8);
        third_board.setMarks(new MarkInterface[]{markonen, marktwon, markthreen, markfourn, markfiven,
                marksixn, marksevenn, markeightn, markninen});

        zero_board = UTTTFactory.createBoard();
        MarkInterface emp1 = UTTTFactory.createMark(Symbol.EMPTY, 0); // empty
        MarkInterface emp2 = UTTTFactory.createMark(Symbol.EMPTY, 1);
        MarkInterface emp3 = UTTTFactory.createMark(Symbol.EMPTY, 2);
        MarkInterface emp4 = UTTTFactory.createMark(Symbol.EMPTY, 3);
        MarkInterface emp5 = UTTTFactory.createMark(Symbol.EMPTY, 4);
        MarkInterface emp6 = UTTTFactory.createMark(Symbol.EMPTY, 5);
        MarkInterface emp7 = UTTTFactory.createMark(Symbol.EMPTY, 6);
        MarkInterface emp8 = UTTTFactory.createMark(Symbol.EMPTY, 7);
        MarkInterface emp9 = UTTTFactory.createMark(Symbol.EMPTY, 8);
        zero_board.setMarks(new MarkInterface[]{emp1, emp2, emp3, emp4, emp5,
                emp6, emp7, emp8, emp9});


        simulator_win = UTTTFactory.createSimulator();
        simulator_win.setBoards(new BoardInterface[]{first_board, second_board, second_board, third_board, first_board,
                third_board, second_board, third_board, first_board});

        simulator_empty = UTTTFactory.createSimulator();
        simulator_empty.setBoards(new BoardInterface[]{zero_board, zero_board, zero_board, zero_board, zero_board,
                zero_board, zero_board, zero_board, zero_board});

        simulator_tie = UTTTFactory.createSimulator();
        simulator_tie.setBoards(new BoardInterface[]{second_board, second_board, second_board, second_board, second_board,
                second_board, second_board, second_board, second_board});

        simulator_co = UTTTFactory.createSimulator();
        simulator_co.setBoards(new BoardInterface[]{zero_board, second_board, zero_board, second_board, zero_board,
                second_board, zero_board, second_board, zero_board});

    }

    @Test
    public void simpleSetPieceTest() {
        assertNotNull(simulator);
    }

    @Test
    public void testGetBoards() {
        assertNotNull(simulator_win.getBoards());
    }

    @Test
    public void testGetBoards2() {
        for (int i = 0; i < 9; i++)
            assertEquals((simulator_win.getBoards())[0].getMarks()[i].getSymbol(),
                    first_board.getMarks()[i].getSymbol());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBoards() {
        simulator_win.setBoards(null);
    }

    @Test
    public void testGetCurrentPlayerSymbol() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator_empty.getCurrentPlayerSymbol() == Symbol.CROSS);
        assertFalse(simulator_empty.getCurrentPlayerSymbol() == Symbol.EMPTY);
        simulator_empty.setMarkAt(Symbol.CROSS, 2, 4);
        assertTrue(simulator_empty.getCurrentPlayerSymbol() == Symbol.CROSS);  //------was cr
        assertFalse(simulator_empty.getCurrentPlayerSymbol() == Symbol.EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCurrentPlayerSymbol() {
        simulator_empty.setCurrentPlayerSymbol(null);
    }

    @Test
    public void testSetCurrentPlayerSymbol2() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        assertEquals(simulator_empty.getCurrentPlayerSymbol(), Symbol.CROSS);
        simulator_empty.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertEquals(simulator_empty.getCurrentPlayerSymbol(), Symbol.CIRCLE);
    }

    @Test
    public void testSetMarkAt() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator_empty.setMarkAt(Symbol.CROSS, 1, 1));
        assertTrue(simulator_empty.getBoards()[1].getMarks()[1].getSymbol() == Symbol.CROSS);

        simulator_empty.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(simulator_empty.setMarkAt(Symbol.CIRCLE, 1, 2));
        assertTrue(simulator_empty.getBoards()[1].getMarks()[2].getSymbol() == Symbol.CIRCLE);
    }

    @Test
    public void testSetMarkAt2() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator_empty.setMarkAt(Symbol.CROSS, 1, 1));
        assertTrue(simulator_empty.getBoards()[1].getMarks()[1].getSymbol() == Symbol.CROSS);

        simulator_empty.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertFalse(simulator_empty.setMarkAt(Symbol.CIRCLE, 1, 1));
        assertTrue(simulator_empty.getBoards()[1].getMarks()[1].getSymbol() == Symbol.CROSS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMarkAt3() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator_empty.setMarkAt(Symbol.CROSS, 100, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMarkAt4() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator_empty.setMarkAt(Symbol.CROSS, 1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMarkAt5() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator_empty.setMarkAt(null, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMarkAt6() {
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator_empty.setMarkAt(Symbol.CROSS, -1, -1);
    }

    @Test
    public void testGetIndexNextBoard() {
        assertEquals(simulator_empty.getIndexNextBoard(), -1);
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator_empty.setMarkAt(Symbol.CROSS, 2, 4));

        assertEquals(simulator_empty.getIndexNextBoard(), 4);
    }

    @Test
    public void testGetIndexNextBoard2() {
        simulator_empty.setIndexNextBoard(3);
        assertEquals(simulator_empty.getIndexNextBoard(), 3);
    }

    @Test
    public void testSetIndexNextBoard() {
        simulator_empty.setIndexNextBoard(-1);
        assertEquals(simulator_empty.getIndexNextBoard(), -1);
        simulator_empty.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator_empty.setMarkAt(Symbol.CROSS, 2, 4));
        //simulator_empty.setIndexNextBoard(4);
        assertEquals(simulator_empty.getIndexNextBoard(), 4);
    }

    @Test
    public void testSetIndexNextBoard2() {
        SimulatorInterface x = UTTTFactory.createSimulator();
        x.setBoards(new BoardInterface[]{second_board, zero_board,zero_board, zero_board,first_board, zero_board,
                first_board, zero_board,zero_board});
        x.setCurrentPlayerSymbol(Symbol.CROSS);
        x.setIndexNextBoard(-1);
        assertTrue(x.setMarkAt(Symbol.CROSS, 2, 0));
        assertEquals(x.getIndexNextBoard(), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIndexNextBoard3() {
        simulator_empty.setIndexNextBoard(-56);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIndexNextBoard4() {
        simulator_empty.setIndexNextBoard(58);
    }

    @Test
    public void testIsGameOver1() {
        assertTrue(simulator_win.isGameOver());
        assertTrue(simulator_tie.isGameOver());
    }

    @Test
    public void testIsGameOver2() {
        assertFalse(simulator_co.isGameOver());
        assertFalse(simulator_empty.isGameOver());
    }

    @Test
    public void testIsMovePossibleSi() {
        simulator_empty.setIndexNextBoard(2);
        assertTrue(simulator_empty.isMovePossible(2));
        simulator_tie.setIndexNextBoard(3);
        assertFalse(simulator_tie.isMovePossible(3));
        SimulatorInterface x = UTTTFactory.createSimulator();
        x.setBoards(new BoardInterface[]{first_board, zero_board,zero_board, zero_board,first_board, zero_board,
                first_board, zero_board,zero_board});
        x.setIndexNextBoard(0);
        assertFalse(x.isMovePossible(0));
        x.setIndexNextBoard(1);
        assertTrue(x.isMovePossible(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsMovePossibleSi2() {
        simulator_co.isMovePossible(34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsMovePossibleSi3() {
        simulator_co.isMovePossible(-2);
    }
    @Test
    public void testIsMovePossibleSiBo(){
        simulator_empty.setIndexNextBoard(1);
        assertTrue(simulator_empty.isMovePossible(1,4));
        simulator_tie.setIndexNextBoard(3);
        assertFalse(simulator_tie.isMovePossible(3,4));
        SimulatorInterface x = UTTTFactory.createSimulator();
        x.setBoards(new BoardInterface[]{first_board, zero_board,zero_board, zero_board,first_board, zero_board,
                first_board, zero_board,zero_board});
        x.setIndexNextBoard(0);
        assertFalse(x.isMovePossible(0,3));
        x.setIndexNextBoard(1);
        assertTrue(x.isMovePossible(1,3));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsMovePossibleSiBo2() {
        simulator_empty.isMovePossible(124,4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsMovePossibleSiBo3() {
        simulator_empty.isMovePossible(2,44);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsMovePossibleSiBo4() {
        simulator_empty.isMovePossible(-24,4);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsMovePossibleSiBo5() {
        simulator_empty.isMovePossible(2,-45);
    }
    @Test
    public void testGetWinner(){
        assertTrue(simulator_win.getWinner() == Symbol.CROSS);
        assertTrue(simulator_tie.getWinner() == Symbol.EMPTY);
        assertTrue(simulator_co.getWinner() == Symbol.EMPTY);
        assertFalse(simulator_tie.getWinner() == Symbol.CIRCLE);
    }
}

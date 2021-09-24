package uttt.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.SimulatorInterface;
import uttt.game.MarkInterface;
import uttt.game.BoardInterface;
import uttt.utils.Symbol;

public class SimpleTest {



	BoardInterface first_board;
	BoardInterface second_board;
	BoardInterface third_board;


	MarkInterface mark_x ;
	MarkInterface empty;
	MarkInterface mark_o ;
	MarkInterface mark_x2 ;
	MarkInterface mark_x3 ;

	@Before
	public void setUp() throws Exception {

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
		first_board.setMarks(new MarkInterface[]{markone,marktwo,markthree,markfour,markfive,
				marksix,markseven,markeight,marknine});

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
		second_board.setMarks(new MarkInterface[]{markonet,marktwot,markthreet,markfourt,markfivet,
				marksixt,marksevent,markeightt,markninet});

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
		third_board.setMarks(new MarkInterface[]{markonen,marktwon,markthreen,markfourn,markfiven,
				marksixn,marksevenn,markeightn,markninen});


	}


	@Test
	public void testSymbolX(){
		mark_x = UTTTFactory.createMark(Symbol.CROSS, 0);
		empty = UTTTFactory.createMark(Symbol.EMPTY, 1);
		mark_o = UTTTFactory.createMark(Symbol.CIRCLE, 2);
		assertNotNull(mark_x);
		assertTrue(mark_x.getSymbol() == Symbol.CROSS);

		assertTrue(empty.getSymbol() == Symbol.EMPTY);

		assertNotNull(mark_o);
		assertTrue(mark_o.getSymbol() == Symbol.CIRCLE);
	}

	@Test
	public void testMarkPosition(){
		mark_x = UTTTFactory.createMark(Symbol.CROSS, 0);

		assertEquals(0, mark_x.getPosition());
	}

	@Test(expected =IllegalArgumentException.class)
	public void testMarkPosition_minus(){
		mark_x2 = UTTTFactory.createMark(Symbol.CROSS, -50);
		mark_x2.getPosition() ;
	}
	@Test(expected =IllegalArgumentException.class)
	public void testMarkPosition_minus2(){
		mark_x3 = UTTTFactory.createMark(Symbol.CROSS, 50);
		mark_x3.getPosition() ;
	}

	@Test
	public void testSetSymbol(){
		empty = UTTTFactory.createMark(Symbol.EMPTY, 1);
		empty.setSymbol(Symbol.CROSS);
		assertTrue(empty.getSymbol() == Symbol.CROSS);
	}

	@Test(expected =IllegalArgumentException.class)
	public void testSetSymbolX(){
		mark_x = UTTTFactory.createMark(Symbol.CROSS, 0);
		mark_x.setSymbol(null);
	}
	@Test(expected =IllegalArgumentException.class)
	public void testSetSymbolO(){
		mark_o = UTTTFactory.createMark(Symbol.CIRCLE, 2);
		mark_o.setSymbol(null);
	}
	@Test(expected =IllegalArgumentException.class)
	public void testSetSymbolE(){
		empty = UTTTFactory.createMark(Symbol.EMPTY, 1);
		empty.setSymbol(null);
	}

//////////////////////////BOARD////////////////////////////////////
	@Test
	public void testGetMarks() { assertNotNull(first_board.getMarks()); }
	@Test
	public void testGetMarks2() {
		MarkInterface array[] = first_board.getMarks() ;
		assertTrue(array[0].getSymbol() == Symbol.CROSS) ;
		assertTrue(array[1].getSymbol() == Symbol.EMPTY) ;
		assertTrue(array[2].getSymbol() == Symbol.CIRCLE) ;
		assertEquals(array.length,9);
	}

	@Test
	public void testSetMarkAt1() {
		assertTrue(third_board.setMarkAt(Symbol.CROSS,8));
	}

	@Test
	public void testSetMarkAt2() {
		assertFalse(third_board.setMarkAt(Symbol.CROSS,0));
	}

	@Test(expected =IllegalArgumentException.class)
	public void testSetMarkAt3(){
		first_board.setMarkAt(Symbol.CROSS, 100); }

	@Test
	public void testIsClosed(){
		assertTrue(first_board.isClosed());
		assertTrue(second_board.isClosed());
	}
	@Test
	public void testIsClosed2(){
		assertFalse(third_board.isClosed()); }
	@Test
	public void testIsMovePossible(){
		assertTrue(third_board.isMovePossible(3));
	}
	@Test
	public void testIsMovePossible2(){
		assertFalse(third_board.isMovePossible(1));
		assertFalse(second_board.isMovePossible(2));
	}
	@Test(expected =IllegalArgumentException.class)
	public void testIsMovePossible3(){
		third_board.isMovePossible(100);
	}
	@Test(expected =IllegalArgumentException.class)
	public void testIsMovePossible4(){
		third_board.isMovePossible(-50);
	}

	@Test
	public void testGetWinner() {
		assertTrue(first_board.getWinner() == Symbol.CROSS);

		assertTrue(second_board.getWinner() == Symbol.EMPTY);
	}
//////////////////////////////////////////////////////////////////////////


}

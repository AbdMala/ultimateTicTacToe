package uttt.tests;

import org.junit.Before;
import org.junit.Test;
import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

import static org.junit.Assert.*;

public class abudi {



	BoardInterface first_board;

	@Before
	public void setUp() throws Exception {
		first_board = UTTTFactory.createBoard();
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_bord1() {

		MarkInterface markone = UTTTFactory.createMark(Symbol.CROSS, 0); //  X win
		MarkInterface marktwo = UTTTFactory.createMark(Symbol.EMPTY, 1);
		MarkInterface markthree = UTTTFactory.createMark(Symbol.CIRCLE, 2);
		MarkInterface markfour = UTTTFactory.createMark(Symbol.EMPTY, 3);
		MarkInterface markfive = UTTTFactory.createMark(Symbol.CROSS, 4);
		MarkInterface marksix = UTTTFactory.createMark(Symbol.CIRCLE, 5);
		MarkInterface markseven = UTTTFactory.createMark(Symbol.EMPTY, 6);
		first_board.setMarks(new MarkInterface[]{markone, marktwo , markthree, markfour , markfive , marksix ,markseven});
		first_board.getWinner();
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_bord12() {

		MarkInterface markone = UTTTFactory.createMark(Symbol.CROSS, 0); //  X win
		MarkInterface marktwo = UTTTFactory.createMark(Symbol.EMPTY, 1);
		MarkInterface markthree = UTTTFactory.createMark(Symbol.CIRCLE, 2);
		MarkInterface markfour = UTTTFactory.createMark(Symbol.EMPTY, 3);
		MarkInterface markfive = UTTTFactory.createMark(Symbol.CROSS, 4);
		MarkInterface marksix = UTTTFactory.createMark(Symbol.CIRCLE, 5);
		MarkInterface markseven = UTTTFactory.createMark(Symbol.EMPTY, 6);
		first_board.setMarks(new MarkInterface[]{markone, marktwo , markthree, markfour , markfive , marksix ,markseven});

	}

	@Test
	public void test_bord2_important() {

		MarkInterface markone = UTTTFactory.createMark(Symbol.EMPTY, 0); //  X win
		MarkInterface m2= UTTTFactory.createMark(Symbol.CROSS, 0); //  X win

		first_board.setMarks(new MarkInterface[]{

				m2,markone,markone,
				markone,m2,markone,
				markone,markone,m2
		});
		assertEquals(Symbol.CROSS , first_board.getWinner());
	}
	@Test
	public void test_sort_marks() {

		MarkInterface markone = UTTTFactory.createMark(Symbol.CROSS, 0); //  X win
		MarkInterface marktwo = UTTTFactory.createMark(Symbol.CROSS, 1);
		MarkInterface markthree = UTTTFactory.createMark(Symbol.CROSS, 2);
		MarkInterface markfour = UTTTFactory.createMark(Symbol.EMPTY, 3);
		MarkInterface markfive = UTTTFactory.createMark(Symbol.EMPTY, 4);
		MarkInterface marksix = UTTTFactory.createMark(Symbol.EMPTY, 5);
		MarkInterface markseven = UTTTFactory.createMark(Symbol.EMPTY, 6);
		MarkInterface markeight = UTTTFactory.createMark(Symbol.EMPTY, 7);
		MarkInterface marknine = UTTTFactory.createMark(Symbol.EMPTY, 8);
		first_board.setMarks(new MarkInterface[]{markfour,markfive,markone,markeight,markthree,marksix,markseven,marktwo,marknine});
		assertTrue(first_board.setMarkAt(Symbol.CROSS,3));
		assertEquals(Symbol.CROSS ,first_board.getMarks()[3].getSymbol());
		assertEquals(Symbol.EMPTY , first_board.getMarks()[0].getSymbol());
	}

	@Test
	public void test_same_symbol() {

		MarkInterface markone = UTTTFactory.createMark(Symbol.EMPTY, 0); //  X win
		MarkInterface marktwo = UTTTFactory.createMark(Symbol.CROSS, 0);
		MarkInterface markthree = UTTTFactory.createMark(Symbol.CROSS, 2);
		MarkInterface markfour = UTTTFactory.createMark(Symbol.EMPTY, 3);
		MarkInterface markfive = UTTTFactory.createMark(Symbol.EMPTY, 4);
		MarkInterface marksix = UTTTFactory.createMark(Symbol.EMPTY, 5);
		MarkInterface markseven = UTTTFactory.createMark(Symbol.EMPTY, 6);
		MarkInterface markeight = UTTTFactory.createMark(Symbol.EMPTY, 7);
		MarkInterface marknine = UTTTFactory.createMark(Symbol.EMPTY, 8);
		first_board.setMarks(new MarkInterface[]{markfive,markone,markfour,markeight,markthree,marksix,markseven,marktwo,marknine});
		assertTrue(first_board.setMarkAt(Symbol.CIRCLE, 1));
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_sort_marks2() {

		MarkInterface markone = UTTTFactory.createMark(Symbol.CROSS, 0); //  X win
		MarkInterface marktwo = UTTTFactory.createMark(Symbol.EMPTY, 1);
		MarkInterface markt2_2 = UTTTFactory.createMark(Symbol.CIRCLE, 1);
		MarkInterface markthree = UTTTFactory.createMark(Symbol.CROSS, 2);
		MarkInterface markfour = UTTTFactory.createMark(Symbol.EMPTY, 3);
		MarkInterface markfive = UTTTFactory.createMark(Symbol.EMPTY, 4);
		MarkInterface marksix = UTTTFactory.createMark(Symbol.EMPTY, 5);
		MarkInterface markseven = UTTTFactory.createMark(Symbol.EMPTY, 6);
		MarkInterface markeight = UTTTFactory.createMark(Symbol.EMPTY, 7);
		MarkInterface marknine = UTTTFactory.createMark(Symbol.EMPTY, 8);
		first_board.setMarks(new MarkInterface[]{markt2_2,markfive,markone,markfour,markeight,markthree,marksix,markseven,marktwo,marknine});
	}
	@Test
	public void test_set_symbol() {

		MarkInterface marktwo = UTTTFactory.createMark(Symbol.EMPTY, 1);
		marktwo.setSymbol(Symbol.CROSS);
		assertEquals(Symbol.CROSS , marktwo.getSymbol());
		marktwo.setSymbol(Symbol.CIRCLE);
		assertEquals(Symbol.CIRCLE , marktwo.getSymbol());
	}
}

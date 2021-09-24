package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Symbol;

public class BoardInterfaceImpl implements BoardInterface {
    MarkInterface[] boardArray ;
    public BoardInterfaceImpl() {

        boardArray = new  MarkInterface[9] ;
        for (int i = 0 ; i < 9 ; ++i){
            boardArray[i] = UTTTFactory.createMark(Symbol.EMPTY,i) ;
        }

    }

    @Override
    public MarkInterface[] getMarks() {
        return boardArray;
    }

    @Override
    public void setMarks(MarkInterface[] marks) throws IllegalArgumentException {
        if (marks == null) throw new IllegalArgumentException();
        if( marks.length != 9) throw new IllegalArgumentException();
        this.boardArray = marks;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int markIndex) throws IllegalArgumentException {
        if (markIndex < 0 || markIndex > 8 || symbol == null) throw new IllegalArgumentException();
        if (boardArray[markIndex].getSymbol() == Symbol.EMPTY) {
            this.boardArray[markIndex].setSymbol(symbol);
            return true;
        } else return false;
    }

    @Override
    public boolean isClosed() {
        if (this.getWinner() != Symbol.EMPTY) return true;
        for (int i = 0; i < 9; i++)
            if (boardArray[i].getSymbol() == Symbol.EMPTY) return false;


        return true;
    }

    @Override
    public boolean isMovePossible(int markIndex) throws IllegalArgumentException {
        if (markIndex < 0 || markIndex > 8) throw new IllegalArgumentException();

        if (boardArray[markIndex].getSymbol() == Symbol.EMPTY && !isClosed()) return true;
        else return false;
    }

    @Override
    public Symbol getWinner() {
        for (int i = 0; i <= 6; i = i + 3) {
            if (boardArray[i].getSymbol() == boardArray[i + 1].getSymbol() && boardArray[i + 1].getSymbol() == boardArray[i + 2].getSymbol()
                    && boardArray[i].getSymbol() != Symbol.EMPTY) return boardArray[i].getSymbol();
        }

        for (int i = 0; i <= 2; ++i) {
            if (boardArray[i].getSymbol() == boardArray[i + 3].getSymbol() && boardArray[i + 3].getSymbol() == boardArray[i + 6].getSymbol()
                    && boardArray[i].getSymbol() != Symbol.EMPTY) return boardArray[i].getSymbol();
        }

        if (boardArray[0].getSymbol() == boardArray[4].getSymbol() && boardArray[4].getSymbol() == boardArray[8].getSymbol()
                && boardArray[0].getSymbol() != Symbol.EMPTY) return boardArray[0].getSymbol();

        if (boardArray[2].getSymbol() == boardArray[4].getSymbol() && boardArray[4].getSymbol() == boardArray[6].getSymbol()
                && boardArray[2].getSymbol() != Symbol.EMPTY) return boardArray[2].getSymbol();


        return Symbol.EMPTY;
    }
}

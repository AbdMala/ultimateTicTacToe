package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Move;
import uttt.utils.Symbol;

public class SimulatorInterfaceImpl implements SimulatorInterface {
    BoardInterface[] simulatorArray;
    Symbol curSymbol = null;
    int nextBoard;

    public SimulatorInterfaceImpl() {
        nextBoard = -1;
        simulatorArray = new BoardInterface[9];
        for (int i = 0; i < 9; ++i) {
            simulatorArray[i] = UTTTFactory.createBoard();
        }
    }

    @Override
    public BoardInterface[] getBoards() {
        return simulatorArray;
    }

    @Override
    public void setBoards(BoardInterface[] boards) throws IllegalArgumentException {
        if (boards == null) throw new IllegalArgumentException();
        if (boards.length != 9) throw new IllegalArgumentException();
        this.simulatorArray = boards;
    }

    @Override
    public Symbol getCurrentPlayerSymbol() {
        if (this.curSymbol == null) {
            curSymbol = Symbol.CROSS;
        }
        return curSymbol;
    }

    @Override
    public void setCurrentPlayerSymbol(Symbol symbol) throws IllegalArgumentException {
        if (symbol == null) throw new IllegalArgumentException();
        curSymbol = symbol;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex > 8) throw new IllegalArgumentException();
        if (markIndex < 0 || markIndex > 8) throw new IllegalArgumentException();
        if (symbol == null) throw new IllegalArgumentException();
        if (symbol != getCurrentPlayerSymbol()) throw new IllegalArgumentException();
        if (isMovePossible(boardIndex, markIndex)) {
            simulatorArray[boardIndex].setMarkAt(symbol, markIndex);
            setIndexNextBoard(markIndex);
            return true;
        } else return false;
    }

    @Override
    public int getIndexNextBoard() {
        return nextBoard;
    }

    @Override
    public void setIndexNextBoard(int index) throws IllegalArgumentException {
        if (index < -1 || index > 8) throw new IllegalArgumentException();
        if (index == -1) nextBoard = index;
        else {
            if (simulatorArray[index].isClosed()) nextBoard = -1;
            else nextBoard = index;
        }
    }

    @Override
    public boolean isGameOver() {
        if (getWinner() != Symbol.EMPTY) return true;
        for (int i = 0; i < 9; i++) {
            if (!simulatorArray[i].isClosed()) return false;
        }
        return true;
    }

    @Override
    public boolean isMovePossible(int boardIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex > 8) throw new IllegalArgumentException();
        if (getIndexNextBoard() == -1)
            return !simulatorArray[boardIndex].isClosed() && !isGameOver();
        else
            return (!simulatorArray[boardIndex].isClosed() && boardIndex == getIndexNextBoard())&& !isGameOver();


    }

    @Override
    public boolean isMovePossible(int boardIndex, int markIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex > 8) throw new IllegalArgumentException();
        if (markIndex < 0 || markIndex > 8) throw new IllegalArgumentException();

        if (getIndexNextBoard() == -1)
            return simulatorArray[boardIndex].isMovePossible(markIndex) && !isGameOver();
        else
            return (simulatorArray[boardIndex].isMovePossible(markIndex) && isMovePossible(boardIndex) && !isGameOver());

    }

    @Override
    public Symbol getWinner() {
        BoardInterface temp = UTTTFactory.createBoard();
        MarkInterface[] marks_temp = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            marks_temp[i] = UTTTFactory.createMark(simulatorArray[i].getWinner(), i);
        }
        temp.setMarks(marks_temp);
        return temp.getWinner();
    }

    @Override
    public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui) throws IllegalArgumentException {
        if (ui == null) throw new IllegalArgumentException();
        if (playerOne.getSymbol() == Symbol.EMPTY) throw new IllegalArgumentException();
        if (playerTwo.getSymbol() == Symbol.EMPTY) throw new IllegalArgumentException();

        while (!isGameOver()) {

            if (playerOne.getSymbol() == getCurrentPlayerSymbol()) {
                Move temp = playerOne.getPlayerMove(this, ui);
                if (isMovePossible(temp.getBoardIndex(), temp.getMarkIndex())) {
                    setMarkAt(playerOne.getSymbol(), temp.getBoardIndex(), temp.getMarkIndex());
                }
                //  while (!isMovePossible(temp.getBoardIndex(),temp.getMarkIndex())){
                //       temp = playerOne.getPlayerMove(this, ui);
                //      setMarkAt(playerOne.getSymbol(), temp.getBoardIndex(), temp.getMarkIndex());
                // }

                setCurrentPlayerSymbol(getCurrentPlayerSymbol().flip());


            } else if (playerTwo.getSymbol() == getCurrentPlayerSymbol()) {
                Move temp = playerTwo.getPlayerMove(this, ui);

                if (isMovePossible(temp.getBoardIndex(), temp.getMarkIndex())) {
                    setMarkAt(playerTwo.getSymbol(), temp.getBoardIndex(), temp.getMarkIndex());
                }
                //  while (!isMovePossible(temp.getBoardIndex(),temp.getMarkIndex())){
                //    temp = playerTwo.getPlayerMove(this, ui);
                //   setMarkAt(playerTwo.getSymbol(), temp.getBoardIndex(), temp.getMarkIndex());
                //}

                setCurrentPlayerSymbol(getCurrentPlayerSymbol().flip());
            }
            ui.updateScreen(this);




        }
        ui.showGameOverScreen(this);
    }
}

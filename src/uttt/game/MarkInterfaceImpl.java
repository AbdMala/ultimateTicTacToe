package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Symbol;

import javax.swing.text.Utilities;

public class MarkInterfaceImpl implements MarkInterface {
    private Symbol symbol;
    private int j;

    public MarkInterfaceImpl(Symbol symbol, int j) {
        if (j < 0 || j > 8 || symbol == null) throw new IllegalArgumentException();
        this.symbol = symbol;
        this.j = j;
    }

    @Override
    public Symbol getSymbol() {
        if (this.j < 0 || this.j > 8) throw new IllegalArgumentException("out of RANGE");
        if (this.symbol == null) throw new IllegalArgumentException("out of RANGE");
        return symbol;
    }

    @Override
    public int getPosition() {
        if (this.j < 0 || this.j > 8) throw new IllegalArgumentException("out of RANGE");
        if (this.symbol == null) throw new IllegalArgumentException("out of RANGE");

        return j;
    }

    @Override
    public void setSymbol(Symbol symbol) throws IllegalArgumentException {
        if (this.j < 0 || this.j > 8) throw new IllegalArgumentException("out of RANGE");
        if (this.symbol == null || symbol == null) throw new IllegalArgumentException("out of RANGE");

        else this.symbol = symbol ;
        }

}

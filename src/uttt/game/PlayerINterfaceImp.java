package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

import java.util.Random;

public class PlayerINterfaceImp implements PlayerInterface {

    private boolean AI;
    private Symbol symbol;

    public PlayerINterfaceImp(Symbol symbol, boolean AI) {
        this.symbol = symbol;
        this.AI = AI;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public Move getPlayerMove(SimulatorInterface game, UserInterface ui) throws IllegalArgumentException {
        if (!AI) {
            return ui.getUserMove();
        }
        Random raAI = new Random();
        int r = raAI.nextInt(9);
        int a = raAI.nextInt(9);
        Move move_AI = new Move(r , a);
        return  move_AI ;
    }
}

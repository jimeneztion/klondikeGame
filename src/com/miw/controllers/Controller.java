package com.miw.controllers;

import com.miew.util.Stack;
import com.miw.models.Board;
import com.miw.models.Card;
import com.miw.models.Pile;
import com.miw.models.StairPile;
import com.miw.models.TYPE_SUIT;
import com.miw.views.VIEW_OPTION;
import com.miw.views.VIEW_SUBOPTION;
import com.miw.views.View;

public class Controller {

    private Board board;

    private View view;

    public Controller(Board board, View view) {
        this.board = board;
        this.view = view;
    }

    public void init() {
        view.setController(this);
        board.init();
    }

    public void play() {
        boolean ok = true;
        while (!board.finish()) {
            view.printStatus();
            ok = move(view.leerOption());
            if (!ok)
                view.lanzarError();

        }
    }

    public boolean move(VIEW_OPTION option) {

        Pile origenPile = null;
        Pile goalPile = null;
        Pile turnPile = null;
        boolean onlyCard = true;

        switch (option) {
        case BARAJA_DESCARTE:
            origenPile = board.getDeck();
            goalPile = board.getDiscard();
            break;
        case DESCARTE_BARAJA:
            origenPile = board.getDiscard();
            goalPile = board.getDeck();
            break;

        case DESCARTE_PALO:
            origenPile = board.getDiscard();
            goalPile = board.getSuit(view.leerSubOption(VIEW_SUBOPTION.A_QUE_PALO, TYPE_SUIT.NUM_CARDS_BY_SUIT) - 1);
            break;

        case DESCARTE_ESCALERA:
            origenPile = board.getDiscard();
            goalPile = board.getStair(view.leerSubOption(VIEW_SUBOPTION.A_ESCALERA, Board.NUM_STAIRS) - 1);
            break;

        case ESCALERA_ESCALERA:
            origenPile = board.getStair(view.leerSubOption(VIEW_SUBOPTION.DE_ESCALERA, Board.NUM_STAIRS) - 1);
            goalPile = board.getStair(view.leerSubOption(VIEW_SUBOPTION.A_ESCALERA, Board.NUM_STAIRS) - 1);
            onlyCard = false;
            break;

        case ESCALERA_PALO:
            origenPile = board.getStair(view.leerSubOption(VIEW_SUBOPTION.DE_ESCALERA, Board.NUM_STAIRS) - 1);
            goalPile = board.getSuit(view.leerSubOption(VIEW_SUBOPTION.A_PALO, TYPE_SUIT.NUM_CARDS_BY_SUIT) - 1);
            break;

        case PALO_ESCALERA:
            origenPile = board.getSuit(view.leerSubOption(VIEW_SUBOPTION.A_QUE_PALO, TYPE_SUIT.NUM_CARDS_BY_SUIT) - 1);
            goalPile = board.getStair(view.leerSubOption(VIEW_SUBOPTION.A_ESCALERA, Board.NUM_STAIRS) - 1);
            break;

        case VOLTEAR_ESCALERA:
            turnPile = board.getStair(view.leerSubOption(VIEW_SUBOPTION.DE_ESCALERA, Board.NUM_STAIRS) - 1);
            break;

        case SALIR:
            exit();
            break;

        default:
            return false;
        }

        if (origenPile != null && goalPile != null)
            if (onlyCard)
                return moveCardPileToPile(origenPile, goalPile);
            else
                return moveCardsBetweenStairs((StairPile) origenPile, (StairPile) goalPile);

        if (turnPile != null)
            return turnCard(turnPile);

        return false;

    }

    private boolean moveCardPileToPile(Pile montonOrigen, Pile montonDestino) {
        if (montonDestino == null || montonOrigen == null)
            return false;
        if (montonOrigen.canUnStack()) {
            Card c = montonOrigen.top();
            c.setFaceUp();
            if (montonDestino.canStack(c))
                return montonDestino.stack(montonOrigen.unstack());
        }
        return false;
    }

    private boolean moveCardsBetweenStairs(StairPile montonOrigen, StairPile montonDestino) {
        if (montonDestino == null || montonOrigen == null)
            return false;
        if (montonOrigen.isEmpty() || montonDestino.isEmpty())
            return false;

        if (montonOrigen.canUnStack()) {
            Stack<Card> stack = montonOrigen.tops();
            if (montonDestino.puedeApilar(stack)) {
                stack = montonOrigen.desapilarVisibles();
                if (montonDestino.apilar(stack))
                    return true;
                else if (!stack.isEmpty()) {
                    montonOrigen.apilar(stack);
                    return false;
                }
            }
        }
        return false;
    }

    private boolean turnCard(Pile montonVoltear) {
        if (montonVoltear == null)
            return false;
        if (!montonVoltear.isTopTurn())
            return false;
        return montonVoltear.topTurn();
    }

    private void exit() {
        view.finalizar();
        System.exit(0);
    }

    public Board getBoard() {
        return board;
    }

}

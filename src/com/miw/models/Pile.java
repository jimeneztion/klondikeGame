package com.miw.models;

import com.miew.util.Stack;

public class Pile {

    protected Stack<Card> cardsPile;

    public Pile(Stack<Card> colaCartas) throws NullPointerException {
        if (colaCartas == null)
            throw new NullPointerException("Cola no puede ser nula");
        this.cardsPile = colaCartas;
    }

    public Pile() {
        this.cardsPile = new Stack<Card>();
    }

    public boolean apilable() {
        return true;
    }

    public boolean desapilable() {
        return true;
    }

    public Card top() {
        if (!canUnStack())
            return null;
        return cardsPile.sample();
    }

    public Stack<Card> tops() {
        return cardsPile.sampleMultiUnstakables();
    }

    public boolean canStack(Card c) {
        if (c == null)
            throw new NullPointerException("Carta no puede ser nula");
        if (!apilable())
            return false;
        if (!c.isFaceUp())
            return false;
        return true;
    }

    public boolean canUnStack() {
        return (desapilable() && !isEmpty());
    }

    public Card unstack() {
        if (!canUnStack())
            return null;
        return cardsPile.unstack();
    }

    public boolean stack(Card c) {
        if (!canStack(c))
            return false;
        cardsPile.stack(c);
        return true;
    }

    public boolean isTopTurn() {
        if (isEmpty())
            return false;
        return !cardsPile.sample().isFaceUp();
    }

    public boolean topTurn() {
        if (!isTopTurn())
            return false;
        cardsPile.sample().setFaceUp();
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "<vacio>";

        Stack<Card> turn = tops();
        StringBuilder sb = new StringBuilder();

        if (turn.isEmpty() && !isEmpty())
            sb.append(cardsPile.sample().toString());
        for (Card volteada : turn)
            sb.append(volteada.toString());

        return sb.toString();
    }

    public boolean isEmpty() {
        return cardsPile.isEmpty();
    }

}

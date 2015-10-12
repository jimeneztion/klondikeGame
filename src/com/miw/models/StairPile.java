package com.miw.models;

import com.miew.util.Stack;

public class StairPile extends Pile {

    public StairPile(Stack<Card> st) throws NullPointerException {
        super(st);
    }

    public boolean canStack(Card c) {
        if (!super.canStack(c))
            return false;

        if (!cardsPile.sample().isFaceUp())
            return true;

        if (cardsPile.sample().getTypeSuit() == c.getTypeSuit())
            return false;

        int numCartaCima = 0;
        if (!cardsPile.isEmpty())
            numCartaCima = cardsPile.sample().getNumCard();

        return (c.getNumCard() != (numCartaCima));
    }

    public boolean puedeApilar(Stack<Card> pilaApilar) {
        if (pilaApilar == null)
            return false;
        return canStack(pilaApilar.sample());
    }

    public boolean apilar(Stack<Card> stack) {
        if (!puedeApilar(stack))
            return false;
        cardsPile.stackAll(stack);

        return true;
    }

    public String toString() {

        Stack<Card> turn = cardsPile.sampleMultiUnstakables();
        StringBuilder sb = new StringBuilder();
        int cardsNoTurn = cardsPile.size() - turn.size();
        for (int i = 0; i < cardsNoTurn; i++) {
            sb.append("[");
        }

        sb.append(super.toString());

        return sb.toString();
    }

    public Stack<Card> desapilarVisibles() {
        return cardsPile.unstackMultiUnstakables();
    }

}

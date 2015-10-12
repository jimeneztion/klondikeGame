package com.miw.models;

import com.miew.util.RandomStack;
import com.miew.util.Stack;

public class DeckPile extends Pile {

    public DeckPile(Stack<Card> st) throws NullPointerException {
        super(new RandomStack<Card>().stackAll(st));
    }

    public boolean canStack(Card c) {
        if (!super.canStack(c))
            return false;

        if (!cardsPile.sample().isFaceUp())
            return true;

        return false;
    }

}

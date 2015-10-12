package com.miw.models;

public class SuitPile extends Pile {

    private TYPE_SUIT suit;

    public SuitPile(TYPE_SUIT suit) {
        this.suit = suit;
    }

    public boolean desapilable() {
        return true;
    }

    public boolean canStack(Card c) {
        if (!super.canStack(c))
            return false;
        if (c.getTypeSuit() != this.suit)
            return false;

        int numCartaCima = 0;
        if (!cardsPile.isEmpty())
            numCartaCima = cardsPile.sample().getNumCard();

        return (c.getNumCard() == (numCartaCima + 1));
    }

    public boolean isFinished() {
        return (cardsPile.size() == TYPE_SUIT.NUM_CARDS_BY_SUIT);
    }

    public TYPE_SUIT getTipo() {
        return suit;
    }

}

package com.miw.models;

import com.miew.util.Stack;

public class Board {

    public static final int NUM_STAIRS = 7;

    private static final int NUM_SUIT = TYPE_SUIT.values().length;

    private DeckPile deck;

    private SuitPile[] suits;

    private Pile discards;

    private StairPile[] stairs;

    public Board() {
        super();
        this.suits = new SuitPile[NUM_SUIT];
        this.stairs = new StairPile[NUM_STAIRS];

    }

    public void init() {
        initDeck();
        initSuits();
        initDiscards();
        initStairs();
    }

    private void initDeck() {
        Stack<Card> candidate = new Stack<>();
        for (int num = 1; num <= TYPE_SUIT.NUM_CARDS_BY_SUIT; num++)
            for (TYPE_SUIT palo : TYPE_SUIT.values())
                candidate.stack(new Card(palo, num));
        candidate.sample();

        deck = new DeckPile(candidate);

        deck.topTurn();
    }

    private void initSuits() {
        int contPalo = 0;
        for (TYPE_SUIT tipo : TYPE_SUIT.values()) {
            suits[contPalo] = new SuitPile(tipo);
            contPalo++;
        }
    }

    private void initDiscards() {
        discards = new Pile();
    }

    private void initStairs() {
        for (int i = 0; i < NUM_STAIRS; i++) {
            Stack<Card> candidate = new Stack<>();
            for (int j = 0; j <= i; j++)
                candidate.stack(deck.unstack());
            StairPile mt = new StairPile(candidate);
            this.stairs[i] = mt;
            mt.topTurn();

        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Baraja: ").append(deck.toString()).append("\n");
        sb.append("Descarte: ").append(discards.toString()).append("\n");
        for (SuitPile palo : suits)
            sb.append("Palo ").append(palo.getTipo().name().toLowerCase()).append(": ").append(palo.toString()).append("\n");
        int cont = 1;
        for (StairPile temp : stairs) {
            sb.append("Escalera ").append(cont).append(": ").append(temp.toString()).append("\n");
            cont++;
        }
        return sb.toString();
    }

    public boolean finish() {
        return win() || game_over();
    }

    private boolean game_over() {
        return (deck.isEmpty() && !win());
    }

    private boolean win() {
        for (SuitPile palo : suits)
            if (!palo.isFinished())
                return false;
        return true;
    }

    public DeckPile getDeck() {
        return deck;
    }

    public Pile getDiscard() {
        return discards;
    }

    public StairPile getStair(int numEscalera) {
        if (numEscalera < 0 && numEscalera > NUM_STAIRS)
            return null;
        return stairs[numEscalera];
    }

    public Pile getSuit(int numPalo) {
        if (numPalo < 0 && numPalo > NUM_SUIT)
            return null;
        return suits[numPalo];
    }

}

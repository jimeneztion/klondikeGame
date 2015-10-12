package com.miw.models;

import com.miew.util.StackElement;

public class Card implements StackElement {

    private TYPE_SUIT tipoPalo;

    private int numCarta;

    private boolean faceUp;

    public Card(TYPE_SUIT tipoPalo, int num_carta) {
        super();
        this.tipoPalo = tipoPalo;
        this.numCarta = num_carta;
        this.faceUp = false;
    }

    public TYPE_SUIT getTypeSuit() {
        return tipoPalo;
    }

    public void setTypeSuit(TYPE_SUIT tipoPalo) {
        this.tipoPalo = tipoPalo;
    }

    public int getNumCard() {
        return numCarta;
    }

    public void setNumCard(int numCarta) {
        this.numCarta = numCarta;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp() {
        this.faceUp = true;
    }

    public boolean isMultiUnstakable() {
        return isFaceUp();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (isFaceUp()) {
            sb.append(getNumCard());
            sb.append(",");
            sb.append(getTypeSuit().name().charAt(0));
        } else {
            sb.append("X,X");
        }
        sb.append("]");

        return sb.toString();
    }

}

package com.miw.views;

public enum VIEW_SUBOPTION {

    A_QUE_PALO("De qué palo"), DE_ESCALERA("De qué escalera"), A_ESCALERA("A qué escalera"), A_PALO("A que palo");
    private String desc;

    VIEW_SUBOPTION(String desc) {

        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDesc());
        sb.append("? ");
        return sb.toString();

    }
}

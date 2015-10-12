package com.miw.views;

public enum VIEW_OPTION {

    BARAJA_DESCARTE(1, "Mover de baraja a descarte"), DESCARTE_BARAJA(2, "Mover de descarte a baraja"), DESCARTE_PALO(3,
            "Mover de descarte a palo"), DESCARTE_ESCALERA(4, "Mover de descarte a escalera"), ESCALERA_PALO(5,
                    "Mover de escalera a palo"), ESCALERA_ESCALERA(6, "Mover de escalera a escalera"), PALO_ESCALERA(7,
                            "Mover de palo a escalera"), VOLTEAR_ESCALERA(8, "Voltear en escalera"), SALIR(9, "Salir");

    private int optionCode;

    private String desc;

    VIEW_OPTION(int optionCode, String desc) {
        this.optionCode = optionCode;
        this.desc = desc;
    }

    public static VIEW_OPTION getOptionFromCode(int optionCode) {
        for (VIEW_OPTION option : values())
            if (option.getOptionCode() == optionCode)
                return option;
        return null;
    }

    public int getOptionCode() {
        return optionCode;
    }

    public String getDesc() {
        return desc;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOptionCode());
        sb.append(". ");
        sb.append(getDesc());
        return sb.toString();

    }

    public static String show() {
        StringBuilder sb = new StringBuilder();
        for (VIEW_OPTION option : values()) {
            sb.append(option.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

}

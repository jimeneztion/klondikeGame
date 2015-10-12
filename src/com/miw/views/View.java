package com.miw.views;

import com.miw.controllers.Controller;

public class View {

    private Controller controller;

    private IO io;

    public View() {
        io = new IO();
    }

    public void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================\n");
        sb.append(controller.getBoard().toString());
        sb.append("---------------------------\n");
        sb.append(VIEW_OPTION.show());
        io.write(sb.toString());

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public VIEW_OPTION leerOption() {

        int codeOption;
        VIEW_OPTION option = null;

        StringBuilder sb = new StringBuilder();
        sb.append("Opción: [1-");
        sb.append(VIEW_OPTION.values().length);
        sb.append("]:");

        String title = sb.toString();

        while (option == null) {
            codeOption = io.readInt(title);
            option = VIEW_OPTION.getOptionFromCode(codeOption);
            if (option == null)
                io.write("ERROR!!! La opción debe ser entre 1 y " + VIEW_OPTION.values().length + " inclusives.\n");
        }

        return option;

    }

    public int leerSubOption(VIEW_SUBOPTION suboption, int maximo) {

        String aux = suboption.toString();
        int codeOption = Integer.MIN_VALUE;
        while (codeOption <= 0 || codeOption > maximo) {
            codeOption = io.readInt(aux);
        }
        return codeOption;

    }

    public void finalizar() {
        io.write("Adios!!!");

    }

    public void lanzarError() {
        io.write("Error no se puede realizar ese movimiento!!!\n");

    }
}

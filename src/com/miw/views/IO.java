package com.miw.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IO {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public String readString(String title) {
        String input = null;
        boolean ok = false;
        do {
            this.write(title);
            try {
                input = bufferedReader.readLine();
                ok = true;
            } catch (Exception ex) {
                this.writeError("de cadena de caracteres");
            }
        } while (!ok);
        return input;
    }

    public int readInt(String title) {
        int input = Integer.MIN_VALUE;
        boolean ok = false;
        do {
            try {
                input = Integer.parseInt(this.readString(title));
                ok = true;
            } catch (Exception ex) {

            }
        } while (!ok);
        return input;
    }

    public boolean readBoolean(String title) {
        boolean booleanValue = true;
        boolean ok = false;
        do {
            String input = this.readString(title);
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                booleanValue = Boolean.valueOf(input).booleanValue();
                ok = true;
            } else {
                this.writeError("logico");
            }
        } while (!ok);
        return booleanValue;
    }

    public void write(String string) {
        System.out.print(string);
    }

    private void writeError(String formato) {
        System.err.println("ERROR DE FORMATO! " + "Introduzca un valor con formato " + formato + ".");
    }
}

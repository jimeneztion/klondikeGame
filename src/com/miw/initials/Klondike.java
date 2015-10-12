package com.miw.initials;

import com.miw.controllers.Controller;
import com.miw.models.Board;
import com.miw.views.View;

public class Klondike {

    private Board board;

    private Controller controller;

    private View view;

    public Klondike() {
        this.board = new Board();
        this.view = new View();
        this.controller = new Controller(this.board, this.view);
    }

    public void init() {
        controller.init();
    }

    public void play() {
        controller.play();
    }

    public static void main(String[] args) {
        Klondike solitario = new Klondike();
        solitario.init();
        solitario.play();
    }
}

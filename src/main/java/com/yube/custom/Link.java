package com.yube.custom;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;

public abstract class Link<T extends Token, E extends Enum<E>, SC extends SearchingContext<SC>> {

    protected T token;
    protected SC context;

    public Link(T token, SC context) {
        this.token = token;
        this.context = context;
    }

    public abstract TextFlow getUserRepresentation();

    public abstract EventHandler<MouseEvent> getMouseHandler();
}

package com.yube.custom;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface Link {
    String getUserRepresentation();
    EventHandler<MouseEvent> getMouseHandler();
}

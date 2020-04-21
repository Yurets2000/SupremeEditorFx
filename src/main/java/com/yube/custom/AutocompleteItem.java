package com.yube.custom;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AutocompleteItem extends Pane {

    public AutocompleteItem(String text) {
        getStyleClass().add("autocomplete-item");
        TextFlow flow = new TextFlow(new Text(text));
        getChildren().add(flow);
    }
}

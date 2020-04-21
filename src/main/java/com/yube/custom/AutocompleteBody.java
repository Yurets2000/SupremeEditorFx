package com.yube.custom;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class AutocompleteBody extends StackPane {

    public AutocompleteBody(List<AutocompleteItem> items) {
        getStyleClass().add("autocomplete-body");
        VBox box = new VBox();
        box.getChildren().addAll(items);
        getChildren().add(box);
    }
}

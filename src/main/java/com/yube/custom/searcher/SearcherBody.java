package com.yube.custom.searcher;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class SearcherBody extends StackPane {

    private VBox box = new VBox();

    public SearcherBody() {
        getStyleClass().add("searcher-body");
        getChildren().add(box);
    }

    public SearcherBody(List<BorderPane> items) {
        this();
        updateItems(items);
    }

    public void updateItems(List<BorderPane> items) {
        box.getChildren().clear();
        box.getChildren().addAll(items);
    }
}

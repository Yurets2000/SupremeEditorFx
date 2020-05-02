package com.yube.custom.searcher;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class SearcherBody extends StackPane {

    private VBox box = new VBox();

    public SearcherBody() {
        getStyleClass().add("searcher-body");
        getChildren().add(box);
    }

    public SearcherBody(List<SearcherItem> items) {
        this();
        box.getChildren().addAll(items);
    }

    public void updateItems(List<SearcherItem> items) {
        box.getChildren().clear();
        box.getChildren().addAll(items);
    }
}

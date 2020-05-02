package com.yube.custom;

import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;

public class SearcherItem extends Pane {

    public SearcherItem() {
        getStyleClass().add("searcher-item");
    }

    public SearcherItem(TextFlow textFlow) {
        this();
        getChildren().add(textFlow);
    }
}

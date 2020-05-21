package com.yube.custom.searcher;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;

public final class SearcherItem {

    private final TextFlow textFlow;
    private final SearcherItemBase base;

    public SearcherItem(TextFlow textFlow, SearcherItemBase base) {
        this.textFlow = textFlow;
        this.base = base;
    }

    public BorderPane constructPane() {
        BorderPane borderPane = base.constructPane();
        borderPane.setRight(textFlow);
        return borderPane;
    }
}
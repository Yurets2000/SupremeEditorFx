package com.yube.custom.searcher;

import javafx.scene.layout.VBox;
import lombok.Getter;

public class SearcherBox extends VBox {
    @Getter
    private SearcherHeader header;
    @Getter
    private SearcherBody body;

    public SearcherBox(SearcherHeader header, SearcherBody body) {
        getStyleClass().add("searcher-box");
        this.header = header;
        this.body = body;
        if (header != null) getChildren().add(header);
        if (body != null) getChildren().add(body);
    }
}

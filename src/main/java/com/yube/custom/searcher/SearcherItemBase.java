package com.yube.custom.searcher;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public final class SearcherItemBase {

    private final Image icon;
    private final Background background;

    public SearcherItemBase(Image icon, Background background) {
        this.icon = icon;
        this.background = background;
    }

    public BorderPane constructPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("searcher-item");
        borderPane.setLeft(new ImageView(icon));
        borderPane.setBackground(background);
        return borderPane;
    }
}

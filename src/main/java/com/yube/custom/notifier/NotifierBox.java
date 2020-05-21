package com.yube.custom.notifier;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class NotifierBox extends VBox {

    @Getter
    private final TabPane body;
    @Getter
    private final Button moreButton;
    @Getter
    private final Button lessButton;

    public NotifierBox() {
        getStyleClass().add("notifier-box");
        body = new TabPane();
        body.prefWidthProperty().bind(widthProperty());
        body.getStyleClass().add("notifier-box-body");
        moreButton = new Button("Get More!");
        moreButton.prefWidthProperty().bind(widthProperty());
        moreButton.getStyleClass().add("notifier-box-button");
        lessButton = new Button("Get Less!");
        lessButton.prefWidthProperty().bind(widthProperty());
        lessButton.getStyleClass().add("notifier-box-button");
        getChildren().add(body);
        getChildren().add(moreButton);
        getChildren().add(lessButton);
    }
}

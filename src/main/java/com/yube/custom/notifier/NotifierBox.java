package com.yube.custom.notifier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class NotifierBox extends VBox {
    @Getter
    private TabPane body;
    @Getter
    private Button button;
    @Getter
    private IntegerProperty notificationsCount = new SimpleIntegerProperty(5);

    public NotifierBox() {
        getStyleClass().add("notifier-box");
        body = new TabPane();
        body.prefWidthProperty().bind(widthProperty());
        body.getStyleClass().add("notifier-box-body");
        button = new Button("Get More!");
        button.prefWidthProperty().bind(widthProperty());
        button.getStyleClass().add("notifier-box-button");
        button.setOnAction(e -> notificationsCount.set(notificationsCount.get() + 5));
        getChildren().add(body);
        getChildren().add(button);
    }
}

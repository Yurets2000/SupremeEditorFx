package com.yube.custom;

import javafx.scene.layout.VBox;
import lombok.Getter;

public class Autocomplete extends VBox {
    @Getter
    private AutocompleteHeader header;
    @Getter
    private AutocompleteBody body;

    public Autocomplete(AutocompleteHeader header, AutocompleteBody body) {
        getStyleClass().add("autocomplete");
        this.header = header;
        this.body = body;
        if(header != null) getChildren().add(header);
        if(body != null) getChildren().add(body);
    }
}

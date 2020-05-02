package com.yube.custom.searcher;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class CodeSearcherHeader extends SearcherHeader {

    @Getter
    @Setter
    private String filter;
    @Getter
    @Setter
    private CodeSearcherTokenType codeSearcherTokenType = CodeSearcherTokenType.TEXT;
    @Getter
    private BooleanProperty changedProperty = new SimpleBooleanProperty(false);

    public CodeSearcherHeader() {
        super();
        getStyleClass().add("code-searcher-header");
        VBox vbox = new VBox();
        GridPane gridPane = new GridPane();
        RadioButton text = new RadioButton("Text");
        RadioButton word = new RadioButton("Word");
        RadioButton regex = new RadioButton("Regex");
        text.setSelected(true);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(Arrays.asList(text, word, regex));
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton selectedToggle = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
            this.setCodeSearcherTokenType(CodeSearcherTokenType.valueOf(selectedToggle.getText().toUpperCase()));
            this.getChangedProperty().setValue(true);
        });
        gridPane.add(text, 0, 0);
        gridPane.add(word, 1, 0);
        gridPane.add(regex, 2, 0);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(33.33);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(33.33);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(33.33);
        gridPane.getColumnConstraints().addAll(c1, c2, c3);
        vbox.getChildren().add(gridPane);
        TextField textField = new TextField();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.setFilter(newValue);
            this.getChangedProperty().setValue(true);
        });
        vbox.getChildren().add(textField);
        getChildren().add(vbox);
    }
}

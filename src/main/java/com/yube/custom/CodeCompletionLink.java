package com.yube.custom;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Arrays;

public class CodeCompletionLink extends Link<CodeToken, CodeCompletorTokenType, SupremeArea> {

    public CodeCompletionLink(CodeToken token, SupremeArea context) {
        super(token, context);
    }

    @Override
    public TextFlow getUserRepresentation() {
        String value = token.getValue();
        int start = value.indexOf(token.getSelected());
        int end = start + token.getSelected().length();
        Text textBefore = new Text(value.substring(0, start));
        Text textAfter = new Text(value.substring(end));
        Text textFilter = new Text(value.substring(start, end));
        Arrays.asList(textBefore, textFilter, textAfter).forEach(t -> t.getStyleClass().add("representation-text"));
        textFilter.getStyleClass().add("representation-filter");
        TextFlow representation = new TextFlow(textBefore, textFilter, textAfter);
        representation.getStyleClass().add("representation-flow");
        return representation;
    }

    @Override
    public EventHandler<MouseEvent> getMouseHandler() {
        return event -> {
            int end = context.getCaretPosition();
            context.selectWord();
            int start = end - context.getSelectedText().length();
            context.deleteText(start, end);
            context.insertText(start, token.getValue());
        };
    }
}

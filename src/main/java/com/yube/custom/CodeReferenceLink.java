package com.yube.custom;

import com.yube.utils.TextHelper;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Arrays;

public class CodeReferenceLink extends Link<CodeToken, CodeSearcherTokenType, SupremeArea> {

    public CodeReferenceLink(CodeToken token, SupremeArea context) {
        super(token, context);
    }

    @Override
    public TextFlow getUserRepresentation() {
        String line = TextHelper.cropToLength(token.getLine(), 50);
        int start = token.getColumn();
        int end = start + token.getSelected().length();
        Text textBefore = new Text(line.substring(0, start));
        Text textAfter = new Text(line.substring(end));
        Text textFilter = new Text(line.substring(start, end));
        Text info = new Text(String.format("%1$s:%2$s", token.getRow() + 1, token.getColumn() + 1));
        Arrays.asList(textBefore, textFilter, textAfter, info).forEach(t -> t.getStyleClass().add("representation-text"));
        textFilter.getStyleClass().add("representation-filter");
        TextFlow leftFlow = new TextFlow(textBefore, textFilter, textAfter);
        TextFlow rightFlow = new TextFlow(info);
        BorderPane pane = new BorderPane();
        pane.setLeft(leftFlow);
        pane.setRight(rightFlow);
        pane.getStyleClass().add("representation-pane");
        TextFlow representation = new TextFlow(pane);
        representation.getStyleClass().add("representation-flow");
        return representation;
    }

    @Override
    public EventHandler<MouseEvent> getMouseHandler() {
        return event -> {
            context.moveTo(token.getRow(), token.getColumn());
            context.requestFollowCaret();
            context.selectRange(token.getRow(), token.getColumn(),
                    token.getRow(), token.getColumn() + token.getSelected().length());
        };
    }
}

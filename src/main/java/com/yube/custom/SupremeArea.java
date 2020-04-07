package com.yube.custom;

import com.yube.configuration.models.styling.LexerStyle;
import com.yube.misc.Highlighter;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.EditableStyledDocument;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Collection;

public class SupremeArea extends StyleClassedTextArea {

    private Subscription highlightingSubscription;

    public SupremeArea() {
        super(false);
        initArea();
    }

    public SupremeArea(EditableStyledDocument<Collection<String>, String, Collection<String>> document) {
        super(document, false);
        initArea();
    }

    private void initArea() {
        getStyleClass().add("supreme-area");
        setParagraphGraphicFactory(LineNumberFactory.get(this));
        setUseInitialStyleForInsertion(true);
    }

    public void enableHighlighting(LexerStyle lexerStyle){
        if(highlightingSubscription != null){
            highlightingSubscription.unsubscribe();
        }
        highlightingSubscription = multiPlainChanges().successionEnds(Duration.ofMillis(500))
                .subscribe(ignore -> setStyleSpans(0, Highlighter.computeHighlighting(getText(), lexerStyle)));
    }

    public void disableHighlighting(){
        highlightingSubscription.unsubscribe();
    }

    @Override
    public void cut() {
    }

    @Override
    public void copy() {
    }

    @Override
    public void paste() {
    }

    @Override
    public void deletePreviousChar() {
    }

    @Override
    public void deleteNextChar() {
    }

    @Override
    public void clear() {
    }

    @Override
    public void redo() {
    }

    @Override
    public void undo() {
    }
}

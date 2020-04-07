package com.yube.custom;

import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.EditableStyledDocument;

import java.util.Collection;

public class SupremeArea extends StyleClassedTextArea {

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

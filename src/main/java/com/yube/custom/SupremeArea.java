package com.yube.custom;

import com.yube.configuration.models.styling.LexerStyle;
import com.yube.main.SupremeEditor;
import com.yube.misc.Highlighter;
import com.yube.misc.SupremeAreaCompletor;
import javafx.geometry.Bounds;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.EditableStyledDocument;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SupremeArea extends StyleClassedTextArea implements SearchingContext<SupremeArea> {

    private Subscription highlightingSubscription;
    private final Map<Class<? extends Enum<?>>, TokenExtractor<SupremeArea, ? extends Token, ? extends Enum<?>>> extractors = new HashMap<>();
    private Subscription autocompleteSubscription;

    public SupremeArea() {
        super(false);
        initArea();
        extractors.put(CodeCompletorTokenType.class, new SupremeAreaCodeCompletorTokenExtractor());
        extractors.put(CodeSearcherTokenType.class, new SupremeAreaCodeSearcherTokenExtractor());
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

    public void enableHighlighting(LexerStyle lexerStyle) {
        if (highlightingSubscription != null) {
            highlightingSubscription.unsubscribe();
        }
        highlightingSubscription = multiPlainChanges().successionEnds(Duration.ofMillis(500))
                .subscribe(ignore -> setStyleSpans(0, Highlighter.computeHighlighting(getText(), lexerStyle)));
    }

    public void enableAutocomplete() {
        if (autocompleteSubscription != null) {
            autocompleteSubscription.unsubscribe();
        }
        SupremeAreaCompletor supremeAreaCompletor = new SupremeAreaCompletor(this);
        Popup completionPopup = supremeAreaCompletor.createPopup();
        multiPlainChanges().successionEnds(Duration.ofMillis(1000))
                .subscribe(ignore -> {
                    selectWord();
                    String lastWord = getSelectedText();
                    deselect();
                    Optional<Bounds> optionalBounds = getCaretBounds();
                    if (lastWord.length() >= 3 && optionalBounds.isPresent()) {
                        Bounds bounds = optionalBounds.get();
                        Stage currentWindow = SupremeEditor.StageContainersRegistry.getInstance()
                                .getCurrentStageContainer().getStage();
                        completionPopup.show(currentWindow, bounds.getMinX() + 5, bounds.getMinY() + 5);
                    } else {
                        completionPopup.hide();
                    }
                });
    }

    public void disableHighlighting() {
        highlightingSubscription.unsubscribe();
        highlightingSubscription = null;
    }

    public void disableAutocomplete() {
        autocompleteSubscription.unsubscribe();
        autocompleteSubscription = null;
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

    @Override
    @SuppressWarnings("unchecked")
    public Map<Class<? extends Enum<?>>, TokenExtractor<SupremeArea, ? extends Token, ? extends Enum<?>>> getExtractors() {
        return extractors;
    }
}

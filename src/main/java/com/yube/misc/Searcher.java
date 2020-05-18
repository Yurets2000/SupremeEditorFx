package com.yube.misc;

import com.yube.custom.searcher.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Searcher<T extends Token, E extends Enum<E>, SC extends SearchingContext<SC>, L extends Link<T, E, SC>> {

    private final static int FILTER_THRESHOLD = 3;
    protected final SC context;
    protected final SearcherBox box;
    protected final BooleanProperty refreshProperty = new SimpleBooleanProperty();

    public Searcher(SC context) {
        this.context = context;
        this.box = createSearcherBox();
        refreshProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                refreshItems(getLinks(getTokenType(), getFilterValue()));
            }
        });
    }

    private SearcherBox createSearcherBox() {
        SearcherHeader header = createSearcherHeader();
        SearcherBody body = new SearcherBody();
        return new SearcherBox(header, body);
    }

    public Popup createPopup() {
        Popup popup = new Popup();
        popup.getContent().add(box);
        popup.setAutoHide(true);
        return popup;
    }

    protected abstract String getFilterValue();

    protected abstract E getTokenType();

    protected abstract L createLinkFromToken(T token);

    protected abstract SearcherHeader createSearcherHeader();

    @SuppressWarnings("unchecked")
    private List<L> getLinks(E tokenType, String filter) {
        if (tokenType == null || filter == null ||
                filter.length() < FILTER_THRESHOLD) return Collections.emptyList();
        TokenExtractor extractor = context.getExtractors().get(tokenType.getClass());
        List<T> tokens = extractor.extractTokens(context, tokenType, filter);
        return tokens.stream().map(this::createLinkFromToken).collect(Collectors.toList());
    }

    private void refreshItems(List<L> links) {
        List<BorderPane> panes = links.stream()
                .map(this::createPaneFromLink).collect(Collectors.toList());
        box.getBody().updateItems(panes);
        refreshProperty.setValue(false);
    }

    private BorderPane createPaneFromLink(L link) {
        SearcherItemBase base = SearcherItemBaseFactory.getSearcherItemBase(getTokenType());
        TextFlow textFlow = link.getUserRepresentation();
        SearcherItem item = new SearcherItem(textFlow, base);
        BorderPane pane = item.constructPane();
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, link.getMouseHandler());
        return pane;
    }
}

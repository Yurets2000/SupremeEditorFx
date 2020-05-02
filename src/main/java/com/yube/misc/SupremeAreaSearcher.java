package com.yube.misc;

import com.yube.custom.*;
import javafx.beans.property.BooleanProperty;

public class SupremeAreaSearcher extends Searcher<CodeToken, CodeSearcherTokenType, SupremeArea, CodeReferenceLink> {

    public SupremeAreaSearcher(SupremeArea context) {
        super(context);
    }

    @Override
    protected String getFilterValue() {
        CodeSearcherHeader header = (CodeSearcherHeader) box.getHeader();
        return header.getFilter();
    }

    @Override
    protected CodeSearcherTokenType getTokenType() {
        CodeSearcherHeader header = (CodeSearcherHeader) box.getHeader();
        return header.getCodeSearcherTokenType();
    }

    @Override
    protected CodeReferenceLink createLinkFromToken(CodeToken token) {
        return new CodeReferenceLink(token, context);
    }

    @Override
    protected SearcherHeader createSearcherHeader() {
        CodeSearcherHeader codeSearcherHeader = new CodeSearcherHeader();
        BooleanProperty changedProperty = codeSearcherHeader.getChangedProperty();
        changedProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                refreshProperty.setValue(true);
                changedProperty.setValue(false);
            }
        });
        return codeSearcherHeader;
    }
}

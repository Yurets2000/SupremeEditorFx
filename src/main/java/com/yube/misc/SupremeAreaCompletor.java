package com.yube.misc;

import com.yube.custom.*;
import com.yube.custom.searcher.CodeCompletionLink;
import com.yube.custom.searcher.CodeCompletorTokenType;
import com.yube.custom.searcher.CodeToken;
import com.yube.custom.searcher.SearcherHeader;

public class SupremeAreaCompletor extends Searcher<CodeToken, CodeCompletorTokenType, SupremeArea, CodeCompletionLink> {

    public SupremeAreaCompletor(SupremeArea context) {
        super(context);
        context.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshProperty.setValue(true);
        });
    }

    @Override
    protected String getFilterValue() {
        context.selectWord();
        String filter = context.getSelectedText().trim();
        context.deselect();
        return filter;
    }

    @Override
    protected CodeCompletorTokenType getTokenType() {
        return CodeCompletorTokenType.WORD_PARTIAL;
    }

    @Override
    protected CodeCompletionLink createLinkFromToken(CodeToken token) {
        return new CodeCompletionLink(token, context);
    }

    @Override
    protected SearcherHeader createSearcherHeader() {
        return null;
    }
}

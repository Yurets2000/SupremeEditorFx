package com.yube.custom;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupremeAreaCodeCompletorTokenExtractor implements TokenExtractor<SupremeArea, CodeToken, CodeCompletorTokenType> {

    @Override
    public List<CodeToken> extractTokens(SupremeArea context, CodeCompletorTokenType tokenType, String filter) {
        Set<CodeToken> codeTokens = new LinkedHashSet<>();
        if (filter != null && !filter.isEmpty()) {
            String text = context.getText();
            Pattern pattern = null;
            switch (tokenType) {
                case WORD_PARTIAL:
                    pattern = Pattern.compile("[a-zA-Z'0-9]+");
                    break;
            }
            if (pattern == null) return Collections.emptyList();
            String[] lines = text.split("\\r?\\n");
            for (int i = 0; i < lines.length; i++) {
                Matcher matcher = pattern.matcher(lines[i]);
                while (matcher.find()) {
                    int index = matcher.start();
                    String group = matcher.group();
                    switch (tokenType) {
                        case WORD_PARTIAL:
                            if (group.contains(filter)) {
                                codeTokens.add(new CodeToken(index, i, filter, group, lines[i]));
                            }
                            break;
                    }
                }
            }
        }
        return new ArrayList<>(codeTokens);
    }
}

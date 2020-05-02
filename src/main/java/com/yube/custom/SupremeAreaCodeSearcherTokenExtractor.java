package com.yube.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SupremeAreaCodeSearcherTokenExtractor implements TokenExtractor<SupremeArea, CodeToken, CodeSearcherTokenType> {

    @Override
    public List<CodeToken> extractTokens(SupremeArea context, CodeSearcherTokenType tokenType, String filter) {
        List<CodeToken> codeTokens = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            String text = context.getText();
            Pattern pattern = null;
            switch (tokenType) {
                case WORD:
                    pattern = Pattern.compile("[a-zA-Z'0-9]+");
                    break;
                case TEXT:
                    pattern = Pattern.compile("^.+$");
                    break;
                case REGEX:
                    try {
                        pattern = Pattern.compile(filter);
                    } catch (PatternSyntaxException ignored) {
                    }
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
                        case WORD:
                            if (group.equalsIgnoreCase(filter)) {
                                codeTokens.add(new CodeToken(index, i, filter, group, lines[i]));
                            }
                            break;
                        case TEXT:
                            Pattern pattern2 = Pattern.compile(Pattern.quote(filter));
                            Matcher matcher2 = pattern2.matcher(lines[i]);
                            while (matcher2.find()) {
                                int index2 = matcher2.start();
                                String group2 = matcher2.group();
                                codeTokens.add(new CodeToken(index2, i, filter, group2, lines[i]));
                            }
                            break;
                        case REGEX:
                            codeTokens.add(new CodeToken(index, i, group, group, lines[i]));
                            break;
                    }
                }
            }
        }
        return codeTokens;
    }
}

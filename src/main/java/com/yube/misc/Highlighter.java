package com.yube.misc;

import com.yube.configuration.models.styling.LexerStyle;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter {

    public static StyleSpans<Collection<String>> computeHighlighting(String text, LexerStyle lexerStyle) {
        String elementsString = lexerStyle.getElements();
        String[] elements = elementsString.split(",");
        Pattern pattern = Pattern.compile(lexerStyle.getPattern());
        Matcher matcher = pattern.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass = null;
            for (int i = 0; i < elements.length; i++) {
                if (matcher.group(elements[i]) != null) {
                    styleClass = lexerStyle.getName() + "-" + elements[i].toLowerCase();
                    break;
                }
            }
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}

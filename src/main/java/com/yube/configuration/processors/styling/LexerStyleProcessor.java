package com.yube.configuration.processors.styling;

import com.yube.configuration.models.styling.LexerStyle;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.styling.LexerStyleTransformer;
import org.dom4j.Document;
import org.dom4j.Element;

public class LexerStyleProcessor extends AbstractProcessor {

    public LexerStyleProcessor(Document document) {
        super(document);
    }

    public LexerStyle getLexerStyle(String name) {
        Element lexerStyleElement = (Element) document.selectSingleNode("//LexerStyle[@name='" + name + "']");
        if (lexerStyleElement == null) throw new IllegalArgumentException("Can't get such LexerStyle");
        return LexerStyleTransformer.createLexerStyle(lexerStyleElement);
    }
}

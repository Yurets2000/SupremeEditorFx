package com.yube.configuration.transformers.styling;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.styling.LexerStyle;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public final class LexerStyleTransformer {

    private LexerStyleTransformer() {
    }

    public static LexerStyle createLexerStyle(Element element) {
        if (!element.getName().equals("LexerStyle"))
            throw new TransformationException("Element name doesn't match 'LexerStyle'");
        try {
            return new LexerStyle(
                    element.attributeValue("name"),
                    element.attributeValue("desc"),
                    element.attributeValue("elements"),
                    element.attributeValue("pattern"));
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to LexerStyle object", ex);
        }
    }

    public static Element createElement(LexerStyle lexerStyle) {
        try {
            Element element = new DOMElement("LexerStyle");
            element.addAttribute("name", lexerStyle.getName());
            element.addAttribute("desc", lexerStyle.getDesc());
            element.addAttribute("elements", lexerStyle.getElements());
            element.addAttribute("pattern", lexerStyle.getPattern());
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed LexerStyle to Element object", ex);
        }
    }
}

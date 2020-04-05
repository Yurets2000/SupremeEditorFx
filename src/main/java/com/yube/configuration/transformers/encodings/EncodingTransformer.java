package com.yube.configuration.transformers.encodings;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.encodings.Encoding;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public final class EncodingTransformer {

    private EncodingTransformer() {
    }

    public static Encoding createEncoding(Element element){
        if (!element.getName().equals("Encoding"))
            throw new TransformationException("Element name doesn't match 'Encoding'");
        try {
            return new Encoding(
                    element.attributeValue("name"),
                    element.attributeValue("type")
            );
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to Encoding object", ex);
        }
    }

    public static Element createElement(Encoding encoding){
        try {
            Element element = new DOMElement("Encoding");
            element.addAttribute("name", encoding.getName());
            element.addAttribute("type", encoding.getType());
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Encoding to Element object", ex);
        }
    }
}

package com.yube.configuration.transformers.encodings;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.encodings.Mapping;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public final class MappingTransformer {

    private MappingTransformer() {
    }

    public static Mapping createMapping(Element element){
        if (!element.getName().equals("Mapping"))
            throw new TransformationException("Element name doesn't match 'Mapping'");
        try {
            return new Mapping(
                    element.attributeValue("type"),
                    element.attributeValue("path")
            );
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to Mapping object", ex);
        }
    }

    public static Element createElement(Mapping mapping){
        try {
            Element element = new DOMElement("Mapping");
            element.addAttribute("type", mapping.getType());
            element.addAttribute("path", mapping.getPath());
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Mapping to Element object", ex);
        }
    }
}

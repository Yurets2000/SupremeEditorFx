package com.yube.configuration.transformers.actions;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.actions.Definer;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public class DefinerTransformer {

    private DefinerTransformer() {
    }

    public static Definer createDefiner(Element element){
        if (!element.getName().equals("Definer"))
            throw new TransformationException("Element name doesn't match 'Definer'");
        try {
            return new Definer(
                    element.attributeValue("value")
            );
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to Definer object", ex);
        }
    }

    public static Element createElement(Definer definer){
        try {
            Element element = new DOMElement("Definer");
            element.addAttribute("value", definer.getValue());
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Definer to Element object", ex);
        }
    }
}

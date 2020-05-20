package com.yube.configuration.transformers.actions;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.actions.Implementor;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import java.util.ArrayList;
import java.util.List;

public final class ImplementorTransformer {

    private ImplementorTransformer() {
    }

    public static Implementor createImplementor(Element element){
        if (!element.getName().equals("Implementor"))
            throw new TransformationException("Element name doesn't match 'Implementor'");
        try {
            Implementor implementor =  new Implementor(
                    element.attributeValue("type")
            );
            List<String> qualifiers =  new ArrayList<>();
            List<String> exceptions =  new ArrayList<>();
            List qualifierElements = element.selectNodes("Qualifiers/Qualifier");
            List exceptionElements = element.selectNodes("Exceptions/Exception");
            for(Object o : qualifierElements){
                Element qualifierElement = (Element) o;
                qualifiers.add(qualifierElement.attributeValue("value"));
            }
            for(Object o : exceptionElements){
                Element exceptionElement = (Element) o;
                exceptions.add(exceptionElement.attributeValue("value"));
            }
            implementor.setQualifiers(qualifiers);
            implementor.setExceptions(exceptions);
            return implementor;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to Implementor object", ex);
        }
    }

    public static Element createElement(Implementor implementor){
        try {
            Element element = new DOMElement("Implementor");
            element.addAttribute("type", implementor.getType());
            Element qualifiersElement = new DOMElement("Qualifiers");
            Element exceptionsElement = new DOMElement("Exceptions");
            element.add(qualifiersElement);
            element.add(exceptionsElement);
            List<String> qualifiers = implementor.getQualifiers();
            List<String> exceptions = implementor.getExceptions();
            for (String qualifier : qualifiers){
                    Element qualifierElement = new DOMElement("Qualifier");
                    qualifierElement.addAttribute("value", qualifier);
                    qualifiersElement.add(qualifierElement);
            }
            for (String exception : exceptions){
                Element exceptionElement = new DOMElement("Exception");
                exceptionElement.addAttribute("value", exception);
                exceptionsElement.add(exceptionElement);
            }
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Implementor to Element object", ex);
        }
    }
}

package com.yube.configuration.transformers.actions;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.actions.Action;
import com.yube.configuration.models.actions.Definer;
import com.yube.configuration.models.actions.Implementor;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import java.util.ArrayList;
import java.util.List;

public class ActionTransformer {

    private ActionTransformer() {
    }

    public static Action createAction(Element element){
        if (!element.getName().equals("Action"))
            throw new TransformationException("Element name doesn't match 'Action'");
        try {
            Action action = new Action(
                    element.attributeValue("name")
            );
            action.setDesc(element.attributeValue("desc"));
            List<Definer> definers = new ArrayList<>();
            List<Implementor> implementors = new ArrayList<>();
            List definerElements = element.selectNodes("Definers/Definer");
            List implementorElements = element.selectNodes("Implementors/Implementor");
            for (Object o : definerElements){
                Element definerElement = (Element) o;
                definers.add(DefinerTransformer.createDefiner(definerElement));
            }
            for (Object o : implementorElements){
                Element implementorElement = (Element) o;
                implementors.add(ImplementorTransformer.createImplementor(implementorElement));
            }
            action.setDefiners(definers);
            action.setImplementors(implementors);
            return action;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to Action object", ex);
        }
    }

    public static Element createElement(Action action){
        try {
            Element element = new DOMElement("Action");
            element.addAttribute("name", action.getName());
            element.addAttribute("desc", action.getDesc());
            Element definersElement = new DOMElement("Definers");
            Element implementorsElement = new DOMElement("Implementors");
            element.add(definersElement);
            element.add(implementorsElement);
            for(Definer definer : action.getDefiners()){
                definersElement.add(DefinerTransformer.createElement(definer));
            }
            for(Implementor implementor : action.getImplementors()){
                implementorsElement.add(ImplementorTransformer.createElement(implementor));
            }
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Action to Element object", ex);
        }
    }
}

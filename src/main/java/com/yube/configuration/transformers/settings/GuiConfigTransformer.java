package com.yube.configuration.transformers.settings;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.settings.GuiConfig;
import com.yube.utils.XmlHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import java.util.HashMap;

public final class GuiConfigTransformer {

    private GuiConfigTransformer() {
    }

    public static GuiConfig createGuiConfig(Element element) {
        if (!element.getName().equals("GuiConfig"))
            throw new TransformationException("Element name doesn't match 'GuiConfig'");
        try {
            HashMap<String, String> attributes = XmlHelper.getMapFromAttributes(element.attributes());
            attributes.remove("name");
            return new GuiConfig(
                    element.attributeValue("name"),
                    attributes);
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to GuiConfig object", ex);
        }
    }

    public static Element createElement(GuiConfig guiConfig) {
        try {
            Element element = new DOMElement("GuiConfig");
            element.addAttribute("name", guiConfig.getName());
            guiConfig.getAdditionalAttributes().forEach(element::addAttribute);
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed GuiConfig to Element object", ex);
        }
    }
}

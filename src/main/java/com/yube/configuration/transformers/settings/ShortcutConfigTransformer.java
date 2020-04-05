package com.yube.configuration.transformers.settings;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.settings.ShortcutConfig;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public final class ShortcutConfigTransformer {

    private ShortcutConfigTransformer() {
    }

    public static ShortcutConfig createShortcutConfig(Element element) {
        if (!element.getName().equals("ShortcutConfig"))
            throw new TransformationException("Element name doesn't match 'ShortcutConfig'");
        try {
            return new ShortcutConfig(
                    element.attributeValue("action"),
                    element.attributeValue("alt"),
                    element.attributeValue("code"),
                    element.attributeValue("control"),
                    element.attributeValue("meta"),
                    element.attributeValue("shift"),
                    element.attributeValue("shortcut"));
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to ShortcutConfig object", ex);
        }
    }

    public static void updateElement(Element element, ShortcutConfig shortcutConfig) {
        if (!element.getName().equals("ShortcutConfig"))
            throw new TransformationException("Element name doesn't match 'ShortcutConfig'");
        element.addAttribute("alt", shortcutConfig.getAlt());
        element.addAttribute("code", shortcutConfig.getCode());
        element.addAttribute("control", shortcutConfig.getControl());
        element.addAttribute("meta", shortcutConfig.getMeta());
        element.addAttribute("shift", shortcutConfig.getShift());
        element.addAttribute("shortcut", shortcutConfig.getShortcut());
    }

    public static Element createElement(ShortcutConfig shortcutConfig) {
        try {
            Element element = new DOMElement("ShortcutConfig");
            element.addAttribute("holder", shortcutConfig.getAction());
            updateElement(element, shortcutConfig);
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed ShortcutConfig to Element object", ex);
        }
    }
}

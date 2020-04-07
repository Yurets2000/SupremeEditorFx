package com.yube.configuration.processors.settings;

import com.yube.configuration.models.settings.GuiConfig;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.settings.GuiConfigTransformer;
import org.dom4j.Document;
import org.dom4j.Element;

public class GuiConfigProcessor extends AbstractProcessor {

    public GuiConfigProcessor(Document document) {
        super(document);
    }

    public GuiConfig getGuiConfig(String name) {
        Element guiConfigElement = (Element) document.selectSingleNode("//GuiConfig[@name='" + name + "']");
        if (guiConfigElement == null) throw new IllegalArgumentException("Can't get such GUIConfig");
        return GuiConfigTransformer.createGuiConfig(guiConfigElement);

    }

    public void updateGuiConfig(GuiConfig guiConfig) {
        Element guiConfigElement = (Element) document.selectSingleNode("//GuiConfig[@name='" + guiConfig.getName() + "']");
        if (guiConfigElement == null) throw new IllegalArgumentException("Can't get such GUIConfig");
        guiConfig.getAdditionalAttributes().forEach(guiConfigElement::addAttribute);
    }
}

package com.yube.configuration.processors.settings;

import com.yube.configuration.models.settings.ShortcutConfig;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.settings.ShortcutConfigTransformer;
import org.dom4j.Document;
import org.dom4j.Element;

public class ShortcutConfigProcessor extends AbstractProcessor {

    public ShortcutConfigProcessor(Document document) {
        super(document);
    }

    public ShortcutConfig getShortcutConfig(String action) {
        Element shortcutConfigElement = (Element) document.selectSingleNode("//ShortcutConfig[@action='" + action + "']");
        if (shortcutConfigElement == null) return null;
        return ShortcutConfigTransformer.createShortcutConfig(shortcutConfigElement);
    }

    public void updateShortcutConfig(ShortcutConfig shortcutConfig) {
        Element shortcutConfigElement = (Element) document.selectSingleNode("//ShortcutConfig[@action='" + shortcutConfig.getAction() + "']");
        if (shortcutConfigElement == null) throw new IllegalArgumentException("Can't get such ShortcutConfig");
        ShortcutConfigTransformer.updateElement(shortcutConfigElement, shortcutConfig);
    }
}

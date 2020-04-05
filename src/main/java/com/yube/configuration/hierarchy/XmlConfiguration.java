package com.yube.configuration.hierarchy;

import lombok.Getter;
import org.dom4j.Document;

public abstract class XmlConfiguration {
    @Getter protected String path;
    @Getter protected Document document;
}

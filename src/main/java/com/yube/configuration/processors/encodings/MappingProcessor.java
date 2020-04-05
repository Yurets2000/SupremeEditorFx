package com.yube.configuration.processors.encodings;

import com.yube.configuration.models.encodings.Mapping;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.encodings.MappingTransformer;
import org.dom4j.Document;
import org.dom4j.Element;

public class MappingProcessor extends AbstractProcessor {

    public MappingProcessor(Document document) {
        super(document);
    }

    public Mapping getMapping(String encodingName) {
        Element mappingElement = (Element) document.selectSingleNode("//Encoding[@name='" + encodingName + "']/Mapping");
        if(mappingElement == null) throw new IllegalArgumentException("Can't find mapping for such encoding");
        return MappingTransformer.createMapping(mappingElement);
    }
}

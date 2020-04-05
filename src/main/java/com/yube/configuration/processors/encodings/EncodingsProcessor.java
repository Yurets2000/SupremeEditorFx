package com.yube.configuration.processors.encodings;

import com.yube.configuration.models.encodings.Encoding;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.encodings.EncodingTransformer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class EncodingsProcessor extends AbstractProcessor {

    public EncodingsProcessor(Document document) {
        super(document);
    }

    public List<Encoding> getSupportedEncodings() {
        List nodes = document.selectNodes("//Encoding/@name");
        List<Encoding> encodings = new ArrayList<>();
        for (Object o : nodes) {
            Attribute attribute = (Attribute) o;
            encodings.add(getEncoding(attribute.getValue()));
        }
        return encodings;
    }

    public Encoding getEncoding(String name) {
        Element encodingElement = (Element) document.selectSingleNode("//Encoding[@name='" + name + "']");
        if(encodingElement == null) throw new IllegalArgumentException("Can't find such encoding");
        return EncodingTransformer.createEncoding(encodingElement);
    }
}

package com.yube.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public final class XmlHelper {

    private static final SAXReader saxReader = new SAXReader();

    public static void write(Document document, String filePath) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter(filePath), format);
        writer.write(document);
        writer.close();
    }

    public static Document read(String filePath) throws DocumentException {
        return saxReader.read(filePath);
    }

    public static HashMap<String, String> getMapFromAttributes(List attributes){
        HashMap<String, String> map = new HashMap<>();
        for (Object o : attributes) {
            Attribute attribute = (Attribute) o;
            map.put(attribute.getName(), attribute.getValue());
        }
        return map;
    }
}

package com.yube.configuration.hierarchy;

import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.utils.PropertiesHelper;
import com.yube.utils.XmlHelper;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourcesXmlConfiguration extends XmlConfiguration {

    private static Map<String, ResourcesXmlConfiguration> instances = new HashMap<>();

    public static ResourcesXmlConfiguration getInstance(String identifier) throws ConfigurationInitializationException {
        if(instances.get(identifier) == null){
            instances.put(identifier, new ResourcesXmlConfiguration(identifier));
        }
        return instances.get(identifier);
    }

    private ResourcesXmlConfiguration(String identifier) throws ConfigurationInitializationException {
        PropertiesHelper propertiesHelper = new PropertiesHelper();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            String relPath = propertiesHelper.getProperties("locations.properties").getProperty(identifier);
            path = classLoader.getResource(relPath).toExternalForm();
            document = XmlHelper.read(path);
        } catch (IOException | DocumentException e) {
            throw new ConfigurationInitializationException(e);
        }
    }
}

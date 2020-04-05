package com.yube.configuration.hierarchy;

import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.utils.PropertiesHelper;
import com.yube.utils.XmlHelper;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExternalXmlConfiguration extends XmlConfiguration {

    private static Map<String, ExternalXmlConfiguration> instances = new HashMap<>();

    public static ExternalXmlConfiguration getInstance(String identifier) throws ConfigurationInitializationException {
        if(instances.get(identifier) == null){
            instances.put(identifier, new ExternalXmlConfiguration(identifier));
        }
        return instances.get(identifier);
    }

    private ExternalXmlConfiguration(String identifier) throws ConfigurationInitializationException {
        PropertiesHelper propertiesHelper = new PropertiesHelper();
        try {
            String pathSuffix = propertiesHelper.getProperties("locations.properties").getProperty(identifier);
            String pathPrefix = System.getProperty("user.home");
            path = pathPrefix + File.separator + pathSuffix;
            document = XmlHelper.read(path);
        } catch (IOException | DocumentException e) {
            throw new ConfigurationInitializationException(e);
        }
    }
}

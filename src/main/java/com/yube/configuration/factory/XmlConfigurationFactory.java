package com.yube.configuration.factory;

import com.yube.configuration.exceptions.ConfigurationInitializationException;
import com.yube.configuration.hierarchy.ResourcesXmlConfiguration;
import com.yube.configuration.hierarchy.XmlConfiguration;

public class XmlConfigurationFactory {

    public static XmlConfiguration getConfiguration(String configurationName) throws ConfigurationInitializationException {
        XmlConfiguration configuration;
        switch (configurationName) {
            case "encodings":
                configuration = ResourcesXmlConfiguration.getInstance("configuration.encodings");
                break;
            case "sessions":
                configuration = ResourcesXmlConfiguration.getInstance("configuration.mock.sessions");
                break;
            case "settings":
                configuration = ResourcesXmlConfiguration.getInstance("configuration.mock.settings");
                break;
            case "styling":
                configuration = ResourcesXmlConfiguration.getInstance("configuration.mock.styling");
                break;
            case "actions":
                configuration = ResourcesXmlConfiguration.getInstance("configuration.actions");
                break;
            default:
                throw new ConfigurationInitializationException("Configuration with such name not found");
        }
        return configuration;
    }
}

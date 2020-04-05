package com.yube.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static final String PROPERTIES_PREFIX = "properties";

    public Properties getProperties(String path) throws IOException {
        Properties prop;
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_PREFIX + File.separator + path)) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            throw ex;
        }
        return prop;
    }
}

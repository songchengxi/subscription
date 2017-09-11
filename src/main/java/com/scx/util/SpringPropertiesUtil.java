package com.scx.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class SpringPropertiesUtil {
    public static final String CONFIG_FOLDER = "config";
    public static final String FILE_EXTENSION = ".properties";

    public Properties loadProperties(String properties) throws IOException {
        Resource resource = new ClassPathResource("/" + CONFIG_FOLDER + "/" + properties + FILE_EXTENSION);
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return props;
    }

    public String getProperty(String properties, String key) throws IOException {
        return loadProperties(properties)
                .getProperty(key);
    }

    public String getProperty(String properties, String key, String defaultValue) throws IOException {
        return loadProperties(properties)
                .getProperty(key, defaultValue);
    }
}

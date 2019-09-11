package edu.aplana.Dns;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ProdProperties {
    private static ProdProperties INSTANCE = null;
    private Properties properties = new Properties();


    private ProdProperties() {
        try {
            String propertyFileName = System.getProperty("dnsShop","prod");
            properties.load(new FileInputStream(new File("src/main/resources/" + propertyFileName + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProdProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProdProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key) {
        return properties.get(key).toString();
    }
}

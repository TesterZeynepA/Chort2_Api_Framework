package com.Asana.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    public static final String EMAIL;
    public static final String PASSWORD;
    public static final String TOKEN_URL;
    public static final String URL;
    public static Properties properties;

    static {
        //  String environment = System.getProperty("environment") != null ? environment = System.getProperty("environment") : ConfigurationReader.get("environment");
        String environment = ConfigurationReader.get("environment");
        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/Environments/" + environment + ".properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL = properties.getProperty("url");
        EMAIL = properties.getProperty("email");
        PASSWORD = properties.getProperty("password");
        TOKEN_URL = properties.getProperty("tokenURL");
    }

}

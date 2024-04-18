package com.example.project.ui;

import com.example.project.utils.Utils;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    private static final Properties properties = new Properties();
    public void property(String namePropertyFile, String pathFieldProperty) {
        try {

        } catch (IOException e) {
            System.out.println();
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        synchronized (properties) {
            if (properties.size() == 0) {
                try (InputStream stream = BaseTest.class.getResourceAsStream("application.properties")) {
                    if (stream != null) {
                        System.out.println("Loading properties from .properties");
                        properties.load(stream);
                    }
                } catch (Exception e) {
                    System.out.println("properties from .properties NOT loaded");
                    e.printStackTrace();
                }
                try {
                    VaultClient vaultClient = new VaultClient(properties);
                    if (vaultClient.propertiesFromVaultCanBeReceived()) {
                        System.out.println("Loading properties from vault");
                        Properties vaultProps = vaultClient.getSecretProperties(ENV);
                        properties.putAll(vaultProps);
                    }
                } catch (Exception e) {
                    System.out.println("properties from vault NOT loaded");
                    e.printStackTrace();
                }
                try (InputStream stream = Paths.get(".env-" + ENV).toUri().toURL().openStream()) {
                    if (stream != null) {
                        System.out.println("Loading properties from .env-" + ENV);
                        properties.load(stream);
                    }
                } catch (Exception e) {
                    System.out.println("properties from .env- NOT loaded");
                    e.printStackTrace();
                }
            }
            return properties;
        }
    }
}

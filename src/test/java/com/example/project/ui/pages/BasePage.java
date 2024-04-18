package com.example.project.ui.pages;

import com.example.project.ui.BaseTest;
import com.example.project.utils.Utils;
import org.junit.jupiter.api.BeforeAll;

public class BasePage extends BaseTest {
    private final String baseURL = property("application.properties","properties.base.URL");




    public static Properties getProperties() {
        synchronized (properties) {
            if (properties.size() == 0) {
                try (InputStream stream = BaseTest.class.getResourceAsStream("/" + ENV + ".properties")) {
                    if (stream != null) {
                        System.out.println("Loading properties from " + ENV + ".properties");
                        properties.load(stream);
                    }
                } catch (Exception e) {
                    System.out.println("properties from " + ENV + ".properties NOT loaded");
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
                    System.out.println("properties from .env-" + ENV + " NOT loaded");
                    e.printStackTrace();
                }
            }
            return properties;
        }
    }
    @BeforeAll
    static void configuration() {

    }
}

package test.client.controllers;

import main.client.configuration.ConfigurationBuilder;
import main.client.configuration.XMLConfigurationBuilder;
import main.client.controllers.ClientController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by seanking on 15/01/2017.
 */
public class ClientControllerTest {
    @Test
    public void testClientControllerConfigurationReturnsTrueWhenNotConfigured(){
        ConfigurationBuilder configurationBuilder = new XMLConfigurationBuilder("src/test/client/assets/config.xml");
        ClientController client = new ClientController(configurationBuilder);
        assertTrue(client.configure());
    }

    @Test
    public void testClientControllerConfigurationReturnsFalseWhenAlreadyConfigured(){
        ConfigurationBuilder configurationBuilder = new XMLConfigurationBuilder("src/test/client/assets/config.xml");
        ClientController client = new ClientController(configurationBuilder);
        client.configure();
        assertTrue(client.configure() == false);
    }
}



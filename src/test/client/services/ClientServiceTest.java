package test.client.services;

import main.client.configuration.Configuration;
import main.client.configuration.ConfigurationBuildException;
import main.client.configuration.ConfigurationBuilder;
import main.client.configuration.XMLConfigurationBuilder;
import main.client.controllers.ClientController;
import main.client.services.ClientService;
import main.http.request.errorHandling.ConnectionRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by seanking on 19/01/2017.
 */
public class ClientServiceTest {
    @Test
    public void testClientServiceConnectMethodReturnsExceptionWhenConfigurationIsNull() {
        Configuration configuration = null;

        ClientService clientService = ClientService.getInstance();
        boolean isNull = false;

        try {
            clientService.connect(configuration);
        } catch (ConnectionRequestException e) {
            isNull = true;
        }

        assertTrue(isNull == true);
    }

    @Test
    public void testClientServiceConnectMethodExecutesWhenConfigurationIsNotNull() {
        ConfigurationBuilder configurationBuilder = new XMLConfigurationBuilder("src/test/client/assets/config.xml");
        Configuration configuration = null;

        try {
            configuration = configurationBuilder.build();
        } catch (ConfigurationBuildException e) {
            e.printStackTrace();
        }

        ClientService clientService = ClientService.getInstance();
        boolean isNull = false;

        try {
            clientService.connect(configuration);
        } catch (ConnectionRequestException e) {
            isNull = true;
        }

        assertTrue(isNull == false);
    }

    @Test
    public void ifClientServiceConnectMethodAlreadyCalledShouldThrowError(){
        ConfigurationBuilder configurationBuilder = new XMLConfigurationBuilder("src/test/client/assets/config.xml");
        Configuration configuration = null;

        try {
            configuration = configurationBuilder.build();
        } catch (ConfigurationBuildException e) {
            e.printStackTrace();
        }

        ClientService clientService = ClientService.getInstance();
        boolean isNull = false;

        try {
            clientService.connect(configuration);
        } catch (ConnectionRequestException e) {
        }

        try {
            clientService.connect(configuration);
        } catch (ConnectionRequestException e){
            isNull = true;
        }

        assertTrue(isNull == true);
    }

    @Test
    public void ifClientServiceHasNoServerConnectionConnectMethodShouldRunWithoutError(){
        ConfigurationBuilder configurationBuilder = new XMLConfigurationBuilder("src/test/client/assets/config.xml");
        Configuration configuration = null;

        try {
            configuration = configurationBuilder.build();
        } catch (ConfigurationBuildException e) {
            e.printStackTrace();
        }

        ClientService clientService = ClientService.getInstance();
        boolean isNull = false;

        try {
            clientService.connect(configuration);
        } catch (ConnectionRequestException e) {
            isNull = true;
        }

        assertTrue(isNull == false);
    }
}
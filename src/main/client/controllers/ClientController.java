package main.client.controllers;

import main.client.configuration.Configuration;
import main.client.configuration.ConfigurationBuildException;
import main.client.configuration.ConfigurationBuilder;
import main.client.services.ClientService;
import main.http.request.errorHandling.ConnectionRequestException;

import java.net.Socket;

/**
 * Created by seanking on 15/01/2017.
 */
public class ClientController {
    private ConfigurationBuilder configurationBuilder;
    private Configuration configuration = null;
    private ClientService clientService;

    private boolean configured = false;
    private Socket s;
    private String clientIp;

    //constructor
    public ClientController(ConfigurationBuilder configBuilder){
        this.configurationBuilder = configBuilder;
        this.clientService = clientService.getInstance();
    }

    //Delegates configuration to ConfigurationBuilder object
    public boolean configure(){

        if(configured){
            System.out.println("Configuration already created");
            return false;
        }

        try{
            configuration = configurationBuilder.build();
            configured = true;
            return true;
        } catch(ConfigurationBuildException e){}

        configured = false;
        return false;
    }

    //connects to a remote server
    public boolean connect(){
        try{
            clientService.connect(configuration);
        }catch(ConnectionRequestException e){
            System.out.println("Could not connect to the server");
            return false;
        }

        return true;
    }
}

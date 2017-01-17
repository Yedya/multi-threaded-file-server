package main.client.controllers;

import main.client.configuration.Configuration;
import main.client.configuration.ConfigurationBuildException;
import main.client.configuration.ConfigurationBuilder;
import main.client.services.ClientService;
import main.request.ConnectionRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by seanking on 15/01/2017.
 */
public class ClientController {
    private ConfigurationBuilder configurationBuilder;
    private Configuration configuration;
    private ClientService clientService;

    private boolean configured = false;
    private Socket s;
    private String clientIp;

    //constructor
    public ClientController(ConfigurationBuilder configBuilder){
        this.configurationBuilder = configBuilder;
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
        String host = configuration.getServerHost();
        int port = configuration.getServerPort();

        try {
            s = new Socket(host, port); //Connect to the server

            //Set clientIp
            this.clientIp = this.s.getLocalAddress().getHostAddress();

            //Serialise / marshal a request to the server
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            out.writeObject(new ConnectionRequest(clientIp, host, port)); //Serialise
            out.flush(); //Ensure all data sent by flushing buffers

            Thread.yield(); //Pause the current thread for a short time (not used much)

            //Handle response from server
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            //response response = (response)in.readObject();
            //logger.log(response);

            String response = (String) in.readObject(); //Deserialise

            //Display connection message to client
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

package main.client.controllers;

import main.client.configuration.Configuration;
import main.client.configuration.ConfigurationBuildException;
import main.client.configuration.ConfigurationBuilder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/**
 * Created by seanking on 15/01/2017.
 */
public class ClientController {
    private ConfigurationBuilder configurationBuilder;
    private Configuration configuration;
    private boolean configured = false;
    private Socket s;


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
        try {
            s = new Socket(configuration.getServerHost(), configuration.getServerPort()); //Connect to the server

            //Get clientIp

            //Serialise / marshal a request to the server
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            String request = "lol";

            out.writeObject(request); //Serialise
            out.flush(); //Ensure all data sent by flushing buffers

            Thread.yield(); //Pause the current thread for a short time (not used much)

            //Handle response from server
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            String response = (String) in.readObject(); //Deserialise

            //Display connection message to client
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

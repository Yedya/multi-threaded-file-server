package main.client.services;

import main.client.configuration.Configuration;
import main.request.ConnectionRequest;
import main.response.ConnectionResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by seanking on 17/01/2017.
 */
public class ClientService {
    private Configuration configuration;
    private String clientIp;

    public ClientService(Configuration configuration){
        this.configuration = configuration;
    }

    //connects to a remote server
    public ConnectionResponse connect(Configuration configuration){
        String host = configuration.getServerHost();
        int port = configuration.getServerPort();
        ConnectionResponse response = null;

        try {
            Socket s = new Socket(host, port); //Connect to the server

            //Set clientIp
            this.clientIp = s.getLocalAddress().getHostAddress();

            //Serialise / marshal a request to the server
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            out.writeObject(new ConnectionRequest(clientIp, host, port)); //Serialise
            out.flush(); //Ensure all data sent by flushing buffers

            Thread.yield(); //Pause the current thread for a short time (not used much)

            //Handle response from server
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            response = (ConnectionResponse)in.readObject();

            //response response = (response)in.readObject();
            //logger.log(response);

            //String response = (String) in.readObject(); //Deserialise
            //Display connection message to client
            //System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}

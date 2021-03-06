package main.client.services;

import main.client.configuration.Configuration;
import main.http.request.ConnectionRequest;
import main.http.request.TestRequest;
import main.http.request.errorHandling.ConnectionRequestException;
import main.http.response.ConnectionResponse;
import main.http.response.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by seanking on 17/01/2017.
 */
public class ClientService {

    private Configuration configuration;
    private String clientIp;
    private boolean connected = false;
    Socket socket;

    private static ClientService instance = null;

    private ClientService() {
        // Exists only to defeat instantiation.
    }

    //gets an instance of the ClientService
    public static ClientService getInstance() {
        if(instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    //connects to a remote server
    public void connect(Configuration configuration) throws ConnectionRequestException {
        if(connected)
            throw new ConnectionRequestException("Already connected");

        if(configuration == null)
            throw new ConnectionRequestException("Configuration object is null");
        else
            this.configuration = configuration;

        String host = configuration.getServerHost();
        int port = configuration.getServerPort();
        ConnectionResponse response = null;

        try {
            socket = new Socket(host, port); //Connect to the server

            //Set clientIp
            this.clientIp = socket.getLocalAddress().getHostAddress();

            //Serialise / marshal a request to the server
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(new ConnectionRequest(clientIp, host, port)); //Serialise
            out.flush(); //Ensure all data sent by flushing buffers

            Thread.yield(); //Pause the current thread for a short time (not used much)

            //Handle response from server
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //response = (ConnectionResponse)in.readObject();

            System.out.println(in.readObject() + "working");
            //response response = (response)in.readObject();
            //logging.log(response);

            //String response = (String) in.readObject(); //Deserialise
            //Display connection message to client
            //System.out.println(response);

            connected = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectionRequestException("Problem with stream");
        }
    }

    public void testRequest(){
        String host = configuration.getServerHost();
        int port = configuration.getServerPort();

        try {
            socket = new Socket(host, port); //Connect to the server

            //Set clientIp
            this.clientIp = socket.getLocalAddress().getHostAddress();

            //Serialise / marshal a request to the server
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(new TestRequest(clientIp, host, port)); //Serialise
            out.flush(); //Ensure all data sent by flushing buffers

            Thread.yield(); //Pause the current thread for a short time (not used much)

            //Handle response from server
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //response = (ConnectionResponse)in.readObject();

            System.out.println(in.readObject() + "working");
            //response response = (response)in.readObject();
            //logging.log(response);

            //String response = (String) in.readObject(); //Deserialise
            //Display connection message to client
            //System.out.println(response);

            connected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

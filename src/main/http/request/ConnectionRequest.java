package main.http.request;

import main.http.request.errorHandling.RequestHandlerException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionRequest extends Request implements Serializable {
    private static final long serialVersionUID = 1L;
    private static boolean connected = false;

    public ConnectionRequest(String clientIp, String serverHost, int serverPort) {
        super(clientIp, serverHost, serverPort);
    }

    public void run() {
        if(connected){
            //Create a new request handler to handle the incoming request
            RequestHandler requestHandler = new RequestHandler(super.getSocket());

            //handle the request
            try {
                Request request = requestHandler.deserialize(super.getSocket());
                requestHandler.start(request, "T- 1");
            } catch (RequestHandlerException e) {
            }
        }
        else{
            connected = true;
            System.out.println("Connection Established");

            String response = "Connection to server " + super.getServerHost() + " has been made.";
            ObjectOutputStream out = null;

            try {
                out = new ObjectOutputStream(super.getSocket().getOutputStream());
                out.writeObject(response);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
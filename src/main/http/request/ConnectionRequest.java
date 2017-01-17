package main.http.request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionRequest extends Request {

    public ConnectionRequest(String clientIp, String serverHost, int serverPort, Socket socket) {
        super(clientIp, serverHost, serverPort, socket);
    }

    public void run() {
        Socket socket = null;

        try { //Try the following. If anything goes wrong, the error will be passed to the catch block

            socket = super.getSocket();

            //Read in the request from the remote computer to this programme. This process is called Deserialization or Unmarshalling
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Object command = in.readObject(); //Deserialise the request into an Object
            System.out.println(command);

            //Write out a response back to the client. This process is called Serialization or Marshalling
            String message = "<h1>Happy Days</h1>";
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(message);
            out.flush();
            out.close(); //Tidy up after and don't wolf up resources unnecessarily

        } catch (Exception e) { //Something nasty happened. We should handle error gracefully, i.e. not like this...
            System.out.println("Error processing request from " + socket.getRemoteSocketAddress());
            e.printStackTrace();
        }
    }
}

package main.http.response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionResponse extends Response implements Serializable{
    private static final long serialVersionUID = 1L;

    public ConnectionResponse(){

    }

    public ConnectionResponse(String clientIp, String serverHost, int serverPort) {
        super(clientIp, serverHost, serverPort);
    }
}

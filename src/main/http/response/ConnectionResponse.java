package main.http.response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionResponse extends Response{

    ConnectionResponse(String clientIp, String serverHost, int serverPort) {
        super(clientIp, serverHost, serverPort);
    }
}

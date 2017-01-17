package main.response;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionResponse extends Response{

    ConnectionResponse(String clientIp, String serverHost, int serverPort) {
        super(clientIp, serverHost, serverPort);
    }
}

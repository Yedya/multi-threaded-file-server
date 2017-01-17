package main.request;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionRequest extends Request {

    public ConnectionRequest(String clientIp, String serverHost, int serverPort) {
        super(clientIp, serverHost, serverPort);
    }

}

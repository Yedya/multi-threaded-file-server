package main.http.request;

import java.io.Serializable;
import java.net.Socket;
import java.util.Date;

/**
 * Created by seanking on 17/01/2017.
 */
public abstract class Request implements Serializable, Runnable{
    private String clientIp;
    private String serverHost;
    private int serverPort;
    private Date requestDate;
    private Socket socket;

    Request(String clientIp, String serverHost, int serverPort, Socket socket){
        this.clientIp = clientIp;
        this.serverHost = serverHost;
        this. serverPort = serverPort;
        this.socket = socket;
    }

    String getClientIp() {
        return clientIp;
    }

    String getServerHost() {
        return serverHost;
    }

    int getServerPort() {
        return serverPort;
    }

    Date getRequestDate() {
        return requestDate;
    }

    Socket getSocket() {
        return socket;
    }
}

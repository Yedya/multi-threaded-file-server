package main.http.request;

import java.io.Serializable;
import java.net.Socket;
import java.util.Date;

/**
 * Created by seanking on 17/01/2017.
 */
public abstract class Request implements Serializable, Runnable{
    private static final long serialVersionUID = 1L;

    private String clientIp;
    private String serverHost;
    private int serverPort;
    private Date requestDate;
    private Socket socket;

    Request(String clientIp, String serverHost, int serverPort){
        this.clientIp = clientIp;
        this.serverHost = serverHost;
        this. serverPort = serverPort;
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

    public void setSocket(Socket s){this.socket = s;}
}

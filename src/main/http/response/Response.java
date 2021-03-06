package main.http.response;

import java.net.Socket;
import java.util.Date;

/**
 * Created by seanking on 17/01/2017.
 */
public abstract class Response {
    private static final long serialVersionUID = 1L;
    private String clientIp;
    private String serverHost;
    private int serverPort;
    private Date requestDate;
    private Date responseDate;
    private Socket socket;

    private String message;

    Response(){

    }

    Response(String clientIp, String serverHost, int serverPort){
        this.clientIp = clientIp;
        this.serverHost = serverHost;
        this. serverPort = serverPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

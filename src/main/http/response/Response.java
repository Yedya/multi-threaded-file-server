package main.http.response;

import java.util.Date;

/**
 * Created by seanking on 17/01/2017.
 */
abstract class Response {
    private String clientIp;
    private String serverHost;
    private int serverPort;
    private Date requestDate;

    Response(String clientIp, String serverHost, int serverPort){
        this.clientIp = clientIp;
        this.serverHost = serverHost;
        this. serverPort = serverPort;
    }
}

package main.request;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by seanking on 17/01/2017.
 */
abstract class Request implements Serializable {
    private String clientIp;
    private String serverHost;
    private int serverPort;
    private Date requestDate;

    Request(String clientIp, String serverHost, int serverPort){
        this.clientIp = clientIp;
        this.serverHost = serverHost;
        this. serverPort = serverPort;
    }
}

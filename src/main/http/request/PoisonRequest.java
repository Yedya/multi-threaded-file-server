package main.http.request;

/**
 * Created by seanking on 19/01/2017.
 */
public class PoisonRequest extends Request{

    PoisonRequest(String clientIp, String serverHost, int serverPort) {
        super(clientIp, serverHost, serverPort);
    }

    @Override
    public void run() {

    }
}

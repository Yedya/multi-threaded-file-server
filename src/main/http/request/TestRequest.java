package main.http.request;

import main.http.request.errorHandling.RequestHandlerException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by seanking on 19/01/2017.
 */
public class TestRequest extends Request implements Serializable {
        private static final long serialVersionUID = 1L;
        private static boolean connected = false;

        public TestRequest(String clientIp, String serverHost, int serverPort) {
            super(clientIp, serverHost, serverPort);
        }

        public void run() {
            System.out.println("Connection still working");

            String response = "Connection to server " + super.getServerHost() + " is still active.";
            ObjectOutputStream out = null;

            try {
                out = new ObjectOutputStream(super.getSocket().getOutputStream());
                out.writeObject(response);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


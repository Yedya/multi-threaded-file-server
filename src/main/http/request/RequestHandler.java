package main.http.request;

import main.http.request.errorHandling.RequestHandlerException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by seanking on 18/01/2017.
 */
public class RequestHandler{

    private Socket socket; //A specific socket connection between some computer on a network and this programme
    private Request request;

    public RequestHandler(Socket socket) { //Taking the client socket as a constructor enables the Listener class above to farm out the request quickly
        this.socket = socket; //Assign to the instance variable sock the value passed to the constructor.
    }

    public Request deserialize(Socket socket) throws RequestHandlerException {
        Request request;

        try{
            //This process is called Deserialization or Unmarshalling
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            //Deserialize the request into a Request Object
            request = (Request) in.readObject();
        } catch(Exception e){
            throw new RequestHandlerException("Error while trying to deserialize");
        }

        //cast request to appropriate request type
        if(request instanceof ConnectionRequest){
            return (ConnectionRequest)request;
        }

        return null;
    }


    public void start(Request request, String name){
        new Thread(request, name).start();
    }
}

package main.http.request.errorHandling;

/**
 * Created by seanking on 18/01/2017.
 */
public class RequestHandlerException extends Exception{
    String s1;

    public RequestHandlerException(String s2) {
        s1 = s2;
    }

    @Override
    public String toString() {return (s1);
    }
}

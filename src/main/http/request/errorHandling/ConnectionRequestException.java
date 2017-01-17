package main.http.request.errorHandling;

/**
 * Created by seanking on 17/01/2017.
 */
public class ConnectionRequestException extends Exception{
    String s1;

    public ConnectionRequestException(String s2) {
        s1 = s2;
    }

    @Override
    public String toString() {return (s1);
    }
}


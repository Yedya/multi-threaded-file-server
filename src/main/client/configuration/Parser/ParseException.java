package main.client.configuration.Parser;

public class ParseException extends Exception{
    String s1;

    ParseException(String s2) {
        s1 = s2;
    }

    @Override
    public String toString() {return (s1);
    }
}

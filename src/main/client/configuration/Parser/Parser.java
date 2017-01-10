package main.client.configuration.Parser;


import java.util.Map;

public interface Parser {
    Map<String, String> parseToMap() throws ParseException;
}

package main.client.configuration.Parser;


import main.client.configuration.Configuration;

public interface Parser {
    Configuration parse() throws ParseException;
}




package main.client.configuration;

import main.client.configuration.Parser.ParseException;
import main.client.configuration.Parser.Parser;
import main.client.configuration.Parser.XMLParser;

import java.util.HashMap;
import java.util.Map;

public class XMLConfiguration implements ConfigurationBuilder{
    private Parser parser;

    private Map<String, String> configMap = null;

    public XMLConfiguration(String absolutePathToFile){
        parser = new XMLParser(absolutePathToFile);
    }

    @Override
    public Map<String, String> build() throws ConfigurationBuildException {

        //Defensive copying
        //Instead of returning reference to object, return new object, which stops local member variable from changing
        if(configMap!=null)
            return new HashMap<>(configMap);

        try{
            configMap = parser.parseToMap();
        }catch(ParseException e){
            throw new ConfigurationBuildException("Build failed");
        }

        //defensive copying
        return new HashMap<>(configMap);
    }
}

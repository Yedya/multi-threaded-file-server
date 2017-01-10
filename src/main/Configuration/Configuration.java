package main.Configuration;

import main.Configuration.Parser.ParseException;
import main.Configuration.Parser.Parser;
import main.Configuration.Parser.XMLParser;

import java.util.Map;

public class Configuration implements ConfigurationBuilder{
    private Parser parser;

    public Configuration(String absolutePathToFile){
        parser = createParser(absolutePathToFile);
    }

    private Parser createParser(String path){
        Parser parser = new XMLParser(path);
        return parser;
    }

    @Override
    public Map<String, String> build() throws ConfigurationBuildException {
        Map<String, String> configMap;

        try{
            configMap = parser.parseToMap();
        }catch(ParseException e){
            throw new ConfigurationBuildException("Build failed");
        }

        return configMap;
    }
}

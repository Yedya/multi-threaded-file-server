package main.client.configuration;

import main.client.configuration.Parser.ParseException;
import main.client.configuration.Parser.Parser;
import main.client.configuration.Parser.XMLParser;

public class XMLConfigurationBuilder implements ConfigurationBuilder{
    private Parser parser;

    private Configuration configuration = null;

    public XMLConfigurationBuilder(String relativePathToFile){
        parser = new XMLParser(relativePathToFile);
    }

    @Override
    public Configuration build() throws ConfigurationBuildException {

        if(configuration!=null){
            throw new ConfigurationBuildException("Configuration already created");
        }
        try{
            configuration = parser.parse();
        }catch(ParseException e){
            throw new ConfigurationBuildException("Configuration build failed");
        }

        return configuration;
    }
}

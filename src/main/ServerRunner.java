package main;


import main.client.configuration.ConfigurationBuildException;
import main.client.configuration.ConfigurationBuilder;
import main.client.configuration.XMLConfiguration;

public class ServerRunner {
    public static void main(String[] args) {
        ConfigurationBuilder config = new XMLConfiguration("config.xml");

        try{
            System.out.println(config.build());
        }catch(ConfigurationBuildException e){}
    }
}

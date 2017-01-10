package main;


import main.Configuration.ConfigurationBuildException;
import main.Configuration.ConfigurationBuilder;
import main.Configuration.XMLConfiguration;

public class ServerRunner {
    public static void main(String[] args) {
        ConfigurationBuilder config = new XMLConfiguration("config.xml");

        try{
            System.out.println(config.build());
        }catch(ConfigurationBuildException e){}
    }
}

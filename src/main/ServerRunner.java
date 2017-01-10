package main;


import main.Configuration.ConfigurationBuildException;
import main.Configuration.ConfigurationBuilder;
import main.Configuration.Configuration;

public class ServerRunner {
    public static void main(String[] args) {
        ConfigurationBuilder config = new Configuration("config.xml");

        try{
            System.out.println(config.build());
        }catch(ConfigurationBuildException e){}
    }
}

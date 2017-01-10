package main.client.configuration;


public class ConfigurationBuildException extends Exception{
    String s1;

    ConfigurationBuildException(String s2) {
        s1 = s2;
    }

    @Override
    public String toString() {return (s1);
    }
}

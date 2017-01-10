package main.client.configuration;

import java.util.Map;

public interface ConfigurationBuilder {
    Map<String, String> build() throws ConfigurationBuildException;
}
